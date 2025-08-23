package com.todo.todoSpring.Repository;

import com.todo.todoSpring.Entity.Todo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByCompleted(boolean completed, Sort sort);
}
