package pl.coderslab.drugie_Warsztaty;

import java.sql.SQLException;
import java.util.Scanner;

public class ExcerciseManager {

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

			System.out.println("\nWybierz jedną z opcji:\n" + "add - dodanie zadania\n" + "edit - edycja zadania\n"
					+ "delete - usunięcie zadania\n" + "quit - zakończenie programu");
			option = scan.nextLine();

			if (option.equals("add")) {
				add();
			} else if (option.equals("edit")) {
				edit();
			} else if (option.equals("delete")) {
				delete();
			} else if (option.equals("quit")) {
				System.out.println("Wyszedłeś z programu. Zapraszamy ponownie.");
				System.exit(0);
			} else {
				System.out.println("Nie wybrałeś poprawnej opcji. Spróbuj ponownie.");
			}

		}

	}

	public static void add() {

		DbConnection db = new DbConnection();
		Scanner scan = new Scanner(System.in);

		System.out.println("Podaj tytuł zadania:");
		String title = scan.nextLine();
		System.out.println("Podaj opis zadania:");
		String description = scan.nextLine();

		Excercise ex = new Excercise(title, description);
		try {
			ex.saveToDB(db.getConnection());
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void edit() {
		DbConnection db = new DbConnection();
		Scanner scan = new Scanner(System.in);

		int id = User.getInt("Podaj id ćwiczenia do edycji:");

		System.out.println("Podaj nowy tytuł ćwiczenia:");
		String title = scan.nextLine();

		System.out.println("Podaj nowy opis ćwiczenia:");
		String description = scan.nextLine();

		Excercise ex = new Excercise(title, description);
		ex.setId(id);
		try {
			ex.updateInDB(db.getConnection(), ex.getId());
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void delete() {
		DbConnection db = new DbConnection();
		Scanner scan = new Scanner(System.in);

		int id = User.getInt("Podaj id ćwiczenia, które chcesz usunąć:");

		try {
			Excercise.delete(db.getConnection(), id);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
