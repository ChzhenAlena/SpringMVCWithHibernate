package org.example.DAO;

import org.example.models.Person;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static int people_count;
    private List<Person> people;
    {
        people = new ArrayList<>();
        people.add(new Person(++people_count, "Tom"));
        people.add(new Person(++people_count, "Bob"));
        people.add(new Person(++people_count, "Alex"));
    }
    public List<Person> index(){
        return people;
    }
    public Person show(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
    public void save(Person person){
        person.setId(++people_count);
        people.add(person);
    }
    public void update(int id, Person person){
        Person person_updated = show(id);
        person_updated.setName(person.getName());
    }
    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
    }
}

