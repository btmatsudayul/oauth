package com.example.oauth.resource.domain.service.todo;

import java.util.Collection;

import com.example.oauth.resource.domain.model.todo.Todo;

public interface TodoService {

	Todo findOne(String todoId, String username);

	Collection<Todo> findAll(String username); // 引数usernameを追加

	Todo create(Todo todo);

	Todo finish(String todoId, String username); // 引数usernameを追加

	void delete(String todoId, String username); // 引数usernameを追加

	Collection<Todo> all(); // usernameに依らず全てのtodoを返すメソッドを追加
}
