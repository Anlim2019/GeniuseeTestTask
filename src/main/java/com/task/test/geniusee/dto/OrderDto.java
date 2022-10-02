package com.task.test.geniusee.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDto {
  @NotNull
  private Long id;

  @NotNull
  private Double price;
}
