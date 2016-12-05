package org.zeroturnaround.jf.performance.bus.board.model;

import java.util.Arrays;

import org.zeroturnaround.jf.performance.bus.board.BoardWriter;
import org.zeroturnaround.jf.performance.util.MathUtil;

/**
 * Board.
 */
public class Board extends BaseBoard implements Cloneable {

	public static final int EMPTY_SQUARE = 0;

	private int[] indexes; // number -> address

	public Board(int size) {
		this(size, size);
	}

	public Board(Board board) {
		super(board);
		this.indexes = board.indexes.clone();
	}

	public Board(BaseBoard board) {
		super(board);
		initIndexes();
	}

	public Board(int width, int height) {
		super(width, height);
		fill();
		initIndexes();
	}

	public Board(int width, int height, int[] data) {
		super(width, height, data);
		initIndexes();
	}

	private void fill() {
		for (int i = 0; i < size(); i++) {
			this.data[i] = i == size() - 1 ? EMPTY_SQUARE : i + 1;
		}
	}

	private void initIndexes() {
		this.indexes = new int[size()];
		for (int y = 0, i = 0; y < height(); y++) {
			for (int x = 0; x < width(); x++, i++) {
				this.indexes[getValue(Square.valueOf(x, y))] = i;
			}
		}
	}

	public Board clone() {
		return new Board(this);
	}

	public void setValue(Square sq, int number) {
		throw new UnsupportedOperationException();
	}

	// Get indexes by numbers

	public Square squareOf(int value) {
		int index = indexes[value];
		return new Square(index % width(), index / width());
	}

	// Sliding (legal modifying)

	public Board slide(Square sq) {
		checkBounds(sq);
		Board b = clone();
		b.swap(b.squareOf(EMPTY_SQUARE), sq);
		return b;
	}

	// Reset

	public Board reset() {
		return new Board(width, height);
	}

	// Checking bounds

	private void checkBounds(Square sq) throws IndexOutOfBoundsException {
		if (!contains(sq)) {
			throw new IndexOutOfBoundsException(sq + " @ " + this);
		}
	}

	public boolean contains(Square sq) {
		return sq.x() >= 0 && sq.y() >= 0 && sq.x() < width()
				&& sq.y() < height();
	}

	// Swapping

	private void swap(Square sq1, Square sq2) {
		if (distance(sq1, sq2) != 1) {
			throw new IllegalArgumentException(
					"Only neighbour squares can be swapped");
		}

		// Swap numbers
		int tmpNumber = getValue(sq1);
		data[indexOf(sq1)] = data[indexOf(sq2)];
		data[indexOf(sq2)] = tmpNumber;

		// Update indexes
		indexes[data[indexOf(sq1)]] = indexOf(sq1);
		indexes[data[indexOf(sq2)]] = indexOf(sq2);
	}

	private static int distance(Square sq1, Square sq2) {
		return MathUtil.distance(sq1.x(), sq1.y(), sq2.x(), sq2.y());
	}

	// toString

	public String toString() {
		return BoardWriter.toString(this);
	}

	// Equals and hashCode

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Board)) {
			return false;
		}
		return Arrays.equals(this.data, ((Board) o).data);
	}

	public int hashCode() {
		return Arrays.hashCode(this.data);
	}

	public int[] getData() {
		return this.data.clone();
	}
}
