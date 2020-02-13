import java.util.Arrays;

public class BruteCollinearPoints {
    int pCount = 4;
    Point[] points = new Point[pCount];
    int segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("points array is empty.");
        Arrays.sort(points);//Sort the array by natural order initially to make sure line segments start at bottom
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == points[i++] || points[i] == null) {
                throw new IllegalArgumentException("No redundant point allowed in points.");
            }
        }
        this.points = points;


    }   // finds all line segments containing 4 points

    public int numberOfSegments() {
        return segments;
    }      // the number of line segments

    public LineSegment[] segments() {
        LineSegment[] lineSegments = new LineSegment[points.length - 1];
        //take a point from the array, check the slope it has with every other point, and if / when you find three
        //add them to the results array and look further
        //You need to deal with what is the natural order of the points i.e. need to know if a new point is higher or
        //lower than any existing ones in the line segment. This is basically a 4sum problem with target being the first common slope
        int end = points.length - 1;
        int right = end - 1;
        int segmentsIndex = 0;
        outerloop:
        for (int i = 0; i < points.length - 1; i++, right--) {
            double temp = points[i].slopeTo(points[end]);
            lineSegments[segmentsIndex] = new LineSegment(points[i], points[end]);
            segmentsIndex++;
            for (int j = i + 1; j < points.length - 3; j++) {
                if (points[j].slopeTo(points[right]) == temp) {
                    end--;
                    continue outerloop;
                }
            }
            if (lineSegments[segmentsIndex].equals(new LineSegment(points[i], points[end]))) {
                segmentsIndex--;
            }
        }
        segments = lineSegments.length;
        return lineSegments;
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
