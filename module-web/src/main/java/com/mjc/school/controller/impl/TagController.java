package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.TagCommandsController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.TagCommandsService;
import com.mjc.school.service.dto.TagDTORequest;
import com.mjc.school.service.dto.TagDTOResponse;
import com.mjc.school.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController implements BaseController<TagDTORequest, TagDTOResponse, Long>, TagCommandsController<TagDTOResponse, Long> {

    @Autowired
    private BaseService<TagDTORequest, TagDTOResponse, Long> service;

    @Autowired
    private TagCommandsService<TagDTOResponse, Long> tagCommandsService;

    @Override
    @GetMapping("/all")
    public List<TagDTOResponse> readAll() {
        return service.readAll();
    }

    @Override
    @GetMapping("/{id}")
    public TagDTOResponse readById(@PathVariable Long id) throws AuthorIDException, NewsIDException, TagIDException {
        return service.readById(id);
    }

    @Override
    @PostMapping
    public TagDTOResponse create(@RequestBody TagDTORequest createRequest) throws AuthorNameException, AuthorIDException, TagNameException, TitleOrContentLengthException {
        return service.create(createRequest);
    }

    @Override
    @PutMapping
    public TagDTOResponse update(@RequestBody TagDTORequest updateRequest) throws AuthorIDException, AuthorNameException, TagNameException, NewsIDException, TagIDException, TitleOrContentLengthException {
        return service.update(updateRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) throws AuthorIDException, NewsIDException, TagIDException {
        return service.deleteById(id);
    }

    @Override
    @GetMapping("/alla")
    public List<TagDTOResponse> readTagsByNewsId(Long id) {
        return tagCommandsService.readTagsByNewsId(id);
    }
}
