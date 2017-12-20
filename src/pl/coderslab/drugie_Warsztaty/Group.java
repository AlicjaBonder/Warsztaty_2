package pl.coderslab.drugie_Warsztaty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * basic functionality for Group in DB schoolOfProgrammers: save to DB, loadAll,
 * loadById, deleteFromDB
 **/

public class Group {

	private int id;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Group() {
	};

	public Group(String name) {

		this.name = name;
	}

	public void saveToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO user_group(name) VALUES (?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			preparedStatement.setString(1, this.name);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		} else {
			String sql = "UPDATE user_group SET name=? where id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, this.name);
			preparedStatement.setInt(2, this.id);
			preparedStatement.executeUpdate();
		}
	}

	static public Group loadUserById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM user_group where id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Group loadedUser = new Group();
			loadedUser.id = resultSet.getInt("id");
			loadedUser.name = resultSet.getString("name");
			return loadedUser;
		}
		return null;
	}

	static public Group[] loadAllUsers(Connection conn) throws SQLException {
		ArrayList<Group> group_users = new ArrayList<Group>();
		String sql = "SELECT * FROM user_group";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Group loadedUser = new Group();
			loadedUser.id = resultSet.getInt("id");
			loadedUser.name = resultSet.getString("name");
			group_users.add(loadedUser);
		}
		Group[] uArray = new Group[group_users.size()];
		uArray = group_users.toArray(uArray);
		return uArray;
	}

	public static void delete(Connection conn, int id) throws SQLException {

		String sql = "DELETE FROM user_group WHERE id= ?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();

	}

	public void updateDB(Connection conn, int id) throws SQLException {
		String sql = "UPDATE user_group SET name=? where id = ?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, this.name);
		preparedStatement.setInt(2, id);
		preparedStatement.executeUpdate();

	}

	public static void printAllUsers() {
		
		DbConnection db = new DbConnection();
		Group[] groups;
		try {
			groups = loadAllUsers(db.getConnection());
			for(Group g: groups) {
				System.out.println(g.getId()+ " - " +g.getName());
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
