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
        LineSegment[] ls = new LineSegment[1];
        Arrays.sort(points, Point::compareTo);
        StdOut.println("After doing sort by order:  ");
        for (Point p : points) {
            StdOut.print("Point p:  " + p);
        }
        StdOut.println();
        Arrays.sort(points);
        StdOut.println("After doing default sort. They should be the same thing:  ");
        for (Point p : points) {
            StdOut.print("Point p:  " + p);
        }
        StdOut.println();
        Arrays.sort(points);
        Point origin = points[0];//This changes after the following sort
        for (int i = points.length; i > 0; i--) {

            Arrays.sort(points, origin.slopeOrder());
            StdOut.println("After sorting by slope wrt origin: ");
            for (Point p : points) {
                StdOut.println("Original point: " + origin + "Point p:  " + p + "Slope to p: " + origin.slopeTo(p));
            }
            int counter = i;
            Point[] currSeg = new Point[points.length];
            currSeg[0] = origin;
            int currSegCounter = 0;
            while (counter > 1) {
                if (origin.slopeTo(points[counter - 1]) == origin.slopeTo(points[counter - 2])
                        && origin.compareTo(points[counter - 1]) != 0) {
                    currSegCounter++;
                    currSeg[currSegCounter] = points[counter - 1];
                }
                counter--;//build an array of points here and then sort by natural order
            }
            if (counter <= (i - 2)) {
                currSegCounter++;
                currSeg[currSegCounter] = points[counter - 1];
                Point[] temp = new Point[currSegCounter + 1];
                for (int j = 0; j <= currSegCounter; j++) {
                    temp[j] = currSeg[j];
                }
                StdOut.println("Here are the points that had the same slope and created a segment.");
                for (Point p : temp) {
                    StdOut.print(p);
                }
                Arrays.sort(temp);
                StdOut.println("Here are the points that had the same slope and created a segment, after sorting by order.");
                for (Point p : temp) {
                    StdOut.print(p);
                }
                if (segCount >= (ls.length - 1)) {
                    ls = resizeLineSeg(ls, (segCount + 1) * 2);
                }
                ls[segCount] = new LineSegment(origin, temp[currSegCounter - 1]);
                segCount++;
                maxSegmentCount++;
            }
        }
        ls = resizeLineSeg(ls, maxSegmentCount);
        return ls;
    }

    private static LineSegment[] resizeLineSeg(LineSegment[] lineSegment, int capacity) {
        LineSegment[] temp = new LineSegment[capacity];
        int i = 0;
        for (LineSegment s : lineSegment) {
            if (s != null) {
                temp[i] = s;
                i++;
            }
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
