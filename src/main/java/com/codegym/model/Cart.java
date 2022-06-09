package com.codegym.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Product, Integer> products = new HashMap<>();

    public Cart() {
    }

    public Cart(Map<Product, Integer> products) {
        this.products = products;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    private boolean checkItemInCart(Product product) { //để kiểm tra xem sản phẩm đó đã có trong giỏ hàng hay chưa
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getId().equals(product.getId())) {
                return true;
            }
        }
        return false;
    }

    private Map.Entry<Product, Integer> selectItemInCart(Product product) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getId().equals(product.getId())) {
                return entry;
            }
        }
        return null;
    }

    public void addProduct(Product product) { //được sử dụng để thêm sản phẩm vào trong giỏ hàng.
        if (!checkItemInCart(product)) {
            products.put(product, 1);
        } else {
            Map.Entry<Product, Integer> itemEntry = selectItemInCart(product);
            Integer newQuantity = itemEntry.getValue() + 1;
            products.replace(itemEntry.getKey(), newQuantity);
        }
    }

    public void decreaseProduct(Product product) { //được sử dụng để trừ sản phẩm vào trong giỏ hàng.
        if (checkItemInCart(product)) {
            Map.Entry<Product, Integer> itemEntry = selectItemInCart(product);
            Integer newQuantity = itemEntry.getValue() - 1;
            if (newQuantity == 0) {
                products.remove(itemEntry);
            }
            products.put(itemEntry.getKey(), newQuantity);
        }
    }


    public Integer countProductQuantity() { //dùng để đếm số lượng sản phẩm đó hiện có trong giỏ hàng.
        Integer productQuantity = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            productQuantity += entry.getValue();
        }
        return productQuantity;
    }

    public Integer countItemQuantity() { //để đếm số lượng sản phẩm có trong giỏ hàng.
        return products.size();
    }

    public Float countTotalPayment() { //dùng để tính tổng số tiền cần phải thanh toán.
        float payment = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            payment += entry.getKey().getPrice() * (float) entry.getValue();
        }
        return payment;
    }

    public void deleteProduct(Product product) { //được sử dụng để xóa sản phẩm trong giỏ hàng.
        if (checkItemInCart(product)) {
            products.remove(product);
        }
    }

    public Map.Entry<Product, Integer> findByIdInCart(int id) { //để kiểm tra xem sản phẩm đó đã có trong giỏ hàng hay chưa
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getId() == id) {
                return entry;
            }
        }
        return null;

    }
}