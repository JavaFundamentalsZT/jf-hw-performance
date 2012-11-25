package org.zeroturnaround.jf2012.perf.bus.search.finder;

import java.util.ArrayList;
import java.util.List;

import org.zeroturnaround.jf2012.perf.bus.search.Production;
import org.zeroturnaround.jf2012.perf.bus.search.UnsatisfiedPreconditionException;

class ProductionUtil {

	public static <T> List<T> getSuccessors(T object,
			Production<T>[] productions) {
		List<T> result = new ArrayList<T>(productions.length);
		for (Production<T> production : productions) {
			try {
				result.add(production.apply(object));
			} catch (UnsatisfiedPreconditionException e) {
				// ignore
			}
		}
		return result;
	}

}
