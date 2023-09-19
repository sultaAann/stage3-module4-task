package com.mjc.school.controller.impl;

import com.mjc.school.controller.TagCommandsController;
import com.mjc.school.service.TagCommandsService;
import com.mjc.school.service.dto.TagDTORequest;
import com.mjc.school.service.dto.TagDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<TagDTOResponse> readAll() {
        return service.readAll();
    }

    @Override
    @GetMapping("/{id}")
    public TagDTOResponse readById(@PathVariable Long id) {
        return service.readById(id);
    }

    @Override
    @PostMapping
    public TagDTOResponse create(@RequestBody TagDTORequest createRequest) {
        return service.create(createRequest);
    }

    @Override
    @PutMapping
    public TagDTOResponse update(@RequestBody TagDTORequest updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @Override
    @GetMapping("/alla")
    public List<TagDTOResponse> readTagsByNewsId(Long id) {
        return service.readTagsByNewsId(id);
    }
}
