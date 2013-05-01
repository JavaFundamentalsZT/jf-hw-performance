package org.zt.jf.perf.test;

import java.util.Arrays;
import java.util.List;

import org.zt.jf.perf.bus.NeverStopDemander;
import org.zt.jf.perf.bus.board.BoardFinderFactory;
import org.zt.jf.perf.bus.board.BoardValidator;
import org.zt.jf.perf.bus.board.RandomBoardFactory;
import org.zt.jf.perf.bus.board.model.Board;
import org.zt.jf.perf.bus.search.Finder;

public class Test {

	public static void main(String[] args) {
		int width = 5, height = 5;
		int minPathSize = Integer.MAX_VALUE;
		int[] minBoard = null;

		Board goal = new Board(width, height);

		for (int i = 0; i < 10; i++) {
			Board start = new RandomBoardFactory(width, height,
					BoardValidator.INSTANCE).create();
			//System.out.println(Arrays.toString(start.getData()));
			Finder<Board> finder = BoardFinderFactory.getFinder(start);
			List<Board> path = finder.find(start, goal,
					NeverStopDemander.INSTANCE);

			if (path == null) {
				System.out.println("No path found");
				continue;
			}

			//System.out.println("Path length: " + path.size());
			if (path.size()<minPathSize) {
				minPathSize = path.size();
				minBoard = start.getData();
			}
		}
		System.out.println("Minimum board is "+Arrays.toString(minBoard));
		System.out.println("Path length:"+minPathSize);
	}

}
