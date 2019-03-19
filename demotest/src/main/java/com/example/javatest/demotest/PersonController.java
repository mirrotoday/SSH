package com.example.javatest.demotest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.javatest.demotest.Person;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonController {


    @Autowired
    PersonRepo repository;
    //查询all
    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        repository.findAll().forEach(persons::add);
        return persons;
    }

    @PostMapping("/person")
    public Person postPerson(@RequestBody Person person) {
        Person _person = repository.save(person);
        System.out.println(person.getName());
        return _person;
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable("id") long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Person has been deleted!", HttpStatus.OK);
    }


    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
        Optional<Person> personData = repository.findById(id);
        if (personData.isPresent()) {
            Person _person = personData.get();
            _person.setName(person.getName());
            _person.setAge(person.getAge());
            _person.setNo(person.getNo());
            _person.setGender(person.isGender());
            return new ResponseEntity<>(repository.save(_person), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //根据age查询all
    @GetMapping("persons/age/{age}")
    public List<Person> findByAge(@PathVariable int age) {
        List<Person> persons = repository.findByAge(age);
        return persons;
    }
}
