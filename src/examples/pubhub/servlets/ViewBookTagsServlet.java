package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookTagDAO;
import examples.pubhub.model.view.BookTagView;
import examples.pubhub.utilities.DAOUtilities;

@WebServlet("/BookTagView")
public class ViewBookTagsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String isbn = request.getParameter("isbn13");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		
		// Grab the list of Book Tags from the Database
		BookTagDAO dao = DAOUtilities.getBookTagsDAO();
		
		// List<BookTagView> tag = dao.getAllBookTags();		
//		BookTagView tag = dao.getViewBookTagByISBN(isbn);
		List<BookTagView> tagList = dao.getViewBookTagByTitle(title);
		BookTagView singleTag = new BookTagView();
		
		if (tagList.size() > 0) {
			singleTag = tagList.get(0);
		} else {
			singleTag.setAuthor(author);
			singleTag.setTitle(title);
			singleTag.setTagName("");
			singleTag.setIsbn13(isbn);
			tagList.add(singleTag);
		}
		
		// Populate the list into a variable that will be stored in the session =
		request.setAttribute("singleTag", singleTag);
		request.getSession().setAttribute("BookTagView", tagList);
		
		// Use dispatcher, get jsp view and forward request/response to client
		request.getRequestDispatcher("viewBookTags.jsp").forward(request, response);
		
	}	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isSuccess = false;
		// String isbn13 = request.getParameter("isbn13");
		String tagName = request.getParameter("tagName");
		
		BookTagDAO dao = DAOUtilities.getBookTagsDAO();
		if (!tagName.isEmpty())
			isSuccess = dao.deleteBookTagByTagName(tagName);
		
		if (isSuccess) {
			request.getSession().setAttribute("message", "Nook was successfully deleted");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("BookPublishing");
		} else {
			request.getSession().setAttribute("message", "There was a problem deleting the tag");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("bookPublishingHome.jsp").forward(request, response);
		}
		
	}

}
