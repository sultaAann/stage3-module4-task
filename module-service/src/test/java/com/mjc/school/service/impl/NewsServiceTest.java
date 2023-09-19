package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.impl.News;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
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
class NewsServiceTest {
    @Mock
    NewsRepository repository;
    @InjectMocks
    NewsService service;
    @Test
    void readAll() {
        News res1 = new News();
        res1.setTitle("Good!");
        res1.setContent("AAA");
        News res2 = new News();
        res2.setTitle("Bad!");
        res2.setContent("BBB");
        List<News> news = Arrays.asList(res1, res2);

        when(repository.readAll()).thenReturn(news);

        List<NewsDTOResponse> result = service.readAll();
        assertThat(result).hasSize(2);
        assertEquals(result.get(0).content(), news.get(0).getContent());
        assertEquals(result.get(1).title(), news.get(1).getTitle());
    }

    @Test
    void readById() {
        News news = new News();
        news.setContent("GREAT");
        news.setTitle("Good");
        when(repository.readById(1L)).thenReturn(Optional.of(news));

        NewsDTOResponse res = service.readById(1L);

        assertThat(res.content()).isEqualTo(news.getContent());
        assertEquals(res.title(), news.getTitle());
    }

    @Test
    void create() {
        NewsDTORequest request = new NewsDTORequest(1L, "HAHA", "Ohh", 1L);

        NewsDTOResponse result = service.create(request);

        assertThat(result.content()).isEqualTo(request.content());
        assertEquals(result.content(), request.content());
        assertEquals(result.title(), request.title());
    }

    @Test
    void update() {
        NewsDTORequest request = new NewsDTORequest(1L, "HAHA", "Ohh", 1L);

        NewsDTOResponse result = service.update(request);

        assertThat(result.content()).isEqualTo(request.content());
        assertEquals(result.content(), request.content());
        assertEquals(result.title(), request.title());
    }

    @Test
    void deleteById() {
        when(repository.deleteById(1L)).thenReturn(true);

        assertTrue(service.deleteById(1L));
    }

}