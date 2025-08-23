package com.todo.todoSpring.Controller;

import com.todo.todoSpring.Dto.TodoDto;
import com.todo.todoSpring.Service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/todoList")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public List<TodoDto> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public TodoDto getTodoById(@PathVariable("id") Integer id) {
        return todoService.getTodoById(id);
    }

    @PostMapping
    public TodoDto createTodo(@RequestBody TodoDto todoDto) {
        return todoService.createTodo(todoDto);
    }

    @PutMapping("/{id}")
    public TodoDto updateTodo(@PathVariable("id") Integer id, @RequestBody TodoDto todoDto) {
        return todoService.updateTodo(id, todoDto);
    }

    @PutMapping("/{id}/completed")
    public TodoDto updateCompleted(@PathVariable("id") Integer id, @RequestBody TodoDto todoDto) {
        return todoService.updateTodo(id, todoDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable("id") Integer id) {
        todoService.deleteTodo(id);
    }
}