package com.umeshjoshi.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

public class TodoService {

	public static List<Todo> todos;
	
	static {
		todos.add(new Todo(1, "umeshjoshi", "learn java",
				LocalDate.now().plusYears(1), false));
		todos.add(new Todo(1, "umeshjoshi", "learn devops",
				LocalDate.now().plusYears(2), false));
		todos.add(new Todo(1, "umeshjoshi", "learn aws",
				LocalDate.now().plusYears(3), false));
	}
	
	public List<Todo> findByUsername(String username) {
		return todos;
	}
}
