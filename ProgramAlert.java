//IMPORT PACKAGES
import java.util.*; //Util
import java.util.stream.Stream; //Stream
import java.io.*; //Io

//CLASS
public class ProgramAlert 
{
	//MAIN METHOD
	public static void main(String [] args) throws Exception
	{
		//DECLARATION AND INITIALIZATION
		Scanner fromFile = new Scanner(new File("DATA_ProgramAlert.txt")); //Scanner
		String intcodeInput = fromFile.next(); //Temporary storage of Intcode program
		//Split the Intcode String at each comma, store the separated values in an int array
		int[] intcode = Stream.of(intcodeInput.split(",")).mapToInt(Integer::parseInt).toArray();
		int intcodeCounter = 0; //Used to count how much of the Intcode program has been run
		fromFile.close(); //Close file input
		
		//RESTORE GRAVITY ASSIST
		intcode[1] = 12; //Set the value at position "1" to "12"
		intcode[2] = 2; //Set the value at position "2" to "2"
		
		//WHILE LOOP
		while(intcodeCounter < intcode.length) //While the program has not finished running...
		{
			if(intcode[intcodeCounter] == 1) //If the opcode at the current position is "1"...
			{
				/*Take the three integers immediately after the opcode
				 *Sum the values from the positions specified by the first two numbers 
				 *Use this sum to set the value of the position specified by the third number
				 */
				intcode[intcode[intcodeCounter + 3]] = intcode[intcode[intcodeCounter + 1]] + intcode[intcode[intcodeCounter + 2]];
				intcodeCounter += 4; //Increment the counter by four (since for positions of the program have been processed) 
			}
			else if(intcode[intcodeCounter] == 2) //If the opcode at the current position is "2"...
			{
				/*Take the three integers immediately after the opcode
				 *Multiply the values from the positions specified by the first two numbers 
				 *Use this product to set the value of the position specified by the third number
				 */
				intcode[intcode[intcodeCounter + 3]] = intcode[intcode[intcodeCounter + 1]] * intcode[intcode[intcodeCounter + 2]];
				intcodeCounter += 4; //Increment the counter by four (since for positions of the program have been processed) 
			}
			else if(intcode[intcodeCounter] == 99) //If the opcode at the current position is "99"...
			{
				break; //Stop the program
			}
		}
		//PRINT STATEMENT
		System.out.println(intcode[0]); //Print out the value at the zeroth position
	}
}
