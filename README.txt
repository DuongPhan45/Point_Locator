
//Name: Duong Phan
//NetID: dphan7
//Lab section: Gavett 244 TR 2-3.15pm

- Extra Credit:
	GraphicImplementation: read in a txt file and draw the lines; user provides an input pair of points by clicking mouse; the program changes the color of one line between the points (if there is)

- !!!! Warning:
	File INPUT0.txt cannot be tested by PointLocator due to format problem
	Other INPUT files runs well on PointLocator
	GraphicImplementation works well with all INPUT files

- 11 files submited
	Point.java: class of Point
	Line.java: class of Line
	Tree.java: tree implementation
	PointLocator.java: read in a txt file of lines and points, show whether there is any lines between the pairs of points
	GraphicImplementation: read in a txt file and draw the lines, user provide an input pair of points; change the color of one line between the points if there is
	4 INPUT files: different test cases
	OUPUT.txt: The result of the test cases on GraphicImplementation
	README.txt

- Observation of external nodes and average path length: these two numbers grow really fast when the number of input line grow
- Result of test cases (Visit OUTPUT.txt for more detail)
----- 1 line -----
* Number of external nodes: 1
* Number of external path length: 0
* Average path length: 	0.00 
----- 5 lines -----
* Number of external nodes: 6
* Number of external path length: 16
* Average path length: 	2.67 
----- 14 lines -----
* Number of external nodes: 22
* Number of external path length: 2310
* Average path length: 	105.00 
----- 25 lines -----
* Number of external nodes: 84
* Number of external path length: 36246
* Average path length: 	431.50 