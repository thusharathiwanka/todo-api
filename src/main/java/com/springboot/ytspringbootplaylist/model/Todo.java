package com.springboot.ytspringbootplaylist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todos")

public class Todo {
    @Id
    private String id;
    private String todo;
    private String description;
    private Boolean isCompleted;
    private Date createdAt;
    private Date updatedAt;
}
