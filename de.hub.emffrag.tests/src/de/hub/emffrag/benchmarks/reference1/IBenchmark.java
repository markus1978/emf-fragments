package de.hub.emffrag.benchmarks.reference1;

public interface IBenchmark<M> {
	
	public interface IBenchmarkContext {
		void onStart();
		void onStop();
	}

	public void populateModel(M model, IBenchmarkContext benchmarkContext);
	
	public boolean runTask(M model, IBenchmarkContext benchmarkContext);
}
