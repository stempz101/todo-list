package com.stempz.todo.todolist.controller;

import com.stempz.todo.todolist.model.TodoItem;
import com.stempz.todo.todolist.repository.TodoItemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

  private final TodoItemRepository todoItemRepository;

  public TodoController(TodoItemRepository todoItemRepository) {
    this.todoItemRepository = todoItemRepository;
  }

  @GetMapping
  public List<TodoItem> getAllTodoItems() {
    return todoItemRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<TodoItem> getTodoItemById(@PathVariable Long id) {
    Optional<TodoItem> todoItem = todoItemRepository.findById(id);
    return todoItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public TodoItem createTodoItem(@RequestBody TodoItem todoItem) {
    return todoItemRepository.save(todoItem);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TodoItem> updateTodoItem(@PathVariable Long id, @RequestBody TodoItem updatedTodoItem) {
    Optional<TodoItem> existingTodoItem = todoItemRepository.findById(id);
    if (existingTodoItem.isPresent()) {
      TodoItem todoItem = existingTodoItem.get();
      todoItem.setTitle(updatedTodoItem.getTitle());
      todoItem.setDescription(updatedTodoItem.getDescription());
      todoItemRepository.save(todoItem);
      return ResponseEntity.ok(todoItem);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTodoItem(@PathVariable Long id) {
    todoItemRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
