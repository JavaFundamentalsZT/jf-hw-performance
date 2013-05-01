package org.zt.jf.perf.bus.search;


public interface Production<T> {

	T apply(T object) throws UnsatisfiedPreconditionException;
	
}
