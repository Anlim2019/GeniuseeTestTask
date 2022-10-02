package com.task.test.geniusee.rest;

import com.task.test.geniusee.criterial.SearchCriteria;
import com.task.test.geniusee.dto.MovieDto;
import com.task.test.geniusee.dto.OrderDto;
import com.task.test.geniusee.entity.Order;
import com.task.test.geniusee.service.OrderService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @GetMapping("/order/{id}")
  public ResponseEntity<OrderDto> getById(@PathVariable Long id) {
    return ResponseEntity.ok(this.orderService.findById(id));
  }

  @GetMapping("/orders")
  public ResponseEntity<List<OrderDto>> getAll(SearchCriteria searchCriteria,
                                               @RequestParam(defaultValue = "0") Integer page,
                                               @RequestParam(defaultValue = "3") Integer size) {
    return ResponseEntity.ok(this.orderService.findAll(searchCriteria, page, size));
  }

  @PostMapping("/order")
  public ResponseEntity<OrderDto> create(@RequestBody OrderDto orderDto) {
    return ResponseEntity.ok(this.orderService.create(orderDto));
  }

  @PutMapping("/order")
  public ResponseEntity<OrderDto> update(@RequestBody OrderDto orderDto) {
    return ResponseEntity.ok(this.orderService.update(orderDto));
  }

  @DeleteMapping("/order/{id}")
  public ResponseEntity<Void> removeById(@PathVariable Long id) {
    this.orderService.removeById(id);
    return ResponseEntity.ok().build();
  }
}
