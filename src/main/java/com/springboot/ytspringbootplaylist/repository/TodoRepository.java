package com.springboot.ytspringbootplaylist.repository;

import com.springboot.ytspringbootplaylist.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {

}
