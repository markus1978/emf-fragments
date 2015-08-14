package de.hub.emffrag.statistics.services;

import java.text.DecimalFormat;

import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import de.hub.emffrag.statistics.IStatisticalService;

public class Summary implements IStatisticalService{
	private static final DecimalFormat f = new DecimalFormat("#.#");
	private final SummaryStatistics summary = new SummaryStatistics();

	@Override
	public void track(double value) {
		summary.addValue(value);
	}

	@Override
	public void report(StringBuilder out) {
		StatisticalSummary summary = this.summary.getSummary();
		out.append("Summary: ");
		out.append("mean=" + f.format(summary.getMean()) + ",min=" + f.format(summary.getMin()) + ",max=" + f.format(summary.getMax()));
		out.append(",n=" + f.format(summary.getN()) + ",sum=" + f.format(summary.getSum()) + ",sd=" + f.format(summary.getStandardDeviation()));
		out.append("\n");
	}

}
