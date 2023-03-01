package com.bancoserfinanza.bookbank.view;

import com.bancoserfinanza.bookbank.controller.BookService;
import com.bancoserfinanza.models.request.BookRequest;
import com.bancoserfinanza.models.response.BookResponse;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class CrudBookView implements Serializable {

    private List<BookResponse> books;
    private BookResponse selectedBook;
    private List<BookResponse> selectedBooks;

    private BookService service = new BookService();

    @PostConstruct
    public void postConstruct() {
        this.books = service.loadBooks();
    }

    public void setSelectedBook(BookResponse selectedBook) {
        this.selectedBook = selectedBook;
    }

    public void setSelectedBooks(List<BookResponse> selectedBooks) {
        this.selectedBooks = selectedBooks;
    }

    public BookResponse openNew() {
        this.selectedBook = new BookResponse();
        return this.selectedBook;
    }

    public void saveBook() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String bookName = ec.getRequestParameterMap().get("dialogs:bookName");
        String idlib = ec.getRequestParameterMap().get("dialogs:idlib");
        String id = ec.getRequestParameterMap().get("dialogs:bookId");

        BookRequest request = new BookRequest();

        if (id != null) {
            request.setId(Long.parseLong(id));
        }

        request.setIdlib(idlib);
        request.setBookName(bookName);

        BookResponse response = request.getId() != null ? service.updateBook(request) : service.updateBook(request);

        if (response != null ) {
            this.books = service.loadBooks();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Libro actualizado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un error!", ""));
        }

        PrimeFaces.current().executeScript("PF('manageBookDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-books");
    }

    public void deleteBook() {
        this.books.remove(this.selectedBook);
        this.selectedBooks.remove(this.selectedBook);
        this.selectedBook = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Book Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-books");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedBooks()) {
            int size = this.selectedBooks.size();
            return size > 1 ? size + " books selected" : "1 book selected";
        }

        return "Delete";
    }

    public boolean hasSelectedBooks() {
        return this.selectedBooks != null && !this.selectedBooks.isEmpty();
    }

    public void deleteSelectedBooks() {
        this.books.removeAll(this.selectedBooks);
        this.selectedBooks = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Books Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-books");
        PrimeFaces.current().executeScript("PF('dtBooks').clearFilters()");
    }

    public List<BookResponse> getBooks() {
        return books;
    }

    public void setBooks(List<BookResponse> books) {
        this.books = books;
    }

    public BookResponse getSelectedBook() {
        return selectedBook;
    }

    public List<BookResponse> getSelectedBooks() {
        return selectedBooks;
    }
}