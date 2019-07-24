package com.llib.cn.dao;

import com.llib.cn.pojo.Book;

import java.util.ArrayList;

public interface BookDao {
    Book queryOne(Book book);//查询某一本书
    int addBook(Book book);//添加新书
    ArrayList<Book> queryAll();//查询所有书
    int obtainedBook(Book book);//下架书籍
    ArrayList<Book> queryByBookName(Book book);
}
