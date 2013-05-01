package org.zt.jf.perf.bus;

public interface Validator<T> {

	boolean validate(T object);
	
}
