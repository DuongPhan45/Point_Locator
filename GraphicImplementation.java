package project2;

//Name: Duong Phan
//NetID: dphan7
//Lab section: Gavett 244 TR 2-3.15pm

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class GraphicImplementation extends JComponent implements MouseListener {
	public static int WIDTH = 500;
	public static int HEIGHT = 500;
	static ArrayList<Line> line = new ArrayList<Line>(); // list of lines to be added
	static ArrayList<Point> point = new ArrayList<Point>(); // list of Points to be compared

	public GraphicImplementation(String args[]) throws FileNotFoundException {
		addMouseListener(this);
		String fileName = args[4];
		readFile(fileName);

	}

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
	}

	// Return the Line between the points
	// Give result of the line test, Print the external nodes
	public static int display() {
		int lineInd = -1;
		// Create a tree, insert and print the lines
		Tree tree = new Tree(line.get(0));
		System.out.println(line.get(0));
		for (int i = 1; i < line.size(); i++) {
			System.out.println(line.get(i).toString());
			tree.insert(line.get(i));
		}
		// Check if two points in the same area

		// Take points from array
		Point point1 = point.get(0);
		Point point2 = point.get(1);
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
			lineInd = Integer.parseInt(tree.findLine(bit1, differInd).label);
		}
		// Print the external nodes, external path length and average path length
		System.out.println("* Number of external nodes: " + tree.getLeaf(tree.root));
		System.out.println("* Number of external path length: " + tree.exPathLen());
		System.out.printf("* Avarage path length: 	%.2f ", tree.avPathLen());
		return lineInd;
	}

	public void paintComponent(Graphics g) {
		// Draw the lines
		for (int i = 0; i < line.size(); i++) {
			g.setColor(Color.BLACK);
			Line newLine = line.get(i);
			int x1 = (int) Math.round(newLine.start.x * WIDTH);
			int y1 = (int) Math.round(HEIGHT - newLine.start.y * HEIGHT);
			int x2 = (int) Math.round(newLine.end.x * WIDTH);
			int y2 = (int) Math.round(HEIGHT - newLine.end.y * HEIGHT);
			g.drawLine(x1, y1, x2, y2);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();

		// Draw the points
		Graphics g = getGraphics();
		int x1 = (int) Math.round(x);
		int y1 = (int) Math.round(y);
		g.setColor(Color.RED);
		g.fillOval(x1, y1, 10, 10);

		// Add the point to the array
		Point newPoint = new Point((double) x / WIDTH, (double) (HEIGHT - y) / HEIGHT);
		point.add(newPoint);

		// When enough points is put into the array, perform calculation and test
		if (point.size() == 2) {

			int lineInd = display();
			if (lineInd == -1)
				return;
			else {// Dectect a line between two points, change the line's color
				Line newLine = line.get(lineInd);
				int x0 = (int) Math.round(newLine.start.x * WIDTH);
				int y0 = (int) Math.round(HEIGHT - newLine.start.y * HEIGHT);
				int x2 = (int) Math.round(newLine.end.x * WIDTH);
				int y2 = (int) Math.round(HEIGHT - newLine.end.y * HEIGHT);
				g.setColor(Color.GREEN);
				g.drawLine(x0, y0, x2, y2);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public static void main(String[] args) throws FileNotFoundException {
		JFrame frame = new JFrame("Window");
		frame.setSize(WIDTH, HEIGHT);

		GraphicImplementation newLines = new GraphicImplementation(args);
		frame.add(newLines);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
