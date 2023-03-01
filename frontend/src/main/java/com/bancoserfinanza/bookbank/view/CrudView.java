package com.bancoserfinanza.bookbank.view;

import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class CrudView<T> implements Serializable {

    private List<T> items;

    private T selectedItem;

    private List<T> selectedItems;

    public List<T> getItems() {
        return items;
    }

    public T getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(T selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<T> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<T> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public void openNew() {
        //this.selectedItem = new ItemResponse();
    }

    public void saveItem() {
        //if (this.selectedItem.getId() == null) {
        if (this.selectedItem == null) {
            this.items.add(this.selectedItem);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Added"));
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageItemDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-items");
    }

    public void deleteItem() {
        this.items.remove(this.selectedItem);
        this.selectedItems.remove(this.selectedItem);
        this.selectedItem = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-items");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedItems()) {
            int size = this.selectedItems.size();
            return size > 1 ? size + " items selected" : "1 item selected";
        }

        return "Delete";
    }

    public boolean hasSelectedItems() {
        return this.selectedItems != null && !this.selectedItems.isEmpty();
    }

    public void deleteSelectedItems() {
        this.items.removeAll(this.selectedItems);
        this.selectedItems = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Items Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-items");
        PrimeFaces.current().executeScript("PF('dtItems').clearFilters()");
    }

}