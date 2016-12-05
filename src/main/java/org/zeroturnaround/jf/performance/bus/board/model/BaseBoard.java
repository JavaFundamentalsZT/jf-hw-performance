package org.zeroturnaround.jf.performance.bus.board.model;

import org.zeroturnaround.jf.performance.bus.board.BoardWriter;

/**
 * Board.
 */
public class BaseBoard implements Cloneable {

	protected final int width;
	protected final int height;
	protected final int[] data;

	public BaseBoard(int width, int height) {
		this.width = width;
		this.height = height;
		this.data = new int[width * height];
	}

	public BaseBoard(BaseBoard board) {
		this.width = board.width;
		this.height = board.height;
		this.data = board.data.clone();
	}

	public BaseBoard(int width, int height, int[] data) {
		if (width * height != data.length) {
			throw new IllegalArgumentException("Size does not match");
		}
		this.width = width;
		this.height = height;
		this.data = data;
	}

	public int width() {
		return width;
	}

	public int height() {
		return height;
	}

	public int size() {
		return width() * height();
	}

	public int getValue(Square sq) {
		return data[indexOf(sq)];
	}

	public void setValue(Square sq, int number) {
		data[indexOf(sq)] = number;
	}

	protected int indexOf(Square sq) {
		return sq.y() * width + sq.x();
	}

	public String toString() {
		return BoardWriter.toString(this);
	}
}
