package com.todo.todoSpring.Repository;

import com.todo.todoSpring.Entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
