import java.util.List;
import java.util.Scanner;

import controller.ListBookHelper;
import model.ListBook;

public class StartProgram {

	static Scanner in = new Scanner(System.in);
	static ListBookHelper lbh = new ListBookHelper();

	private static void addABook() {

		System.out.print("Enter the name of your book: ");
		String title = in.nextLine();
		System.out.print("Enther the name of the author: ");
		String author = in.nextLine();
		System.out.print("Enter the genre: ");
		String genre = in.nextLine();
		ListBook toAdd = new ListBook(title, author, genre);
		lbh.insertBook(toAdd);

	}

	private static void deleteABook() {
		// TODO Auto-generated method stub
		System.out.print("Enter the book to delete: ");
		String title = in.nextLine();
		/*System.out.println("Enter the author to delete"); //commented out to avoid typing author and genre to delete for now
		String author = in.nextLine();
		System.out.print("Enter the genre to delete: ");
		String genre = in.nextLine();*/
		ListBook toDelete = new ListBook(title);
		lbh.deleteBook(toDelete);

	}

	private static void editABook() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by title");
		System.out.println("2 : Search by author");
		System.out.println("3 : Search by genre");
		int searchBy = in.nextInt();
		in.nextLine();
		List<ListBook> foundBook;
		if (searchBy == 1) {
			System.out.print("Enter the book name: ");
			String bookName = in.nextLine();
			foundBook = lbh.searchForBookByTitle(bookName);

		} else if(searchBy ==2){
			System.out.print("Enter the author: ");
			String authorName = in.nextLine();
			foundBook = lbh.searchForBookByAuthor(authorName);

		} else {
			System.out.print("Enter the genre: ");
			String genreName = in.nextLine();
			foundBook = lbh.searchForBookByGenre(genreName);
		}

		if (!foundBook.isEmpty()) {
			System.out.println("Found Results.");
			for (ListBook l : foundBook) {
				System.out.println("ID #: "+l.getId() + " - " + l.getTitle());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			ListBook toEdit = lbh.searchForBookById(idToEdit);
			System.out.println("Retrieved " + toEdit.getTitle() + " in the " + toEdit.getGenre() + " genre");
			System.out.println("1 : Update Title");
			System.out.println("2 : Update Author");
			System.out.println("3 : Update Genre");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Title: ");
				String newTitle = in.nextLine();
				toEdit.setTitle(newTitle);
			} else if (update == 2) {
				System.out.print("New Author: ");
				String newAuthor = in.nextLine();
				toEdit.setAuthor(newAuthor);
			} else {
				System.out.print("New Genre: ");
				String newGenre = in.nextLine();
				toEdit.setGenre(newGenre);
			}

			lbh.updateBook(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();

	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to Neil's book list! ---");
		while (goAgain) {
			System.out.println("*  Select an item:");
			System.out.println("*  1 -- Add a Book");
			System.out.println("*  2 -- Edit a Book");
			System.out.println("*  3 -- Delete a Book");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit the Reading List program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addABook();
			} else if (selection == 2) {
				editABook();
			} else if (selection == 3) {
				deleteABook();
			} else if (selection == 4) {
				viewTheList();
			} else {
				lbh.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}

		}

	}

	private static void viewTheList() {
		List<ListBook> allBooks = lbh.showAllBooks();
		for (ListBook singleBook : allBooks) {
			System.out.println(singleBook.returnBookDetails());
		}

	}

}
