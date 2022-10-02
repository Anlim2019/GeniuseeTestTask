package com.task.test.geniusee.service;

import static java.lang.String.format;

import com.task.test.geniusee.dto.MovieDto;
import com.task.test.geniusee.entity.Movie;
import com.task.test.geniusee.mapper.MovieMapper;
import com.task.test.geniusee.repository.MovieRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieService {

  private final MovieRepository movieRepository;
  private final MovieMapper movieMapper;

  public MovieDto findById(final Long id) {
    Movie movie = this.movieRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(format("no movie with id %d", id)));
    return this.movieMapper.toDto(movie);
  }

  public List<MovieDto> findAll(Integer page, Integer size) {
    Pageable paging = PageRequest.of(page, size);
    return this.movieRepository.findAll(paging)
        .stream()
        .map(this.movieMapper::toDto).collect(Collectors.toList());
  }

  public List<MovieDto> findAllByOrderId(Long orderId) {
    return this.movieRepository.findAll()
        .stream().map(this.movieMapper::toDto)
        .filter(m -> m.getOrderId() != null)
        .filter(m -> m.getOrderId().equals(orderId))
        .collect(Collectors.toList());
  }

  public MovieDto create(final MovieDto movieDto) {
    return this.movieMapper.toDto(this.movieRepository.save(this.movieMapper.toEntity(movieDto)));
  }

  public MovieDto update(final MovieDto movieDto) {
    Long id = movieDto.getId();
    Movie movie = this.movieRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(format("no movie with id %d", id)));
    this.movieMapper.updateProperties(movieDto, movie);
    return this.movieMapper.toDto(this.movieRepository.save(movie));
  }

  public void removeById(final Long id) {
    this.movieRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(format("no movie with id %d", id)));
    this.movieRepository.deleteById(id);
  }
}
