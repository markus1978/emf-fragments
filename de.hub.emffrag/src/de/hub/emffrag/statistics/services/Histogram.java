package de.hub.emffrag.statistics.services;

import java.util.ArrayList;
import java.util.List;

import com.flaptor.hist4j.AdaptiveHistogram;

import de.hub.emffrag.statistics.IStatisticalService;

public class Histogram implements IStatisticalService {
	
	private final AdaptiveHistogram histogram = new AdaptiveHistogram();
	private float min = Float.MAX_VALUE;
	private float max = Float.MIN_VALUE;
	
	private final int binCount = 23;

	@Override
	public void track(double doubleValue) {
		float value = (float)doubleValue;
		histogram.addValue(value);
		min = value < min ? value : min;
		max = value > max ? value : max;
	}
	
	@Override
	public void report(StringBuilder out) {
		out.append("Histogram:\n");
		
		histogram.normalize(min, max);
		float size = max - min;
		float binSize = size / binCount;
		List<Double> values = new ArrayList<Double>();
		long lastCount = 0;
		for (int i = 0; i < binCount; i++) {
			float start = i*binSize + min;
			float end = start + binSize;
		
			long accumCount = histogram.getAccumCount(end);
			long change = accumCount - lastCount;
			lastCount = accumCount;
			values.add((double)change);
		}			
		Helper.plotChart(out, values, min, binSize);
	}

	public static void main(String args[]) {
		Histogram histogram = new Histogram();
		for (int i = 0; i < 10; i++) {
			for (int ii = 0; ii < i; ii++) {
				histogram.track(i);
			}
		}
		StringBuilder out = new StringBuilder();
		histogram.report(out);
		System.out.println(out);
	}
}
