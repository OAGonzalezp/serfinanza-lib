package com.bancoserfinanza.bookbank.view;

import com.bancoserfinanza.bookbank.controller.BookService;
import com.bancoserfinanza.bookbank.controller.CustomerService;
import com.bancoserfinanza.bookbank.controller.LoanBookService;
import com.bancoserfinanza.models.bookbank.Book;
import com.bancoserfinanza.models.bookbank.Customer;
import com.bancoserfinanza.models.request.BookRequest;
import com.bancoserfinanza.models.request.CustomerRequest;
import com.bancoserfinanza.models.request.LoanBookRequest;
import com.bancoserfinanza.models.response.BookResponse;
import com.bancoserfinanza.models.response.CustomerResponse;
import com.bancoserfinanza.models.response.LoanBookResponse;
import com.bancoserfinanza.models.response.LoanBookResponseData;
import lombok.Getter;
import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class CrudLoanBookView implements Serializable {

    private List<LoanBookResponse> loanBooks;
    private LoanBookResponse selectedLoanBook;
    private List<LoanBookResponse> selectedLoanBooks;
    private LoanBookService service = new LoanBookService();
    private CustomerService customerService = new CustomerService();
    private BookService bookService = new BookService();

    @Getter
    private String urlCustomer = "http://localhost:8080/v0/customer/autocomplete";
    @Getter
    private String urlBook = "http://localhost:8080/v0/book/autocomplete";

    @PostConstruct
    public void postConstruct() {
        loadData();
    }

    public void loadData() {
        this.loanBooks = service.loadLoanBooks();
    }

    public void setSelectedLoanBook(LoanBookResponse selectedLoanBook) {
        this.selectedLoanBook = selectedLoanBook;
    }

    public void setSelectedLoanBooks(List<LoanBookResponse> selectedLoanBooks) {
        this.selectedLoanBooks = selectedLoanBooks;
    }

    public LoanBookResponse openNew() {
        this.selectedLoanBook = new LoanBookResponse();
        return this.selectedLoanBook;
    }

    public void saveLoanBook() {
        boolean save = true;

        LoanBookRequest request = new LoanBookRequest();
        request.setId(this.selectedLoanBook.getId());
        request.setBook(BookRequest.build(this.selectedLoanBook.getBook()));
        request.setCustomer(CustomerRequest.build(this.selectedLoanBook.getCustomer()));
        request.setLoanDate(this.selectedLoanBook.getLoanDate());
        request.setDeliveryDate(this.selectedLoanBook.getDeliveryDate());

        if (request.getId() != null ) {
            save = false;
        }

        LoanBookResponseData response = save ? service.saveLoanBook(request) : service.updateLoanBook(request);

        if (response.getCode() == 1) {
            this.loanBooks = service.loadLoanBooks();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Prestamo actualizado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un error!", response.getMessage()));
        }

        PrimeFaces.current().executeScript("PF('manageLoanBookDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-loanBooks");
    }

    public void deleteLoanBook() {
        this.loanBooks.remove(this.selectedLoanBook);
        this.selectedLoanBooks.remove(this.selectedLoanBook);
        this.selectedLoanBook = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("LoanBook Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-loanBooks");
    }

    public void deliveryBook(LoanBookResponse loanBookResponse) {
        service.updateLoanBook(LoanBookRequest.buildRequest(loanBookResponse));
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedLoanBooks()) {
            int size = this.selectedLoanBooks.size();
            return size > 1 ? size + " loanBooks selected" : "1 loanBook selected";
        }

        return "Delete";
    }

    public boolean hasSelectedLoanBooks() {
        return this.selectedLoanBooks != null && !this.selectedLoanBooks.isEmpty();
    }

    public void deleteSelectedLoanBooks() {
        this.loanBooks.removeAll(this.selectedLoanBooks);
        this.selectedLoanBooks = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("LoanBooks Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-loanBooks");
        PrimeFaces.current().executeScript("PF('dtLoanBooks').clearFilters()");
    }

    public List<LoanBookResponse> getLoanBooks() {
        return loanBooks;
    }

    public void setLoanBooks(List<LoanBookResponse> loanBooks) {
        this.loanBooks = loanBooks;
    }

    public LoanBookResponse getSelectedLoanBook() {
        return selectedLoanBook;
    }

    public List<LoanBookResponse> getSelectedLoanBooks() {
        return selectedLoanBooks;
    }
}