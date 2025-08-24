package com.todo.todoSpring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.todoSpring.Dto.TodoDto;
import com.todo.todoSpring.Repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;        // 컨테이너 없이 가짜로 호출하고 응답을 검증하는 도구

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Integer todoId;

    @BeforeEach
    void setUp() {
        todoRepository.deleteAll();     // 테스트 실행 전 DB 초기화
        var todo = new com.todo.todoSpring.Entity.Todo();       // 생성
        todo.setText("테스트 할 일");
        todo.setCompleted(false);
        todoId = todoRepository.save(todo).getId();
    }

    @Test
    void getTodos() throws Exception {          // getTodos 전체 조회 테스트
        mockMvc.perform(get("/api/todoList"))
                .andExpect(status().isOk())         // 상태가 200 인지 체크
                .andExpect(jsonPath("$[0].text", is("테스트 할 일")));       // 텍스트가 일치한 지 체크
    }

    @Test
    void getTodoById() throws Exception {       // getTodoById ID로 조회 테스트
        mockMvc.perform(get("/api/todoList/" + todoId))
                .andExpect(status().isOk())         // 상태가 200 인지 체크
                .andExpect(jsonPath("$.text", is("테스트 할 일")));
    }

    @Test
    void createTodo() throws Exception {        // createTodo 생성 테스트
        TodoDto dto = new TodoDto(null, "새로운 할 일", null, false);        // 생성

        mockMvc.perform(post("/api/todoList")
                        .contentType(MediaType.APPLICATION_JSON)        // Json 형식임을 명시
                        .content(objectMapper.writeValueAsString(dto))) // Dto를 JSON 문자열로 직렬화
                .andExpect(status().isOk())         // 상태가 200 인지 체크
                .andExpect(jsonPath("$.text", is("새로운 할 일")));
    }

    @Test
    void updateTodo() throws Exception {        // updateTodo 수정 테스트
        TodoDto dto = new TodoDto(todoId, "수정된 할 일", null, true);       // 수정할 할 일 생성

        mockMvc.perform(put("/api/todoList/" + todoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())     // 상태가 200 인지 체크
                .andExpect(jsonPath("$.text", is("수정된 할 일")))       // 수정 되었는지 체크
                .andExpect(jsonPath("$.completed", is(true)));
    }

    @Test
    void updateCompleted() throws Exception {   // updateCompleted Completed 변경 테스트
        TodoDto dto = new TodoDto(todoId, "테스트 할 일", null, true);       // 수정할 할 일 생성

        mockMvc.perform(put("/api/todoList/" + todoId + "/completed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())     // 상태가 200 인지 체크
                .andExpect(jsonPath("$.completed", is(true)));
    }

    @Test
    void deleteTodo() throws Exception {        // deleteTodo 삭제 테스트
        mockMvc.perform(delete("/api/todoList/" + todoId))
                .andExpect(status().isOk());        // 삭제 성공 시 200

        mockMvc.perform(get("/api/todoList/" + todoId))     // 삭제 후 조회
                .andExpect(status().isNotFound())           // 상태가 404 인지 체크
                .andExpect(content().string("할 일을 찾을 수 없습니다."));
    }
}
