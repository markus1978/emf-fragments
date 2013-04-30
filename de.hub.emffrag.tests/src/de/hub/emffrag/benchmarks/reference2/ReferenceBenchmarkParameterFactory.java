package de.hub.emffrag.benchmarks.reference2;

import java.util.ArrayList;
import java.util.List;

import de.hub.emffrag.benchmarks.reference2.ReferenceBenchmark.Command;
import de.hub.emffrag.benchmarks.reference2.ReferenceBenchmark.Parameters;

public class ReferenceBenchmarkParameterFactory implements IParameterFactory<Parameters> {
	@Override
	public List<IParameters> createParameters() {
		List<IParameters> result = new ArrayList<IParameters>();
		long referenceNumbers[] = new long[] { 1, 10, 100, 1000, 10000, 100000, 1000000 };
		for (long references: referenceNumbers) {
			result.add(new Parameters(Command.importModel, references, 0));
			for (int i = 0; i < 23; i++) {
				result.add(new Parameters(Command.runTask, references, 1));
				if (references > 1) {
					result.add(new Parameters(Command.runTask, references, references/2));
				}
				result.add(new Parameters(Command.runTask, references, references));
			}
		}
		return result;
	}

	@Override
	public Parameters fromString(String parameterString) {
		String[] split = parameterString.split(",");
		return new Parameters(Command.valueOf(split[0]), Long.parseLong(split[1]), Long.parseLong(split[2]));
	}

	@Override
	public String toString(Parameters parameters) {
		return parameters.toString();
	}		
}