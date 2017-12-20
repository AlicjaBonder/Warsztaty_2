package pl.coderslab.drugie_Warsztaty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private int person_group_id;

	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.setPassword(password);
	}
	public User(String username,String email, String password,  int person_group_id) {
		super();
		this.username = username;
		this.setPassword(password);
		this.email = email;
		this.person_group_id = person_group_id;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return this.id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public User setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
		return this;
	}

	public void saveToDB() throws SQLException {
		DbConnection db = new DbConnection();
		Connection conn = db.getConnection();
		if (this.id == 0) {
			String sql = "INSERT INTO users(username, email, password,person_group_id) VALUES (?, ?, ?,?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			preparedStatement.setString(1, this.username);
			preparedStatement.setString(2, this.email);
			preparedStatement.setString(3, this.password);
			preparedStatement.setInt(4, this.person_group_id);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		} else {
			String sql = "UPDATE users SET username=?, email=?, password=? where id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, this.username);
			preparedStatement.setString(2, this.email);
			preparedStatement.setString(3, this.password);
			preparedStatement.setInt(4, this.id);
			preparedStatement.executeUpdate();
		}
	}

	static public User loadUserById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM users where id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			User loadedUser = new User();
			loadedUser.id = resultSet.getInt("id");
			loadedUser.username = resultSet.getString("username");
			loadedUser.password = resultSet.getString("password");
			loadedUser.email = resultSet.getString("email");
			return loadedUser;
		}
		return null;
	}

	static public User[] loadAllUsers(Connection conn) throws SQLException {
		ArrayList<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM users";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			User loadedUser = new User();
			loadedUser.id = resultSet.getInt("id");
			loadedUser.username = resultSet.getString("username");
			loadedUser.password = resultSet.getString("password");
			loadedUser.email = resultSet.getString("email");
			users.add(loadedUser);
		}
		User[] uArray = new User[users.size()];
		uArray = users.toArray(uArray);
		return uArray;
	}

	static public User[] loadUsersByGroupId(int id) throws SQLException {
		DbConnection db = new DbConnection();
		Connection conn = db.getConnection();
		List<User> loadedUserGroup = new ArrayList<User>();
		String sql = "SELECT * FROM users WHERE person_group_id = ?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {

			User user = new User();
			user.id = resultSet.getInt("id");
			user.username = resultSet.getString("username");
			user.password = resultSet.getString("password");
			user.email = resultSet.getString("email");
			loadedUserGroup.add(user);
		}
		User[] uArray = new User[loadedUserGroup.size()];
		uArray = loadedUserGroup.toArray(uArray);
		return uArray;

	}

	public static void printAllUsersByGroupId( int groupId) {

		DbConnection db = new DbConnection();
		Connection conn = db.getConnection();
		User[] users;
		try {
			users = loadUsersByGroupId( groupId);
			for (User u : users) {
				System.out.println(u.getId() + " - " + u.getUsername());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void printAllUsers() {

		DbConnection db = new DbConnection();
		User[] users;
		try {
			users = loadAllUsers(db.getConnection());
			for (User u : users) {
				System.out.println(u.getId() + " - " + u.getUsername());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public void setId(int id) {
		this.id = id;
	}

	public static void delete(int id) throws SQLException {
		DbConnection db = new DbConnection();
		Connection conn = db.getConnection();
		
			String sql = "DELETE FROM users WHERE id= ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
		
	}

	public int getPerson_group_id() {
		return person_group_id;
	}

	public void setPerson_group_id(int person_group_id) {
		this.person_group_id = person_group_id;
	}

	public static int getInt(String message) {
		Scanner scan = new Scanner(System.in);
		System.out.println(message);
		while (!scan.hasNextInt()) {
			System.out.println("Podane Id nie jest liczbą!\n" + message);
			scan.next();
		}
		return scan.nextInt();

	}

	
}
