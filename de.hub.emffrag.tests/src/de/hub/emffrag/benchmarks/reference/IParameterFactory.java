package de.hub.emffrag.benchmarks.reference;

import java.util.List;

public interface IParameterFactory<Parameters extends IParameters> {
	public List<IParameters> createParameters();
	public Parameters fromString(String parameterString);
	public String toString(Parameters parameters);
}
