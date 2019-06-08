//import edu.princeton.cs.algs4.In;
//import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

  private final LineSegment[] lineSegments;

  public BruteCollinearPoints(Point[] points) {
    checkNull(points);
    Point[] sortedPoints = points.clone();
    Arrays.sort(sortedPoints);
    int arrayLength = sortedPoints.length;
    checkDupes(sortedPoints);

    List<LineSegment> list = new ArrayList<>();
    for (int i = 0; i < arrayLength - 3; i++) {
      Point p = sortedPoints[i];
      for (int j = i + 1; j < arrayLength - 2; j++) {
        Point q = sortedPoints[j];
        for (int k = j + 1; k < arrayLength - 1; k++) {
          Point r = sortedPoints[k];
          if (p.slopeTo(q) != q.slopeTo(r)){ continue; }
          for (int m = k + 1; m < arrayLength; m++) {
            Point s = sortedPoints[m];
            if (p.slopeTo(q) == q.slopeTo(r) && q.slopeTo(r) == r.slopeTo(s)) {
              list.add(new LineSegment(p, s));
            }
          }
        }
      }
    }
    lineSegments = list.toArray(new LineSegment[0]);
  }

  private void checkNull(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException("Array of points is null");
    }

    for (Point p : points) {
      if (p == null) {
        throw new IllegalArgumentException("Element in array is null");
      }
    }
  }

  private void checkDupes(Point[] points) {
    for (int i = 0; i < points.length - 1; i++) {
      if (points[i].compareTo(points[i + 1]) == 0) {
        throw new IllegalArgumentException("Dupes Found");
      }
    }
  }

  /**
   * The number of line segments.
   */
  public int numberOfSegments() {
    return lineSegments.length;
  }

  /**
   * returns array of line segments.
   */
  public LineSegment[] segments() {
    return lineSegments.clone();
  }

//  public static void main(String[] args) {
//
//    In in = new In(args[0]);
//    int n = in.readInt();
//    Point[] points = new Point[n];
//    for (int i = 0; i < n; i++) {
//      int x = in.readInt();
//      int y = in.readInt();
//      points[i] = new Point(x, y);
//    }
//
//    StdDraw.enableDoubleBuffering();
//    StdDraw.setXscale(0, 32768);
//    StdDraw.setYscale(0, 32768);
//    for (Point p : points) {
//      p.draw();
//    }
//    StdDraw.show();
//
//    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
//    for (LineSegment segment : collinear.segments()) {
//      StdOut.println(segment);
//      segment.draw();
//    }
//    StdDraw.show();
//  }
}