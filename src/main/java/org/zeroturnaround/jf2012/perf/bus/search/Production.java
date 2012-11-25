package org.zeroturnaround.jf2012.perf.bus.search;


public interface Production<T> {

	T apply(T object) throws UnsatisfiedPreconditionException;
	
}
