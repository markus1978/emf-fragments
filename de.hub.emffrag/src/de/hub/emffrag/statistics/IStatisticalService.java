package de.hub.emffrag.statistics;

public interface IStatisticalService {
	public void track(double value);
	public void report(StringBuilder out);
}
