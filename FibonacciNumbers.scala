
/*
 * Scala implementation of the fibonacci numbers
 * @author: ArvindRS
 * @date: 05/23/2017
 */
import scala.collection.mutable.Map

object FibonacciNumbers {
	def main(args: Array[String]) {
		// Test cases
		println(fibo(40))
		println(fiboMemo(40))
		println(fiboIter(40))
	}

	// Normal recursive function to compute the fibonacci numbers
	def fibo(n: Int): Int = if(n == 0) return 0 else if(n == 1) return 1 else return fibo(n-2) + fibo(n-1)

	// Wrapper function to call the memoized version
	def fiboMemo(n: Int): Int = {
		var memo = Map[Int,Int]()

		// Defining the function inside the wrapper function because scala allows it :D
		def fiboMemo(n: Int, memo: Map[Int,Int]): Int = {
			// Base cases
			if(n == 0) return 0
			if(n == 1) return 1
			if(memo.contains(n)) return memo(n)
			val result = fiboMemo(n-2,memo) + fiboMemo(n-1,memo)
			memo(n) = result
			return result
		}
		return fiboMemo(n,memo)
	}

	// Iterative version of fibonacci numbers
	def fiboIter(n: Int): Int = {
		var a = 0
		var b = 1
		for(i <- 1 until n) {
			val t = a
			a = b
			b = a + t
		}
		return b
	}
}