package org.zeroturnaround.jf.performance.bus;

public interface Validator<T> {

	boolean validate(T object);
	
}
