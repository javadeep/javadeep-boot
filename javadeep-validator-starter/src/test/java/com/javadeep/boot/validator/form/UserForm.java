package com.javadeep.boot.validator.form;

import com.javadeep.boot.validator.group.Group1;
import com.javadeep.boot.validator.group.Group2;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;

public class UserForm {

    @Max(value = 100, groups = Group1.class)
    private Integer id;

    @Length(max = 10, groups = Group2.class)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
