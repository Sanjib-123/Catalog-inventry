package com.sanjib.catalog_inventory.service;

import com.sanjib.catalog_inventory.client.InventoryClient;
import com.sanjib.catalog_inventory.dto.Item;
import com.sanjib.catalog_inventory.dto.ItemRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    private final InventoryClient inventoryClient;

    public CatalogService(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    public List<Item> viewProducts(){
        return inventoryClient.viewProducts();
    }

    public List<Item> viewProductsByCategory(String category){
        List<Item> items = inventoryClient.viewProductsByCategory(category);
        if (items.isEmpty()) {
            System.out.println("No products found for category: " + category);
        }
        return items;
        //return inventoryClient.viewProductsByCategory(category);
    }

    public Item receiveNewShipment(ItemRequestDTO itemRequestDTO){
        return inventoryClient.receiveNewShipment(itemRequestDTO);
    }
}
