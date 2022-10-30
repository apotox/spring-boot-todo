package com.safi.myfirst.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.safi.myfirst.domainv.TodoPrivacy;
import org.springframework.lang.NonNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodoDT
{
    @NonNull
    private String text;

    private TodoPrivacy privacy;

    public TodoDT(String text,TodoPrivacy privacy)
    {
        this.text = text;
        this.privacy = privacy;
    }
    public TodoDT()
    {
    }


    public TodoPrivacy getPrivacy()
    {
        return privacy;
    }


    public void setPrivacy(TodoPrivacy privacy)
    {
        this.privacy = privacy;
    }


    public String getText()
    {
        return text;
    }


    public void setText(String text)
    {
        this.text = text;
    }
}
