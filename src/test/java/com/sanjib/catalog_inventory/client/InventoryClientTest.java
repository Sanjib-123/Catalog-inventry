package com.sanjib.catalog_inventory.client;

import com.sanjib.catalog_inventory.dto.Item;
import com.sanjib.catalog_inventory.dto.ItemRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.graphql.client.GraphQlClient.RequestSpec;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class InventoryClientTest {

    @Mock
    private HttpGraphQlClient graphQlClient;

    @InjectMocks
    private InventoryClient inventoryClient;

    @Test
    void testViewProducts() {
        // Mock the HttpGraphQlClient and its behavior
        RequestSpec requestSpec = mock(RequestSpec.class);
        when(graphQlClient.document(any(String.class))).thenReturn(requestSpec);

        // Mock the 'retrieve' and 'toEntityList' behavior
        when(requestSpec.retrieve("getProducts")).thenReturn((GraphQlClient.RetrieveSpec) requestSpec);
        when(((GraphQlClient.RetrieveSpec) requestSpec).toEntityList(Item.class)).thenReturn(Mono.just(List.of(new Item("Item1", "Category1", 100.0f, 10))));

        // Call the method
        List<Item> items = inventoryClient.viewProducts();

        // Verify the behavior and the results
        verify(graphQlClient).document(any(String.class)); // Ensure the document method was called
        verify(requestSpec).retrieve("getProducts"); // Ensure retrieve was called
        assert items.size() == 1; // Assert that the list has one item
    }

    @Test
    void testViewProductsByCategory() {
        // Mock the HttpGraphQlClient and its behavior
        RequestSpec requestSpec = mock(RequestSpec.class);
        when(graphQlClient.document(any(String.class))).thenReturn(requestSpec);

        // Mock the 'retrieve' and 'toEntityList' behavior
        when(requestSpec.retrieve("getProductsByCategory")).thenReturn((GraphQlClient.RetrieveSpec) requestSpec);
        when(((GraphQlClient.RetrieveSpec) requestSpec).toEntityList(Item.class)).thenReturn(Mono.just(List.of(new Item("Item1", "Category1", 100.0f, 10))));

        // Call the method with a category
        List<Item> items = inventoryClient.viewProductsByCategory("Category1");

        // Verify the behavior and the results
        verify(graphQlClient).document(any(String.class)); // Ensure the document method was called
        verify(requestSpec).retrieve("getProductsByCategory"); // Ensure retrieve was called
        assert items.size() == 1; // Assert that the list has one item
    }

    @Test
    void testReceiveNewShipment() {
        // Mock the HttpGraphQlClient and its behavior
        RequestSpec requestSpec = mock(RequestSpec.class);
        when(graphQlClient.document(any(String.class))).thenReturn(requestSpec);

        // Mock the 'retrieve' and 'toEntity' behavior
        when(requestSpec.retrieve("receiveNewShipment")).thenReturn((GraphQlClient.RetrieveSpec) requestSpec);
        when(((GraphQlClient.RetrieveSpec) requestSpec).toEntity(Item.class)).thenReturn(Mono.just(new Item("Item1", "Category1", 100.0f, 20)));

        // Call the method
        Item item = inventoryClient.receiveNewShipment(new ItemRequestDTO(1, 10));

        // Verify the behavior and the results
        verify(graphQlClient).document(any(String.class)); // Ensure the document method was called
        verify(requestSpec).retrieve("receiveNewShipment"); // Ensure retrieve was called
        assert item != null; // Assert that the returned item is not null
        assert item.getName().equals("Item1"); // Assert item name
    }
}
