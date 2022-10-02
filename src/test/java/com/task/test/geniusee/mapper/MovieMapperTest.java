package com.task.test.geniusee.mapper;


import com.task.test.geniusee.dto.MovieDto;
import com.task.test.geniusee.entity.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ComponentScan("com.task.test.geniusee.mapper")
public class MovieMapperTest {



  @Autowired
  private MovieMapper movieMapper;

  @Test
  void shouldBeMappedToDto() {
    Movie expected = getMovie();
    MovieDto actual = movieMapper.toDto(expected);


    assertAll(
        () -> assertEquals(expected.getId(), actual.getId()),
        () -> assertEquals(expected.getName(), actual.getName()),
        () -> assertEquals(expected.getRating(), actual.getRating())
    );
  }

  @Test
  void shouldBeMappedToEntity() {
    MovieDto expected = getMovieDto();
    Movie actual = movieMapper.toEntity(expected);


    assertAll(
        () -> assertEquals(expected.getId(), actual.getId()),
        () -> assertEquals(expected.getName(), actual.getName()),
        () -> assertEquals(expected.getRating(), actual.getRating())
    );
  }

  @Test
  void shouldUpdateProperties() {
    MovieDto source = getMovieDto();
    Movie target = getMovie();

    movieMapper.updateProperties(source, target);
    assertAll(
        () -> assertEquals(target.getId(), source.getId()),
        () -> assertEquals(target.getName(), source.getName()),
        () -> assertEquals(target.getRating(), source.getRating())
    );
  }

  private static Movie getMovie() {
    Movie movie = new Movie();
    movie.setId(1L);
    movie.setRating(9.4);
    movie.setName("Test name Entity");
    return movie;
  }

  private static MovieDto getMovieDto() {
    MovieDto movie = new MovieDto();
    movie.setId(1L);
    movie.setRating(9.1);
    movie.setName("Test name Dto");
    return movie;
  }
}
