import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    private int pCount = 4;
    Point[] points = new Point[pCount];
    private int maxSegmentCount = 0;
    private int segCount = 0;
    private LineSegment[] ls = new LineSegment[1];


    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("points [] should have content. It is empty.");
        this.points = points;
    }   // finds all line segments containing 4 or more points

    public int numberOfSegments() {
        return maxSegmentCount;
    }      // the number of line segments

    public LineSegment[] segments() {
        LineSegment[] ls = new LineSegment[10];
        int segCount = 0;
//        Arrays.sort(points, Point::compareTo);
//        StdOut.println("After doing sort by order:  ");
//        for (Point p : points) {
//            StdOut.print("Point p:  " + p);
//        }
//        StdOut.println();
//        Arrays.sort(points);
//        StdOut.println("After doing default sort. They should be the same thing:  ");
//        for (Point p : points) {
//            StdOut.print("Point p:  " + p);
//        }
//        StdOut.println();
        Point origin;

//        StdOut.println("After sorting using default sort: ");
//        for (Point p : points) {
//            StdOut.println("Point p:  " + p);
//        }
        for (int i = 0; i < points.length - 1; i++) {
            Arrays.sort(points);
            origin = points[i];
            Point[] collinierEndPoints = new Point[10];
            collinierEndPoints[0] = origin;
            //collinierEndPoints[1] = points[i + 1];

            Arrays.sort(points, origin.slopeOrder());
//            StdOut.println("After sorting by slope wrt origin: ");
//            for (Point p : points) {
//                StdOut.println("Original point: " + origin + "Point p:  " + p + "Slope to p: " + origin.slopeTo(p));
//            }


            int next = 1;
            //The slope sorted array ends with NEGATIVE_INFINITY slope
            for (int j = 0; j < points.length - 1; j++) {

                if (origin.slopeTo(points[j]) == origin.slopeTo(points[j + 1])) {
                    collinierEndPoints[next] = points[j];
                    next++;
                    collinierEndPoints[next] = points[j + 1];
                    next++;
                }
            }
            if (next >= 3) {
                if (collinierEndPoints[0] == origin && origin.compareTo(collinierEndPoints[1]) < 0) {
                    //Only create a segment if [0] is lower than [1]. This ensures you
                    //are starting from the right location. It is not possible for the collection to be a sub segment since
                    //the for loop above goes through every point each time.
                    //Arrays.sort(collinierEndPoints);
                    //Arrays.sort(collinierEndPoints, Point::compareTo);
                    collinierEndPoints = pointArrayResize(collinierEndPoints, next);
                    Arrays.sort(collinierEndPoints);

                    ls[maxSegmentCount] = new LineSegment(collinierEndPoints[0], collinierEndPoints[next - 1]);
                    if (maxSegmentCount == ls.length) ls = resizeLineSeg(ls, (2 * maxSegmentCount));
                    maxSegmentCount++;
                }
            }

//            for (Point p : collinierEndPoints) {
//                StdOut.println("Original point: " + origin + "Point p in Collinier Array:  " + p + "Slope to p: " + origin.slopeTo(p));
//            }
//
//            ls[maxSegmentCount] = new LineSegment(collinierArray[0], collinierArray[collinierArray.length - 1]);
//                StdOut.println("Here are the points that had the same slope and created a segment.");
//                for (Point p : temp) {
//                    StdOut.print(p);
//                }
//            Arrays.sort(temp);
//                StdOut.println("Here are the points that had the same slope and created a segment, after sorting by order.");
//                for (Point p : temp) {
//                    StdOut.print(p);
//                }
//            if (segCount >= (ls.length - 1)) {
//                ls = resizeLineSeg(ls, (segCount + 1) * 2);
//            }
//            ls[segCount] = new LineSegment(origin, temp[currSegLength - 1]);
//            segCount++;
//            maxSegmentCount++;
        }
        ls = resizeLineSeg(ls, (maxSegmentCount));
        return ls;
    }

    private static Point[] pointArrayResize(Point[] pointArray, int capacity) {
        Point[] temp = new Point[capacity];
        for (int i = 0; i < capacity; i++) {
            temp[i] = pointArray[i];
        }
        pointArray = temp;
        return pointArray;
    }

    private static LineSegment[] resizeLineSeg(LineSegment[] lineSegment, int capacity) {
        LineSegment[] temp = new LineSegment[capacity];
        for (int i = 0; i < capacity; i++) {
            temp[i] = lineSegment[i];
        }
        lineSegment = temp;
        return lineSegment;
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
        StdDraw.setPenRadius(0.015);
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
