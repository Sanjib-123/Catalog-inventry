package com.sanjib.catalog_inventory.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ItemTest {

    private Item item;

    @BeforeEach
    void setUp() {
        item = new Item("Laptop", "Electronics", 599.99f, 50);
    }

    @Test
    void testAllArgsConstructor() {
        Item newItem = new Item("Mobile", "Electronics", 299.99f, 100);

        assertThat(newItem.getName()).isEqualTo("Mobile");
        assertThat(newItem.getCategory()).isEqualTo("Electronics");
        assertThat(newItem.getPrice()).isEqualTo(299.99f);
        assertThat(newItem.getStock()).isEqualTo(100);
    }

    @Test
    void testNoArgsConstructor() {
        Item emptyItem = new Item();

        assertThat(emptyItem.getName()).isNull();
        assertThat(emptyItem.getCategory()).isNull();
        assertThat(emptyItem.getPrice()).isNull();
        assertThat(emptyItem.getStock()).isNull();
    }

    @Test
    void testGetters() {
        assertThat(item.getName()).isEqualTo("Laptop");
        assertThat(item.getCategory()).isEqualTo("Electronics");
        assertThat(item.getPrice()).isEqualTo(599.99f);
        assertThat(item.getStock()).isEqualTo(50);
    }

    @Test
    void testSetters() {
        item.setName("Tablet");
        item.setCategory("Gadgets");
        item.setPrice(199.99f);
        item.setStock(30);

        assertThat(item.getName()).isEqualTo("Tablet");
        assertThat(item.getCategory()).isEqualTo("Gadgets");
        assertThat(item.getPrice()).isEqualTo(199.99f);
        assertThat(item.getStock()).isEqualTo(30);
    }

    @Test
    void testToString() {
        String expectedToString = "Item{name='Laptop', category='Electronics', price=599.99, stock=50}";
        assertThat(item.toString()).isEqualTo(expectedToString);
    }

    @Test
    void testPartialUpdateUsingSetters() {
        item.setPrice(499.99f);
        item.setStock(20);

        assertThat(item.getName()).isEqualTo("Laptop");
        assertThat(item.getCategory()).isEqualTo("Electronics");
        assertThat(item.getPrice()).isEqualTo(499.99f);
        assertThat(item.getStock()).isEqualTo(20);
    }

    @Test
    void testEqualityOfTwoItems() {
        Item item1 = new Item("Laptop", "Electronics", 599.99f, 50);
        Item item2 = new Item("Laptop", "Electronics", 599.99f, 50);

        assertThat(item1).usingRecursiveComparison().isEqualTo(item2);
    }

    @Test
    void testInequalityOfTwoItems() {
        Item item1 = new Item("Laptop", "Electronics", 599.99f, 50);
        Item item2 = new Item("Phone", "Electronics", 299.99f, 100);

        assertThat(item1).isNotEqualTo(item2);
    }

    /**
     * 🔥 Use Mockito to create a mock Item object.
     */
    @Test
    void testMockingWithMockito() {
        Item mockItem = Mockito.mock(Item.class);
        when(mockItem.getName()).thenReturn("Mocked Item");
        when(mockItem.getCategory()).thenReturn("Mocked Category");
        when(mockItem.getPrice()).thenReturn(100.99f);
        when(mockItem.getStock()).thenReturn(10);

        // Assertions
        assertThat(mockItem.getName()).isEqualTo("Mocked Item");
        assertThat(mockItem.getCategory()).isEqualTo("Mocked Category");
        assertThat(mockItem.getPrice()).isEqualTo(100.99f);
        assertThat(mockItem.getStock()).isEqualTo(10);

        // Verify that the mock methods were called
        verify(mockItem, times(1)).getName();
        verify(mockItem, times(1)).getCategory();
        verify(mockItem, times(1)).getPrice();
        verify(mockItem, times(1)).getStock();
    }

    /**
     * 🔥 Use Mockito to spy on a real Item object.
     */
    @Test
    void testSpyingWithMockito() {
        Item spyItem = spy(new Item("TV", "Appliances", 399.99f, 10));

        // Call real methods
        assertThat(spyItem.getName()).isEqualTo("TV");
        assertThat(spyItem.getCategory()).isEqualTo("Appliances");

        // Stubbing behavior on specific methods
        doReturn("Spy Name").when(spyItem).getName();
        doReturn("Spy Category").when(spyItem).getCategory();

        // Call stubbed methods
        assertThat(spyItem.getName()).isEqualTo("Spy Name");
        assertThat(spyItem.getCategory()).isEqualTo("Spy Category");

        // Verify interactions
        verify(spyItem, times(2)).getName();
        verify(spyItem, times(2)).getCategory();
    }
}
