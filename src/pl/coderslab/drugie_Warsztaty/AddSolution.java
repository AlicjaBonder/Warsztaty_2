package pl.coderslab.drugie_Warsztaty;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddSolution {

	public static void main(String[] args) {

		User.printAllUsers();
		int id = User.getInt("Podaj Id użytkownia,który chce dodać zadania: ");

		Scanner scan = new Scanner(System.in);
		String option = "";

		while (true) {

			System.out.println("\nWybierz jedną z opcji:\n" + "add - dodaj rozwiązanie\n"
					+ "view - wyświetl rozwiązania danego użutkownika\n" + "quit - zakończenie programu");
			option = scan.nextLine();

			if (option.equals("add")) {
				addSolution(id);
			} else if (option.equals("view")) {
				System.out.println("===Rozwiązania===");
				Solution.viewAllByUserId(id);
			} else if (option.equals("quit")) {
				System.out.println("Wyszedłeś z programu. Zapraszamy ponownie.");
				System.exit(0);
			} else {
				System.out.println("Nie wybrałeś poprawnej opcji. Spróbuj ponownie.\n");
			}

		}

	}

	public static void addSolution(int id) {
		DbConnection db = new DbConnection();
		System.out.println("Lista zadań dla których nie dodałeś rozwiązań: ");
		Excercise.printLoadEmptyByUserId(id);

		int exId = User.getInt("Podaj Id zadania, którego rozwiązanie dodajesz.");

		Scanner scan = new Scanner(System.in);
		System.out.println("Wpisz rozwiązanie: ");
		String solution = scan.nextLine();

		String sql = "Update solution set description = ?, updated = CURRENT_TIMESTAMP where users_id = ? and exerc_id = ?;";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = db.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, solution);
			preparedStatement.setInt(2, id);
			preparedStatement.setInt(3, exId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Rozwiązanie zostało dodane!");

	}

}
