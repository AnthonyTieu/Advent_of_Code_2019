
//IMPORT PACKAGES
import java.util.*; //Util
import java.util.stream.Stream; //Stream
import java.io.*; //Io

public class ProgramAlert2 
{
	// MAIN METHOD
	public static void main(String[] args) throws Exception 
	{
		// DECLARATION AND INITIALIZATION
		Scanner fromFile = new Scanner(new File("DATA_ProgramAlert.txt")); // Scanner
		String intcodeInput = fromFile.next(); // Temporary storage of Intcode program
		// Split the Intcode String at each comma, store the separated values in an int array
		int[] intcode = Stream.of(intcodeInput.split(",")).mapToInt(Integer::parseInt).toArray();
		int intcodeCounter = 0; // Used to count how much of the Intcode program has been run
		fromFile.close(); // Close file input
		
		int intcode1 = 0; //Used as a temporary storage for the value at position "1"
		int intcode2 = 0; //Used as a temporary storage for the value at position "2"
		//These survive the memory wipe each time the Intcode program is reset (in the array)

		//WHILE LOOP
		while (intcode[0] != 19690720) //While the value at position "0" is not equal to 19690720... 
		{
			//Reset the Intcode program in the array
			intcode = Stream.of(intcodeInput.split(",")).mapToInt(Integer::parseInt).toArray();
			intcodeCounter = 0; //Reset the program progress counter  

			if (intcode1 < 99) //If the value at position "1" is less than 99... 
			{
				intcode1++; //Increment it by 1
			}
			else //Otherwise, if the value at position "1" is 99...
			{
				intcode1 = 0; //Reset the value at position "1" to 0
				intcode2++; //Increment the value at position "2" by 1
			}
			
			intcode[1] = intcode1; //Set the value at position "1" to its temporarily stored value
			intcode[2] = intcode2; //Set the value at position "2" to its temporarily stored value

			// WHILE LOOP
			while (intcodeCounter < intcode.length) // While the program has not finished running...
			{
				if (intcode[intcodeCounter] == 1) // If the opcode at the current position is "1"...
				{
					/*
					 * Take the three integers immediately after the opcode Sum the values from the
					 * positions specified by the first two numbers Use this sum to set the value of
					 * the position specified by the third number
					 */
					intcode[intcode[intcodeCounter + 3]] = intcode[intcode[intcodeCounter + 1]] + intcode[intcode[intcodeCounter + 2]];
					intcodeCounter += 4; // Increment the counter by four (since for positions of the program have been
											// processed)
				} else if (intcode[intcodeCounter] == 2) // If the opcode at the current position is "2"...
				{
					/*
					 * Take the three integers immediately after the opcode Multiply the values from
					 * the positions specified by the first two numbers Use this product to set the
					 * value of the position specified by the third number
					 */
					intcode[intcode[intcodeCounter + 3]] = intcode[intcode[intcodeCounter + 1]] * intcode[intcode[intcodeCounter + 2]];
					intcodeCounter += 4; // Increment the counter by four (since for positions of the program have been
											// processed)
				} else if (intcode[intcodeCounter] == 99) // If the opcode at the current position is "99"...
				{
					break; // Stop the program
				}
			}
		}
		// PRINT STATEMENT
		System.out.println((100 * intcode[1]) + intcode[2]); // Print out the value of the pair
	}
}
