package com.springboot.ytspringbootplaylist.controller;

import com.springboot.ytspringbootplaylist.model.Todo;
import com.springboot.ytspringbootplaylist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();

        if(todos.size() > 0) {
            return new ResponseEntity<List<Todo>>(todos, HttpStatus.OK);
        }

        return new ResponseEntity<String>("No todos available", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody Todo todo) {
        try {
            todo.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todo);
            return new ResponseEntity<Todo>(todo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getTodo(@PathVariable("id") String id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if(todoOptional.isPresent()) {
            return new ResponseEntity<Optional>(todoOptional, HttpStatus.OK);
        }

        return new ResponseEntity<String>("Todo not found with id " + id,
                HttpStatus.NOT_FOUND);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable("id") String id, @RequestBody Todo todo) {
        Optional<Todo> todoOptional = todoRepository.findById(id);

        if(todoOptional.isPresent()) {
            Todo todoToSave = todoOptional.get();

            todoToSave.setIsCompleted(todo.getIsCompleted() != null ? todo.getIsCompleted()
                    : todoToSave.getIsCompleted());
            todoToSave.setTodo(todo.getTodo() != null ? todo.getTodo() : todoToSave.getTodo());
            todoToSave.setDescription(todo.getDescription() != null ? todo.getDescription()
                    : todoToSave.getDescription());
            todoToSave.setUpdatedAt(new Date(System.currentTimeMillis()));

            todoRepository.save(todoToSave);

            return new ResponseEntity<Todo>(todoToSave, HttpStatus.OK);
        }

        return new ResponseEntity<String>("Todo not found with id " + id,
                HttpStatus.NOT_FOUND);
    }
}
