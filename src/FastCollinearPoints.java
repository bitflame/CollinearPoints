import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    int pCount = 4;
    Point[] points = new Point[pCount];
    int segments;
    private LineSegment[] ls = new LineSegment[60];
    private int lsCount = 0;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("points [] should have content. It is empty.");
        this.points = points;
    }   // finds all line segments containing 4 or more points

    public int numberOfSegments() {
        int temp = 0;
        return temp;
    }      // the number of line segments

    public LineSegment[] segments() {
        // If points appear on the same segment, do not include them
        // If slopes of two segments are the same, and they have a common point(s) use the furthest
        // points to represent the segment
        LineSegment[] ls = new LineSegment[40];
        for (Point p : points) {
            Arrays.sort(points, p.slopeOrder());
            for (int i = 0; i < points.length - 1; i++) {
                while (p.slopeTo(points[i]) == p.slopeTo(points[i + 1])) {
                    //add point[i] to a line segment, and increment LineSegment size ()
                    ls[segments] = new LineSegment(points[i], points[i + 1]);
                    i++;
                }
                segments++;
            }
        }
        return ls;
    }

    // the line segments
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
