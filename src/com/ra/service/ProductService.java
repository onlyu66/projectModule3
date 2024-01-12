package com.ra.service;

import com.ra.database.Database;
import com.ra.function.Function;
import com.ra.model.Category;
import com.ra.model.Product;

import java.io.File;
import java.util.List;
import java.util.Scanner;


public class ProductService {
    static File file = Database.createFile("product.txt");
   @SuppressWarnings("unchecked")
   List<Product> products = (List<Product>) Database.getAllToFile(file);
   public static void addCategory() {
      Scanner scanner = new Scanner(System.in);
      @SuppressWarnings("unchecked")
      List<Product> products = (List<Product>) Database.getAllToFile(file);
      do {
         System.out.println("Enter product's information:");
         // khoi tao doi tuong sinh
         Product product = new Product();
         product.inputData();
         products.add(product);
         Database.saveToFile(file, products);
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
   public static void displayProduct() {
      @SuppressWarnings("unchecked")
      List<Product> products = (List<Product>) Database.getAllToFile(file);
      // Headers
      String[] headers = {"Product Id", "Product Name", "Description", "Status"};

      // Data
      String[][] data = new String[products.size()][4];

      // Populate the data array from the list of Category objects
      for (int i = 0; i < products.size(); i++) {
         Product product = products.get(i);
         data[i][0] = String.valueOf(product.getId());
         data[i][1] = product.getName();
         data[i][2] = product.getDescription();
         data[i][3] = String.valueOf(product.getStatus());
      }
      Function.displayTable(headers, data);
      System.out.println();
   }
}
