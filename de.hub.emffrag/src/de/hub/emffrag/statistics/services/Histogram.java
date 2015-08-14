package de.hub.emffrag.statistics.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.flaptor.hist4j.AdaptiveHistogram;
import com.flaptor.hist4j.Cell;

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
	
	private final List<Float> overlapHelperList = new ArrayList<Float>();
	
	private float overlap(float s1, float e1, float s2, float e2) {
		overlapHelperList.clear();
		overlapHelperList.add(s1);
		overlapHelperList.add(s2);
		overlapHelperList.add(e1);
		overlapHelperList.add(e2);
		Collections.sort(overlapHelperList);
		float snd = overlapHelperList.get(1);
		float trd = overlapHelperList.get(2);
		if ((snd == s1 || snd == s2) && (trd == e1 || trd == e2)) {
			return trd - snd;
		} else {
			return 0;
		}
	}

	@Override
	public void report(StringBuilder out) {
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
