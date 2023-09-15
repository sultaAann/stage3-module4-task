package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.Comment;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.CommentCommandsService;
import com.mjc.school.service.dto.CommentDTORequest;
import com.mjc.school.service.dto.CommentDTOResponse;
import com.mjc.school.service.exceptions.AuthorIDException;
import com.mjc.school.service.exceptions.AuthorNameException;
import com.mjc.school.service.exceptions.TagIDException;
import com.mjc.school.service.exceptions.TagNameException;
import com.mjc.school.service.mapper.CommentMapper;
import com.mjc.school.service.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements BaseService<CommentDTORequest, CommentDTOResponse, Long>, CommentCommandsService<CommentDTOResponse, Long> {

    @Autowired
    private BaseRepository<Comment, Long> repository;

    @Override
    public List<CommentDTOResponse> readAll() {
        return repository.readAll().stream().map(CommentMapper.INSTANCE::modelToDto).toList();
    }

    @Override
    public CommentDTOResponse readById(Long id) throws TagIDException {
        Validator.tagIdValidator(String.valueOf(id));
        if (repository.readById(id).isPresent()) {
            return CommentMapper.INSTANCE.modelToDto(repository.readById(id).get());
        }
        return null;
    }

    @Override
    public CommentDTOResponse create(CommentDTORequest createRequest) throws AuthorNameException, TagNameException {
        Validator.tagNameValidator(createRequest.content());
        Comment model = CommentMapper.INSTANCE.dtoToModel(createRequest);
        repository.create(model);
        return CommentMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public CommentDTOResponse update(CommentDTORequest updateRequest) throws AuthorIDException, AuthorNameException, TagIDException, TagNameException {
        Validator.tagIdValidator(String.valueOf(updateRequest.id()));
        Validator.tagNameValidator(updateRequest.content());
        Comment model = CommentMapper.INSTANCE.dtoToModel(updateRequest);
        repository.update(model);
        return CommentMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public boolean deleteById(Long id) throws TagIDException {
        Validator.tagIdValidator(String.valueOf(id));
        return repository.deleteById(id);
    }

    @Override
    public List<CommentDTOResponse> readCommentsByNewsId(Long id) {
        return null;
    }
}
