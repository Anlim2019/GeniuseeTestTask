package com.task.test.geniusee.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieDto {

  @NotNull
  private Long id;

  @NotNull
  private String name;

  @Max(value = 10)
  private Double rating;

  private Long orderId;
}
