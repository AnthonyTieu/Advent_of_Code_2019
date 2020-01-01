//IMPORT PACKAGES
import java.util.*; //Util
import java.io.*; //Io

//CLASS
public class TheTyrannyOfTheRocketEquation2
{
  //MAIN METHOD
  public static void main(String [] args) throws Exception
  {
    //DECLARATION AND INITIALIZATION
    Scanner fromFile = new Scanner(new File("DATA_RocketModule.txt")); //Scanner
    int totalFuel = 0; //Total fuel counter
    int fuelRequiredModule = 0; //Fuel required for module
    int fuelIteration1 = 0; //Temporary value used to store the fuel required for given mass
    int fuelIteration2 = 0; //Value used to store the fuel required for the given mass
    
    //WHILE LOOP
    while(fromFile.hasNext()) //While there are still modules...
    {
      fuelIteration1 = 0; //Reset value
      fuelIteration2 = 0; //Reset value
      fuelRequiredModule = ((fromFile.nextInt())/3) - 2; //Calculate the fule required for the module alone (not the fuel for its fuel)
      fuelIteration1 = fuelRequiredModule; //Set the temporary value to the mass of the module
      
      //WHILE LOOP
      while(fuelIteration1 > 0) //While the required fuel for the fuel is not less than or equal to zero...
      {
        fuelRequiredModule += fuelIteration2; //Add the required fuel for the given mass to the total fuel required for the module  
        fuelIteration2 = ((fuelIteration1)/3) - 2; //Calculate the required fuel for the previous mass
        fuelIteration1 = fuelIteration2; //Set the temporary value to the mass of the new fuel
      }
      totalFuel += fuelRequiredModule; //Add the required fuel for the module to the total fuel
    }
    System.out.println(totalFuel); //Print the total fuel required
  }
}