package com.example.oauth.resource.app.todo;

import java.util.Collection;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.dozer.Mapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.oauth.resource.app.todo.TodoForm.TodoCreate;
import com.example.oauth.resource.app.todo.TodoForm.TodoDelete;
import com.example.oauth.resource.app.todo.TodoForm.TodoFinish;
import com.example.oauth.resource.domain.model.todo.Todo;
import com.example.oauth.resource.domain.service.todo.TodoService;

@Controller
@RequestMapping("todo")
public class TodoController {

	@Inject
	TodoService todoService;

	@Inject
	Mapper beanMapper;

	@ModelAttribute
	public TodoForm setUpForm() {
		TodoForm form = new TodoForm();
		return form;
	}

	@RequestMapping(value = "list")
	// ログインしたユーザの情報がuserに格納されるように
	// @AuthenticationPrincipal UserDetails user, を追加
	public String list(@AuthenticationPrincipal UserDetails user, Model model) {
		// メソッドfindAllを引数を指定して呼ぶように引数を追加
		Collection<Todo> todos = todoService.findAll(user.getUsername());
		model.addAttribute("todos", todos);
		return "todo/list";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Validated({ Default.class, TodoCreate.class }) TodoForm todoForm, BindingResult bindingResult,
			Model model, @AuthenticationPrincipal UserDetails user, // 追加
			RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return list(user, model); // 修正
		}

		Todo todo = beanMapper.map(todoForm, Todo.class);
		todo.setUsername(user.getUsername()); // 追加

		try {
			todoService.create(todo);
		} catch (BusinessException e) {
			model.addAttribute(e.getResultMessages());
			return list(user, model); // 修正
		}

		attributes.addFlashAttribute(ResultMessages.success().add(ResultMessage.fromText("Created successfully!")));
		return "redirect:/todo/list";
	}

	@RequestMapping(value = "finish", method = RequestMethod.POST)
	public String finish(@Validated({ Default.class, TodoFinish.class }) TodoForm form, BindingResult bindingResult,
			Model model, @AuthenticationPrincipal UserDetails user, // 追加
			RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return list(user, model); // 修正
		}

		try {
			todoService.finish(form.getTodoId(), user.getUsername()); // 修正
		} catch (BusinessException e) {
			model.addAttribute(e.getResultMessages());
			return list(user, model); // 修正
		}

		attributes.addFlashAttribute(ResultMessages.success().add(ResultMessage.fromText("Finished successfully!")));
		return "redirect:/todo/list";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(@Validated({ Default.class, TodoDelete.class }) TodoForm form, BindingResult bindingResult,
			Model model, @AuthenticationPrincipal UserDetails user, // 追加
			RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return list(user, model); // 修正
		}

		try {
			todoService.delete(form.getTodoId(), user.getUsername()); // 修正
		} catch (BusinessException e) {
			model.addAttribute(e.getResultMessages());
			return list(user, model); // 修正
		}

		attributes.addFlashAttribute(ResultMessages.success().add(ResultMessage.fromText("Deleted successfully!")));
		return "redirect:/todo/list";
	}

}
