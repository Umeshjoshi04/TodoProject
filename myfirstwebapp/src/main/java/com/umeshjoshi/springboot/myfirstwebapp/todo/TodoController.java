package com.umeshjoshi.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes({"name", "password"})
public class TodoController {

	private TodoService todosService;
	
	public TodoController(TodoService todosService) {
		super();
		this.todosService = todosService;
	}

	@RequestMapping("list-todos")
	public String getAllTodos(ModelMap model) {
		List<Todo> todos = todosService.findByUsername("umeshjoshi");
		model.addAttribute("todos", todos);
		return "listTodos";
	}
	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username = (String)model.get("name");
		Todo todo =new Todo(0, username,
				"Default Desc",
				LocalDate.now().plusYears(1),
				false);
		model.addAttribute("todo", todo);
		return "todo";
	}
	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) return "todo";
		todosService.addTodo((String)model.get("name"), todo.getDescription(),
				LocalDate.now().plusYears(1), false);
		return "redirect:list-todos";
	}
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todosService.deleteById(id);
		return "redirect:list-todos";
	}
	@RequestMapping("update-todo")
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todosService.findById(id);
		model.addAttribute("todo", todo);
		return "todo";
	}
}
