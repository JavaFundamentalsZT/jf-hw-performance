package org.zeroturnaround.jf.performance.bus.search.finder;

import java.util.ArrayList;
import java.util.List;

import org.zeroturnaround.jf.performance.bus.search.Production;
import org.zeroturnaround.jf.performance.bus.search.UnsatisfiedPreconditionException;

class ProductionUtil {

  public static <T> List<T> getSuccessors(T object, Production<T>[] productions) {
    List<T> result = new ArrayList<T>(productions.length);
    for (Production<T> production : productions) {
      try {
        result.add(production.apply(object));
      }
      catch (UnsatisfiedPreconditionException e) {
        // ignore
      }
    }
    return result;
  }

}
