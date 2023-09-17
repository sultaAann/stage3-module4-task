package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.repository.model.impl.Author;
import com.mjc.school.service.dto.AuthorDTORequest;
import com.mjc.school.service.dto.AuthorDTOResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    AuthorRepository repository;
    @InjectMocks
    AuthorService service;

    @Test
    void readAll() {
        Author res1 = new Author();
        res1.setName("Michael");
        Author res2 = new Author();
        res2.setName("Petya");
        List<Author> authors = Arrays.asList(res1, res2);

        when(repository.readAll()).thenReturn(authors);

        List<AuthorDTOResponse> result = service.readAll();
        assertThat(result).hasSize(2);
    }

    @Test
    void readById() {
        Author author = new Author();
        author.setName("Michael");
        when(repository.readById(1L)).thenReturn(Optional.of(author));

        AuthorDTOResponse res = service.readById(1L);

        assertThat(res.name()).isEqualTo(author.getName());
    }

    @Test
    void create() {
        AuthorDTORequest request = new AuthorDTORequest(null, "John");

        AuthorDTOResponse result = service.create(request);

        assertThat(result.name()).isEqualTo(request.name());
    }

    @Test
    void update() {
        AuthorDTORequest request = new AuthorDTORequest(null, "John");

        AuthorDTOResponse result = service.update(request);

        assertThat(result.name()).isEqualTo(request.name());
    }

    @Test
    void deleteById() {
        when(repository.deleteById(1L)).thenReturn(true);

        assertTrue(service.deleteById(1L));
    }

    @Test
    void readAuthorByNewsId() {
    }
}