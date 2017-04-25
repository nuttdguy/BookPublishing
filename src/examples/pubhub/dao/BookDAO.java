package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.view.BookTagView;

/**
 * Interface for our Data Access Object to handle database queries related to Books.
 */
public interface BookDAO {

	public List<Book> getAllBooks();
	public List<BookTagView> getAllBooksWithTag();
	
	public List<Book> getBooksByTitle(String title);
	public List<Book> getBooksByAuthor(String author);
	public List<Book> getBooksLessThanPrice(double price);
	public Book getBookByISBN(String isbn);
	
	public boolean addBook(Book book);
	public boolean updateBook(Book book);
	public boolean deleteBookByISBN(String isbn);
}
