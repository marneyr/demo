package com.example.Week12.Student; //Imports necessary classes and annotations for the Spring framework and JDBC operations

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate; //Imports JdbcTemplate class from Spring framework to interact with the database
import org.springframework.web.bind.annotation.*; //imports all necessary annotation subclasses: getmapping, restcontroller, requestparam, etc.


import java.util.List;

@RestController
@RequestMapping("/students") //Maps HTTP requests to the specified URL path ("/students") to the methods in this class
public class StudentController { //Handles HTTP requests related to students

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Student> getAllStudents() { //Fetches all students from the database when a GET request is made to "/students"
        String sql = "SELECT * FROM students;"; //SQL query to select all students from the database
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Student student = new Student(); //Creates a new Student object to hold the data from the result set
            student.setId(rs.getInt("id")); //Sets the ID of the student from the result set
            student.setName(rs.getString("name"));
            student.setAge(rs.getInt("age"));
            student.setEmail(rs.getString("email"));
            return student; //Returns a Student object for each row in the result set
        });
    }

    @GetMapping("/{id}")    //Fetches a specific student by ID when a GET request is made to "/students/{id}"
    public Student getStudentById(@PathVariable int id) { 
        String sql = "SELECT * FROM students WHERE id = ?"; //SQL query to select a student by ID, with ? as a placeholder for the ID parameter
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Student student = new Student(); //Creates a new Student object to hold the data from the result set
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setAge(rs.getInt("age"));
            student.setEmail(rs.getString("email"));
            return student; //Returns the information of the student with the specified ID
        });
    }

    @PostMapping
    public void createStudent(@RequestBody Student student) { //Creates a new student when a POST request is made to "/students" with the student information in the request body
        String sql = "INSERT INTO students (name, age, email) VALUES (?, ?, ?)"; //SQL query to insert a new student into the database
        jdbcTemplate.update(sql, student.getName(), student.getAge(), student.getEmail()); //Executes the SQL query with the provided student information
    }

    @PutMapping("/{id}") //Updates an existing student when a PUT request is made to "/students/{id}" with the updated student information in the request body
    public void updateStudent(@PathVariable int id, @RequestBody Student student) { //Updates the student with the specified ID
        String sql = "UPDATE students SET name = ?, age = ?, email = ? WHERE id = ?"; //SQL query to update a student's information in the database
        jdbcTemplate.update(sql, student.getName(), student.getAge(), student.getEmail(), id);
    }

    @DeleteMapping("/{id}") //Deletes a student when a DELETE request is made to "/students/{id}"
    public void deleteStudent(@PathVariable int id) { //Deletes the student with the specified ID
        String sql = "DELETE FROM students WHERE id = ?"; //SQL query to delete a student from the database by ID
        jdbcTemplate.update(sql, id);
    }
}
