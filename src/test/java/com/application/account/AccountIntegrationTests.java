package com.application.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountIntegrationTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    @Disabled
    public void getAllCustomersTest_StatusOK() throws Exception{
        mockMvc.perform(get("/account/customer/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void getAccountByIdTest_StatusNotFound() throws Exception{
        mockMvc.perform(get("/account/4").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void getAccountByIdTest_StatusOK() throws Exception{
        mockMvc.perform(get("/account/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void postAccountTest_StatusOK() throws Exception{
        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "id": "10",
                                    "accountType": 1,
                                    "accountDescription": "Test Account",
                                    "customerId": "10"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                                {
                                    "id": "10",
                                    "accountType": "CURRENT",
                                    "accountDescription": "Test Account",
                                    "customerId": "10"
                                }
                                """));

    }

    @Test
    public void postIncorrectAccountTypeTest_StatusBadRequest() throws Exception{
        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "id": "10",
                                    "accountType": 9,
                                    "accountDescription": "Test Account",
                                    "customerId": "10"
                                }
                                """))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void putAccountTest_StatusOK() throws Exception{
        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "id": "10",
                                    "accountType": 1,
                                    "accountDescription": "Test Account",
                                    "customerId": "8"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                                {
                                    "id": "10",
                                    "accountType": "CURRENT",
                                    "accountDescription": "Test Account",
                                    "customerId": "8"
                                }
                                """));

    }

    @Test
    public void putIncorrectAccountTypeTest_StatusBadRequest() throws Exception{
        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "id": "10",
                                    "accountType": 9,
                                    "accountDescription": "Test Account",
                                    "customerId": "8"
                                }
                                """))
                .andExpect(status().isBadRequest());

    }
}
