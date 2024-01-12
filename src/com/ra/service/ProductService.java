package com.ra.service;

import com.ra.database.Database;
import com.ra.model.Product;

import java.io.File;
import java.util.List;


public class ProductService {
   File file = Database.createFile("product.txt");
   @SuppressWarnings("unchecked")
   List<Product> products = (List<Product>) Database.getAllToFile(file);
}
