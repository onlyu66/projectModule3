package com.ra.model;

import com.ra.database.Database;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Category implements ICategory, Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    private int id;
    private String name;
    private String description;
    private boolean status;

    public Category() {
    }

    public Category(int id, String name, String description, boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter categoryId: ");
        do {
            try{
                id = Integer.parseInt(scanner.nextLine());
                if(id>0){
                    if (isOnlyCategoryId(id)){
                        break;
                    }else {
                        System.err.println("This id is existed! Please re-enter!");
                    }
                }else {
                    System.err.println(id+" has to be greater than 0! Please re-enter!");
                }
            }catch (Exception e){
                    System.err.println(id+" has to be a number! Please re-enter!");
            }
        }while(true);

        System.out.println("Enter categoryName: ");
        do {
            name=scanner.nextLine();
            if (isOnlyCategoryName(name)){
                if (name.length()>=6&&name.length()<=30){
                    break;
                }else {
                    System.err.println(name+" has to be from 6 to 30 characters! Please re-enter!");
                }
            }else {
                System.err.println(name+" is exited! Please re-enter!");
            }
        }while (true);

        System.out.println("Enter categoryDescription: ");
        do {
            description=scanner.nextLine();
            if(!description.isEmpty()){
                break;
            }else {
                System.err.println(description+" isn't empty! Please re-enter!");
            }
        }while (true);

        System.out.println("Enter categoryStatus (true/false): ");
        do {
            try {
                // Read the user input as a string
                String input = scanner.nextLine().toLowerCase(); // Convert to lowercase for case-insensitive comparison

                // Check if the input is either "true" or "false"
                if (input.equals("true") || input.equals("false")) {
                    // If valid, parse and assign the boolean value to the status variable
                    status = Boolean.parseBoolean(input);
                    break; // Exit the loop if the input is valid
                } else {
                    // If not valid, display an error message
                    System.err.println("Invalid input! Please enter 'true' or 'false'!");
                }
            } catch (Exception e) {
                // Handle other exceptions if necessary
                System.err.println("Error processing input. Please try again!");
            }
        } while (true);

    }

    @Override
    public void displayData() {
        System.out.printf("%d\t\t%s\t\t%s\t\t%b\n",id,name,description,status);
    }

    public boolean isOnlyCategoryId(int id){
        File file = Database.createFile("category.txt");

        if (file.exists()) {
            @SuppressWarnings("unchecked")
            List<Category> categories = (List<Category>) Database.getAllToFile(file);
            for (Category category : categories) {
                if (id == category.id) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isOnlyCategoryName(String name){
        File file = Database.createFile("category.txt");

        if (file.exists()) {
            @SuppressWarnings("unchecked")
            List<Category> categories = (List<Category>) Database.getAllToFile(file);
            for (Category category : categories) {
                if (name.equals(category.name)) {
                    return false;
                }
            }
        }
        return true;
    }
}
