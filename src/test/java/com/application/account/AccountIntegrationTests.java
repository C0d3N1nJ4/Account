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
                                    "accountId": "10",
                                    "accountType": 1,
                                    "accountDescription": "Test Account",
                                    "accountStatus": 0,
                                    "currency": "EUR",
                                    "balance": 750.00,
                                    "customerId": "1"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                                {
                                    "accountId": "10",
                                    "accountType": "CURRENT",
                                    "accountDescription": "Test Account",
                                    "accountStatus": "ACTIVE",
                                    "currency": "EUR",
                                    "balance": 750.00,
                                    "customerId": "1"
                                }
                                """));
    }

    @Test
    public void postAccountWithIncorrectCustomerIdTest_StatusBadRequest() throws Exception{
        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "accountId": "10",
                                    "accountType": 1,
                                    "accountDescription": "Test Account",
                                    "accountStatus": 0,
                                    "currency": "EUR",
                                    "balance": 750.00,
                                    "customerId": "100"
                                }
                                """))
                .andExpect(status().isNotFound());

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
                                    "accountId": "10",
                                    "accountType": 1,
                                    "accountDescription": "Test Account",
                                    "accountStatus": 0,
                                    "currency": "EUR",
                                    "balance": 1050.00,
                                    "customerId": "1"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                                {
                                    "accountId": "10",
                                    "accountType": "CURRENT",
                                    "accountDescription": "Test Account",
                                    "accountStatus": "ACTIVE",
                                    "currency": "EUR",
                                    "balance": 1050.00,
                                    "customerId": "1"
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

    @Test
    public void getAccountByStatusTest_StatusOK() throws Exception{
        mockMvc.perform(get("/account/filter/status/ACTIVE").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAccountByStatusTest_StatusNotFound() throws Exception{
        mockMvc.perform(get("/account/filter/status").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAccountByTypeTest_StatusOK() throws Exception{
        mockMvc.perform(get("/account/filter/type/CREDIT").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAccountByTypeTest_StatusNotFound() throws Exception{
        mockMvc.perform(get("/account/filter/type").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getBalanceTest_StatusOK() throws Exception{
        mockMvc.perform(get("/account/balance/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
