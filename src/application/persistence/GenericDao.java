package application.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Generic persistence class for database connection
 */


public class GenericDao {
	
	/**
	 * Connection variable
	 */
	private Connection c;
	
	/**
	 * <p> Get connection to DB </p>
	 * @since 1.0
	 */
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		String hostName = "localhost";
		String dbName = "perfumex";
		String user = "Perfumex";
		String senha = "123456";
		
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		
		c = DriverManager.getConnection(
				String.format("jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s;", 
						hostName, dbName, user, senha));
		return c;
	}

}
