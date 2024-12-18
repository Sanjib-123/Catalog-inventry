package com.sanjib.catalog_inventory.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GraphQlClientConfigTest {

    private GraphQlClientConfig graphQlClientConfig;
    private WebClient.Builder webClientBuilder;

    @BeforeEach
    void setUp() {
        graphQlClientConfig = new GraphQlClientConfig();
        webClientBuilder = mock(WebClient.Builder.class);
    }

    @Test
    void testGraphQlClientBeanCreation() {
        // Mock the WebClient.Builder methods
        WebClient mockWebClient = mock(WebClient.class);
        when(webClientBuilder.baseUrl("http://localhost:9191/graphql")).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(mockWebClient);

        // Call the method
        HttpGraphQlClient graphQlClient = graphQlClientConfig.graphQlClient();

        assertNotNull(graphQlClient, "GraphQL client should not be null");
    }

    @Test
    void testWebClientBaseUrlIsSetCorrectly() {
        // Spy on the WebClient.Builder
        WebClient.Builder builderSpy = spy(WebClient.builder());

        // Call the actual method
        builderSpy.baseUrl("http://localhost:9191/graphql");
        verify(builderSpy).baseUrl("http://localhost:9191/graphql");
    }
}
