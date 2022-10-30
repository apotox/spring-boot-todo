package com.safi.myfirst.domaino;

import com.safi.myfirst.domainv.TodoPrivacy;
import net.bytebuddy.implementation.bind.annotation.Default;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.NotBlank;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(name= "uc_text", columnNames = {"text"})
)
public class Todo
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Text is mandatory")
    private String text;

    @Enumerated(EnumType.STRING)
    private TodoPrivacy privacy;

    public Todo(String text, TodoPrivacy privacy)
    {
        this.text = text;
        this.privacy = privacy;
    }


    public TodoPrivacy getPrivacy()
    {
        return privacy;
    }


    public void setPrivacy(TodoPrivacy privacy)
    {
        this.privacy = privacy;
    }


    public Todo()
    {

    }


    public Long getId()
    {
        return id;
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
