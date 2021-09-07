package com.codegym.controller;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.List;

import static com.codegym.view.ProductMenu.scanner;

public class ProductManagement {
    private final List<Product> productList = new ArrayList<>();

    public List<Product> getProductList() {
        return productList;
    }

    public void showAll() {
        if (productList.isEmpty()) {
            System.out.println("Không có sản phẩm nào hết trơn");
        }
        int count = 0;
        for (Product product : productList) {
            System.out.println(product);
            count++;
            if (count == 5) {
                count = 0;
                scanner.nextLine();
            }
        }
    }

    public void addNew(Product product) {
        productList.add(product);
    }

    public void updateById(String id, Product product) {
        int index = findById(id);
        productList.set(index, product);
    }


    public void removeById(String id) {
        int index = findById(id);
        productList.remove(index);
    }

    public int findById(String id) {
        int index = -1;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void sortByPrice() {
        productList.sort(Product::compareTo);
    }

    public int findByProductToPriceMax() {
        int index = -1;
        if (productList.isEmpty()) {
            return index;
        }
        double max = productList.get(0).getPrice();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getPrice() > max) {
                max = productList.get(i).getPrice();
                index = i;
            }
        }
        return index;
    }
}