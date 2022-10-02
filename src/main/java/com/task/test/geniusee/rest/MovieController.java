package com.task.test.geniusee.rest;

import com.task.test.geniusee.criterial.SearchCriteria;
import com.task.test.geniusee.dto.MovieDto;
import com.task.test.geniusee.service.MovieService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MovieController {

  private final MovieService movieService;

  @PostMapping("/movie")
  public ResponseEntity<MovieDto> create(@RequestBody MovieDto movie) {
    return ResponseEntity.ok(this.movieService.create(movie));
  }

  @PutMapping("/movie")
  public ResponseEntity<MovieDto> update(@RequestBody MovieDto movie) {
    return ResponseEntity.ok(this.movieService.update(movie));
  }

  @GetMapping("/movie/{id}")
  public ResponseEntity<MovieDto> getById(@PathVariable Long id) {
    return ResponseEntity.ok(this.movieService.findById(id));
  }

  @GetMapping("/movies/{orderId}")
  public ResponseEntity<List<MovieDto>> getAllByOrderId(@PathVariable Long orderId) {
    return ResponseEntity.ok(this.movieService.findAllByOrderId(orderId));
  }

//  @GetMapping("/movies")
//  public ResponseEntity<List<MovieDto>> getAll(@RequestParam(defaultValue = "0") Integer page,
//                                               @RequestParam(defaultValue = "3") Integer size) {
//    return ResponseEntity.ok(this.movieService.findAll(page, size));
//  }

  @GetMapping("/movies")
  public ResponseEntity<List<MovieDto>> getAllByName(SearchCriteria searchCriteria,
                                                     @RequestParam(defaultValue = "0") Integer page,
                                                     @RequestParam(defaultValue = "3") Integer size) {
    return ResponseEntity.ok(this.movieService.findAll(searchCriteria, page, size));
  }
  @DeleteMapping("/movie/{id}")
  public ResponseEntity<Void> removeById(@PathVariable Long id) {
    this.movieService.removeById(id);
    return ResponseEntity.ok().build();
  }
}
