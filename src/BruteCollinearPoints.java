import java.util.Arrays;

public class BruteCollinearPoints {
    int pCount = 4;
    Point[] points = new Point[pCount];
    int segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("points array is empty.");
        Arrays.sort(points);//Sort the array by natural order initially to make sure line segments start at bottom
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
        int left = 0;
        int right = points.length - 1;
        int lsCount = 0;
        LineSegment[] ls = new LineSegment[40];
        for (int i = 0; i < points.length - 4; i++) {
            LineSegment maxLineSeg;
            for (int j = i + 1; j < points.length - 3; j++) {
                double target = points[i].slopeTo(points[j]);
                left = j + 1;
                while (right > left) {
                    if (points[j].slopeTo(points[left]) < target) left++;
                    if (points[j].slopeTo(points[right]) > target) right--;
                    if (target == points[j].slopeTo(points[right])) {
                        ls[lsCount] = new LineSegment(points[i], points[right]);
                        right--;
                        lsCount++;
                    }
                    if (target == points[j].slopeTo(points[left])) {
                        ls[lsCount] = new LineSegment(points[i], points[left]);
                        lsCount++;
                        left++;
                    }
                    //right--;
                    //left++;
                }
            }
        }
        return ls;
    }
    // the line segments

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
