package com.llib.daoImpl;

import com.llib.dao.BookDao;
import com.llib.pojo.Book;
import com.llib.util.ConnectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDaoImpl implements BookDao {
    private Connection con = null;

    public BookDaoImpl() {
        con = ConnectUtil.getConnect();
    }

    public Book queryOne(Book book) {
        String sql = "SELECT * from tb_book where isbn=?";
        PreparedStatement preparedStatement = null;
        Book book1 = null;
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, book.getIsbn());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                book1 = new Book(resultSet.getString("isbn"),
                        resultSet.getString("bookname"),
                        resultSet.getDouble("price"),
                        resultSet.getString("author"),
                        resultSet.getString("publisher"),
                        resultSet.getDate("pubdate"),
                        resultSet.getInt("remaining"),
                        resultSet.getInt("counter"),
                        resultSet.getInt("total"),
                        resultSet.getInt("obtained"));
            }
            return book1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book1;

    }

    public int addBook(Book book) {
        String sql = "insert INTO tb_book(isbn,bookname,price,author,publisher,pubdate,remaining,total)" +
                " VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        book.setRemaining(book.getTotal());
        if (queryOne(book) == null) {
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, book.getIsbn());
                preparedStatement.setString(2, book.getBookname());
                preparedStatement.setDouble(3, book.getPrice());
                preparedStatement.setString(4, book.getAuthor());
                preparedStatement.setString(5, book.getPublisher());
                preparedStatement.setDate(6, new java.sql.Date(book.getPubdate().getTime()));
                preparedStatement.setInt(7, book.getRemaining());
                preparedStatement.setInt(8, book.getTotal());
                return preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    public ArrayList<Book> queryAll() {
        String sql = "SELECT*FROM tb_book;";
        ArrayList<Book> list = new ArrayList<Book>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(resultSet.getString("isbn"),
                        resultSet.getString("bookname"),
                        resultSet.getDouble("price"),
                        resultSet.getString("author"),
                        resultSet.getString("publisher"),
                        resultSet.getDate("pubdate"),
                        resultSet.getInt("remaining"),
                        resultSet.getInt("counter"),
                        resultSet.getInt("total"),
                        resultSet.getInt("obtained")
                );
                list.add(book);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public void obtainedBook(Book book) {
        String sql = "update tb_book set Obtained=? WHERE isbn=?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, book.getObtained());
            preparedStatement.setString(2, book.getIsbn());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public ArrayList<Book> queryByBookName(Book book) {
        PreparedStatement preparedStatement = null;
        String sql = "select * from tb_book where bookname like ?;";
        ArrayList<Book> arrayList = new ArrayList<Book>();
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, book.getBookname());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book1 = new Book(resultSet.getString("isbn"),
                        resultSet.getString("bookname"),
                        resultSet.getDouble("price"),
                        resultSet.getString("author"),
                        resultSet.getString("publisher"),
                        resultSet.getDate("pubdate"),
                        resultSet.getInt("remaining"),
                        resultSet.getInt("counter"),
                        resultSet.getInt("total"),
                        resultSet.getInt("obtained")
                );
                arrayList.add(book1);
            }
            return arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
