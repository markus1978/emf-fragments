package de.hub.emffrag.benchmarks.reference;

public interface IBenchmark<Parameters extends IParameters> {

	public void runTask(Parameters parameters, IGauge gauge);
}
