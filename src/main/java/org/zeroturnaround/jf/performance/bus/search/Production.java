package org.zeroturnaround.jf.performance.bus.search;

public interface Production<T> {
  T apply(T object) throws UnsatisfiedPreconditionException;
}
