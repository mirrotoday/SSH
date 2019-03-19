package com.example.javatest.demotest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepo extends CrudRepository<Person,Long> {
    List<Person> findByAge(int age);
}
