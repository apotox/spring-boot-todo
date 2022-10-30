package com.safi.myfirst.domaino;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "userx")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;
    private String password;
    private String[] roles;


    public User(String username, String password, String[] roles)
    {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }


    public User()
    {
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public void setUsername(String username)
    {
        this.username = username;
    }


    public void setPassword(String password)
    {
        this.password = password;
    }


    public void setRoles(String[] roles)
    {
        this.roles = roles;
    }


    public Long getId()
    {
        return id;
    }


    public String getUsername()
    {
        return username;
    }


    public String getPassword()
    {
        return password;
    }


    public String[] getRoles()
    {
        return roles;
    }
}
