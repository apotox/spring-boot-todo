package com.safi.myfirst.controller.mapper;

import com.safi.myfirst.domaino.Todo;
import com.safi.myfirst.dto.TodoDT;

public class TodoMapper
{

    public static Todo makeTodoDO(TodoDT t){
        return new Todo(t.getText(), t.getPrivacy());
    }

    public static TodoDT makeTodoDT(Todo t){
        return new TodoDT(t.getText(), t.getPrivacy());
    }
}
