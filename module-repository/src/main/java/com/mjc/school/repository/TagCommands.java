package com.mjc.school.repository;

import java.util.List;

public interface TagCommands<T, K> {
    List<T> readTagsByNewsId(K id);
}
