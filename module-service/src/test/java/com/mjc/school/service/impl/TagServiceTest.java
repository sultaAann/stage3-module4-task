package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.TagRepository;
import com.mjc.school.repository.model.impl.Tag;
import com.mjc.school.service.dto.TagDTORequest;
import com.mjc.school.service.dto.TagDTOResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {
    @Mock
    TagRepository repository;
    @InjectMocks
    TagService service;
    @Test
    void readAll() {
        Tag res1 = new Tag();
        res1.setName("rec!");
        Tag res2 = new Tag();
        res2.setName("Bad!");
        List<Tag> news = Arrays.asList(res1, res2);

        when(repository.readAll()).thenReturn(news);

        List<TagDTOResponse> result = service.readAll();
        assertThat(result).hasSize(2);
        assertEquals(result.get(0).name(), news.get(0).getName());
        assertEquals(result.get(1).name(), news.get(1).getName());
    }

    @Test
    void readById() {
        Tag news = new Tag();
        news.setName("GREAT");
        when(repository.readById(1L)).thenReturn(Optional.of(news));

        TagDTOResponse res = service.readById(1L);

        assertThat(res.name()).isEqualTo(news.getName());
    }

    @Test
    void create() {
        TagDTORequest request = new TagDTORequest(1L, "HAHA");

        TagDTOResponse result = service.create(request);

        assertThat(result.name()).isEqualTo(request.name());
    }

    @Test
    void update() {
        TagDTORequest request = new TagDTORequest(1L, "HAHA");

        TagDTOResponse result = service.update(request);

        assertThat(result.name()).isEqualTo(request.name());
        assertEquals(result.id(), request.id());
    }

    @Test
    void deleteById() {
        when(repository.deleteById(1L)).thenReturn(true);

        assertTrue(service.deleteById(1L));
    }

}