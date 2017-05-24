
/*
 * Scala code to test connecting to MySQL and executing SQL commands
 * @author: ArvindRS
 * @date: 05/24/2017
 */ 

import java.sql.DriverManager
import java.sql.Connection

object DBConnectionTest {

	def main(args: Array[String]) {
		// connect to the database named "mysql" on the localhost
    	val driver = "com.mysql.jdbc.Driver"
    	val url = "jdbc:mysql://localhost/scala_test"
    	val username = "root"
    	val password = "ubuntu"

    	var connection: Connection = null

    	try {
    		Class.forName(driver)
    		connection = DriverManager.getConnection(url,username,password)
    		val statement = connection.createStatement()
      		val resultSet = statement.executeQuery("select id,name from student;")
      		while ( resultSet.next() ) {
        		val id = resultSet.getString("id")
        		val name = resultSet.getString("name")
        		println(id + ", " + name)
      		}
    	}
    	catch {
    		case e => e.printStackTrace
    	}
    	connection.close()
	}
}