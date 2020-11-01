package project2;

//Name: Duong Phan
//NetID: dphan7
//Lab section: Gavett 244 TR 2-3.15pm
public class Point {
	public double x;
	public double y;
	public String bitString = ""; // String indicates the path of point in the tree

	public Point(Double x, Double y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "Point  " + x + "\t" + y;
	}

}
