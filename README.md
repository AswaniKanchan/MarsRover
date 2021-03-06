# MarsRover

Problem Statement
- A squad of robotic rovers are to be landed by NASA on a plateau on Mars. This plateau, which is curiously rectangular, must be navigated by the rovers so that their on-board cameras can get a complete view of the surrounding terrain to send back to Earth. 
A rover's position and location is represented by a combination of x and y co-ordinates and a letter representing one of the four cardinal compass points. The plateau is divided up into a grid to simplify navigation. An example position might be 0, 0, N, which means the rover is in the bottom left corner and facing North. 
In order to control a rover, NASA sends a simple string of letters. The possible letters are 'L', 'R' and 'M'. 'L' and 'R' makes the rover spin 90 degrees left or right respectively, without moving from its current spot. 'M' means move forward one grid point, and maintain the same heading. 
Assume that the square directly North from (x, y) is (x, y+1).

Input
- The first line of input is the upper-right coordinates of the plateau, the lower-left coordinates are assumed to be 0,0. 
The rest of the input is information pertaining to the rovers that have been deployed. Each rover has two lines of input. The first line gives the rover's position, and the second line is a series of instructions telling the rover how to explore the plateau. 
The position is made up of two integers and a letter separated by spaces, corresponding to the x and y co-ordinates and the rover's orientation. 
Each rover will be finished sequentially, which means that the second rover won't start to move until the first one has finished moving.
 

Output
- The output for each rover should be its final co-ordinates and heading. 

Assumptions
-	Lower left coordinates are 0,0
-	M would mean move by 1 step only
-	Input file will have data in consecutive lines, else empty line for command string would imply no action is to be taken and for others would fail saying unable to derive coordinates
- Input file will follow a template given below

Template File
- Line 1: Two integers separated by spaces to derive grid top right coordinates: <5 5>
- <Loop>
-     Line 2i  : Two integers followed by a capital letter from the given list: [W, N, E, S] to derive rovers current position: <3 3 N>
-      Line 2i+1: A string which is either empty or is made up with characters from given list: [LRM]
- </Loop>


Tech Stack
- Java
- Gradle
- Junit5

Developer Guidelines
- Install gradle and java
- Execute: gradle build
- To run test cases: gradle cleanTest test
- To run program with input file:  	./gradlew run --args=SampleInput.txt
