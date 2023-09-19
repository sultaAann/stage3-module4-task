package com.mjc.school.controller.impl;

import com.mjc.school.controller.TagCommandsController;
import com.mjc.school.controller.exceptions.ResourceNotFoundException;
import com.mjc.school.service.TagCommandsService;
import com.mjc.school.service.dto.TagDTORequest;
import com.mjc.school.service.dto.TagDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController implements TagCommandsController {

    private final TagCommandsService service;

    @Autowired
    public TagController(TagCommandsService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/all")
    public List readAll() {
        return service.readAll();
    }

    @Override
    @GetMapping("/{id}")
    public TagDTOResponse readById(@PathVariable Long id) {
        return service.readById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public TagDTOResponse create(@RequestBody TagDTORequest createRequest) {
        return service.create(createRequest);
    }

    @Override
    @PutMapping("/{id}")
    public TagDTOResponse update(@PathVariable Long id, @RequestBody TagDTORequest updateRequest) {
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
    public List<TagDTOResponse> readTagsByNewsId(@PathVariable Long id) {
        return service.readTagsByNewsId(id);
    }
}
