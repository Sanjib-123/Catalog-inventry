package com.sanjib.catalog_inventory.service;

import com.sanjib.catalog_inventory.client.InventoryClient;
import com.sanjib.catalog_inventory.dto.Item;
import com.sanjib.catalog_inventory.dto.ItemRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CatalogServiceTest {

    @Mock
    private InventoryClient inventoryClient;

    @InjectMocks
    private CatalogService catalogService;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testViewProducts() {
        // Given
        List<Item> mockItems = List.of(
                new Item("Phone", "Electronics", 699.99f, 50),
                new Item("Laptop", "Electronics", 999.99f, 30)
        );

        // When
        when(inventoryClient.viewProducts()).thenReturn(mockItems);

        // Act
        List<Item> result = catalogService.viewProducts();

        // Then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("Phone");
        verify(inventoryClient, times(1)).viewProducts();
    }

    @Test
    void testViewProductsByCategory_withProducts() {
        // Given
        String category = "Electronics";
        List<Item> mockItems = List.of(
                new Item("Phone", "Electronics", 699.99f, 50),
                new Item("Laptop", "Electronics", 999.99f, 30)
        );

        // When
        when(inventoryClient.viewProductsByCategory(category)).thenReturn(mockItems);

        // Act
        List<Item> result = catalogService.viewProductsByCategory(category);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getCategory()).isEqualTo(category);
        verify(inventoryClient, times(1)).viewProductsByCategory(category);
    }

    @Test
    void testViewProductsByCategory_noProductsFound() {
        // Given
        String category = "Toys";
        List<Item> emptyList = new ArrayList<>();

        // When
        when(inventoryClient.viewProductsByCategory(category)).thenReturn(emptyList);

        // Act
        List<Item> result = catalogService.viewProductsByCategory(category);

        // Then
        assertThat(result).isEmpty();
        verify(inventoryClient, times(1)).viewProductsByCategory(category);
    }

    @Test
    void testReceiveNewShipment() {
        // Given
        ItemRequestDTO itemRequestDTO = new ItemRequestDTO(101, 50);
        Item mockItem = new Item("Tablet", "Electronics", 399.99f, 100);

        // When
        when(inventoryClient.receiveNewShipment(itemRequestDTO)).thenReturn(mockItem);

        // Act
        Item result = catalogService.receiveNewShipment(itemRequestDTO);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Tablet");
        assertThat(result.getCategory()).isEqualTo("Electronics");
        assertThat(result.getPrice()).isEqualTo(399.99f);
        assertThat(result.getStock()).isEqualTo(100);
        verify(inventoryClient, times(1)).receiveNewShipment(itemRequestDTO);
    }

}
