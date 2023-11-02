package com.umeshjoshi.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes({ "name", "password" })
public class TodoControllerJpa {

	private TodoRepository todoRepository;

	public TodoControllerJpa(TodoService todosService, TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}

	@RequestMapping("list-todos")
	public String getAllTodos(ModelMap model) {
		String username = getLoggedInUsername(model);
		List<Todo> todos = todoRepository.findByUsername(username);
		model.addAttribute("todos", todos);
		return "listTodos";
	}


	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username = getLoggedInUsername(model);
		Todo todo = new Todo(0, username, "Default Desc", LocalDate.now().plusYears(1), false);
		model.addAttribute("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors())
			return "todo";
		
		todo.setUsername(getLoggedInUsername(model));
//		todosService.addTodo(getLoggedInUsername(model), todo.getDescription(), todo.getTargetDate(), todo.isDone());
		//todoRepository.save(new Todo(' ', getLoggedInUsername(model), todo.getDescription(), LocalDate.now().plusYears(2), todo.isDone()));
		todoRepository.save(todo);

		return "redirect:list-todos";
	}

	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoRepository.deleteById(id);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todoRepository.findById(id).get();
		model.addAttribute("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}

		String username = getLoggedInUsername(model);
		todo.setUsername(username);
//		todosService.updateTodo(todo);
		todoRepository.deleteById(todo.getId());
		todoRepository.save(todo);
		return "redirect:list-todos";
	}
	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder
				.getContext()
				.getAuthentication();
		return authentication.getName();
	}
}
