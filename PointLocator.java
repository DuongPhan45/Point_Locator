package project2;

//Name: Duong Phan
//NetID: dphan7
//Lab section: Gavett 244 TR 2-3.15pm
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PointLocator {
	static ArrayList<Line> line = new ArrayList<Line>(); // list of lines to be added
	static ArrayList<Point> point = new ArrayList<Point>(); // list of Points to be compared

	public static void readFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);

		// Read in the number of lines
		int lineNum = Integer.parseInt(scanner.nextLine());

		// Read in new lines
		Line[] lineList = new Line[lineNum];

		for (int i = 0; i < lineNum; i++) {
			String[] pointList = scanner.nextLine().split(" ");
			double x1 = Double.parseDouble(pointList[0]);
			double y1 = Double.parseDouble(pointList[1]);
			double x2 = Double.parseDouble(pointList[2]);
			double y2 = Double.parseDouble(pointList[3]);
			// Add lines to the array list
			line.add(new Line(Integer.toString(i), new Point(x1, y1), new Point(x2, y2)));
		}

		scanner.nextLine();
		// Read in point pairs
		while (scanner.hasNextLine()) {
			String[] pointList = scanner.nextLine().split(" ");
			double x1 = Double.parseDouble(pointList[0]);
			double y1 = Double.parseDouble(pointList[1]);
			double x2 = Double.parseDouble(pointList[2]);
			double y2 = Double.parseDouble(pointList[3]);
			// Add points to the array list
			point.add(new Point(x1, y1));
			point.add(new Point(x2, y2));

		}

	}

	// Give result of the line test, Print the external nodes
	public static void display() {
		// Create a tree, insert and print the lines
		Tree tree = new Tree(line.get(0));
		System.out.println(line.get(0));
		for (int i = 1; i < line.size(); i++) {
			System.out.println(line.get(i).toString());
			tree.insert(line.get(i));
		}
		// Check if two points in the same area
		for (int i = 0; i < point.size() - 1; i += 2) {
			// Take points from array
			Point point1 = point.get(i);
			Point point2 = point.get(i + 1);
			// Create the bit strings that represent the path of the points
			String bit1 = tree.lookup(point1);
			String bit2 = tree.lookup(point2);

			System.out.println(point1.toString() + "\t||\t" + point2.toString());

			// Compare two strings to check if they are in the same region
			if (bit1.equals(bit2))
				System.out.println("----- No lines between two points -----");
			else {
				int differInd = 0;
				// Locate the place of difference
				for (int j = 0; j < bit1.length(); j++) {
					if (bit1.charAt(j) != bit2.charAt(j)) {
						differInd = j;
						break;
					}
				}
				System.out.println("----- Line " + tree.findLine(bit1, differInd).label + " between two points -----");
			}

		}

		// Print the external nodes, external path length and average path length
		System.out.println("* Number of external nodes: " + tree.getLeaf(tree.root));
		System.out.println("* Number of external path length: " + tree.exPathLen());
		System.out.printf("* Avarage path length: 	%.2f ", tree.avPathLen());
	}

	public static void main(String[] args) throws FileNotFoundException {
		String fileName = "C:\\Users\\phanh\\eclipse-workspace\\CSC172\\src\\project2\\INPUT.txt";
		readFile(fileName);
		display();

	}

}
