package com.mjc.school.service.impl;

import com.mjc.school.repository.AuthorCommands;
import com.mjc.school.repository.model.impl.Author;
import com.mjc.school.service.AuthorCommandsService;
import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;
import com.mjc.school.service.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements AuthorCommandsService {
    private final AuthorCommands authorCommands;

    @Autowired
    public AuthorService(AuthorCommands authorCommands) {
        this.authorCommands = authorCommands;
    }

    @Override
    public List<AuthorDTOResponse> readAll() {
        return authorCommands.readAll().stream().map(AuthorMapper.INSTANCE::modelToDto).toList();
    }

    @Override
    public AuthorDTOResponse readById(Long id) {
        if (authorCommands.readById(id).isPresent()) {
            return AuthorMapper.INSTANCE.modelToDto(authorCommands.readById(id).get());
        }
        return null;
    }

    @Override
    public AuthorDTOResponse create(AuthorDTORequest createRequest) {
        Author model = AuthorMapper.INSTANCE.dtoToModel(createRequest);
        authorCommands.create(model);
        return AuthorMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public AuthorDTOResponse update(AuthorDTORequest updateRequest) {
        Author model = AuthorMapper.INSTANCE.dtoToModel(updateRequest);
        authorCommands.update(model);
        return AuthorMapper.INSTANCE.modelToDto(model);
    }

    @Override
    public boolean deleteById(Long id) {
        return authorCommands.deleteById(id);
    }

    @Override
    public List<AuthorDTOResponse> readAuthorByNewsId(Long id) {
        return authorCommands.readAuthorByNewsId(id).stream().map(AuthorMapper.INSTANCE::modelToDto).toList();
    }
}
