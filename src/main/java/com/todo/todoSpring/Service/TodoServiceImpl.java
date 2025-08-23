package com.todo.todoSpring.Service;

import com.todo.todoSpring.Dto.TodoDto;
import com.todo.todoSpring.Entity.Todo;
import com.todo.todoSpring.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;

    @Override           // 전체 조회
    public List<TodoDto> getAllTodos() {
        return todoRepository.findAll().stream().map(TodoDto::fromEntity).collect(Collectors.toList());
    }

    @Override           // ID로 조회
    public TodoDto getTodoById(Integer id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("할 일을 찾을 수 없습니다."));
        return TodoDto.fromEntity(todo);
    }

    @Override           // 생성
    public TodoDto createTodo(TodoDto todoDto) {
        Todo todo = todoRepository.save(todoDto.toEntity());
        return TodoDto.fromEntity(todo);
    }

    @Override           // 수정
    public TodoDto updateTodo(Integer id, TodoDto todoDto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("할 일을 찾을 수 없습니다."));

        todo.setText(todoDto.getText());
        todo.setCompleted(todoDto.isCompleted());

        Todo updated = todoRepository.save(todo);
        return TodoDto.fromEntity(updated);
    }

    @Override           // Completed 수정
    public TodoDto updateCompleted(Integer id, TodoDto todoDto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("할 일을 찾을 수 없습니다."));

        todo.setCompleted(todoDto.isCompleted());

        Todo updated = todoRepository.save(todo);
        return TodoDto.fromEntity(updated);
    }

    @Override           // 삭제
    public void deleteTodo(Integer id) {
        todoRepository.deleteById(id);
    }
}
