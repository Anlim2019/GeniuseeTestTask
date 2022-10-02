package com.task.test.geniusee.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import com.task.test.geniusee.criterial.SearchCriteria;
import com.task.test.geniusee.dto.MovieDto;
import com.task.test.geniusee.entity.Movie;
import com.task.test.geniusee.entity.Order;
import com.task.test.geniusee.mapper.MovieMapper;
import com.task.test.geniusee.repository.MovieRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.stylesheets.LinkStyle;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class MovieServiceTest {

  private static final Long MOVIE_ID = 1L;

  private static final MovieDto MOVIE_DTO = getMovieDto();

  private static final Movie MOVIE_ENTITY = getMovie();

  @InjectMocks
  private MovieService movieService;

  @Mock
  private MovieMapper movieMapper;

  @Mock
  private MovieRepository movieRepository;


  @Test
  void givenMovie_ShouldBeSaved() {
    when(movieMapper.toEntity(MOVIE_DTO)).thenReturn(MOVIE_ENTITY);
    when(movieMapper.toDto(MOVIE_ENTITY)).thenReturn(MOVIE_DTO);
    when(movieRepository.save(MOVIE_ENTITY)).thenReturn(MOVIE_ENTITY);

    MovieDto result = movieService.create(MOVIE_DTO);

    verify(movieMapper).toDto(MOVIE_ENTITY);
    verify(movieRepository).save(MOVIE_ENTITY);
    verify(movieMapper).toEntity(MOVIE_DTO);

    assertAll(
        () -> assertNotNull(result),
        () -> assertEquals(MOVIE_DTO, result)
    );
  }

  @Test
  void shouldReturnListOfMovies() {
    Pageable pageable = PageRequest.of(0, 2);
    List<Movie> movies = new ArrayList<>(List.of(MOVIE_ENTITY, MOVIE_ENTITY));
    Page<Movie> page = new PageImpl<>(movies);
    when(movieRepository.findAll(pageable))
        .thenReturn(page);
    List<MovieDto> result = movieService.findAll(new SearchCriteria(),0, 2);

    assertEquals(result.size(), movies.size());

  }

  @Test
  void shouldFindById() {
    when(movieRepository.findById(MOVIE_ID)).thenReturn(Optional.of(MOVIE_ENTITY));
    when(movieMapper.toDto(MOVIE_ENTITY)).thenReturn(MOVIE_DTO);

    MovieDto result = movieService.findById(MOVIE_ID);

    assertAll(
        () -> assertNotNull(result),
        () -> assertEquals(MOVIE_DTO, result)
    );
  }

  @Test
  void shouldUpdate() {
    when(movieRepository.findById(MOVIE_ID)).thenReturn(Optional.of(MOVIE_ENTITY));
    when(movieRepository.save(MOVIE_ENTITY)).thenReturn(MOVIE_ENTITY);
    when(movieMapper.toDto(MOVIE_ENTITY)).thenReturn(MOVIE_DTO);

    MovieDto result = movieService.update(MOVIE_DTO);

    assertAll(
        () -> assertNotNull(result),
        () -> assertEquals(result, MOVIE_DTO)
    );
  }

  @Test
  void shouldDelete() {
    when(movieRepository.findById(MOVIE_ID)).thenReturn(Optional.of(MOVIE_ENTITY));

    movieService.removeById(MOVIE_ID);

    verify(movieRepository).deleteById(MOVIE_ID);
  }


  private static Movie getMovie() {
    Movie movie = new Movie();
    movie.setId(1L);
    movie.setName("Test Name");
    movie.setRating(9.1);
    Order order = new Order();
    order.setPrice(123.4);
    order.setId(1L);
    movie.setOrder(order);

    return movie;
  }

  private static MovieDto getMovieDto() {
    MovieDto movie = new MovieDto();
    movie.setId(1L);
    movie.setName("Test Name");
    movie.setRating(9.1);
    movie.setOrderId(1L);

    return movie;
  }

}
