package com.example.oauth.resource.domain.service.todo;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.oauth.resource.domain.model.todo.Todo;
import com.example.oauth.resource.domain.repository.todo.TodoRepository;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

	private static final long MAX_UNFINISHED_COUNT = 5;

	@Inject
	TodoRepository todoRepository;

	public Todo findOne(String todoId, String username) { // 引数usernameを追加
		Todo todo = todoRepository.findOne(todoId, username); // 引数usernameを追加
		if (todo == null) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage.fromText("[E404] The requested Todo is not found. (id=" + todoId + ")"));
			throw new ResourceNotFoundException(messages);
		}
		return todo;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Todo> findAll(String username) { // 引数usernameを追加
		return todoRepository.findAll(username); // 引数usernameを追加
	}

	@Override
	public Todo create(Todo todo) {
		// 引数を追加
		long unfinishedCount = todoRepository.countByFinished(false, todo.getUsername());
		if (unfinishedCount >= MAX_UNFINISHED_COUNT) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage
					.fromText("[E001] The count of un-finished Todo must not be over " + MAX_UNFINISHED_COUNT + "."));
			throw new BusinessException(messages);
		}

		String todoId = UUID.randomUUID().toString();
		Date createdAt = new Date();

		todo.setTodoId(todoId);
		todo.setCreatedAt(createdAt);
		todo.setFinished(false);

		todoRepository.create(todo);

		return todo;
	}

	@Override
	public Todo finish(String todoId, String username) { // 引数usernameを追加
		Todo todo = findOne(todoId, username); // 引数usernameを追加
		if (todo.isFinished()) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage.fromText("[E002] The requested Todo is already finished. (id=" + todoId + ")"));
			throw new BusinessException(messages);
		}
		todo.setFinished(true);
		todoRepository.update(todo);
		return todo;
	}

	@Override
	public void delete(String todoId, String username) { // 引数usernameを追加
		Todo todo = findOne(todoId, username); // 引数usernameを追加
		todoRepository.delete(todo);
	}

	// usernameに依らず全てのtodoを返すメソッドを追加
	@Override
	public Collection<Todo> all() {
		return todoRepository.all();
	}
}
