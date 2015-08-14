package de.hub.emffrag.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

import de.hub.emffrag.statistics.Statistics.StatisticFactory;

public final class Statistic {
	
	public static class StatisticBuilder implements StatisticFactory {		
		private final List<IStatisticalService> services = new ArrayList<IStatisticalService>();
		private long sumTime = -1;
		
		private void add(IStatisticalService service) {
			services.add(service);
		}
		
		public StatisticBuilder sumTime(long timeInMillies) {
			sumTime = timeInMillies;
			return this;
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
			statistic.sumTime = sumTime;
			for (IStatisticalService service : services) {
				statistic.addService(service);
			}
			return statistic;
		} 
	}
	
	private final List<IStatisticalService> services = new ArrayList<IStatisticalService>();
	private long sumTime = -1;
	private Stopwatch watch = null;
	private double sum = 0;
	
	private Statistic() {
	}
	
	private void addService(IStatisticalService service) {
		services.add(service);
	}
	
	public void track(double value) {
		if (sumTime < 0) {
			for(IStatisticalService service: services) {
				service.track(value);
			}
		} else {
			if (watch == null) {
				watch = Stopwatch.createStarted();
			}
			if (watch.elapsed(TimeUnit.MILLISECONDS) > sumTime) {
				for(IStatisticalService service: services) {
					service.track(sum);
				}	
				watch.reset().start();
				sum = 0;
			}
			sum += value;
		}
	}
	
	public void report(StringBuilder out) {
		for(IStatisticalService service: services) {
			service.report(out);
		}
	}
}