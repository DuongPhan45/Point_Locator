# Point_Locator
This project is a Point Locator program to support Math learners, using Binary Tree Data Structure.
## Guideline
The program takes in a .txt file with points and lines' coordinates or users' interactive input and notify whether the given points are on the same plane ( not separated by any lines).
## View

<img src="https://user-images.githubusercontent.com/47710293/97809139-30a89400-1c39-11eb-82e7-fded4685e00b.png" width = "400" height = "400">

## Java Files
The repo contains five java files, corresponding to five classes: 
- Line.java
- Point.java
- Tree.java
- PointLocator.java: read in a txt file of lines and points, show whether there is any lines between the pairs of points
- GraphicImplementation.java: read in a txt file and draw the lines, user provide an input pair of points; change the color of one line between the points if there is
GraphicImplementation is the main executable class.
## Data Used
The sets of demo data are stored in five .txt files:
- INPUT0.txt
- INPUT1.txt
- INPUT2.txt
- INPUT3.txt
File postfix_eval_short.txt is used to store information calculated and used by the program's algorithm
## Testing Guidance
- All INPUT files runs well on PointLocator excet for INPUT0.txt
- GraphicImplementation works well with all INPUT files
