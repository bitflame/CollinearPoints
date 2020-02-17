import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

import static java.lang.Double.POSITIVE_INFINITY;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

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
        /*  Added the following two lines myself */
        double thisX = ((double) this.x);
        double thisY = ((double) this.y);
        double thatX = ((double) that.x);
        double thatY = ((double) that.y);
        StdDraw.line(thisX, thisY, thatX, thatY);
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
            slope = ((thatY - thisY) / (thatX - thisX));
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
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
        //Point p = new Point(this.x, this.y);
        Point p = new Point(this.x, this.y);
        Comparator<Point> com = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (p.slopeTo(o1) < p.slopeTo(o2)) return 1;
                else if (p.slopeTo(o1) > p.slopeTo(o2)) return -1;
                else return 0;
            }
        };
        return com;
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
        /*Point origin = new Point(0, 0);
        Point p1 = new Point(1000, 2000);
        Point p2 = new Point(2000, 1000);
        Point p3 = new Point(2000, 2000);
        Point p4 = new Point(1000, 2000);
        StdOut.println("Slope of P1 P2 P3 P4 wrt origin: ");
        StdOut.println(origin.slopeTo(p1) + " " + origin.slopeTo(p2) + " " + origin.slopeTo(p3) + " " + origin.slopeTo(p4));
        StdOut.println("Slope of origin P2 P3 P4 wrt p1: ");
        StdOut.println(p1.slopeTo(origin) + " " + p1.slopeTo(p2) + " " + p1.slopeTo(p3) + " " + p1.slopeTo(p4));
        StdOut.println("Expected: 1");
        StdOut.println(p1.compareTo(p2));
        StdOut.println("Expected: -1");
        StdOut.println(p1.compareTo(p3));
        StdOut.println("Expected: 0");
        StdOut.println(p1.compareTo(p4));*/
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
        //StdDraw.enableDoubleBuffering();
        StdDraw.setPenRadius(0.015);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        /*StdOut.println("Before Sorting:");
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
            //StdOut.print("The slope is: " + p.slopeTo(new Point(0, 0)));
        }
        StdOut.println();
        Arrays.sort(points, points[0].slopeOrder());
        StdOut.println("After Sorting By Slope wrt p[0]:");
        StdOut.println("====================================");
        StdOut.println();
        for (Point p : points) {
            StdOut.print(p.toString());
            //StdOut.print("The slope is: " + p.slopeTo(new Point(0, 0)));
            //StdOut.println();
            //StdOut.print("The slope is: " + p.slopeTo(new Point(0, 0)));
        }
        StdOut.println();
        Arrays.sort(points, points[1].slopeOrder());
        StdOut.println("After Sorting By Slope wrt p[1]:");
        StdOut.println("====================================");
        StdOut.println();
        for (Point p : points) {
            StdOut.print(p.toString());
            //StdOut.print("The slope is: " + p.slopeTo(new Point(0, 0)));
            //StdOut.println();
            //StdOut.print("The slope is: " + p.slopeTo(new Point(0, 0)));
        }
        StdOut.println();
        Arrays.sort(points, points[2].slopeOrder());
        StdOut.println("After Sorting By Slope wrt p[2]:");
        StdOut.println("====================================");
        StdOut.println();
        for (Point p : points) {
            StdOut.print(p.toString());
            //StdOut.print("The slope is: " + p.slopeTo(new Point(0, 0)));
            //StdOut.println();
            //StdOut.print("The slope is: " + p.slopeTo(new Point(0, 0)));
        }
        StdOut.println();
        Arrays.sort(points, points[3].slopeOrder());
        StdOut.println("After Sorting By Slope wrt p[3]:");
        StdOut.println("====================================");
        StdOut.println();
        for (Point p : points) {
            StdOut.print(p.toString());
            //StdOut.print("The slope is: " + p.slopeTo(new Point(0, 0)));
            //StdOut.println();
            //StdOut.print("The slope is: " + p.slopeTo(new Point(0, 0)));
        }
        StdOut.println();
        Arrays.sort(points, points[4].slopeOrder());
        StdOut.println("After Sorting By Slope wrt p[4]:");
        StdOut.println("====================================");
        StdOut.println();
        for (Point p : points) {
            StdOut.print(p.toString());
            //StdOut.print("The slope is: " + p.slopeTo(new Point(0, 0)));
            //StdOut.println();
            //StdOut.print("The slope is: " + p.slopeTo(new Point(0, 0)));
        }
        StdOut.println();
        Arrays.sort(points, points[5].slopeOrder());
        StdOut.println("After Sorting By Slope wrt p[5]:");
        StdOut.println("====================================");
        StdOut.println();
        for (Point p : points) {
            StdOut.print(p.toString());
            //StdOut.print("The slope is: " + p.slopeTo(new Point(0, 0)));
            //StdOut.println();
            //StdOut.print("The slope is: " + p.slopeTo(new Point(0, 0)));
        }
        StdOut.println();
        Arrays.sort(points, points[5].slopeOrder());
        StdOut.println("After Sorting By Slope wrt p[6]:");
        StdOut.println("====================================");
        StdOut.println();
        for (Point p : points) {
            StdOut.print(p.toString());
            //StdOut.print("The slope is: " + p.slopeTo(new Point(0, 0)));
            //StdOut.println();
            //StdOut.print("The slope is: " + p.slopeTo(new Point(0, 0)));
        }
        for (Point p : points) {
            for (Point q : points) {

                StdOut.println("Slope from " + p + "to " + q + ":" + p.slopeTo(q));
            }
        }*/
        // print and draw the line segments using Brute Force
        for (Point p : points) {
            p.draw();
        }
        BruteCollinearPoints bc = new BruteCollinearPoints(points);
        for (LineSegment segment : bc.segments()) {
            if (segment == null) break;
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        // print and draw the line segments
        /*FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        */
    }
}
