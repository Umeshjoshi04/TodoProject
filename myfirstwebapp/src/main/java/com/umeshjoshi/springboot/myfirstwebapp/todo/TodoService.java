package com.umeshjoshi.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

	public static List<Todo> todos = new ArrayList<Todo>();
	
	private static int todoCount = 0 ;
	
	static {
		todos.add(new Todo(++todoCount, "umeshjoshi", "learn java",
				LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todoCount, "umeshjoshi", "learn devops",
				LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todoCount, "umeshjoshi", "learn aws",
				LocalDate.now().plusYears(3), false));
	}
	
	public List<Todo> findByUsername(String username) {
		return todos;
	}
	public void addTodo(String userName, String description, LocalDate date, Boolean done) {
		Todo todo = new Todo(++todoCount, userName, description, date, done);
		todos.add(todo);
	}
}
