public class BruteCollinearPoints {
    int pCount = 4;
    Point[] points = new Point[pCount];
    int segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("points array is empty.");
        this.points = points;

    }   // finds all line segments containing 4 points

    public int numberOfSegments() {
        return segments;
    }      // the number of line segments

    public LineSegment[] segments() {
        LineSegment[] lsAarray = new LineSegment[points.length - 1];
        //take a point from the array, check the slope it has with every other point, and if / when you find three
        //add them to the results array and look further

        segments = lsAarray.length;
        return lsAarray;
    }               // the line segments


    public static void main(String[] args) {
        Point[] ps;
        Point p = new Point(1, 2);
        Point q = new Point(1, 3);
        Point r = new Point(2, 2);
        Point s = new Point(3, 2);
        ps = new Point[]{p, q, r, s};
        BruteCollinearPoints bc = new BruteCollinearPoints(ps);
    }

}
