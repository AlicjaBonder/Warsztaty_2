package pl.coderslab.drugie_Warsztaty;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	

				DbConnection db = new DbConnection();
				User[] users1 = null;
				User[] users2 = null;
				
				try {
					
					User user1 = User.loadUserById(db.getConnection(), 3);
					//System.out.println(user1.getUsername());
					User us = new User();
					//us.printAllUsersByGroupId(db.getConnection(), 3);
//					loaded.delete(db.getConnection());
//					
					//User loaded = User.loadUserById(db.getConnection(), 3);
					users1= User.loadAllUsers(db.getConnection());
					
					for(User u : users1) {
						System.out.println(u.getUsername());
					}
					
					users2 = User.loadAllUsers(db.getConnection());
					for(User u: users2) {
						System.out.println(u.getUsername());
					}
					
//					loaded.setEmail("new_email@email.com");
//					loaded.setPassword("nowe_haslo");
//					loaded.saveToDB(db.getConnection());
//					
					//users1 = User.loadAllUsers(db.getConnection());
						
//					User us1 = new User("Miauczyński", "maiu@wp.pl", "haslo1");
//					us1.saveToDB(db.getConnection());
//					
//					User us2 = new User("Dzwonek", "dzwoneczek2@wp.pl", "haslo2");
//					us2.saveToDB(db.getConnection());
//					
//					User us3 = new User("Kot", "kot@gmail.com", "haslo3");
//					us3.saveToDB(db.getConnection());
					
//					Group gr1 = new Group ("Poczatkujący");
//					gr1.saveToDB(db.getConnection());
//					users2 = User.loadAllUsers(db.getConnection());
				
					
				}catch (SQLException ex) {
					System.out.println("Wystąpił błąd");
					System.out.println(ex.getMessage());
				}
				

//				
				
//				

				db.closeConnection();
			}
		}

	