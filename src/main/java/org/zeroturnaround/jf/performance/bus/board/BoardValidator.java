package org.zeroturnaround.jf.performance.bus.board;

import org.zeroturnaround.jf.performance.bus.Validator;
import org.zeroturnaround.jf.performance.bus.board.model.BaseBoard;
import org.zeroturnaround.jf.performance.bus.board.model.Board;
import org.zeroturnaround.jf.performance.bus.board.model.Square;

public class BoardValidator<T extends BaseBoard> implements Validator<T> {

	public static final Validator<BaseBoard> INSTANCE = new BoardValidator<BaseBoard>();

	public boolean validate(T object) {
		Board board = new Board(object);

		// Move the space to the bottom right corner
		for (Square sq = board.squareOf(Board.EMPTY_SQUARE);;) {
			if (sq.x() < board.width() - 1) {
				sq = new Square(sq.x() + 1, sq.y());
			} else if (sq.y() < board.height() - 1) {
				sq = new Square(sq.x(), sq.y() + 1);
			} else {
				break;
			}
			board = board.slide(sq);
		}

		// Find the sequence of numbers on the non-empty squares
		int[] numbers = new int[board.width() * board.height()];
		for (int index = 0, y = 0; y < board.height(); y++) {
			for (int x = 0; x < board.width(); x++, index++) {
				numbers[index] = board.getValue(Square.valueOf(x, y));
			}
		}
		numbers[numbers.length - 1] = numbers.length;

		// Count inversions
		int invariants = countInversions(numbers);

		// The count of inversions must be even
		return invariants % 2 == 0;
	}

	private static int countInversions(int[] numbers) {
		int count = 0;
		for (int i = 0; i < numbers.length; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[i] > numbers[j]) {
					count++;
				}
			}
		}
		return count;
	}
}
