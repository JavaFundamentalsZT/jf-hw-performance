package org.zeroturnaround.jf.performance.bus.board;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.zeroturnaround.jf.performance.bus.Validator;
import org.zeroturnaround.jf.performance.bus.board.model.BaseBoard;
import org.zeroturnaround.jf.performance.bus.board.model.Board;
import org.zeroturnaround.jf.performance.util.MathUtil;

public class RandomBoardFactory<T extends Board> {

	private final int width;
	private final int height;
	private final Validator<BaseBoard> validator;

	public RandomBoardFactory(int width, int height,
			Validator<BaseBoard> validator) {
		this.width = width;
		this.height = height;
		this.validator = validator;
	}

	public RandomBoardFactory(int width, int height) {
		this(width, height, BoardValidator.INSTANCE);
	}

	public T create() {
		Board result;
		int size = width * height;

		// The numbers in the ascending order
		Integer[] numbers = new Integer[size];
		for (int i = 0; i < size; i++) {
			numbers[i] = i;
		}

		do {
			List<Integer> tmp = new LinkedList(Arrays.asList(numbers));

			// The numbers in a random order
			int[] data = new int[size];
			for (int i = 0; i < size; i++) {
				data[i] = tmp.remove(MathUtil.random(tmp.size()));
			}

			result = new Board(width, height, data);

			// Validate the board
		} while (!validator.validate(result));
		return (T) result;
	}
}
