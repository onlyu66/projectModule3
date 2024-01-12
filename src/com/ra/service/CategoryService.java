package com.ra.service;

import com.ra.database.Database;
import com.ra.function.Function;
import com.ra.model.Category;

import java.io.File;
import java.util.*;

public class CategoryService {
    static File file = Database.createFile("category.txt");

    public static void addCategory() {
        Scanner scanner = new Scanner(System.in);
        @SuppressWarnings("unchecked")
        List<Category> categories = (List<Category>) Database.getAllToFile(file);
        do {
            System.out.println("Enter category's information:");
            // khoi tao doi tuong sinh
            Category category = new Category();
            category.inputData();
            categories.add(category);
            Database.saveToFile(file, categories);
            System.out.println("Do you want to continue entering?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choice;
            do {
                choice = Integer.parseInt(scanner.nextLine());
                if(choice==1 || choice==2){
                    break;
                }else {
                    System.err.println("Please enter 1 or 2!");
                }
            }while(true);
            if(choice==2){
                break;
            }
        } while (true);
    }

    public static void displayCategory() {
        @SuppressWarnings("unchecked")
        List<Category> categories = (List<Category>) Database.getAllToFile(file);
        // Headers
        String[] headers = {"Category Id", "Category Name", "Description", "Status"};

        // Data
        String[][] data = new String[categories.size()][4];

        // Populate the data array from the list of Category objects
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            data[i][0] = String.valueOf(category.getId());
            data[i][1] = category.getName();
            data[i][2] = category.getDescription();
            data[i][3] = String.valueOf(category.getStatus());
        }
//        System.out.println("======CATEGORY TABLE==========");
        Function.displayTable(headers, data);
        System.out.println();
    }

    public static void updateCategory() {
        Scanner scanner = new Scanner(System.in);
        @SuppressWarnings("unchecked")
        List<Category> categories = (List<Category>) Database.getAllToFile(file);
        boolean flag = false;
        System.out.println("Enter categoryId that you want to update:");
        do {
            try {
                int categoryId = Integer.parseInt(scanner.nextLine());
                for (Category category : categories) {
                    if (categoryId == category.getId()) {
                        flag = true;
                        do {
                            System.out.println("Do you want update categoryName?\n 1. Yes\n 2. No\n");
                            int choose = Integer.parseInt(scanner.nextLine());
                            if (choose == 1) {
                                System.out.print("Update " + category.getName() + " => ");
                                String updateCategoryName = scanner.nextLine();
                                category.setName(updateCategoryName);
                                break;
                            } else if (choose == 2) {
                                break;
                            } else {
                                System.err.println("Please enter 1 or 2!");
                            }
                        } while (true);
                        do {
                            System.out.println("Do you want update description?\n 1. Yes\n 2. No\n");
                            int choose = Integer.parseInt(scanner.nextLine());
                            if (choose == 1) {
                                System.out.print("Update " + category.getDescription() + " => ");
                                String updateDescription = scanner.nextLine();
                                category.setDescription(updateDescription);
                                break;
                            } else if (choose == 2) {
                                break;
                            } else {
                                System.err.println("Please enter 1 or 2!");
                            }
                        } while (true);
                        do {
                            System.out.println("Do you want update categoryStatus?\n 1. Yes\n 2. No\n");
                            int choose = Integer.parseInt(scanner.nextLine());
                            if (choose == 1) {
                                System.out.print("Update " + category.getStatus() + " => ");
//                                boolean updateCategoryStatus;
                                do {
                                    // Read the user input as a string
                                    String input = scanner.nextLine().toLowerCase(); // Convert to lowercase for case-insensitive comparison

                                    // Check if the input is either "true" or "false"
                                    if (input.equals("true") || input.equals("false")) {
                                        // If valid, parse and assign the boolean value to the status variable
                                        boolean updateCategoryStatus = Boolean.parseBoolean(input);
                                        category.setStatus(updateCategoryStatus);
                                        break; // Exit the loop if the input is valid
                                    } else {
                                        // If not valid, display an error message
                                        System.err.println("Invalid input! Please enter 'true' or 'false'!");
                                    }
                                }while(true);
                                break;
                            } else if (choose == 2) {
                                break;
                            } else {
                                System.err.println("Please enter 1 or 2!");
                            }
                        } while (true);
                    }
                }
                if(!flag){
                    System.err.println(categoryId+" isn't existing in categories! Please re-enter!");
                }else {
                    Database.saveToFile(file, categories);
                    break;
                }
            } catch (Exception e) {
                System.err.println("categoryId has to be a number! Please re-enter!");
            }
        }while (true);


    }


    public static void deleteCategory() {
        Scanner scanner = new Scanner(System.in);
        @SuppressWarnings("unchecked")
        List<Category> categories = (List<Category>) Database.getAllToFile(file);
        System.out.println("Enter categoryId that you want to delete:");
//        do{
//            try{
//                int categoryId=Integer.parseInt(scanner.nextLine());
//                for (Category category : categories) {
//                    if(categoryId==category.getId()){
//                        categories.remove(categoryId);
//                        break;
//                    }
//                }
//            }catch (Exception e){
//                System.err.println("categoryId has to be a number");
//            }
//        }while(true);
        boolean flag=true;
        boolean flag_=false;
        while (flag) {
            try {
                String input = scanner.nextLine();

                // Check if the user wants to exit
//                if (!input.matches("\\d+")) {
//                    break;
//                }

                int categoryId = Integer.parseInt(input);
                Iterator<Category> iterator = categories.iterator();

                while (iterator.hasNext()) {
                    Category category = iterator.next();
                    if (categoryId == category.getId()) {
                        iterator.remove();
                        System.out.println("Category deleted successfully.");
                        flag=false;
                        flag_=true;
                        break;
                    }
                }
                if(!flag_){
                    System.err.println(categoryId+" isn't existing in categories! Please re-enter!");
                }else {
                    Database.saveToFile(file,categories);
                    break;
                }
            } catch (NumberFormatException e) {
                System.err.println("categoryId has to be a number");
            }
        }

    }

    public static void searchByCategoryName() {
        Scanner scanner = new Scanner(System.in);
        @SuppressWarnings("unchecked")
        List<Category> categories = (List<Category>) Database.getAllToFile(file);
        do {
            System.out.println("Enter keyword that you want to search:");
            String keyword = scanner.nextLine();
            // Headers
            String[] headers = {"Category Id", "Category Name", "Description", "Status"};
            // Data
            List<String[]> matchingData = new ArrayList<>();
            for (Category category : categories) {
                if (category.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    // Populate the data array for each matching category
                    String[] rowData = new String[4];
                    rowData[0] = String.valueOf(category.getId());
                    rowData[1] = category.getName();
                    rowData[2] = category.getDescription();
                    rowData[3] = String.valueOf(category.getStatus());
                    matchingData.add(rowData);
                }
            }
            // Display the matching data outside of the loop
            Function.displayTable(headers, matchingData.toArray(new String[0][0]));
            System.out.println();
            System.out.println("Do you want to continue searching?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choice;
            do {
                choice = Integer.parseInt(scanner.nextLine());
                if(choice==1 || choice==2){
                    break;
                }else {
                    System.err.println("Please enter 1 or 2!");
                }
            }while(true);
            if(choice==2){
                break;
            }
        }while (true);
    }

    public static int quantityOfProductsBelongingToTheCategory(String categoryName) {
        return 0;
    }
}


