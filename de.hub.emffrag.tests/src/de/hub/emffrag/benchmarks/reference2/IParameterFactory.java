package de.hub.emffrag.benchmarks.reference2;

import java.util.List;

public interface IParameterFactory<Parameters extends IParameters> {
	public List<IParameters> createParameters();
	public Parameters fromString(String parameterString);
	public String toString(Parameters parameters);
}
