import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

  private final int x;     // x-coordinate of this point
  private final int y;     // y-coordinate of this point

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void draw() {
    StdDraw.point(x, y);
  }

  public void drawTo(Point that) {
    StdDraw.line(this.x, this.y, that.x, that.y);
  }

  public double slopeTo(Point that) {
    /* Positive inf means vertical line
     *  Negative inf means degenerate line
     *  Positive zero means horizontal line*/
    if (that.x == this.x) {
      if (that.y == this.y) {
        return Double.NEGATIVE_INFINITY;
      }
      return Double.POSITIVE_INFINITY;
    }

    if (that.y == this.y) {
      return 0.0;
    }

    return (this.y - that.y) * 1.0 / (this.x - that.y);
  }

  @Override
  public int compareTo(Point that) {
    /* -1 obj is before passed obj
     *  1 obj is after passed obj
     *  0 obj two objects are the same */
    if (this.y < that.y) {
      return -1;
    } else if (this.y > that.y) {
      return 1;
    } else {
      return Integer.compare(this.x, that.x);
    }
  }

  public Comparator<Point> slopeOrder() {
    return new SlopeComparator();
  }

  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  private class SlopeComparator implements Comparator<Point> {

    /* -1 obj is before passed obj
     *  1 obj is after passed obj
     *  0 obj two objects are the same */
    @Override
    public int compare(Point point1, Point point2) {
      double slope1 = slopeTo(point1);
      double slope2 = slopeTo(point2);

      return Double.compare(slope1, slope2);
    }
  }
//
//    public static void main(String[] args) {
//        /* YOUR CODE HERE */
//    }
}
