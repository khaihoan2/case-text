package com.codegym.view;

import com.codegym.controller.ProductManagement;
import com.codegym.model.Product;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProductMenu {

    public static Scanner scanner = new Scanner(System.in);
    public static ProductManagement productManagement = new ProductManagement();

    static {
        productManagement.addNew(new Product("1", "Quần", 700, 1, "jean"));
        productManagement.addNew(new Product("2", "Áo", 100, 1, "taydai"));
        productManagement.addNew(new Product("3", "Gạo", 11.5, 1, "Ngon"));
        productManagement.addNew(new Product("4", "giày", 1000, 1, "adidas"));
        productManagement.addNew(new Product("5", "Iphone", 10000, 1, "promax"));
    }

    public void run() {
        int choice;
        do {
            menuProduct();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    displayProduct();
                    break;
                }
                case 2: {
                    addProduct();
                    break;
                }
                case 3: {
                    updateProduct();
                    break;
                }
                case 4: {
                    deleteProduct();
                    break;
                }
                case 5: {
                    sortProduct();
                    break;
                }
                case 6: {
                    productToPriceMax();
                    break;
                }
                case 7: {
                    readDataToFile();
                    break;
                }
                case 8: {
                    writeDataToFile();
                    break;
                }
                case 9: {
                    System.out.println("See You Again !!!");
                }
                default: {
                    System.out.println("Không hợp lệ !!! mời bạn chọn lại dùm");
                }
            }

        } while (choice != 9);
    }

    public void writeDataToFile() {
        List<Product> products = productManagement.getProductList();
        try {
            System.out.print("Bạn muốn ghi vào file nào: ");
            String filePath = scanner.nextLine();
            File file = new File(filePath);

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(String.valueOf(products));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void readDataToFile() {
        try {
            System.out.print("Nhập vào file cần đọc: ");
            String filePath = scanner.nextLine();
            File file = new File(filePath);

            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println(Arrays.toString(line.split(",")));
            }
            br.close();
        } catch (IOException ie) {
            System.err.println("Fie không tồn tại or nội dung có lỗi!");
        }
    }

    private void productToPriceMax() {
        int index = productManagement.findByProductToPriceMax();
        System.out.println("Sản phẩm có giá đắt nhất đây ạ");
        System.out.println(productManagement.getProductList().get(index));
    }

    private void sortProduct() {
        int choice;
        do {
            System.out.println("1. Sắp xếp sản phẩm theo giá tăng dần");
            System.out.println("2. Hiển thị danh sách sản phẩm sau khi đã sắp xếp");
            System.out.println("3. Quay lại");
            System.out.println("Chọn: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    productManagement.sortByPrice();
                    break;
                }
                case 2: {
                    productManagement.showAll();
                    break;
                }
                case 3: {
                    System.out.println("Bạn sẽ được đưa về Menu chính");
                }
            }
        } while (choice != 3);

    }

    private void deleteProduct() {
        System.out.println("Xóa một sản phẩm");
        System.out.print("Nhập mã sản phẩm mà bạn muốn xóa: ");
        String productId = scanner.nextLine();
        int index = productManagement.findById(productId);
        if (index != -1) {
            System.out.println("Bạn có muốn xóa sản phẩm khỏi danh sách k??");
            System.out.println("Bấm Y để xóa, Bấm phím bất kỳ để thoát");
            String check = scanner.nextLine();
            if (check.equals("Y")) {
                productManagement.removeById(productId);
                System.out.println("Xóa thành công");
            }
        } else {
            if (productId.equals("")) {
                System.out.println("Bye");
                return;
            }
            System.out.println("Không tìm thấy :(( mời bạn nhập lại");
            deleteProduct();
        }
    }

    public void updateProduct() {
        System.out.println("Sửa thông tin sản phẩm");
        System.out.print("Nhập mã sản phẩm cần sửa: ");
        String productId = scanner.nextLine();
        int index = productManagement.findById(productId);
        if (index != -1) {
            Product product = inputProduct();
            productManagement.updateById(productId, product);
        } else {
            if (productId.equals("")) {
                System.out.println("Bye");
                return;
            }
            System.out.println("Không tìm thấy :(( mời bạn nhập lại");
            updateProduct();
        }
    }

    public void displayProduct() {
        productManagement.showAll();
    }

    public void addProduct() {
        Product product = inputProduct();
        productManagement.addNew(product);
        System.out.println("Thêm mới thành công");
    }

    public Product inputProduct() {
        System.out.println("Thêm mới sản phẩm");
        String productId, productName, description;
        double price;
        int amount;
        int count = 0;
        do {
            if (count > 0) {
                System.out.println("Bạn nhập sai rồi mời nhập lại");
            }
            System.out.print("Nhập mã sản phẩm: ");
            productId = scanner.nextLine();
            System.out.print("Nhập tên sản phẩm: ");
            productName = scanner.nextLine();
            System.out.print("Nhập giá sản phẩm: ");
            price = scanner.nextDouble();
            System.out.print("Nhập số lượng sản phẩm: ");
            amount = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nhập mô tả sản phẩm: ");
            description = scanner.nextLine();
            count++;
        } while (productId.equals("") || productName.equals("") || description.equals(""));
        return new Product(productId, productName, price, amount, description);
    }

    public void menuProduct() {
        System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM ----");
        System.out.println("Chọn chứ năng theo số (để tiếp tục)");
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm mới");
        System.out.println("3. Cập nhật");
        System.out.println("4. Xóa");
        System.out.println("5. Sắp xếp");
        System.out.println("6. Tìm sản phẩm có giá trị đắt nhất");
        System.out.println("7. Đọc từ file");
        System.out.println("8. Ghi vào file");
        System.out.println("9. Thoát");
        System.out.print("Chọn chứ năng: ");
    }

}