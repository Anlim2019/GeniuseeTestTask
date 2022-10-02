package com.task.test.geniusee.mapper;

import com.task.test.geniusee.dto.MovieDto;
import com.task.test.geniusee.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public abstract class MovieMapper {

  @Mapping(target = "order.id", source = "orderId")
  public abstract Movie toEntity(MovieDto dto);

  @Mapping(target = "orderId", source = "order.id")
  public abstract MovieDto toDto(Movie movie);
//
  @Mapping(target = "id", ignore = true)
  public abstract void updateProperties(MovieDto source,
                                        @MappingTarget Movie target);


}
