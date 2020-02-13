import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Double.POSITIVE_INFINITY;

public class Point implements Comparable<Point> {


    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    public static final Comparator<Point> BY_SLOPE = new byOrder();

    private static class byOrder implements Comparator<Point> {
        private final Point p = new Point(0, 0);

        @Override
        public int compare(Point o1, Point o2) {
            if (p.slopeTo(o1) > p.slopeTo(o2)) return 1;
            else if (p.slopeTo(o2) > p.slopeTo(o1)) return -1;
                //else if (p.slopeTo(o1) == p.slopeTo(o2)) return 0;
            else return 0;
        }
    }

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        assert ((0 < x) && (x < 32767) && (0 < y) && (y < 32767));
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        double slope;
        double thisX = this.x;
        double thisY = this.y;
        double thatX = that.x;
        double thatY = that.y;
        if (that.x == this.x && that.y == this.y) return Double.NEGATIVE_INFINITY;
        else if (that.y == this.y) return +0;
        else if (that.x == this.x) return POSITIVE_INFINITY;
        else {
            slope = (Math.abs(thatY - thisY) / Math.abs(thatX - thisX));
        }
        return slope;

    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     * point (x0 = x1 and y0 = y1);
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */
    public int compareTo(Point that) {
        if (this.y == that.y) {
            return Integer.compare(this.x, that.x);
        } else if (this.y > that.y) return 1;
        else return -1;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
        return new byOrder();
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
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
        StdDraw.setPenRadius(0.015);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdOut.println("Before Sorting:");
        StdOut.println("====================================");
        StdOut.println();
        for (Point p : points) {
            p.draw();
            StdOut.println(p.toString());
            StdOut.print("The slope is: " + p.slopeTo(new Point(0, 0)));
        }
        StdOut.println();
        StdOut.println("Here is the slope of each point wrt origin:");
        StdOut.println("====================================");
        StdOut.println();
        for (Point p : points) {
            StdOut.println();
            StdOut.print("For : " + p.toString());
            StdOut.print("The slope is: " + p.slopeTo(new Point(0, 0)));
        }
        StdOut.println();
        Arrays.sort(points);
        StdOut.println("After Sorting:");
        StdOut.println("====================================");
        StdOut.println();
        for (Point p : points) {
            StdOut.println(p.toString());
            StdOut.print("The slope is: " + p.slopeTo(new Point(0, 0)));
        }
        StdOut.println();
        Arrays.sort(points, BY_SLOPE);
        StdOut.println("After Sorting By Slope:");
        StdOut.println("====================================");
        StdOut.println();
        for (Point p : points) {
            StdOut.println(p.toString());
            //StdOut.print("The slope is: " + p.slopeTo(new Point(0, 0)));
        }
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            //StdOut.println(segment);
            //segment.draw();
        }
        StdDraw.show();
    }
}
