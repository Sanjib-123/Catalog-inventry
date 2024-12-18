package com.sanjib.catalog_inventory.dto;

public class ItemRequestDTO {

    private int id;
    private int qty;

    public ItemRequestDTO(int id, int qty) {
        this.id = id;
        this.qty = qty;
    }

    public ItemRequestDTO() {
    }

    public int getId() {
        return id;
    }

    public int getQty() {
        return qty;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "ItemRequestDTO{" +
               "id=" + id +
               ", qty=" + qty +
               '}';
    }
}
