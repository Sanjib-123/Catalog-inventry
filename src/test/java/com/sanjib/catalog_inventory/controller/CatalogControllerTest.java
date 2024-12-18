package com.sanjib.catalog_inventory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanjib.catalog_inventory.dto.Item;
import com.sanjib.catalog_inventory.dto.ItemRequestDTO;
import com.sanjib.catalog_inventory.service.CatalogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CatalogController.class)
class CatalogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CatalogService catalogService;

    @InjectMocks
    private CatalogController catalogController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // Initializes the mocks
    }

    @Test
    void testViewProducts() throws Exception {
        // Given
        Item mockItem = new Item("Phone", "Electronics", 699.99f, 50);
        List<Item> mockItems = List.of(mockItem);
        when(catalogService.viewProducts()).thenReturn(mockItems);

        // When & Then
        mockMvc.perform(get("/catalog/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Phone"))
                .andExpect(jsonPath("$[0].category").value("Electronics"));
    }

    @Test
    void testViewProductsByCategory() throws Exception {
        // Given
        Item mockItem = new Item("Phone", "Electronics", 699.99f, 50);
        List<Item> mockItems = List.of(mockItem);
        when(catalogService.viewProductsByCategory("Electronics")).thenReturn(mockItems);

        // When & Then
        mockMvc.perform(get("/catalog/products/category")
                        .param("category", "Electronics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Phone"))
                .andExpect(jsonPath("$[0].category").value("Electronics"));
    }

    @Test
    void testReceiveNewShipment() throws Exception {
        // Given
        ItemRequestDTO requestDTO = new ItemRequestDTO(1, 50);
        Item mockItem = new Item("Phone", "Electronics", 699.99f, 100);
        when(catalogService.receiveNewShipment(requestDTO)).thenReturn(mockItem);

        // When & Then
        mockMvc.perform(post("/catalog/shipment")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Phone"))
                .andExpect(jsonPath("$.stock").value(100));
    }

    @Test
    void testViewProducts_EmptyList() throws Exception {
        // Given
        List<Item> mockItems = List.of();
        when(catalogService.viewProducts()).thenReturn(mockItems);

        // When & Then
        mockMvc.perform(get("/catalog/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testViewProductsByCategory_NoProducts() throws Exception {
        // Given
        List<Item> mockItems = List.of();
        when(catalogService.viewProductsByCategory("Electronics")).thenReturn(mockItems);

        // When & Then
        mockMvc.perform(get("/catalog/products/category")
                        .param("category", "Electronics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testReceiveNewShipment_InvalidRequest() throws Exception {
        // Given
        ItemRequestDTO invalidRequestDTO = new ItemRequestDTO(-1, -50);
        when(catalogService.receiveNewShipment(invalidRequestDTO)).thenReturn(null);

        // When & Then
        mockMvc.perform(post("/catalog/shipment")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(invalidRequestDTO)))
                .andExpect(status().isBadRequest());
    }
}
