//import edu.princeton.cs.algs4.In;
//import edu.princeton.cs.algs4.StdDraw;
//import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

  private final LineSegment[] lineSegments;


  public FastCollinearPoints(Point[] points) {

    List<LineSegment> maxLineSegments = new ArrayList<>();

    checkNull(points);
    Point[] sortedArray = points.clone();
    Arrays.sort(sortedArray);
    int arrayLength = sortedArray.length;
    checkDupes(sortedArray);

    for (Point p : sortedArray) {
      Point[] pointsSortedSlope = sortedArray.clone();
      Arrays.sort(pointsSortedSlope, p.slopeOrder());

      int x = 1;
      while (x < arrayLength) {
        List<Point> potenialPoints = new ArrayList<>();
        final double SLOPE_REF = p.slopeTo(pointsSortedSlope[x]);
        do {
          potenialPoints.add(pointsSortedSlope[x++]);
        } while (x < arrayLength && p.slopeTo(pointsSortedSlope[x]) == SLOPE_REF);
        if (potenialPoints.size() >= 3 && p.compareTo(potenialPoints.get(potenialPoints.size() - 1)) < 0) {
          Point last = potenialPoints.get(potenialPoints.size() - 1);
          maxLineSegments.add(new LineSegment(p, last));
        }
      }
    }
    lineSegments = maxLineSegments.toArray(new LineSegment[0]);
  }

  public int numberOfSegments() {
    return lineSegments.length;
  }

  public LineSegment[] segments() {
    return lineSegments.clone();
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

//  public static void main(String[] args) {
//
//    // read the n points from a file
//    In in = new In(args[0]);
//    int n = in.readInt();
//    Point[] points = new Point[n];
//    for (int i = 0; i < n; i++) {
//      int x = in.readInt();
//      int y = in.readInt();
//      points[i] = new Point(x, y);
//    }
//
//    // draw the points
//    StdDraw.enableDoubleBuffering();
//    StdDraw.setXscale(0, 32768);
//    StdDraw.setYscale(0, 32768);
//    for (Point p : points) {
//      p.draw();
//    }
//    StdDraw.show();
//
//    // print and draw the line segments
//    FastCollinearPoints collinear = new FastCollinearPoints(points);
//    for (LineSegment segment : collinear.segments()) {
//      StdOut.println(segment);
//      segment.draw();
//    }
//    StdDraw.show();
//  }
}
