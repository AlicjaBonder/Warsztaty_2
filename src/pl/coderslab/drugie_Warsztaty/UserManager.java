package pl.coderslab.drugie_Warsztaty;

import java.sql.SQLException;
import java.util.Scanner;

public class UserManager {
public static void main(String[] args) {
		
				
		Scanner scan = new Scanner(System.in);
		String option = "";
		
		while(true){
						
			User.printAllUsers();
			
			System.out.println("\nWybierz jedną z opcji:\n"
					+ "add - dodanie użytkownika\n"
					+ "edit - edycja użytkownika\n"
					+ "delete - usunięcie użytkownika\n"
					+ "load - wyświetlenie wszystkich użytkowników danej grupy\n"
					+ "quit - zakończenie programu");
			option = scan.nextLine();
			
			if(option.equals("add")) {
				add();
			}else if(option.equals("edit")) {
				edit();
			}else if(option.equals("delete")) {
				delete();
			}else if(option.equals("load")) {
				loadUserByGroup();
			}else if (option.equals("quit")){
				System.out.println("Wyszedłeś z programu. Zapraszamy ponownie.");
				System.exit(0);
			}else {
				System.out.println("Nie wybrałeś poprawnej opcji. Spróbuj ponownie.");
			}
			
		
		}		

	}
	
	public static void add() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Podaj nazwę użytkownika:");
		String userName = scan.nextLine();
		System.out.println("Podaj email użytkownika:");
		String mail = scan.nextLine();

		System.out.println("Podaj hasło użytkownika:");
		String pass = scan.nextLine();
		
		int groupId = User.getInt("Podaj id grupy użytkownika:");
		
		User user = new User(userName, mail, pass, groupId);
		try {
			user.saveToDB();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	
	}
	
	public static void edit() {
	
		Scanner scan = new Scanner(System.in);

		int id =  User.getInt("Podaj Id użytkownika do edycji: ");              
		
		System.out.println("Podaj nową nazwę użytkownika:");
		String userName = scan.nextLine();
		
		System.out.println("Podaj nowy email użytkownika:");
		String mail = scan.nextLine();

		int groupId = User.getInt("Podaj Id nowej grupy użytkownika: ");  
		
		System.out.println("Podaj nowe hasło użytkownika:");
		String pass = scan.nextLine();

		User user = new User(userName, mail, pass, groupId );
		user.setId(id);
		
			try {
				user.saveToDB();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
	public static void delete() {
		
		int id = User.getInt("Podaj Id użytkownika, którego chcesz usunąć:");    
		
		
			try {
				User.delete(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
	}
	
	public static void loadUserByGroup() {
		
		
		Group.printAllUsers();
		int groupId = User.getInt("Podaj Id grupy, aby wyświetlić jej członków: ");
		System.out.println("Członkwie grupy numer "+ groupId);
		User.printAllUsersByGroupId(groupId);
		
		
	}
}
