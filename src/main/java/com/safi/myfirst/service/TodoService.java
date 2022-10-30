package com.safi.myfirst.service;

import com.safi.myfirst.dao.TodoRepository;
import com.safi.myfirst.domaino.Todo;
import com.safi.myfirst.errors.TodoExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService
{
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos(){

        List<Todo> list = new ArrayList();
        todoRepository.findAll().forEach(t -> list.add(t));
        return list;
    }
    public Long create(Todo t) throws TodoExistsException
    {

       Optional<Todo> exists= todoRepository.findOneByText(t.getText());

       if(exists.isPresent()){
           throw new TodoExistsException("todo text exist");
       }

        Todo inserted =  todoRepository.save(t);

        return  inserted.getId();
    }

}
