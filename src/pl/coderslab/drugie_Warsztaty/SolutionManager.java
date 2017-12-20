package pl.coderslab.drugie_Warsztaty;

import java.sql.SQLException;
import java.util.Scanner;

public class SolutionManager {

	public static void main(String[] args) {

		DbConnection db = new DbConnection();

		Scanner scan = new Scanner(System.in);
		String option = "";

		while (true) {

			System.out.println(">>>Lista ćwiczeń<<<");
			Excercise[] exercises;
			try {
				exercises = Excercise.loadAll(db.getConnection());
				for (Excercise e : exercises) {
					System.out.println(e.getId() + " - " + e.getTitle() + " - " + e.getDescription());
				}

			} catch (SQLException e1) {

				e1.printStackTrace();
			}

			System.out.println("\nWybierz jedną z opcji:\n"
					+ "add - przypisz zadanie do użytkownika\n"
					+ "view - wyświetl rozwiązania danego użutkownika\n"
					+ "viewEx - wyświetl kolejno wszystkie rozwiązania danego zadania\n"
					+ "quit - zakończenie programu");
			option = scan.nextLine();

			if(option.equals("add")) {
				add();
			}else if(option.equals("view")) {
				view();
			}else if(option.equals("viewEx")) {
				viewByExerciseId();
			}else if (option.equals("quit")){
				System.out.println("Wyszedłeś z programu. Zapraszamy ponownie.");
				System.exit(0);
			}else {
				System.out.println("Nie wybrałeś poprawnej opcji. Spróbuj ponownie.\n");
			}

		}

	}
public static void add() {
		DbConnection db = new DbConnection();
		System.out.println(">>>Lista użytkowników<<<");
		User[] users;
		try {
			users = User.loadAllUsers(db.getConnection());
			for(User u : users) {
				System.out.println(u.getId()+" "+u.getUsername());
			}
			
		} catch (SQLException e3) {
			
			e3.printStackTrace();
		}
		
		int id = User.getInt("Podaj id użytkownika, któremu chcesz przypisać zadanie:");
		
		System.out.println(">>>Lista ćwiczeń<<<");
		Excercise[] exercises;
		try {
			exercises = Excercise.loadAll(db.getConnection());
			for(Excercise e : exercises) {
				System.out.println(e.getId()+" - "+e.getTitle()+" - "+e.getDescription());
			}
			
		} catch (SQLException e2) {
			
			e2.printStackTrace();
		}
		
		int exId = User.getInt("Podaj id zadania, które chcesz przypisać:");
		
		Solution sol = new Solution(id, exId);
		try {
			sol.saveToDB(db.getConnection());
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
	}
	
	public static void view() {
		
		DbConnection db = new DbConnection();
		System.out.println(">>>Lista użytkowników<<<");
		User[] users;
		try {
			users = User.loadAllUsers(db.getConnection());
			for(User u : users) {
				System.out.println(u.getId()+" "+u.getUsername());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int id = User.getInt("Podaj id użytkownika, którego rozwiązania chcesz zobaczyć:");
		
		Solution.viewAllByUserId(id);
		
	}
	
	public static void viewByExerciseId() {
		
		int exId = User.getInt("Podaj Id zadania, którego rozwiązania chcesz wyświetlić: ");
		Solution.viewAllByExerciseId(exId);
		
	}
}
