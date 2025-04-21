package com.example.Week12.Student; //Imports necessary classes and annotations for the Spring framework and JDBC operations

public class Student { //Creates a student class
    private int id; //Each student has a unique ID
    private String name; //Each student has a name
    private int age; // Each student has an age
    private String email; //Each student has an email address

    // constructors

    public Student() {
    }

    public Student(int id, String name, int age, String email) { //Constructor to initialize a student object with ID, name, age, and email specified within the parentheses
        setId(id); // Sets the ID of the student
        setName(name); // Sets the name of the student
        setAge(age);  // Sets the age of the student
        setEmail(email); // Sets the email of the student
    }

    // Getters and Setters

    public int getId() { //Returns the ID of the student
        return id;
    }

    public void setId(int id) { //Sets the ID of the student
        if (id < 0) { //Checks if the ID is less than 0
            throw new IllegalArgumentException("ID must be greater than 0"); //Id must be greater than 0
        }
        this.id = id; //Sets the ID of the student
    }

    public String getName() { //Returns the name of the student
        return name;
    }

    public void setName(String name) { //Sets the name of the student
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty"); //Error is thrown if the name is null or empty, as every student has to have a name
        }
        this.name = name;
    }

    public int getAge() { //Returns the age of the student
        return age;
    }

    public void setAge(int age) { //Sets the age of the student
        if (age < 0) { //Checks if the age is less than 0
            throw new IllegalArgumentException("Age must be greater than 0"); //Student cannot be a negative age, so an error message is thrown
        }
        this.age = age; //Sets the age of the student
    }

    public String getEmail() { //Returns the email of the student
        return email;
    }

    public void setEmail(String email) { //Sets the email of the student
        if (email == null || email.isEmpty()) { //Checks if the email is null or empty
            throw new IllegalArgumentException("Email cannot be null or empty"); //Error is thrown if the email is null or empty, as every student has to have an email address
        }
        this.email = email; //Sets the email of the student
    }

}

