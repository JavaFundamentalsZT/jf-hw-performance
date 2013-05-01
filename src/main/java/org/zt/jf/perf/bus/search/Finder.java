package org.zt.jf.perf.bus.search;

import java.util.List;

import org.zt.jf.perf.bus.ActionStoppedException;
import org.zt.jf.perf.bus.StopDemander;

public interface Finder<T> {

	List<T> find(T start, T goal, StopDemander stop) throws ActionStoppedException;
	
}
