
/*
 * A simple scala app to read a conversion chart and a list of student grades, convert the numeric grades to letter grades, calculate the mean,
 * and write the output to an output text file.
 * @author: ArvindRS
 * @date: 05/13/2017
 */

import scala.io._;
import scala.collection.mutable._;
import scala.util.matching.Regex;
import java.io._;

object GradeConverter {
	// Main function
	def main(args: Array[String]) {

		// Define the file names
		val conversionChartFile = "num_to_letter_grade_conversion_chart.txt"
		val studentGradesFile = "student_grades.txt"
		val outputFile = "output.txt"

		// Read the conversion chart from a text file
		val conversionChart = getConversionChart(conversionChartFile)
		// Read the student grades from the text file
		val studentGrades = getStudentGrades(studentGradesFile)
		println(conversionChart)
		println(studentGrades)

		var total: Int = 0;
		val modifiedStudentGrades: Map[Int,String] = Map()
		// Use the chart to convert the numeric grades to letter grades
		for(grade <- studentGrades) {
			var (studentId,numericGrade) = grade
			for(range <- conversionChart) {
				// Split the tuple to constituent values
				var ((minValue,maxValue),letter) = range
				if(numericGrade >= minValue && numericGrade <= maxValue)
					modifiedStudentGrades += (studentId -> letter)
			}
			// Compute the total grades
			total += numericGrade
		}

		// Calculate the average
		var mean = total / studentGrades.size

		// Display the results
		println("*** Modified Student Grades***")
		modifiedStudentGrades.foreach(println)
		println("class mean: "+mean)

		// Write the converted grades to a text file
		writeFinalValues(modifiedStudentGrades,outputFile)
	}

	// Function to read the conversion chart from the specified text file
	def getConversionChart(file: String): Map[(Int,Int),String] = {
		println(file)
		// Create a Map datastructure to store the values
		var chart: Map[(Int,Int),String] = Map()

		// Read the file
		val data = Source.fromFile(file).getLines

		// Parse each line to get the range and associated letter grades
		for(line <- data if !line.startsWith("number_range")) {
			
			// Create a regex pattern. Here we are using the .r() method instead of explicitly using new Regex()
			val pattern = "\t+".r
			// Modify the line to remove excess '\t' to avoid issues
			var modifiedLine = (pattern replaceAllIn(line,"\t"))
			// Split the line along '\t'
			val elements: Array[String] = modifiedLine.split("\t")
			
			// Extract the min, max and letter values
			var minValue = elements(0).split("to")(0).trim().toInt
			var maxValue = elements(0).split("to")(1).trim().toInt
			var letter = elements(1)

			// Add the Tuple(min,max) and letter as (key,value) pair to the map
			chart += ((minValue,maxValue) -> letter)
		}

		return chart
	}

	// Function to read the student grades from the given input file
	def getStudentGrades(file: String): Map[Int,Int] = {

		// Create a Map datastructure to store the values
		var grades: Map[Int,Int] = Map()

		// Read the file
		val data = Source.fromFile(file).getLines

		// Parse each line to get the student id and the corresponding numeric grade
		for(line <- data if !line.startsWith("student")) {

			// Create a regex pattern.
			val pattern = "\t+".r
			// Modify the line to remove excess '\t' to avoid issues
			var modifiedLine = (pattern replaceAllIn(line,"\t"))

			// Split the line along '\t'
			val elements: Array[String] = modifiedLine.split("\t")

			// Extract the values
			var studentId = elements(0).trim().toInt
			var numericGrade = elements(1).trim().toInt

			// Add to the map
			grades += (studentId -> numericGrade)
		}

		return grades
	}

	// Function to write the final value to a text file
	def writeFinalValues(studentGrades: Map[Int,String], file: String) {
		// Open a reference to the text file
		val printWriter = new PrintWriter(file)

		// Add header information
		printWriter.println("student\tgrade")

		// Get the (key,value) pairs from the map in sorted order
		val (keys,values) = studentGrades.toSeq.sorted.unzip

		// Write the data to the file line by line
		for(k <- keys)
			printWriter.println(k+"\t"+studentGrades(k))
		
		// Close the pointer
		printWriter.close()
	}
}

