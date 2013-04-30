package de.hub.emffrag.benchmarks.reference2;

public interface IBenchmark<Parameters extends IParameters> {

	public void runTask(Parameters parameters, IGauge gauge);
}
