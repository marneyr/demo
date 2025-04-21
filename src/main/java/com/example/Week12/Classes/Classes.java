package com.example.Week12.Classes;

public class Classes { //Creates a class for classes
    
    private String class_code; //Each class has a unique class code
    private String class_name; //Each class has a name

    // Constructors
    public Classes() { // Default constructor
    } 

    public Classes(String class_code, String class_name) { // Constructor to initialize a class object with class code and class name specified within the parentheses
        setClassCode(class_code); //Set the class code of the class object
        setClassName(class_name); //Set the name of the class
    }

    // Getters and Setters

    public String getClassCode() { //Return the class code of the class
        return class_code;
    }

    public void setClassCode(String class_code) { //Set the class code of the class
        if (class_code == null || class_code.isEmpty()) { //Check if the class code is null or empty
            throw new IllegalArgumentException("Class code cannot be null or empty"); //Class must have a class code, so an error message is thrown if it does not
        }
        this.class_code = class_code; //Set the class code of the class
    }

    public String getClassName() { //Return the name of the class
        return class_name;
    }

    public void setClassName(String class_name) { //Set the name of the class
        if (class_name == null || class_name.isEmpty()) { //Check if the class name is null or empty
            throw new IllegalArgumentException("Class name cannot be null or empty"); //Class must have a name, so an error message is thrown if it does not
        }
        this.class_name = class_name; //Set the name of the class
    }
}
