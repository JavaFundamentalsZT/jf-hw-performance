package org.zeroturnaround.jf.performance.bus.board;

import org.zeroturnaround.jf.performance.bus.board.model.Board;
import org.zeroturnaround.jf.performance.bus.search.Finder;
import org.zeroturnaround.jf.performance.bus.search.finder.AStarFinder;
import org.zeroturnaround.jf.performance.bus.search.finder.DepthFirstFinder;

public class BoardFinderFactory {

	private static final Finder<Board> DEPTH_FIRST = new DepthFirstFinder<Board>(
			BoardProduction.ALL);

	private static final Finder<Board> A_STAR = new AStarFinder<Board>(
			BoardProduction.ALL, BoardEstimator.INSTANCE);

	public static Finder<Board> getFinder(Board board) {
		if (Math.sqrt(board.width() * board.height()) <= 3) {
			return DEPTH_FIRST;
		} else {
			return A_STAR;
		}
	}

}
