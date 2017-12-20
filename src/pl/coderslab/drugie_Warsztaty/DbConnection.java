package pl.coderslab.drugie_Warsztaty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** one common class which make connection with database in mysql **/

public class DbConnection {

	private Connection dbConnection = null;

	public DbConnection() {
		try {
			this.dbConnection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/szkola_programowania?useSSL=false", "root", "coderslab");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return this.dbConnection;
	}

	public void closeConnection() {

		if (this.dbConnection != null) {
			try {
				this.dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
