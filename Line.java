package project2;

//Name: Duong Phan
//NetID: dphan7
//Lab section: Gavett 244 TR 2-3.15pm
public class Line {
	public Point start;
	public Point end;
	public String label;

	public Line(String label, Point start, Point end) {
		this.label = label;
		this.start = start;
		this.end = end;
	}

	// Show whether a point is on the left or right of a line
	public static int ccw(Point p0, Line line) {
		Point p1 = line.start;
		Point p2 = line.end;

		double dx1 = p1.x - p0.x;
		double dy1 = p1.y - p0.y;
		double dx2 = p2.x - p0.x;
		double dy2 = p2.y - p0.y;

		if (dx1 * dy2 > dy1 * dx2)
			return -1;
		else if (dx1 * dy2 < dy1 * dx2)
			return 1; // right
		else if ((dx1 * dx2 < 0) || (dy1 * dy2 < 0))
			return 1; // right
		else if ((dx1 * dx1 + dy1 * dy1) < (dx2 * dx2 + dy2 * dy2))
			return -1; // left
		else
			return 0;
	}

	// Given two intersected lines, return the point of intersection
	public Point getIntersection(Line l1, Line l2) {
		double a1, b1, a2, b2;
		a1 = (l1.end.y - l1.start.y) / (l1.end.x - l1.start.x);
		b1 = l1.end.y - a1 * l1.end.x;
		a2 = (l2.end.y - l2.start.y) / (l2.end.x - l2.start.x);
		b2 = l2.end.y - a2 * l2.end.x;

		double inter_x = (b2 - b1) / (a1 - a2);
		double inter_y = a1 * inter_x + b1;
		return new Point(inter_x, inter_y);

	}

	// Test whether two line intersect
	public int doIntersect(Line l1, Line l2) {
		// Find the orientation needed for general and special cases
		int o1 = ccw(l1.start, l2);
		int o2 = ccw(l1.end, l2);

		// Intersection case
		if (o1 != o2)
			return 2;
		// Colinear case
		else if ((o1 == o2 && o1 == 0))
			return 0;
		// To the left case
		else if (o1 == o2 && o1 == -1)
			return -1;
		// To the right case
		else if (o1 == o2 && o1 == 1)
			return 1;
		else {
			System.out.println("doIntersect error");
			return 3;
		}
	}

	public String toString() {
		return "Line " + label + ":\t " + start.x + "\t " + start.y + "\t " + end.x + "\t " + end.y;
	}

}
