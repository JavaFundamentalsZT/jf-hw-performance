package org.zeroturnaround.jf.performance;

import java.util.List;

import org.zeroturnaround.jf.performance.bus.NeverStopDemander;
import org.zeroturnaround.jf.performance.bus.board.BoardFinderFactory;
import org.zeroturnaround.jf.performance.bus.board.model.Board;
import org.zeroturnaround.jf.performance.bus.search.Finder;

public class Main {
  private static final int width = 5;
  private static final int height = 5;

  private static final int[] superSlow = new int[] { 16, 11, 8, 7, 6, 2, 24,
      5, 1, 23, 22, 14, 17, 3, 19, 9, 13, 0, 20, 18, 12, 10, 4, 21, 15 };

  private static final int[] data1 = new int[] { 11, 13, 17, 24, 6, 19, 14,
      5, 15, 12, 0, 16, 22, 4, 2, 9, 20, 23, 18, 8, 7, 21, 3, 10, 1 };

  private static final int[] data2 = new int[] { 5, 14, 12, 13, 10, 3, 2, 6,
      19, 18, 4, 24, 11, 22, 0, 16, 7, 15, 23, 9, 20, 21, 17, 8, 1 };

  private static final int[] superQuick = new int[] { 18, 22, 11, 24, 5, 4,
      20, 0, 17, 12, 10, 13, 16, 6, 19, 21, 8, 7, 9, 23, 1, 15, 14, 2, 3 };

  private static final int[] superQuickSmall = new int[] { 0, 13, 8, 15, 6,
      14, 12, 7, 1, 5, 10, 11, 9, 2, 4, 3 };

  private static final Board goal5x5 = new Board(width, height);
  private static final Board goal4x4 = new Board(4, 4);

  public static void main(String[] args) {
    // maybe you would add something here
    warmup();
    Board board = new Board(width, height, superSlow);
    Finder<Board> finder = BoardFinderFactory.getFinder(board);
    long[] timings = new long[10];
    long sum = 0;
    for (int i = 0; i < timings.length; i++) {

      long msNow = System.currentTimeMillis();
      List<Board> path = finder.find(board, goal5x5,
          NeverStopDemander.INSTANCE);
      long elapsed = System.currentTimeMillis() - msNow;
      sum += elapsed;
    }
    System.out.println(sum / 10d + "ms");
  }

  private static List<Board> warmup() {
    Board board = new Board(5, 5, superSlow);

    Finder<Board> finder = BoardFinderFactory.getFinder(board);
    List<Board> path = null;
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 100; j++) {
        path = finder.find(board, goal5x5, NeverStopDemander.INSTANCE);
      }
      System.out.print(".");
    }
    System.out.println();
    return path;
  }
}
