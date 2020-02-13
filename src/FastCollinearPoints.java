import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("points [] should have content. It is empty.");

    }   // finds all line segments containing 4 or more points

    public int numberOfSegments() {
        int temp = 0;
        return temp;
    }      // the number of line segments

    public LineSegment[] segments() {
        // If points appear on the same segment, do not include them
        // If slopes of two segments are the same, and they have a common point(s) use the furthest
        // points to represent the segment
        LineSegment[] ls = new LineSegment[3];
        return ls;
    }// the line segments

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
