package examples.pubhub.service;

import java.util.List;

import examples.pubhub.model.view.BookTagView;

public interface BookTagViewService {

	// Retrieve Single Results
	public BookTagView getViewBookTagByTitle(String title);
	public BookTagView getViewBookTagByISBN(String isbn);
	
	// Retrieve List of Results
	public List<BookTagView> getAllViewBookTagByISBN(String isbn);
	public List<BookTagView> getAllViewBookTagByTitle(String title);
	
	// Add Record
	public boolean addViewBookTag(BookTagView viewBookTag);
	
	// Update Record
	public boolean updateViewBookTag(BookTagView viewBookTag);
	
	// Delete Record
public boolean deleteBookTagByTagName(String tagName);														
	
}
