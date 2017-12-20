package pl.coderslab.drugie_Warsztaty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * basic functionality for Exercise in DB schoolOfProgrammers: save to DB,
 * loadAll, loadById, loadByUserId, deleteFromDB
 **/
public class Excercise {

	private int id = 0;
	private String title;
	private String description;

	public Excercise() {

	}

	public Excercise(String title, String description) {
		setTitle(title);
		setDescription(description);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public Excercise setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Excercise setDescription(String description) {
		this.description = description;
		return this;
	}

	public void saveToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO excercise(title,description) VALUES (?,?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			preparedStatement.setString(1, this.title);
			preparedStatement.setString(2, this.description);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		} else {
			String sql = "UPDATE excercise SET title=? description=? where id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, this.title);
			preparedStatement.setString(2, this.description);
			preparedStatement.setInt(3, this.id);
			preparedStatement.executeUpdate();
		}
	}

	static public Excercise loadById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM excercise where id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Excercise exLoadById = new Excercise();
			exLoadById.id = resultSet.getInt("id");
			exLoadById.title = resultSet.getString("title");
			exLoadById.description = resultSet.getString("decription");
			return exLoadById;
		}
		return null;
	}

	public static Excercise[] loadAll(Connection conn) throws SQLException {

		ArrayList<Excercise> exercises = new ArrayList<>();
		String sql = "Select * from excercise;";

		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Excercise loadedEx = new Excercise();
			loadedEx.id = resultSet.getInt("id");
			loadedEx.title = resultSet.getString("title");
			loadedEx.description = resultSet.getString("description");
			exercises.add(loadedEx);
		}
		Excercise[] uArray = new Excercise[exercises.size()];
		uArray = exercises.toArray(uArray);
		return uArray;
	}

	public static void delete(Connection conn, int id) throws SQLException {
		
			String sql = "DELETE FROM excercise WHERE id= ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
		}
	

	public static Excercise[] loadAllByUserId(Connection conn, int id) throws SQLException {
		ArrayList<Excercise> exercises = new ArrayList<>();
		String sql = "select * from exercise e join solution s " + "on e.id=s.exercise_id " + "where s.users_id = ?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Excercise loadedEx = new Excercise();
			loadedEx.id = resultSet.getInt("id");
			loadedEx.title = resultSet.getString("title");
			loadedEx.description = resultSet.getString("description");
			exercises.add(loadedEx);
		}
		Excercise[] uArray = new Excercise[exercises.size()];
		uArray = exercises.toArray(uArray);
		return uArray;
	}

	public void updateInDB(Connection conn, int id) throws SQLException  {
		String sql = "UPDATE excercise SET title=? description=? where id = ?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		
		preparedStatement.setString(1, this.title);
		preparedStatement.setString(2, this.description);
		preparedStatement.setInt(3, this.id);
		preparedStatement.setInt(4, id);
		preparedStatement.executeUpdate();
		
	}
	public static Excercise[] loadEmptyExcercise(int id) throws SQLException {
		DbConnection db = new DbConnection();
		ArrayList<Excercise> exercises = new ArrayList<>();
		String sql = "SELECT * FROM excercise e JOIN solution s ON e.id=s.excerc_id WHERE s.users_id = ? and s.description is null";
					
		PreparedStatement preparedStatement;
		preparedStatement = db.getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Excercise loadedEx = new Excercise();
			loadedEx.id = resultSet.getInt("id");
			loadedEx.title = resultSet.getString("title");
			loadedEx.description = resultSet.getString("description");
			exercises.add(loadedEx);
		}
		Excercise[]exList = new Excercise[exercises.size()];
		exList = exercises.toArray(exList);
		return exList;
	}
	
	
	public static void printLoadEmptyByUserId(int id) {
		Excercise[] ex;
		try {
			ex = loadEmptyExcercise(id);
			for (Excercise e : ex) {
				System.out.println(e.getId()+" - "+e.getTitle()+" - "+e.getDescription());
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
