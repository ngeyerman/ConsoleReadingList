package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListBook;

public class ListBookHelper {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ConsoleReadingList");

	public void insertBook(ListBook li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}

	public List<ListBook> showAllBooks() {
		EntityManager em = emfactory.createEntityManager();
		List<ListBook> allBooks = em.createQuery("SELECT i FROM ListBook i").getResultList();
		return allBooks;

	}

	public void deleteBook(ListBook toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListBook> typedQuery = em.createQuery(
				"select li from ListBook li where li.title = :selectedTitle", // and li.genre=:selectedGenre", commented out to match deleteBook method
				ListBook.class);

		// sub parameter with actual data from the toDelete book
		typedQuery.setParameter("selectedTitle", toDelete.getTitle());
		//typedQuery.setParameter("selectedGenre", toDelete.getGenre());

		// set option for only 1 result
		typedQuery.setMaxResults(1);

		// get the result and save into a new list item
		ListBook result = typedQuery.getSingleResult();

		// remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public ListBook searchForBookById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListBook found = em.find(ListBook.class, idToEdit);
		em.close();
		return found;
	}

	public void updateBook(ListBook toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();

		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();

	}

	public List<ListBook> searchForBookByTitle(String bookName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListBook> typedQuery = em.createQuery("select li from ListBook li where li.title = :selectedTitle",
				ListBook.class);
		typedQuery.setParameter("selectedTitle", bookName);

		List<ListBook> foundBook = typedQuery.getResultList();
		em.close();
		return foundBook;
	}

	public List<ListBook> searchForBookByGenre(String genreName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListBook> typedQuery = em.createQuery("select li from ListBook li where li.genre = :selectedGenre",
				ListBook.class);
		typedQuery.setParameter("selectedGenre", genreName);

		List<ListBook> foundBook = typedQuery.getResultList();
		em.close();
		return foundBook;
	}

	public void cleanUp() {
		emfactory.close();
	}

	public List<ListBook> searchForBookByAuthor(String authorName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListBook> typedQuery = em.createQuery("select li from ListBook li where li.author = :selectedAuthor",
				ListBook.class);
		typedQuery.setParameter("selectedAuthor", authorName);

		List<ListBook> foundBook = typedQuery.getResultList();
		em.close();
		return foundBook;
	}

}