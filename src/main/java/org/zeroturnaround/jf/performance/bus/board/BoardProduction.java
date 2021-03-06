package org.zeroturnaround.jf.performance.bus.board;

import org.zeroturnaround.jf.performance.bus.board.model.Board;
import org.zeroturnaround.jf.performance.bus.board.model.Square;
import org.zeroturnaround.jf.performance.bus.search.Production;
import org.zeroturnaround.jf.performance.bus.search.UnsatisfiedPreconditionException;

public class BoardProduction<T extends Board> implements Production<T> {

  public static final Production<Board> LEFT = new BoardProduction<Board>(
      Direction.LEFT);
  public static final Production<Board> RIGHT = new BoardProduction<Board>(
      Direction.RIGHT);
  public static final Production<Board> UP = new BoardProduction<Board>(Direction.UP);
  public static final Production<Board> DOWN = new BoardProduction<Board>(
      Direction.DOWN);

  public static final Production[] ALL = new Production[] { LEFT,
      RIGHT, UP, DOWN };

  private static enum Direction {

    LEFT {
      Square transform(Square sq) {
        return new Square(sq.x() - 1, sq.y());
      }
    },
    RIGHT {
      Square transform(Square sq) {
        return new Square(sq.x() + 1, sq.y());
      }
    },
    UP {
      Square transform(Square sq) {
        return new Square(sq.x(), sq.y() - 1);
      }
    },
    DOWN {
      Square transform(Square sq) {
        return new Square(sq.x(), sq.y() + 1);
      }
    };

    abstract Square transform(Square sq);

  }

  private Direction direction;

  private BoardProduction(Direction direction) {
    this.direction = direction;
  }

  public T apply(T board) throws UnsatisfiedPreconditionException {
    Square slide = direction.transform(board.squareOf(Board.EMPTY_SQUARE));
    if (!board.contains(slide)) {
      throw new UnsatisfiedPreconditionException("Cannot make a slide");
    }
    return (T) board.slide(slide);
  }
}
