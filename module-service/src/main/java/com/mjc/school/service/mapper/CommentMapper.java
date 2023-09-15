package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.impl.Comment;
import com.mjc.school.service.dto.CommentDTORequest;
import com.mjc.school.service.dto.CommentDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDTOResponse modelToDto(Comment model);

    Comment dtoToModel(CommentDTORequest modelDTO);
}
