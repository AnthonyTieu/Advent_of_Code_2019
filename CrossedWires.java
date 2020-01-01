//Code would not have been possible without my mum. Love you! :) 

//IMPORT PACKAGES
import java.util.*; //Util
import java.io.*; //Io

//CLASS
public class CrossedWires 
{
  //MAIN METHOD
  public static void main(String [] args) throws Exception
  {
    //DECLARATION AND INITIALIZATION
    Scanner fromFile = new Scanner(new File("DATA_CrossedWires.txt")); //Scanner
    String wireAInput = fromFile.next(); //Wire A path input
    String wireBInput = fromFile.next(); //Wire B path input
    fromFile.close(); //Close file scanner
    
    String[] wireAPath = wireAInput.split(","); //Store Wire A's path in an array, remove commas
    String[] wireBPath = wireBInput.split(","); //Store Wire B's path in an array, remove commas
    
    //2D Arrays to Store the Coordinates for each Line Segment of the Wire
    int[][] wireACoordinates = new int[wireAPath.length][4]; //Array for wire A
    int[][] wireBCoordinates = new int[wireBPath.length][4]; //Array for wire B
    
    int wireAPathSegments = wireAPath.length; //Stores the number of segments in wire A's path
    int wireBPathSegments = wireBPath.length; //Stores the number of segments in wire B's path
    
    boolean intersection; //Stores whether the line segments intersect
    int manhattanDistance; //Stores the manhattan distance of the intersection point
    int shortestManhattanDistance = (int)(Double.POSITIVE_INFINITY); //Stores the shortest manhattan distance
    
    //WIRE A COORDINATES
    wireACoordinates = wireCoordinatesCalculate(wireAPathSegments, wireAPath);
    //Store the coordinate pairs of each line segment in the path of wire A
    
    //WIRE B COORDINATES
    wireBCoordinates = wireCoordinatesCalculate(wireBPathSegments, wireBPath);
    //Store the coordinate pairs of each line segment in the path of wire B
    
    //NESTED FOR LOOP
    for(int wireBCount = 0; wireBCount < wireBPathSegments; wireBCount++) //For the number of segments in wire B's path...
    {
      for(int wireACount = 0; wireACount < wireAPathSegments; wireACount++) //For the number of segments in wire A's path...
      {
        intersection = false; //Reset intersection to false
        
        //If the wire A segment is horizontal and the wire B segment is vertical, or, if wire A segment is vertical
        //and wire B segment is horizontal...
        if(horizontalOrientation(wireACoordinates[wireACount]) != horizontalOrientation(wireBCoordinates[wireBCount]))
        {
          //Check if the lines intersect
          intersection = checkIntersection(wireACoordinates[wireACount], wireBCoordinates[wireBCount]);
        }
        
        if(intersection) //If the lines intersect...
        {
          if(horizontalOrientation(wireACoordinates[wireACount])) //If wire A is horizontal (wire B is vertical)...
          {
            
            manhattanDistance = Math.abs(wireBCoordinates[wireBCount][0]) + Math.abs(wireACoordinates[wireACount][1]);
            if(manhattanDistance < shortestManhattanDistance)
            {
              if(manhattanDistance > 0)
              {
                shortestManhattanDistance = manhattanDistance;
              }
            }
          }
          else
          {
            manhattanDistance = Math.abs(wireACoordinates[wireACount][0]) + Math.abs(wireBCoordinates[wireBCount][1]);
            if(manhattanDistance < shortestManhattanDistance)
            {
              if(manhattanDistance > 0)
              {
                shortestManhattanDistance = manhattanDistance;
              }
            }
          }
          System.out.println(manhattanDistance);
        }
      }
    }
    System.out.println(shortestManhattanDistance);
  }
  
  //CALCULATE PATH COORDINATES
  public static int[][] wireCoordinatesCalculate(int wirePathSegments, String[] wirePath)
  {
    String direction; //Stores the direction of the segment
    int horizontalShift; //Stores the horizontal shift--relative to the previous point
    int verticalShift; //Stores the vertical shift--relative to the previous point
    int[][] wireCoordinates = new int[wirePath.length][4]; //2D array to store the coordinates
    
    for(int count = 0; count < wirePathSegments; count++) //For the number of segments in its path...
    {
      //Reset Values
      horizontalShift = 0; //Reset horizontal shift
      verticalShift = 0; //Reset vertical shift
      
      //Determine the Direction of the Segment
      direction = wirePath[count].substring(0,1); //Take the first letter of the string
      //Ex. if the String says R180, take "R" 
      
      //Determine the Distance and Direction
      if(direction.contentEquals("R")) //If the direction is "right"
      {
        /*Take the rest of the String, convert it to an int value 
         *Make this value positive to indicate that it is in the rightward direction 
         *Set the horizontal shift equal to this value 
         *Ex. if the String says R270, then set the horizontal shift to 270
         */
        horizontalShift = Integer.parseInt(wirePath[count].substring(1,wirePath[count].length()));
      }
      else if(direction.contentEquals("L")) //If the direction is "Left"
      {
        /*Take the rest of the String, convert it to an int value
         *Make this value negative to indicate that it is in the leftward direction  
         *Set the horizontal shift equal to this value 
         *Ex. if the String says L360, then set the horizontal shift to -360
         */
        horizontalShift = -1 * (Integer.parseInt(wirePath[count].substring(1,wirePath[count].length())));
      }
      else if(direction.contentEquals("U")) //If the direction is "Up"
      {
        /*Take the rest of the String, convert it to an int value
         *Make this value positive to indicate that it is in the upward direction  
         *Set the vertical shift equal to this value 
         *Ex. if the String says U90, then set the vertical shift to 90
         */
        verticalShift = Integer.parseInt(wirePath[count].substring(1,wirePath[count].length()));
      }
      else if(direction.contentEquals("D")) //If the direction is "Down"
      {
        /*Take the rest of the String, convert it to an int value
         *Make this value negative to indicate that it is in the downward direction  
         *Set the vertical shift equal to this value 
         *Ex. if the String says D60, then set the vertical shift to -60
         */
        verticalShift = -1 * (Integer.parseInt(wirePath[count].substring(1,wirePath[count].length())));
      }
      
      //Determine Coordinates
      if(count == 0) //If this is the first iteration of the loop...
      {
        //Set the initial coordinates of the wire to (0,0)
        wireCoordinates[0][0] = 0; //Initial x coordinate
        wireCoordinates[0][1] = 0; //Initial y coordinate
        //Determine the new coordinates based on the vertical or horizontal shifts
        wireCoordinates[0][2] = wireCoordinates[0][0] + horizontalShift; //Current x coordinate
        wireCoordinates[0][3] = wireCoordinates[0][1] + verticalShift; //Current y coordinate
      }
      else //Otherwise, if this is not the first iteration of the loop...
      {
        //Copy the coordinates of the previous point (this makes access of information easier later on)
        wireCoordinates[count][0] = wireCoordinates[count - 1][2]; //Previous x coordinate
        wireCoordinates[count][1] = wireCoordinates[count - 1][3]; //Previous y coordinate
        //Determine the new coordinates based on the vertical or horizontal shifts
        wireCoordinates[count][2] = wireCoordinates[count][0] + horizontalShift; //Current x coordinate
        wireCoordinates[count][3] = wireCoordinates[count][1] + verticalShift; //Current y coordinate
      }
    }
    return wireCoordinates; //return the 2D array of coordinates
  }
  
  //DETERMINE THE ORIENTATION
  public static boolean horizontalOrientation(int[] wireCoordinates)
  {
    boolean horizontal; //Stores whether the path started horizontally
    
    //If the new x coordinate is equal to the original x coordinate...
    if(wireCoordinates[0] == wireCoordinates [2])
    {
      horizontal = false; //Then the path did not start horizontally
    }
    //Otherwise, if the new x coordinate is not equal to the original x coordinate...
    else 
    {
      horizontal = true; //Then the path started horizontally
    }
    return horizontal; //Return whether the path started horizontally
  }
  
  //DETERMINE WHETHER TWO SEGMENTS INTERSECT
  public static boolean checkIntersection(int[] wireACoordinates, int[] wireBCoordinates)
  {
    boolean intersect = false;
    
    if(horizontalOrientation(wireACoordinates))
    {
      int wireA_Smallx = Math.min(wireACoordinates[0], wireACoordinates[2]);
      int wireA_Bigx = Math.max(wireACoordinates[0], wireACoordinates[2]); 
      
      int wireB_Smally = Math.min(wireBCoordinates[1], wireBCoordinates[3]);
      int wireB_Bigy = Math.max(wireBCoordinates[1], wireBCoordinates[3]); 
      
      if(wireBCoordinates[0] >= wireA_Smallx && wireBCoordinates[0] <= wireA_Bigx)
      {
        if(wireACoordinates[1] >= wireB_Smally && wireACoordinates[1] <= wireB_Bigy)
        {
          intersect = true;
        }
      }
    }
    else
    {
      int wireA_Smally = Math.min(wireACoordinates[1], wireACoordinates[3]);
      int wireA_Bigy = Math.max(wireACoordinates[1], wireACoordinates[3]);
      
      int wireB_Smallx = Math.min(wireBCoordinates[0], wireBCoordinates[2]);
      int wireB_Bigx = Math.max(wireBCoordinates[0], wireBCoordinates[2]);
      
      if(wireBCoordinates[1] >= wireA_Smally && wireBCoordinates[1] <= wireA_Bigy)
      {
        if(wireACoordinates[0] >= wireB_Smallx && wireACoordinates[0] <= wireB_Bigx)
        {
          intersect = true;
        }
      }
    }
    return intersect;
  }
}
