package de.hub.emffrag.statistics;

import java.util.ArrayList;
import java.util.List;

import de.hub.emffrag.statistics.Statistics.StatisticFactory;

public final class Statistic {
	
	public static class StatisticBuilder implements StatisticFactory {		
		private final List<IStatisticalService> services = new ArrayList<IStatisticalService>();
		
		private void add(IStatisticalService service) {
			services.add(service);
		}
		
		public StatisticBuilder withService(IStatisticalService service) {
			add(service);
			return this;
		}
		
		public Statistic register(Class<?> clazz, String name) {
			return Statistics.register(clazz, name, this);
		}
		
		public Statistic register(IWithStatistics source, String name) {
			return Statistics.register(source, name, this);
		}

		@Override
		public Statistic createStatistic() {
			Statistic statistic = new Statistic();
			for (IStatisticalService service : services) {
				statistic.addService(service);
			}
			return statistic;
		} 
	}
	
	private final List<IStatisticalService> services = new ArrayList<IStatisticalService>();
	
	private Statistic() {
	}
	
	private void addService(IStatisticalService service) {
		services.add(service);
	}
	
	public void track(double value) {
		for(IStatisticalService service: services) {
			service.track(value);
		}
	}
	
	public void report(StringBuilder out) {
		for(IStatisticalService service: services) {
			service.report(out);
		}
	}
}