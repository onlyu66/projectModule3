package com.ra.model;

import com.ra.database.Database;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Product implements IProduct, Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    private String id;
    private String name;
    private double importPrice;
    private double exportPrice;
    private double profit;
    private String description;
    private boolean status;
    private int categoryId;

    public Product() {
    }

    public Product(String id, String name, double importPrice, double exportPrice, double profit, String description, boolean status, int categoryId) {
        this.id = id;
        this.name = name;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.description = description;
        this.status = status;
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public void inputData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input productId:");
        id=scanner.nextLine();
        do {
            id = scanner.nextLine();
            if(id.startsWith("P")){
                if(id.length()==4){
                    if (isOnlyProductId(id)){
                        break;
                    }else {
                        System.err.println("This id is existed! Please re-enter!");
                    }
                }else {
                    System.err.println(id+" must consist of 4 characters! Please re-enter!");
                }
            }else {
                System.err.println(id+" must start with 'P'! Please re-enter!");
            }
        }while(true);
        System.out.println("Input productName:");
        do {
            name=scanner.nextLine();
            if (isOnlyProductName(name)){
                if (name.length()>=6&&name.length()<=30){
                    break;
                }else {
                    System.err.println(name+" has to be from 6 to 30 characters! Please re-enter!");
                }
            }else {
                System.err.println(name+" is exited! Please re-enter!");
            }
        }while (true);
        System.out.println("Input productImportPrice:");
        do{
            importPrice=Double.parseDouble(scanner.nextLine());
            try{
                if(importPrice>0){
                    break;
                }else {
                    System.err.println(importPrice+" must be greater than 0! Please re-enter!");
                }
            }catch(Exception e){
                System.err.println(importPrice+" has to be a number! Please re-enter!");
            }
        }while(true);
        System.out.println("Input productExportPrice:");
        do{
            exportPrice=Double.parseDouble(scanner.nextLine());
            try{
                if(exportPrice>0){
                    if((exportPrice/importPrice)>=MIN_INTEREST_RATE){
                        break;
                    }else{
                        System.err.println("Export price must be greater than at least "+ MIN_INTEREST_RATE +" times the Import price value! Please re-enter!");
                    }
                }else {
                    System.err.println(exportPrice+" must be greater than 0! Please re-enter!");
                }
            }catch(Exception e){
                System.err.println(exportPrice+" has to be a number! Please re-enter!");
            }
        }while(true);
        System.out.println("Input productDescription:");
        do {
            description=scanner.nextLine();
            if(!description.isEmpty()){
                break;
            }else {
                System.err.println(description+" isn't empty! Please re-enter!");
            }
        }while (true);
        System.out.println("Input productStatus:");
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
        System.out.println("Input productCategoryId:");
        do {
            try{
                categoryId=Integer.parseInt(scanner.nextLine());
                if(isExistedCategoryId(categoryId)){
                    break;
                }
            }catch (Exception e){
                System.err.println(exportPrice+" has to be a number! Please re-enter!");
            }
        }while(true);
    }

    @Override
    public void displayData() {
        System.out.printf("%s\t\t%s\t\t%f\t\t%f\t\t%f\t\t%s\t\t%b\t\t%d",id,name,importPrice,exportPrice,calProfit(),description,status,categoryId);
    }

    @Override
    public double calProfit() {
        return exportPrice-importPrice;
    }

    public boolean isOnlyProductId(String id){
        File file = Database.createFile("product.txt");

        if (file.exists()) {
            @SuppressWarnings("unchecked")
            List<Product> products = (List<Product>) Database.getAllToFile(file);
            for (Product product : products) {
                if (id.equals(product.id) ) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isOnlyProductName(String name){
        File file = Database.createFile("product.txt");

        if (file.exists()) {
            @SuppressWarnings("unchecked")
            List<Product> products = (List<Product>) Database.getAllToFile(file);
            for (Product product : products) {
                if (name.equals(product.name)) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isExistedCategoryId(int categoryId){
        File file = Database.createFile("category.txt");

        if (file.exists()) {
            @SuppressWarnings("unchecked")
            List<Category> categories = (List<Category>) Database.getAllToFile(file);
            for (Category category : categories) {
                if(categoryId==category.getId()){
                    return true;
                }
            }
        }
        return false;
    }
}
