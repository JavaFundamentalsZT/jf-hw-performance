package org.zt.jf.perf.bus.board;

import org.zt.jf.perf.bus.board.model.Board;
import org.zt.jf.perf.bus.board.model.Square;
import org.zt.jf.perf.bus.search.Estimator;
import org.zt.jf.perf.util.MathUtil;

public class BoardEstimator<T extends Board> implements Estimator<T> {

	public static final Estimator<Board> INSTANCE = new BoardEstimator<Board>();

	private BoardEstimator() {
	}

	public int estimate(T current, T goal) {
		int result = 0;
		int count = current.width() * current.height() - 1;
		for (int i = 1; i <= count; i++) {
			Square sq1 = current.squareOf(i);
			Square sq2 = goal.squareOf(i);
			result += MathUtil.distance(sq1.x(), sq1.y(), sq2.x(), sq2.y());
		}
		return result;
	}

}
