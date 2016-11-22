package com.example.oauth.resource.domain.repository.todo;

import java.util.Collection;

import com.example.oauth.resource.domain.model.todo.Todo;

public interface TodoRepository {

	Todo findOne(String todoId, String username); // 引数usernameを追加

	Collection<Todo> findAll(String username); // 引数usernameを追加

	Collection<Todo> all(); // usernameに依らず全てのtodoを返すメソッドを追加

	void create(Todo todo);

	boolean update(Todo todo);

	void delete(Todo todo);

	long countByFinished(boolean finished, String username); // 引数usernameを追加
}
