package com.todo.todoSpring.Service;

import com.todo.todoSpring.Dto.TodoDto;
import com.todo.todoSpring.Entity.Todo;
import com.todo.todoSpring.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;

    @Override
    public List<TodoDto> getTodos(String sortOption) {
        List<Todo> todos;

        switch (sortOption) {
            case "latest": // 최신순
                todos = todoRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt"));
                break;
            case "oldest": // 오래된순
                todos = todoRepository.findAll(Sort.by(Sort.Direction.ASC, "createAt"));
                break;
            case "completed": // 완료
                todos = todoRepository.findByCompleted(true, Sort.by(Sort.Direction.DESC, "createAt"));
                break;
            case "notCompleted": // 미완료
                todos = todoRepository.findByCompleted(false, Sort.by(Sort.Direction.DESC, "createAt"));
                break;
            default: // 전체
                todos = todoRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt"));
                break;
        }

        return todos.stream().map(TodoDto::fromEntity).toList();
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
