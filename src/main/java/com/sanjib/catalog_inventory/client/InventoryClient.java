package com.sanjib.catalog_inventory.client;

import com.sanjib.catalog_inventory.dto.Item;
import com.sanjib.catalog_inventory.dto.ItemRequestDTO;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryClient {

    private final HttpGraphQlClient graphQlClient;

    public InventoryClient(HttpGraphQlClient graphQlClient) {
        this.graphQlClient = graphQlClient;
    }

    public List<Item> viewProducts(){
         String graphQLQuery = """
    query GetProducts {
        getProducts {
            name
            category
            price
        }
    }
""";
         return graphQlClient.document(graphQLQuery)
                 .retrieve("getProducts")
                 .toEntityList(Item.class).block();


    }

    public List<Item> viewProductsByCategory(String category) {
        String graphQLQuery = """
                    query GetProductsByCategory {
                        getProductsByCategory(category: "%s") {
                            name
                            category
                            price
                            stock
                        }
                    }
                """.formatted(category);
        System.out.println("GraphqlQuery:" + graphQLQuery);
        List<Item> items = graphQlClient.document(graphQLQuery)
                .retrieve("getProductsByCategory")
                .toEntityList(Item.class).block();

        System.out.println("Response from GraphQL:"+items);
        return items;

    }

    public Item receiveNewShipment(ItemRequestDTO itemRequestDTO){
        String graphQLQuery = """
    mutation ReceiveNewShipment {
        receiveNewShipment(id: "%s", quantity: %d) {
            id
            name
            price
            stock
        }
    }
  """.formatted(itemRequestDTO.getId(), itemRequestDTO.getQty());
        return graphQlClient.document(graphQLQuery)
                .retrieve("receiveNewShipment")
                .toEntity(Item.class).block();
    }


}
