package pl.coderslab.drugie_Warsztaty;

import java.sql.SQLException;
import java.util.Scanner;

public class GroupManager {

	public static void main(String[] args) {

		DbConnection db = new DbConnection();

		Scanner scan = new Scanner(System.in);
		String option = "";

		while (true) {

			System.out.println(">>>Lista grup<<<");
			Group[] group;
			try {
				group = Group.loadAllUsers(db.getConnection());
				for (Group g : group) {
					System.out.println(g.getId() + " - " + g.getName());
				}

			} catch (SQLException e1) {

				e1.printStackTrace();
			}

			System.out.println("\nWybierz jedną z opcji:\n" + "add - dodanie grupy\n" + "edit - edycja grupy\n"
					+ "delete - usunięcie grupy\n" + "quit - zakończenie programu");
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

		System.out.println("Podaj nazwę grupy:");
		String name = scan.nextLine();

		Group gr = new Group(name);
		try {
			gr.saveToDB(db.getConnection());
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void edit() {
		DbConnection db = new DbConnection();
		Scanner scan = new Scanner(System.in);

		int id = User.getInt("Podaj id grupy do edycji:");

		System.out.println("Podaj nową nazwę grupy:");
		String name = scan.nextLine();

		Group gr = new Group(name);
		gr.setId(id);
		try {
			gr.updateDB(db.getConnection(), gr.getId());
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void delete() {
		DbConnection db = new DbConnection();
		Scanner scan = new Scanner(System.in);

		int id = User.getInt("Podaj id grupy, którą chcesz usunąć:");

		try {
			Group.delete(db.getConnection(), id);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
