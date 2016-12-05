package org.zeroturnaround.jf.performance.bus.board;

import org.zeroturnaround.jf.performance.bus.board.model.BaseBoard;
import org.zeroturnaround.jf.performance.bus.board.model.Board;
import org.zeroturnaround.jf.performance.bus.board.model.Square;

public class BoardWriter {

	public static String toString(BaseBoard board) {
		StringBuilder sb = new StringBuilder();
		int maxNumberLength = lengthOf(board.width() * board.height() - 1);
		String empty = repeat(" ", maxNumberLength);
		String line = repeat("-", (maxNumberLength + 1) * board.width() + 1);
		int index = 0;
		for (int y = 0; y < board.height(); y++) {
			sb.append(line).append("\n");
			for (int x = 0; x < board.width(); x++) {
				sb.append("|");
				int number = board.getValue(Square.valueOf(x, y));
				if (number == Board.EMPTY_SQUARE) {
					sb.append(empty);
				} else {
					sb.append(repeat(" ", maxNumberLength - lengthOf(number)));
					sb.append(number);
				}
				index++;
			}
			sb.append("|\n");
		}
		sb.append(line);
		sb.append("\n");
		return sb.toString();
	}
	
	private static int lengthOf(int number) {
		return Integer.valueOf(number).toString().length();
	}
	
	private static String repeat(String str, int times) {
		if (times == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder(str.length() * times);
		for (int i = 0; i < times; i++) {
			sb.append(str);
		}
		return sb.toString();
	}
	
}
