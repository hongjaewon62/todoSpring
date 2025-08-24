package com.todo.todoSpring;

import com.todo.todoSpring.Dto.TodoDto;
import com.todo.todoSpring.Entity.Todo;
import com.todo.todoSpring.Repository.TodoRepository;
import com.todo.todoSpring.Service.TodoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test") // 테스트 전용 DB 적용
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)        // 테스트 후 초기화
class TodoServiceTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoServiceImpl todoService;

    private TodoDto savedTodo;

    @BeforeEach
    void setUp() {
        todoRepository.deleteAll();         // 테스트 실행 전 DB 초기화

        Todo todo = new Todo();             // 새로운 할 일 생성
        todo.setText("테스트 할 일");
        todo.setCompleted(false);
        savedTodo = TodoDto.fromEntity(todoRepository.save(todo));
    }

    @Test
    void getTodos() {           // getTodos 테스트
        List<TodoDto> result = todoService.getTodos("all"); // 전체 조회
        assertThat(result).isNotEmpty();                             // 비어있는지 체크
        assertThat(result.get(0).getText()).isEqualTo("테스트 할 일");       // 첫 번째 텍스트가 일치한 지 체크
    }

    @Test
    void getTodoById() {        // getTodoById 테스트
        TodoDto result = todoService.getTodoById(savedTodo.getId());        // ID 조회
        assertThat(result.getText()).isEqualTo("테스트 할 일");
    }

    @Test
    void createTodo() {         // createTodo 테스트
        TodoDto newTodo = new TodoDto(null, "새로운 할 일", null, false);        // Todo생성
        TodoDto result = todoService.createTodo(newTodo);
        assertThat(result.getId()).isNotNull();         // ID가 자동 생성 되었는지 체크
        assertThat(result.getCreateAt()).isNotNull();   // CreateAt이 자동 생성 되었는지 체크
        assertThat(result.getText()).isEqualTo("새로운 할 일");
    }

    @Test
    void updateTodo() {         // updateTodo 테스트
        savedTodo.setText("수정된 할 일");       // 기존 텍스트 값 변경
        TodoDto result = todoService.updateTodo(savedTodo.getId(), savedTodo);
        assertThat(result.getText()).isEqualTo("수정된 할 일");
    }

    @Test
    void updateCompleted() {    // updateCompleted 테스트
        savedTodo.setCompleted(true);       // 할 일 완료 상태 변경
        TodoDto result = todoService.updateCompleted(savedTodo.getId(), savedTodo);
        assertThat(result.isCompleted()).isTrue();
    }

    @Test
    void deleteTodo() {         // deleteTodo 테스트
        todoService.deleteTodo(savedTodo.getId());      // 삭제
        List<TodoDto> todos = todoService.getTodos("all");      // 삭제 되었는지 전체 조회
        assertThat(todos).isEmpty();                // 목록이 비었는지 체크
    }
}