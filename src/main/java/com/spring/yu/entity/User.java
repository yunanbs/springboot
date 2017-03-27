package com.spring.yu.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by yunan on 2017/3/10.
 */
@Entity
@Table(name = "users")
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    int state;
    @Column(nullable = false)
    private Date created;
    @Column(nullable = false)
    private Date modifyed;

    public User()
    {

    }

    public User(String username, String password, int state, Date created, Date modifyed)
    {
        this.username = username;
        this.password = password;
        this.state = state;
        this.created = created;
        this.modifyed = modifyed;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getState()
    {
        return state;
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public Date getModifyed()
    {
        return modifyed;
    }

    public void setModifyed(Date modifyed)
    {
        this.modifyed = modifyed;
    }
}
