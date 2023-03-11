package com.bancoserfinanza.bookbank.view;

import com.bancoserfinanza.bookbank.controller.CustomerService;
import com.bancoserfinanza.models.request.CustomerRequest;
import com.bancoserfinanza.models.response.CustomerResponse;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class CrudCustomerView implements Serializable {

    private List<CustomerResponse> customers;
    private CustomerResponse selectedCustomer;
    private List<CustomerResponse> selectedCustomers;

    private CustomerService service = new CustomerService();

    @PostConstruct
    public void postConstruct() {
        loadData();
    }

    public void loadData() {
        this.customers = service.loadCustomers();
    }

    public void setSelectedCustomer(CustomerResponse selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public void setSelectedCustomers(List<CustomerResponse> selectedCustomers) {
        this.selectedCustomers = selectedCustomers;
    }

    public CustomerResponse openNew() {
        this.selectedCustomer = new CustomerResponse();
        return this.selectedCustomer;
    }

    public void saveCustomer() {
        boolean save = true;

        CustomerRequest request = new CustomerRequest();
        request.setFirstName(this.selectedCustomer.getFirstName());
        request.setLastName(this.selectedCustomer.getLastName());
        request.setId(this.selectedCustomer.getId());
        request.setIdentification(this.selectedCustomer.getIdentification());
        request.setDateOfBirth(this.selectedCustomer.getDateOfBirth());

        if (request.getId() != null ) {
            save = false;
        }

        CustomerResponse response = save ? service.saveCustomer(request) : service.updateCustomer(request);

        if (response != null ) {
            this.customers = service.loadCustomers();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Libro actualizado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un error!", ""));
        }

        PrimeFaces.current().executeScript("PF('manageCustomerDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-customers");
    }

    public void deleteCustomer() {
        this.customers.remove(this.selectedCustomer);
        this.selectedCustomers.remove(this.selectedCustomer);
        this.selectedCustomer = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-customers");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedCustomers()) {
            int size = this.selectedCustomers.size();
            return size > 1 ? size + " customers selected" : "1 customer selected";
        }

        return "Delete";
    }

    public boolean hasSelectedCustomers() {
        return this.selectedCustomers != null && !this.selectedCustomers.isEmpty();
    }

    public void deleteSelectedCustomers() {
        this.customers.removeAll(this.selectedCustomers);
        this.selectedCustomers = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customers Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-customers");
        PrimeFaces.current().executeScript("PF('dtCustomers').clearFilters()");
    }

    public List<CustomerResponse> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerResponse> customers) {
        this.customers = customers;
    }

    public CustomerResponse getSelectedCustomer() {
        return selectedCustomer;
    }

    public List<CustomerResponse> getSelectedCustomers() {
        return selectedCustomers;
    }
}