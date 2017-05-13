import scala.io._;
import scala.util.control.Breaks._;

/*
 * Scala implementation of an advanced calculator
 * Author: ArvindRS
 * Date: 05/10/2017
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

class ExtendedCalculator extends Calculator {

	// Declare a new variable
	var c: Int = _;

	// Define a method to perform square-root operation
	def sqrt() {
		println("Enter a number to find the sqrt for ");
		c = StdIn.readInt();
		println("sqrt( "+c+" ) = "+(Math.sqrt(c)));
	}
}

object AdvancedCalc {
	// Define the main method
	def main(args: Array[String]) {
		val calc = new ExtendedCalculator();
		calc.sqrt();
	}
}
