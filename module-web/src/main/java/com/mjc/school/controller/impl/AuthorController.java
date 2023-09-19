package com.mjc.school.controller.impl;

import com.mjc.school.controller.AuthorCommandsController;
import com.mjc.school.service.AuthorCommandsService;
import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController implements AuthorCommandsController {
    private final AuthorCommandsService service;
    @Autowired
    public AuthorController(AuthorCommandsService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/all")
    public List<AuthorDTOResponse> readAll() {
        return service.readAll();
    }

    @Override
    @GetMapping("/{id}")
    public AuthorDTOResponse readById(@PathVariable Long id) {
        return service.readById(id);
    }

    @Override
    @PostMapping
    public AuthorDTOResponse create(@RequestBody AuthorDTORequest createRequest) {
        return service.create(createRequest);
    }

    @Override
    @PutMapping
    public AuthorDTOResponse update(@RequestBody AuthorDTORequest updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @Override
    public List<AuthorDTOResponse> readAuthorByNewsId(Long id) {
        return service.readAuthorByNewsId(id);
    }
}
