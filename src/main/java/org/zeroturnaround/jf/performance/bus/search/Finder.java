package org.zeroturnaround.jf.performance.bus.search;

import java.util.List;

import org.zeroturnaround.jf.performance.bus.ActionStoppedException;
import org.zeroturnaround.jf.performance.bus.StopDemander;

public interface Finder<T> {

	List<T> find(T start, T goal, StopDemander stop) throws ActionStoppedException;
	
}
