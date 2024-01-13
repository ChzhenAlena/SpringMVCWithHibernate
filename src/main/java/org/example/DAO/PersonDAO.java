package org.example.DAO;

import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //private static int people_count;

    public List<Person> index(){

        return jdbcTemplate.query("Select * from Person", new BeanPropertyRowMapper<>(Person.class));
    }
    public Person show(int id){

        return jdbcTemplate.query("select * from Person where id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    public void save(Person person){
        jdbcTemplate.update("insert into person(name, age, email) values (?, ?, ?)", person.getName(), person.getAge(), person.getEmail());
    }
    public void update(int id, Person person){
        jdbcTemplate.update("update person set name=?, age=?, email=? where id=?", person.getName(), person.getAge(), person.getEmail(), person.getId());
    }
    public void delete(int id){
        jdbcTemplate.update("delete from person where id=?", id);
    }
}

