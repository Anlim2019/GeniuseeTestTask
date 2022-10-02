package com.task.test.geniusee.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.test.geniusee.entity.Movie;
import com.task.test.geniusee.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class OrderControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;


  @Test
  void shouldReturnListOfOrders() throws Exception {
    mockMvc.perform(get("/api/orders")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }


  @Test
  void shouldReturnOrderById() throws Exception {
    mockMvc.perform(get("/api/order/{id}", 1L)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void shouldRemoveByGivenId() throws Exception {
    mockMvc.perform(delete("/api/order/{id}", 1L)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void shouldReturnCreatedMovie() throws Exception {
    mockMvc.perform(post("/api/order")
            .content(this.asJsonOrder())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void shouldReturnUpdatedMovie() throws Exception {
    mockMvc.perform(put("/api/order")
            .content(this.asJsonOrder())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  private String asJsonOrder() throws JsonProcessingException {
    Order order = new Order();
    order.setId(1L);
    order.setPrice(123.4);
    return objectMapper.writeValueAsString(order);
  }
}
