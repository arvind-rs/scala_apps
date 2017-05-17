
/*
 * A simple implementation of a web crawler.
 * @author: ArvindRS
 * @date: 05/16/2017
 */

import scala.collection.mutable.Queue
import org.htmlcleaner.HtmlCleaner
import scala.collection.mutable.ListBuffer
import java.io._;

object Crawler {
	// Main function
	def main(args: Array[String]) {

		// Seed URL
		val seed = "http://asf.sdes.ucf.edu/"

		// Maintain a set of visited URLs
		val visited = Set()

		// Create a queue to hold the next URLs to be crawled
		val queue = Queue[String](seed)

		while(!queue.isEmpty) {
			// Get the next URL to be crawled
			val url = queue.dequeue()

			// Verify if the URL is valid and is allowed to be crawled
			// stub: verify(url)
			// stub: isAllowed(url)

			// Fetch the URL
			val response = fetch(url)

			// Extract the next set of links
			val links = extractLinks(response,url)
			links.foreach(println)
			// Add them to the queue
			// stub: updateVisited(url,visited)

			// Save the response
			writeToDisk(response,url)
		}
	}

	// Function to fetch the given URL
	def fetch(url: String): String = {
		val response = scala.io.Source.fromURL(url).mkString
		return response
	}

	// Function to extract the next set of URLs to be crawled
	def extractLinks(response: String, url: String): List[String] = {
		println(response)
		// Using ListBuffer() instead of List() as List() collection is immutable in Scala
		val links = ListBuffer[String]()
		val cleaner = new HtmlCleaner
		val props = cleaner.getProperties
		val rootNode = cleaner.clean(response)
		val elements = rootNode.getElementsByName("a",true)
		for(element <- elements) {
			val link = element.getAttributeByName("href")
			if(link != null && link.contains("/www.sdes.ucf.edu/")) {
				links += link
			}
		}

		return links.toList
	}

	// Function to write the response to the disk
	def writeToDisk(response: String, url:String) {
		val fileName = scala.util.Random.nextInt(1000)
		val pw = new PrintWriter(new File(fileName+".html"))
		pw.write(response)
		pw.close
	}
}