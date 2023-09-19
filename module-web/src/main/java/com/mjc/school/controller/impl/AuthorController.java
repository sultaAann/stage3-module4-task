package com.mjc.school.controller.impl;

import com.mjc.school.controller.AuthorCommandsController;
import com.mjc.school.controller.exceptions.ResourceNotFoundException;
import com.mjc.school.service.AuthorCommandsService;
import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(value = HttpStatus.CREATED)
    public AuthorDTOResponse create(@RequestBody AuthorDTORequest createRequest) {
        return service.create(createRequest);
    }

    @Override
    @PutMapping("/{id}")
    public AuthorDTOResponse update(@PathVariable Long id, @RequestBody AuthorDTORequest updateRequest) {
        return service.update(id, updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        if (!service.deleteById(id)) {
            throw new ResourceNotFoundException("Resource not found with id: " + id);
        }
    }

    @Override
    @GetMapping("/{id}")
    public List<AuthorDTOResponse> readAuthorByNewsId(@PathVariable Long id) {
        return service.readAuthorByNewsId(id);
    }
}
