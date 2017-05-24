
/*
 * Playing around with Scala functions
 * @author: ArvindRS
 * @date: 05/24/2017
 */

object ExperimentsOnFunctions {
	def main(args: Array[String]) {

		// Normal function
		def normalFun(a: Int): Int = a * 2
		println(normalFun(5))

		// Anonymous function
		var nameless = (x: Int) => x * 3
		println(nameless(5))

		// Higher-order function
		def higher(f: Int => Int,x: Int): Int = f(x)
		println(higher(nameless,10))

		def higher1(f:Int => Int)(x: Int): Int = f(x)
		println(higher1(nameless)(10))



		// Examples of Currying. Mind-fucked! :O
		//var nameless = (x: Int) => x * 3
		def higher2(f:Int => Int)(x: Int): (Int,Int) => Int = (a:Int,b:Int) => f(x) + a + b
		println(higher2(nameless)(10)(2,3))

		def higher3(f:Int => Int)(x: Int): (Int,Int) => (Int) => (Int) = (a:Int,b:Int) => (c:Int) => f(x) + a + b + c
		println(higher3(nameless)(10)(2,3)(4))

	}
}