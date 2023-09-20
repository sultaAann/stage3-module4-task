package com.mjc.school.repository.impl;

import com.mjc.school.repository.CommentCommands;
import com.mjc.school.repository.model.impl.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;


@Repository
public class CommentRepository implements CommentCommands {
    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Comment> readAll(int limit, int offset) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Comment> criteriaQuery = criteriaBuilder.createQuery(Comment.class);
        Root<Comment> root = criteriaQuery.from(Comment.class);
        CriteriaQuery<Comment> all = criteriaQuery.select(root);

        TypedQuery<Comment> allQuery = entityManager.createQuery(all)
                .setFirstResult(offset)
                .setMaxResults(limit);
        return allQuery.getResultList();
    }

    @Override
    public Optional<Comment> readById(Long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    @Override
    public Comment create(Comment model) {
        entityManager.persist(model);
        return model;
    }

    @Override
    public Comment update(Comment model) {
        return entityManager.merge(model);
    }

    @Override
    public boolean deleteById(Long id) {
        if (existById(id)) {
            Comment tag = entityManager.find(Comment.class, id);
            entityManager.remove(tag);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean existById(Long id) {
        Comment model = entityManager.find(Comment.class, id);
        return entityManager.contains(model);
    }


    @Override
    public List<Comment> readCommentsByNewsId(Long id) {
        return entityManager.createQuery(
                "SELECT c FROM Comment c" +
                        "INNER JOIN news_tag n ON c.comment_id = n.news_id" +
                        "INNER JOIN Comment ON news_tag.news_id = Comment.news_id;"
        ).getResultList();
    }
}
