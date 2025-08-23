package com.todo.todoSpring.Dto;

import com.todo.todoSpring.Entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private Integer id;
    private String text;
    private LocalDateTime createAt;
    private boolean completed;

    // Entity -> DTO
    public static TodoDto fromEntity(Todo todo) {
        return new TodoDto(
                todo.getId(),
                todo.getText(),
                todo.getCreateAt(),
                todo.isCompleted()
        );
    }

    // DTO → Entity
    public Todo toEntity() {
        Todo todo = new Todo();
        todo.setId(this.id);
        todo.setText(this.text);
        todo.setCreateAt(this.createAt);
        todo.setCompleted(this.completed);
        return todo;
    }
}
