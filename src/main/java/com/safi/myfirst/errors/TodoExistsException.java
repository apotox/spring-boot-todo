package com.safi.myfirst.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "todos text already exists")
public class TodoExistsException extends Exception
{

    public TodoExistsException(String message)
    {
        super(message);
    }
}
