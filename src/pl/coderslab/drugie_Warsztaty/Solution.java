package pl.coderslab.drugie_Warsztaty;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Solution {

	private int id;
	private Date created;
	private Date updated;
	private String description;
	private int exerc_id;
	private int users_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getExerc_id() {
		return exerc_id;
	}

	public void setExercise_id(int exercise_id) {
		this.exerc_id = exercise_id;
	}

	public int getUsers_id() {
		return users_id;
	}

	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}

	public Solution(int id, Date created, Date updated, String description) {
		super();
		this.id = id;
		this.created = created;
		this.updated = updated;
		this.description = description;
	}

	public Solution(int users_id, int excercise_id) {
		setUsers_id(users_id);
		setExercise_id(excercise_id);
	}

	public Solution() {
	}

	public void saveToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO solution(created,updated,description) VALUES (?,?,?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			preparedStatement.setDate(1, this.created);
			preparedStatement.setDate(2, this.updated);
			preparedStatement.setString(3, this.description);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		} else {
			String sql = "UPDATE solution SET created=? updated=? description=? where id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setDate(1, this.created);
			preparedStatement.setDate(2, this.updated);
			preparedStatement.setString(3, this.description);
			preparedStatement.setInt(4, this.id);
			preparedStatement.executeUpdate();
		}
	}

	static public Solution[] loadByUserId(int id) throws SQLException {
		DbConnection db = new DbConnection();
		ArrayList<Solution> solutions = new ArrayList<>();

		String sql = "SELECT * FROM solution where users_id=?";
		PreparedStatement preparedStatement;
		preparedStatement = db.getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Solution solLoadById = new Solution();
			solLoadById.id = resultSet.getInt("id");
			solLoadById.created = resultSet.getDate("created");
			solLoadById.updated = resultSet.getDate("updated");
			solLoadById.description = resultSet.getString("description");
			solutions.add(solLoadById);
		}
		Solution[] uArray = new Solution[solutions.size()];
		uArray = solutions.toArray(uArray);
		return uArray;

	}

	public static Solution[] loadAll(Connection conn) throws SQLException {

		ArrayList<Solution> solutions = new ArrayList<>();
		String sql = "Select * from solution;";

		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Solution loadedSol = new Solution();
			loadedSol.id = resultSet.getInt("id");
			loadedSol.created = resultSet.getDate("created");
			loadedSol.updated = resultSet.getDate("updated");
			loadedSol.description = resultSet.getString("description");
			solutions.add(loadedSol);
		}
		Solution[] uArray = new Solution[solutions.size()];
		uArray = solutions.toArray(uArray);
		return uArray;
	}

	public void delete(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql = "DELETE FROM solution WHERE id= ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id = 0;
		}
	}

	public static Solution[] loadAllByExerciseId(Connection conn, int id) throws SQLException {
		ArrayList<Solution> solutions = new ArrayList<>();
		String sql = "select * from solution s where s.excerc_id = 3";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Solution loadedSol = new Solution();
			loadedSol.id = resultSet.getInt("id");
			loadedSol.created = resultSet.getDate("created");
			loadedSol.updated = resultSet.getDate("updated");
			loadedSol.description = resultSet.getString("description");
			loadedSol.users_id = resultSet.getInt("users_id");
			solutions.add(loadedSol);
		}
		Solution[] uArray = new Solution[solutions.size()];
		uArray = solutions.toArray(uArray);
		return uArray;
	}

	public static void viewAllByUserId(int id) {

		
		Solution[] usersSolutions;
		try {
			usersSolutions = loadByUserId( id);
			for (Solution s : usersSolutions) {
				System.out.println("Id zadania - " + s.getExerc_id() + " - rozwiązanie:\n" + s.getDescription());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void viewAllByExerciseId(int exId) {

		DbConnection db = new DbConnection();
		Solution[] exerrciseSolutions;
		try {
			exerrciseSolutions = loadAllByExerciseId(db.getConnection(), exId);
			for (Solution s : exerrciseSolutions) {
				System.out.println("Id zadania - " + s.getExerc_id() + ", Id użytkownika - " + s.getUsers_id()
						+ ", dodano - " + s.getCreated() + "\n   Rozwiązanie:\n    " + s.getDescription() + "\n");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	
}
