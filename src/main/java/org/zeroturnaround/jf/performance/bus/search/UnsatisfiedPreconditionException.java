package org.zeroturnaround.jf.performance.bus.search;

public class UnsatisfiedPreconditionException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public UnsatisfiedPreconditionException() {
	}
	
	public UnsatisfiedPreconditionException(String s) {
		super(s);
	}

}
