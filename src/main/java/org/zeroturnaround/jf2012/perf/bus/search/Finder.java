package org.zeroturnaround.jf2012.perf.bus.search;

import java.util.List;

import org.zeroturnaround.jf2012.perf.bus.ActionStoppedException;
import org.zeroturnaround.jf2012.perf.bus.StopDemander;

public interface Finder<T> {

	List<T> find(T start, T goal, StopDemander stop) throws ActionStoppedException;
	
}
