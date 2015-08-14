package de.hub.emffrag.statistics.services;

import java.text.DecimalFormat;

public class Helper {
	private static final DecimalFormat format = new DecimalFormat("0.0");
	
	public static void plotLine(StringBuilder out, double value, double maxValue) {
		out.append("[");
		int projected = (int)Math.round(23*value / maxValue);
		int i = 0;
		for (i = 0; i < projected; i++) {
			out.append("*");
		}
		for (;i<23;i++) {
			out.append(" ");
		}
		out.append("]");
	}
	
	public static void plotChart(StringBuilder out, Iterable<Double> source, double start, double binSize) {
		double max = Double.MIN_VALUE;
		int binCount = 0;
		for (Object binValueAsObject: source) {
			double binValue = (Double)binValueAsObject;
			max = binValue > max ? binValue : max;
			binCount++;
		}
		int index = 0;
		String formatedMaxValue = format.format(start+binSize*binCount);
		for (Object binValueAsObject: source) {
			double binValue = (Double)binValueAsObject;
			String formatedValue = format.format(start + ((index++)*binSize));
			out.append(formatedValue);
			for (int i = formatedValue.length(); i <= formatedMaxValue.length(); i++) {
				out.append(" ");
			} 
			Helper.plotLine(out, binValue, max);
			out.append(" " + format.format(binValue) + "\n");
		}
	}
}
