package com.example.oauth.resource.app.todo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.oauth.resource.domain.model.todo.Todo;
import com.example.oauth.resource.domain.service.todo.TodoService;

@RestController
@RequestMapping("todos")
public class TodoRestController {

    @Inject
    TodoService todoService;
    @Inject
    Mapper beanMapper;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    // アノテーション@AuthenticationPrincipalが付けられた引数にユーザ情報が格納される。
    // 今回のように，リソースサーバが認可サーバとトークンを管理するDBを共有していれば，
    // UserDetailsのインスタンスが渡される。
    // トークンの管理にRemoteTokenServicesを使用していれば，通常は，ユーザ名が文字列として
    // 渡される。
    // 汎用性を持たせるために，Objectで受けて，演算子instanceofでクラスを判定し，
    // クラスごとの処理をするようにしてもよい。
    // public List<TodoResource> getTodos(@AuthenticationPrincipal Object priciple) {
    //     String username = null;
    //     if (principle instanceof UserDetails)
    //          username = ((UserDetails)principle).getUsername();
    //     else if (principle instanceof String)
    //          username = (String)principle;
    //     …
    // }
    public List<TodoResource> getTodos(@AuthenticationPrincipal UserDetails user) {
        String username = user.getUsername();
        Collection<Todo> todos = todoService.findAll(username);
        List<TodoResource> todoResources = new ArrayList<>();
        for (Todo todo : todos) {
            todoResources.add(beanMapper.map(todo, TodoResource.class));
        }
        return todoResources;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResource postTodos(@RequestBody @Validated TodoResource todoResource,
            @AuthenticationPrincipal UserDetails user) {
        String username = user.getUsername();
        Todo todo = beanMapper.map(todoResource, Todo.class);
        todo.setUsername(username);
        Todo createdTodo = todoService.create(todo);
        TodoResource createdTodoResponse = beanMapper.map(createdTodo, TodoResource.class);
        return createdTodoResponse;
    }

    @RequestMapping(value="{todoId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public TodoResource getTodo(@PathVariable("todoId") String todoId, @AuthenticationPrincipal UserDetails user) {
        String username = user.getUsername();
        Todo todo = todoService.findOne(todoId, username);
        TodoResource todoResource = beanMapper.map(todo, TodoResource.class);
        return todoResource;
    }

    @RequestMapping(value="{todoId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public TodoResource putTodo(@PathVariable("todoId") String todoId, @AuthenticationPrincipal UserDetails user) {
        String username = user.getUsername();
        Todo finishedTodo = todoService.finish(todoId, username);
        TodoResource finishedTodoResource = beanMapper.map(finishedTodo, TodoResource.class);
        return finishedTodoResource;
    }

    @RequestMapping(value="{todoId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable("todoId") String todoId, @AuthenticationPrincipal UserDetails user) {
        String username = user.getUsername();
        todoService.delete(todoId, username);
    }
}
