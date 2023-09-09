package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.NewsCommands;
import com.mjc.school.repository.model.impl.Author;
import com.mjc.school.repository.model.impl.News;
import com.mjc.school.repository.model.impl.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<News, Long>, NewsCommands<News, Long> {


    private EntityManager entityManager;

    @PersistenceUnit
    public void setEntityManager(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<News> readAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
        Root<News> root = criteriaQuery.from(News.class);
        CriteriaQuery<News> all = criteriaQuery.select(root);

        TypedQuery<News> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public Optional<News> readById(Long id) {
        return Optional.ofNullable(entityManager.find(News.class, id));
    }

    @Override
    public News create(News model) {
        entityManager.getTransaction().begin();
        model.setCreatedDate(LocalDateTime.now());
        entityManager.persist(model);
        entityManager.getTransaction().commit();
        return model;
    }

    @Override
    public News update(News model) {
        entityManager.getTransaction().begin();
        model.setLastUpdatedDate(LocalDateTime.now());
        News res = entityManager.merge(model);
        entityManager.getTransaction().commit();
        return res;
    }

    @Override
    public boolean deleteById(Long id) {
        if (existById(id)) {
            entityManager.getTransaction().begin();
            entityManager.remove(id);
            entityManager.getTransaction().commit();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean existById(Long id) {
        News model = entityManager.find(News.class, id);
        return entityManager.contains(model);
    }

    @Override
    public List<News> readByTagName(String tagName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
        Root<News> news = criteriaQuery.from(News.class);
        Join<News, Tag> newsJoin = news.join("tags", JoinType.LEFT);
        criteriaQuery.where(criteriaBuilder.equal(newsJoin.get("name"), tagName));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<News> readByTagId(Long tagId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
        Root<News> news = criteriaQuery.from(News.class);
        Join<News, Tag> newsJoin = news.join("tags", JoinType.LEFT);
        criteriaQuery.where(criteriaBuilder.equal(newsJoin.get("id"), tagId));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<News> readByAuthorName(String authorName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
        Root<News> news = criteriaQuery.from(News.class);
        Join<News, Author> newsJoin = news.join("authors", JoinType.LEFT);
        criteriaQuery.where(criteriaBuilder.equal(newsJoin.get("name"), authorName));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<News> readByTitle(String title) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
        Root<News> news = criteriaQuery.from(News.class);
        criteriaQuery.select(news).where(criteriaBuilder.like(news.get("title"), "%" + title + "%"));
        List<News> res = entityManager.createQuery(criteriaQuery).getResultList();
        return res;
    }

    @Override
    public List<News> readByContent(String content) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
        Root<News> news = criteriaQuery.from(News.class);
        criteriaQuery.select(news).where(criteriaBuilder.like(news.get("content"), "%" + content + "%"));
        List<News> res = entityManager.createQuery(criteriaQuery).getResultList();
        return res;
    }
}
