import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {
    int pCount = 4;
    Point[] points = new Point[pCount];
    int segments;
    private LineSegment[] ls = new LineSegment[60];
    private int lsCount = 0;


    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("points array is empty.");
        /*for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == points[i++] || points[i] == null) {
                throw new IllegalArgumentException("No redundant point allowed in points.");
            }
        }*/
        this.points = points;
    }   // finds all line segments containing 4 points

    public int numberOfSegments() {
        return segments;
    }      // the number of line segments

    public LineSegment[] segments() {
        LineSegment[] result = new LineSegment[60];
        Arrays.sort(points);//Sort the array by natural order initially to make sure line segments start at bottom
        ls = new LineSegment[100];
        for (Point p : points) {
            result = segments(p, ls);
        }
        return result;
    }

    // the line segments
    private LineSegment[] segments(Point origin, LineSegment[] ls) {
        //sort the array based thee slope of points to the origin - need to make sure it is working
        // you can also do: Array.sort(points, point[0].slopeOrder();
        Arrays.sort(points, origin.slopeOrder());
        //Find the points that have the same slope wrt origin and connect to it via 3 or more other points.
        int lineSegIns = 0;
        for (int i = 1; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (origin.compareTo(points[j]) < 0 && points[i] != points[j] && origin.slopeTo(points[i]) == points[i].slopeTo(points[j])) {
                    for (int k = 0; k < points.length; k++) {
                        if (origin.compareTo(points[k]) < 0 && points[i].compareTo(points[k]) < 0 && points[j].compareTo(points[k]) < 0 && points[j].slopeTo(points[k]) == origin.slopeTo(points[k])) {
                            ls[lineSegIns++] = new LineSegment(points[j], points[k]);
                            ls[lineSegIns++] = new LineSegment(points[i], points[j]);
                            ls[lineSegIns++] = new LineSegment(origin, points[i]);
                        }
                    }
                }
            }
        }
        return ls;
    }

    public static void main(String[] args) {
//        Point[] ps;
//        Point p = new Point(1, 2);
//        Point q = new Point(1, 3);
//        Point r = new Point(2, 2);
//        Point s = new Point(3, 2);
//        ps = new Point[]{p, q, r, s};
//        BruteCollinearPoints bc = new BruteCollinearPoints(ps);
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
