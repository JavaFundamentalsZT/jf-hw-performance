package org.zeroturnaround.jf2012.perf.bus.search;

public interface Estimator<T> {
	
	/**
	 * The estimated cost is used as a priority. The lower the better.
	 */
	int estimate(T current, T goal);

}
