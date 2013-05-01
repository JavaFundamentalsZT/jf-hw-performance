package org.zt.jf.perf.util;

public class MathUtil {

	/**
	 * Returns a Manhattan distance between two coordinates.
	 */
	public static int distance(int x1, int y1, int x2, int y2) {
		return Math.abs(x2 - x1) + Math.abs(y2 - y1);
	}

	/**
	 * Returns a random integer between 0 (included) and <code>max</code>
	 * (excluded).
	 */
	public static int random(int max) {
		return (int) (Math.random() * max);
	}

}
