package com.ra.view;

import com.ra.service.CategoryService;
import com.ra.service.ProductService;

import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showMenu(){
        do{
            System.out.println("==========QUẢN LÝ KHO==========");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.println("Mời bạn chọn từ 1 - 3");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    menuCategory();
                    break;
                case 2:
                    menuProduct();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Chọn sai mời chọn lại!");
            }
        }while (true);
    }
    public static void menuCategory(){
        do {
            System.out.println("======== QUẢN LÝ DANH MỤC=========\n");
            System.out.println("1. Thêm mới danh mục");
            System.out.println("2. Cập nhật danh mục");
            System.out.println("3. Xóa danh mục");
            System.out.println("4. Tìm kiếm danh mục theo tên danh mục");
            System.out.println("5. Thống kê số lượng sp đang có trong danh mục");
            System.out.println("6. Quay lại\n");
            CategoryService.displayCategory();
            System.out.println("Mời bạn chọn từ 1 - 6");
            int choice1 = Integer.parseInt(scanner.nextLine());
            switch (choice1){
                case 1:
                    CategoryService.addCategory();
                    break;
                case 2:
                    CategoryService.updateCategory();
                    break;
                case 3:
                    CategoryService.deleteCategory();
                    break;
                case 4:
                    CategoryService.searchByCategoryName();
                    break;
                case 5:
                    System.out.println("Thống kê số lượng sp đang có trong danh mục");
                    break;
                case 6:
                    showMenu();
                    break;
                default:
                    System.out.println("Chọn sai mời chọn lại!");
            }
        } while (true);
    }
    public static void menuProduct(){
        do {
            System.out.println("======== QUẢN LÝ SẢN PHẨM=========");
            System.out.println("1. Thêm mới sản phẩm");
            System.out.println("2. Cập nhật sản phẩm");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("4. Hiển thị sản phẩm theo tên A-Z");
            System.out.println("5. Hiển thị sản phẩm theo lợi nhuận từ cao-thấp");
            System.out.println("6. Tìm kiếm sản phẩm");
            System.out.println("7. Quay lại\n");
            ProductService.displayProduct();
            System.out.println("Mời bạn chọn từ 1 - 7");
            int choice1 = Integer.parseInt(scanner.nextLine());
            switch (choice1){
                case 1:
                    ProductService.addCategory();
                    break;
                case 2:
                    System.out.println("Cập nhật sản phẩm");
                    break;
                case 3:
                    System.out.println("Xóa sản phẩm");
                    break;
                case 4:
                    System.out.println("Hiển thị sản phẩm theo tên A-Z");
                    break;
                case 5:
                    System.out.println("Hiển thị sản phẩm theo lợi nhuận từ cao-thấp");
                    break;
                case 6:
                    System.out.println("Tìm kiếm sản phẩm");
                    break;
                case 7:
                    showMenu();
                    break;
                default:
                    System.out.println("Chọn sai mời chọn lại!");
            }
        } while (true);
    }
}
