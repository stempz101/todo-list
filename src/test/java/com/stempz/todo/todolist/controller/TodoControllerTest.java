package com.stempz.todo.todolist.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.stempz.todo.todolist.model.TodoItem;
import com.stempz.todo.todolist.repository.TodoItemRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class TodoControllerTest {

  @Mock
  private TodoItemRepository todoItemRepository;

  @InjectMocks
  private TodoController todoController;

  @Test
  public void testGetAllTodoItems() {
    // Arrange
    TodoItem item1 = new TodoItem("Task 1", "Description 1");
    TodoItem item2 = new TodoItem("Task 2", "Description 2");
    List<TodoItem> todoItems = Arrays.asList(item1, item2);
    when(todoItemRepository.findAll()).thenReturn(todoItems);

    // Act
    List<TodoItem> result = todoController.getAllTodoItems();

    // Assert
    assertEquals(2, result.size());
    verify(todoItemRepository, times(1)).findAll();
  }

  @Test
  public void testGetTodoItemById_ExistingItem() {
    // Arrange
    Long itemId = 1L;
    TodoItem todoItem = new TodoItem("Task 1", "Description 1");
    when(todoItemRepository.findById(itemId)).thenReturn(Optional.of(todoItem));

    // Act
    ResponseEntity<TodoItem> result = todoController.getTodoItemById(itemId);

    // Assert
    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(todoItem, result.getBody());
    verify(todoItemRepository, times(1)).findById(itemId);
  }

  @Test
  public void testGetTodoItemById_NonExistingItem() {
    // Arrange
    Long itemId = 1L;
    when(todoItemRepository.findById(itemId)).thenReturn(Optional.empty());

    // Act
    ResponseEntity<TodoItem> result = todoController.getTodoItemById(itemId);

    // Assert
    assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    verify(todoItemRepository, times(1)).findById(itemId);
  }

  @Test
  public void testCreateTodoItem() {
    // Arrange
    TodoItem newTodoItem = new TodoItem("New Task", "New Description");
    when(todoItemRepository.save(newTodoItem)).thenReturn(newTodoItem);

    // Act
    TodoItem result = todoController.createTodoItem(newTodoItem);

    // Assert
    assertEquals(newTodoItem, result);
    verify(todoItemRepository, times(1)).save(newTodoItem);
  }

  @Test
  public void testUpdateTodoItem_ExistingItem() {
    // Arrange
    Long itemId = 1L;
    TodoItem existingTodoItem = new TodoItem("Task 1", "Description 1");
    TodoItem updatedTodoItem = new TodoItem("Updated Task 1", "Updated Description 1");

    when(todoItemRepository.findById(itemId)).thenReturn(Optional.of(existingTodoItem));
    when(todoItemRepository.save(existingTodoItem)).thenReturn(existingTodoItem);

    // Act
    ResponseEntity<TodoItem> result = todoController.updateTodoItem(itemId, updatedTodoItem);

    // Assert
    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(updatedTodoItem, result.getBody());
    verify(todoItemRepository, times(1)).findById(itemId);
    verify(todoItemRepository, times(1)).save(existingTodoItem);
  }

  @Test
  public void testUpdateTodoItem_NonExistingItem() {
    // Arrange
    Long itemId = 1L;
    TodoItem updatedTodoItem = new TodoItem("Updated Task 1", "Updated Description 1");

    when(todoItemRepository.findById(itemId)).thenReturn(Optional.empty());

    // Act
    ResponseEntity<TodoItem> result = todoController.updateTodoItem(itemId, updatedTodoItem);

    // Assert
    assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    verify(todoItemRepository, times(1)).findById(itemId);
    verify(todoItemRepository, never()).save(any(TodoItem.class));
  }

  @Test
  public void testDeleteTodoItem() {
    // Arrange
    Long itemId = 1L;

    // Act
    ResponseEntity<Void> result = todoController.deleteTodoItem(itemId);

    // Assert
    assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    verify(todoItemRepository, times(1)).deleteById(itemId);
  }
}
