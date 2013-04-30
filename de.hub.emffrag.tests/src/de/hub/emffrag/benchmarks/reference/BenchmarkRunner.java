package de.hub.emffrag.benchmarks.reference;

import java.util.List;

public class BenchmarkRunner {
	
	private final static class Gauge implements IGauge {
		private long time;

		@Override
		public void onStart() {
			time = System.nanoTime()/1000;
		}

		@Override
		public void onStop() {
			time = (System.nanoTime()/1000) - time;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final void main(String args[]) throws Exception {
		IBenchmarkFactory benchmarkFactory = null;
		IParameterFactory parameterFactory = null;
		
		if (args.length >= 3) {
			String benchmarkClass = args[1];
			String parameterClass = args[2];
			
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			benchmarkFactory = (IBenchmarkFactory)cl.loadClass(benchmarkClass).getConstructors()[0].newInstance();
			parameterFactory = (IParameterFactory)cl.loadClass(parameterClass).getConstructors()[0].newInstance();
		} else {
			System.err.println("Wrong args.");
			System.exit(1);
		}

		
		if (args.length == 4) {
			String parameterString = args[3];
			IParameters parameters = parameterFactory.fromString(parameterString);			
			IBenchmark benchmark = benchmarkFactory.createBenchmark();
			
			Gauge gauge = new Gauge();			
			benchmark.runTask(parameters, gauge);
			System.out.println(parameterString + "," + gauge.time);
		} else if (args.length == 3) {
			List<IParameters> createParameters = parameterFactory.createParameters();
			for (IParameters parameters: createParameters) {
				ProcessBuilder pb = new ProcessBuilder("java", "-jar", args[0], args[0], args[1], args[2], parameterFactory.toString(parameters));
				pb.redirectErrorStream(true);
				Process process = pb.start();
				org.apache.commons.io.IOUtils.copy(process.getInputStream(), System.out);
				process.waitFor();
			}
		} else {
			System.err.println("Wrong args.");
			System.exit(1);
		}
	}
}
