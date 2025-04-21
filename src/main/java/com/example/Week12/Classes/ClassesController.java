package com.example.Week12.Classes; //Imports necessary classes and annotations for the Spring framework and JDBC operations

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate; //Imports JdbcTemplate class from Spring framework to interact with the database
import org.springframework.web.bind.annotation.*; //Imports all necessary annotation subclasses: getmapping, restcontroller, requestparam, etc.

import java.util.List;

@RestController
@RequestMapping("/classes") //Maps HTTP requests to the specified URL path ("/classes") to the methods in this class
public class ClassesController {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Classes> getAllClasses() { //Fetches all classes from the database when a GET request is made to "/classes"
        String sql = "SELECT * FROM classes;"; //SQL query to select all classes from the database
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Classes classes = new Classes();
            classes.setClassCode(rs.getString("class_code"));
            classes.setClassName(rs.getString("class_name"));
            return classes; //Returns a Classes object for each row in the result set
        });
    }

    @GetMapping("/{class_code}") //Fetches a specific class by class code when a GET request is made to "/classes/{class_code}"
    public Classes getClassesByClassCode(@PathVariable String class_code) {
        String sql = "SELECT * FROM classes WHERE class_code = ?"; //SQL query to select a class by class code, with ? as a placeholder for the class code parameter
        return jdbcTemplate.queryForObject(sql, new Object[]{class_code}, (rs, rowNum) -> {
            Classes classes = new Classes();
            classes.setClassCode(rs.getString("class_code"));
            classes.setClassName(rs.getString("class_name"));
            return classes; //returns the information of the class with the specified class code
        });
    }

    @PostMapping 
    public void createClass(@RequestBody Classes classes) { //Creates a new class when a POST request is made to "/classes" with the class information
        String sql = "INSERT INTO classes (class_name) VALUES (?)"; //SQL query to insert a new class into the database, where the class name is represented by the ? placeholder
        jdbcTemplate.update(sql, classes.getClassName()); //Executes the SQL query with the provided class information, and update database
    }

    @PutMapping("/{class_code}") //Updates an existing class when a PUT request is made to "/classes/{class_code}" with the updated class information in the request body
    public void updateClasses(@PathVariable String class_code, @RequestBody Classes classes) {
        String sql = "UPDATE classes SET class_name = ? WHERE class_code = ?"; //SQL query to update a class's information in the database, where the class name is represented by the ? placeholder and the class code is used to identify the class to be updated
        jdbcTemplate.update(sql, classes.getClassName(), class_code); //Executes the SQL query with the provided class information and class code, and update database
    }

    @DeleteMapping("/{class_code}") //Deletes a class when a DELETE request is made to "/classes/{class_code}"
    public void deleteClasses(@PathVariable String class_code) {
        String sql = "DELETE FROM classes WHERE class_code = ?"; //SQL query to delete a class from the database by class code
        jdbcTemplate.update(sql, class_code); //Execute the sql query and update the database
    }
}