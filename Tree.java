package project2;

//Name: Duong Phan
//NetID: dphan7
//Lab section: Gavett 244 TR 2-3.15pm
public class Tree {
	MyTreeNode root;
	int nodeCount;

	class MyTreeNode {
		public Line l;
		public MyTreeNode leftChild;
		public MyTreeNode rightChild;

		public MyTreeNode(Line x) {
			l = x;
			leftChild = rightChild = null;
		}

		public boolean isLeaf() {
			if (leftChild == null && rightChild == null)
				return true;
			return false;
		}

		public void printInOrder(MyTreeNode node) {
			if (node == null)
				return;
			printInOrder(node.leftChild);
			System.out.println(node.l.label);
			printInOrder(node.rightChild);
		}

		public void insertRecursive(MyTreeNode node, Line key) {
			// Call insert on left child if key is on the left of node's line
			if (key.doIntersect(key, l) == -1) {
				if (leftChild == null) {
					leftChild = new MyTreeNode(key);
				} else {
					leftChild.insertRecursive(this, key);
				}
			} else if (key.doIntersect(key, l) == 1) { // if key is on the right of node's line
				if (rightChild == null) {
					rightChild = new MyTreeNode(key);
				} else {
					rightChild.insertRecursive(this, key);
				}
			} else if (key.doIntersect(key, l) == 0) {// if key and node's line is colinear
				if (leftChild == null) {
					leftChild = new MyTreeNode(key);
				} else {
					leftChild.insertRecursive(this, key);
				}
				if (rightChild == null) {
					rightChild = new MyTreeNode(key);
				} else {
					rightChild.insertRecursive(this, key);
				}
				nodeCount++;
			} else if (key.doIntersect(key, l) == 2) {// if two lines intersect
				nodeCount++;
				Point intersection = key.getIntersection(key, l);
				Line leftLine;
				Line rightLine;
				// Determine the direction of the segment from key.start to intersection
				int dir = key.ccw(key.start, l);
				if (dir == -1) {
					leftLine = new Line(key.label, key.start, intersection);
					rightLine = new Line(key.label, intersection, key.end);
				} else if (dir == 1) {
					rightLine = new Line(key.label, key.start, intersection);
					leftLine = new Line(key.label, intersection, key.end);
				} else {
					rightLine = leftLine = new Line(key.label, new Point(0.0, 0.0), new Point(0.0, 0.0));
				}
				// Insert leftLine to the left
				if (leftChild == null) {
					leftChild = new MyTreeNode(leftLine);

				} else {
					leftChild.insertRecursive(this, leftLine);
				}
				// Insert rightLine to the right
				if (rightChild == null) {
					rightChild = new MyTreeNode(rightLine);

				} else {
					rightChild.insertRecursive(this, rightLine);
				}

			}

		}

		public String lookUpRecursive(MyTreeNode node, Point point) {
			if (node == null)
				return "";
			// Determine the orientation of the Point compare to the node's line
			int dir = l.ccw(point, node.l);
			if (dir == -1) { // point on the left
				point.bitString += "0";
				lookUpRecursive(node.rightChild, point);
			} else if (dir == 1) {// point on the right
				point.bitString += "1";
				lookUpRecursive(node.rightChild, point);
			} else {
				System.out.println("Problem in lookupRecursive");
			}
			return point.bitString;
		}

	}

	// Constructor with root value
	public Tree(Line l) {
		this.root = new MyTreeNode(l);
		nodeCount++;
	}

	public void insert(Line x) {

		// Check if root is null
		if (root.l == null) {
			root.l = x;
			nodeCount++;
			return;
		}
		root.insertRecursive(root, x);
		nodeCount++;

	}

	public void printInOrder() {
		root.printInOrder(root);
		System.out.println();
	}

	public String lookup(Point x) {
		if (root.l == (null)) {
			return "-1";
		}
		return root.lookUpRecursive(root, x);
	}

	// Given the bitString and the index of the bit that marks the difference in
	// path of two points, find the line in that different part
	public Line findLine(String bit, int number) {
		MyTreeNode temp = root;
		for (int i = 0; i < number; i++) {
			if (Integer.parseInt(String.valueOf(bit.charAt(i))) == 0)
				temp = temp.leftChild;
			else if (Integer.parseInt(String.valueOf(bit.charAt(i))) == 1)
				temp = temp.rightChild;
		}
		return temp.l;
	}

	// Return the number of external nodes
	public int getLeaf(MyTreeNode node) {
		if (node == null)
			return 0;
		else if (node.leftChild == null && node.rightChild == null)
			return 1;
		else
			return getLeaf(node.leftChild) + getLeaf(node.rightChild);
	}

	// Return the internal path length
	public int inPathLen(MyTreeNode node) {
		int len = 0;
		if (node == null)
			return 0;
		if (node.isLeaf())
			return 0;
		// The path between leaf nodes and their parents are not counted
		if (node.leftChild != null && node.rightChild != null) {
			if (node.leftChild.isLeaf() && node.rightChild.isLeaf())
				return 0;
		}
		if (node.leftChild != null)
			len += 1 + 2 * inPathLen(node.leftChild);
		if (node.rightChild != null)
			len += 1 + 2 * inPathLen(node.rightChild);
		return len;
	}

	// Return the external path length calculate by the formula
	public int exPathLen() {
		return inPathLen(root) + 2 * (nodeCount - getLeaf(root));
	}

	// Return avarage pathlength
	public double avPathLen() {
		return (double) exPathLen() / getLeaf(root);
	}
}
