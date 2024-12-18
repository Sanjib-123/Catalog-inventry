package com.sanjib.catalog_inventory.controller;

import com.sanjib.catalog_inventory.dto.Item;
import com.sanjib.catalog_inventory.dto.ItemRequestDTO;
import com.sanjib.catalog_inventory.service.CatalogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/products")
    public List<Item> viewProducts(){
        return catalogService.viewProducts();
    }

    @GetMapping("/products/category")
    public List<Item> viewProductsByCategory(@RequestParam String category){
        System.out.println("Category received "+ category);
        return catalogService.viewProductsByCategory(category);

    }

    @PostMapping("/shipment")
    public Item receiveNewShipment(@RequestBody ItemRequestDTO itemRequestDTO){
        return catalogService.receiveNewShipment(itemRequestDTO);
    }
}
