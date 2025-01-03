package com.bookStore.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductTests {
    @Autowired
    private MockMvc mockMvc;

    private String productJson = """
            {
                "name": "iPad",
                "description": "brand new iPad",
                "author": "Steve Jobs",
                "price": 0.13,
                "imagePath": "/somePath/iPad"
            }
            """;

    @Test
    @WithMockUser(username = "manager", roles = {"MANAGER"})
    void testCreateProductAsManager() throws Exception {
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("iPad"));
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testCreateProductAsAdmin() throws Exception {
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "customer", roles = {"CUSTOMER"})
    void testCreateProductAsCustomer() throws Exception {
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "customer", roles = {"CUSTOMER"})
    void testGetAllProductsAsCustomer() throws Exception {
        mockMvc.perform(get("/api/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(username = "manager", roles = {"MANAGER"})
    void testGetAllProductsAsManager() throws Exception {
        mockMvc.perform(get("/api/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testGetAllProductsAsAdmin() throws Exception {
        mockMvc.perform(get("/api/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "manager", roles = {"MANAGER"})
    void testEditProduct() throws Exception {
        String updatedProductJson = """
                {
                        "name": "iPhone",
                        "description": "brand new phone",
                        "author": "Steve Jobs",
                        "price": 1.24,
                        "imagePath": "/somePath/iPhone"
                    }
                """;
        mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedProductJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("1.24"));
    }

    @Test
    @WithMockUser(username = "manager", roles = {"MANAGER"})
    void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
