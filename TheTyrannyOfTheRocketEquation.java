//IMPORT PACKAGES
import java.util.*; //Util
import java.io.*; //Io

//CLASS
public class TheTyrannyOfTheRocketEquation
{
  //MAIN METHOD
  public static void main(String [] args) throws Exception
  {
    //DECLARATION AND INITIALIZATION
    Scanner fromFile = new Scanner(new File("DATA_RocketModule.txt")); //Scanner
    int totalFuel = 0; //Total fuel required
    int fuelRequiredModule = 0; //Fuel required for module
    
    //WHILE LOOP
    while(fromFile.hasNext()) //While there are still modules...
    {
      fuelRequiredModule = ((fromFile.nextInt())/3) - 2; //Calculate the fuel required for the module
      totalFuel += fuelRequiredModule; //Add the required module fuel to the total fuel counter 
    }
    System.out.println(totalFuel); //Print the total fuel 
  }
}