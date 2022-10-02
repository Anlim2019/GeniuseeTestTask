package com.task.test.geniusee.mapper;

import com.task.test.geniusee.dto.MovieDto;
import com.task.test.geniusee.dto.OrderDto;
import com.task.test.geniusee.entity.Movie;
import com.task.test.geniusee.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {
  public abstract Order toEntity(OrderDto dto);

  public abstract OrderDto toDto(Order order);

  @Mapping(target = "id", ignore = true)
  public abstract void updateProperties(OrderDto source,
                                        @MappingTarget Order target);


}
