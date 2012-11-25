package org.zeroturnaround.jf2012.perf.bus.board.model;

public class Square {

	public static Square valueOf(int x, int y) {
		return new Square(x, y);
	}

	private final int x;
	private final int y;

	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public String toString() {
		return new StringBuilder("(").append(x).append(", ").append(y)
				.append(")").toString();
	}
}
