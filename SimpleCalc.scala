import java.io._;
import scala.io._;
import scala.util.control._;
import scala.util.control.Breaks._;

/*
 * Scala implementation of a simple calculator.
 * Author: ArvindRS
 * Date: 05/09/2017
 */

class Calculator {

	// Declare two variables to store our values
	var a: Int = _;
	var b: Int = _;

	// Define a method to get the input from the user
	def getInputs() {
		// Get the inputs from the user through command-line
		a = StdIn.readInt();
		b = StdIn.readInt();
	}

	// Define the method to add the two numbers and display the result
	def addition() {
		println(a+" + "+b+" = "+(a + b));
	}

	// Define the method to add the two numbers and display the result
	def subtraction() {
		println(a+" - "+b+" = "+(a - b));
	}

	// Define the method to add the two numbers and display the result
	def multiplication() {
		println(a+" * "+b+" = "+(a * b));
	}

	// Define the method to add the two numbers and display the result
	def division() {
		println(a+" / "+b+" = "+(a / b));
	}
}

object SimpleCalc {
	// Define the main method
	def main(args: Array[String]) {

		println("***This is a simple calculator***");
		while(true) {
			// Create a Calculator object
			val calc = new Calculator();

			println("Enter two numbers...");
			calc.getInputs();
			println("Choose an option:");
			println("1. Addition");
			println("2. Subtraction");
			println("3. Multiplication");
			println("4. Division");
			println("5. Exit");
			val choice = StdIn.readInt();
			choice match {
				case 1 => calc.addition();
				case 2 => calc.subtraction();
				case 3 => calc.multiplication();
				case 4 => calc.division();
				case 5 => break;
			}
		}
	}
}