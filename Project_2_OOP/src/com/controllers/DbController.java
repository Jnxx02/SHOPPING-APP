/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controllers;

/**
 *
 * @author Yongsung
 */
import com.models.Produk;
import com.config.DbConnect;

import java.sql.SQLException;
import java.util.ArrayList;

public class DbController extends DbConnect {

    public static ArrayList<Produk> getDatabase() {
        connection();

        ArrayList<Produk> list_prod = new ArrayList<>();

        try {
            query = "SELECT name, price, stock FROM tb_product";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                list_prod.add(new Produk(resultSet.getString("name"), resultSet.getInt("price"), resultSet.getInt("stock")));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_prod;
    }

    public static void insertData(String nama, long harga, int stok) {
        connection();
        query = "INSERT INTO tb_product (name, price, stock) VALUES (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nama);
            preparedStatement.setLong(2, harga);
            preparedStatement.setInt(3, stok);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // public static void insertHistory(String total, String tunai, String Kembali) {
    //     connection();
    //     query = "INSERT INTO tb_product (name, price, stock) VALUES (?, ?, ?)";
    //     try {
    //         preparedStatement = connection.prepareStatement(query);
    //         preparedStatement.setString(1, nama);
    //         preparedStatement.setLong(2, harga);
    //         preparedStatement.setInt(3, stok);
    //         preparedStatement.executeUpdate();
    //         preparedStatement.close();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    public static void updateProduct(String nama, int harga, int jumlah) {
        connection();
        query = "UPDATE tb_product SET price=?, stock=? WHERE name=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(3, nama);
            preparedStatement.setInt(1, harga);
            preparedStatement.setInt(2, jumlah);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteData(String nama) {
        connection();
        query = "DELETE FROM tb_product WHERE name=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nama);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}