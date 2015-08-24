package de.hub.emffrag.statistics.services;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.buffer.CircularFifoBuffer;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import com.google.common.base.Stopwatch;

import de.hub.emffrag.statistics.IStatisticalService;

public class Plot implements IStatisticalService {
	
	private static final int binCount = 23;
	
	private final long binDuration;
	private final SummaryStatistics bin = new SummaryStatistics();
	private final Stopwatch watch = Stopwatch.createUnstarted();
	
	private final CircularFifoBuffer binValues = new CircularFifoBuffer(binCount);
	
	public Plot(long windowSizeInMillies) {
		binDuration = windowSizeInMillies / binCount;
	}

	@Override
	public void track(double value) {
		if (!watch.isRunning()) {
			watch.start();
		}
		if (watch.elapsed(TimeUnit.MILLISECONDS) > binDuration) {
			double binValue = bin.getMean();
			bin.clear();
			binValues.add(binValue);
			watch.reset().start();
		}
		
		bin.addValue(value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void report(StringBuilder out) {
		out.append("Plot:\n");
		Helper.plotChart(out, (Collection<Double>)binValues, 0, binDuration);
	}
}
