package com.mjc.school.service.impl;

import com.mjc.school.repository.CommentCommands;
import com.mjc.school.repository.model.impl.Comment;
import com.mjc.school.service.CommentCommandsService;
import com.mjc.school.service.dto.CommentDTORequest;
import com.mjc.school.service.dto.CommentDTOResponse;
import com.mjc.school.service.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements CommentCommandsService {
    private final CommentCommands repository;

    @Autowired
    public CommentService(CommentCommands repository) {
        this.repository = repository;
    }

    @Override
    public List<CommentDTOResponse> readAll() {
        return repository.readAll().stream().map(CommentMapper.INSTANCE::modelToDto).toList();
    }

    @Override
    public CommentDTOResponse readById(Long id) {
        if (repository.readById(id).isPresent()) {
            return CommentMapper.INSTANCE.modelToDto(repository.readById(id).get());
        }
        return null;
    }

    @Override
    public CommentDTOResponse create(CommentDTORequest createRequest) {
        Comment model = CommentMapper.INSTANCE.dtoToModel(createRequest);
        repository.create(model);
        return CommentMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public CommentDTOResponse update(CommentDTORequest updateRequest) {
        Comment model = CommentMapper.INSTANCE.dtoToModel(updateRequest);
        repository.update(model);
        return CommentMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }

    @Override
    public List<CommentDTOResponse> readCommentsByNewsId(Long id) {
        return null;
    }
}
