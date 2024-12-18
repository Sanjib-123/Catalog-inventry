package com.sanjib.catalog_inventory.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ItemRequestDTOTest {

    private ItemRequestDTO itemRequestDTO;

    @BeforeEach
    void setUp() {
        itemRequestDTO = new ItemRequestDTO(101, 25);
    }

    @Test
    void testAllArgsConstructor() {
        // Test all-args constructor
        ItemRequestDTO newItemRequestDTO = new ItemRequestDTO(202, 50);

        assertThat(newItemRequestDTO.getId()).isEqualTo(202);
        assertThat(newItemRequestDTO.getQty()).isEqualTo(50);
    }

    @Test
    void testNoArgsConstructor() {
        // Test no-args constructor
        ItemRequestDTO emptyItemRequestDTO = new ItemRequestDTO();

        assertThat(emptyItemRequestDTO.getId()).isEqualTo(0); // Default int is 0
        assertThat(emptyItemRequestDTO.getQty()).isEqualTo(0); // Default int is 0
    }

    @Test
    void testGetters() {
        // Test getters for ID and QTY
        assertThat(itemRequestDTO.getId()).isEqualTo(101);
        assertThat(itemRequestDTO.getQty()).isEqualTo(25);
    }

    @Test
    void testSetters() {
        // Set new values using setters and test
        itemRequestDTO.setId(999);
        itemRequestDTO.setQty(55);

        assertThat(itemRequestDTO.getId()).isEqualTo(999);
        assertThat(itemRequestDTO.getQty()).isEqualTo(55);
    }

    @Test
    void testToString() {
        // Test the toString() method
        String expectedToString = "ItemRequestDTO{id=101, qty=25}";
        assertThat(itemRequestDTO.toString()).isEqualTo(expectedToString);
    }

    /**
     * 🔥 Use Mockito to create a mock of ItemRequestDTO.
     */
    @Test
    void testMockingWithMockito() {
        // Create a mock object of ItemRequestDTO
        ItemRequestDTO mockItemRequestDTO = Mockito.mock(ItemRequestDTO.class);

        // Define mock behavior for the getter methods
        when(mockItemRequestDTO.getId()).thenReturn(200);
        when(mockItemRequestDTO.getQty()).thenReturn(30);

        // Call methods on the mock object
        assertThat(mockItemRequestDTO.getId()).isEqualTo(200);
        assertThat(mockItemRequestDTO.getQty()).isEqualTo(30);

        // Verify that the mocked methods were called
        verify(mockItemRequestDTO, times(1)).getId();
        verify(mockItemRequestDTO, times(1)).getQty();
    }

    /**
     * 🔥 Use Mockito to spy on a real ItemRequestDTO object.
     */
    @Test
    void testSpyingWithMockito() {
        // Spy on a real object
        ItemRequestDTO spyItemRequestDTO = spy(new ItemRequestDTO(100, 40));

        // Call real methods on the spy object
        assertThat(spyItemRequestDTO.getId()).isEqualTo(100);
        assertThat(spyItemRequestDTO.getQty()).isEqualTo(40);

        // Stub specific methods on the spy object
        doReturn(888).when(spyItemRequestDTO).getId();
        doReturn(77).when(spyItemRequestDTO).getQty();

        // Call the stubbed methods
        assertThat(spyItemRequestDTO.getId()).isEqualTo(888);
        assertThat(spyItemRequestDTO.getQty()).isEqualTo(77);

        // Verify interactions with the spy object
        verify(spyItemRequestDTO, times(2)).getId();
        verify(spyItemRequestDTO, times(2)).getQty();
    }

    @Test
    void testSetterWithMockito() {
        // Spy on a real object
        ItemRequestDTO spyItemRequestDTO = spy(new ItemRequestDTO());

        // Set values using setter methods
        spyItemRequestDTO.setId(555);
        spyItemRequestDTO.setQty(123);

        // Verify that the setter methods were called
        verify(spyItemRequestDTO, times(1)).setId(555);
        verify(spyItemRequestDTO, times(1)).setQty(123);

        // Assert that the getter returns the set value
        assertThat(spyItemRequestDTO.getId()).isEqualTo(555);
        assertThat(spyItemRequestDTO.getQty()).isEqualTo(123);
    }

}
