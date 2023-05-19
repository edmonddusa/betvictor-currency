package com.betvictor.currency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.betvictor.currency.entity.CurrencyExchange;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class WebTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void contextLoads() throws Exception {
        assertThat(mockMvc).isNotNull();
    }

    @Test
    void testTestSingle() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/EUR/USD/1000.3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        List<CurrencyExchange> list = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<List<CurrencyExchange>>() {
                });

        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0).getFrom().getSymbol()).isEqualTo("EUR");
        assertThat(list.get(0).getTo().getSymbol()).isEqualTo("USD");
    }

    @Test
    void testMultiple() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/EUR/USD,SEK/1000.3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        List<CurrencyExchange> list = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<List<CurrencyExchange>>() {
                });

        assertThat(list.size()).isEqualTo(2);
        assertThat(list.get(0).getFrom().getSymbol()).isEqualTo("EUR");
        assertThat(list.get(0).getTo().getSymbol()).isEqualTo("USD");
        assertThat(list.get(1).getFrom().getSymbol()).isEqualTo("EUR");
        assertThat(list.get(1).getTo().getSymbol()).isEqualTo("SEK");
    }

    @Test
    void testBadSymbol() throws Exception {
        assertThrows(ServletException.class, () -> {
            this.mockMvc.perform(get("/EUR/XXX/1000.3"))
                    .andDo(print())
                    .andExpect(status().isInternalServerError())
                    .andReturn();

        });
    }
}
