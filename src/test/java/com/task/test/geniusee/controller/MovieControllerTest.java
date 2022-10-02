package com.task.test.geniusee.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.test.geniusee.entity.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class MovieControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;


  @Test
  void shouldReturnListOfMovies() throws Exception {
    mockMvc.perform(get("/api/movies")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void shouldReturnListOfMoviesByOrderId() throws Exception {
    mockMvc.perform(get("/api/movies/{orderId}", 1L)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void shouldReturnMovieByID() throws Exception {
    mockMvc.perform(get("/api/movie/{id}", 1L)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void shouldRemoveByGivenId() throws Exception {
    mockMvc.perform(delete("/api/movie/{id}", 1L)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void shouldReturnCreatedMovie() throws Exception {
    mockMvc.perform(post("/api/movie")
        .content(this.asJsonMovie())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void shouldReturnUpdatedMovie() throws Exception {
    mockMvc.perform(put("/api/movie")
            .content(this.asJsonMovie())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  private String asJsonMovie() throws JsonProcessingException {
    Movie  m = new Movie();
    m.setId(1L);
    m.setName("123");
    m.setRating(9.1);
    return objectMapper.writeValueAsString(m);
  }
}
