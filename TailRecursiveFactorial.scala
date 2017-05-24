
/*
 * A tail recursive implemention of the factorial function
 * @author: ArvindRS
 * @date: 05/23/2017
 */

object Factorial {
	def main(args: Array[String]) {

		// Test case
		println(fact(5))
		println(fact(10))
	}

	// Wrapper function to call the actual tail recursive factorial function
	def fact(n: Int) = factTail(1,1,n)

	// This function first computes the terms and then makes the recursive call, thus tail recursive
	def factTail(i: Int, value: Int, n: Int): Int = if(i <= n) factTail(i+1,i*value,n) else return value
}
