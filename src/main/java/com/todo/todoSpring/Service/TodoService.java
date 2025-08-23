package com.todo.todoSpring.Service;

import com.todo.todoSpring.Dto.TodoDto;

import java.util.List;

public interface TodoService {
    List<TodoDto> getAllTodos();

    TodoDto getTodoById(Integer id);

    TodoDto createTodo(TodoDto todoDto);

    TodoDto updateTodo(Integer id, TodoDto todoDto);
    TodoDto updateCompleted(Integer id, TodoDto todoDto);

    void deleteTodo(Integer id);
}
