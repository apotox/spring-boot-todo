package com.safi.myfirst.controller;

import com.safi.myfirst.controller.mapper.TodoMapper;
import com.safi.myfirst.domaino.Todo;
import com.safi.myfirst.dto.TodoDT;
import com.safi.myfirst.errors.TodoExistsException;
import com.safi.myfirst.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/todos")
public class TodoController
{

    private static final Logger log = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoDT>> getAllTodos(){
        log.info("handle get all todos request");
        return ResponseEntity.ok(todoService.getAllTodos().stream().map(TodoMapper::makeTodoDT).toList());
    }

    @PostMapping
    public Long createTodo(@RequestBody TodoDT t) throws TodoExistsException
    {
        return todoService.create(TodoMapper.makeTodoDO(t));
    }


}
