package com.llib.service;

import com.llib.dao.BookDao;
import com.llib.daoImpl.BookDaoImpl;
import com.llib.pojo.Book;

import java.util.ArrayList;
import java.util.Scanner;

public class BookWindowService {

    public static void query() {
        BookDao bookDao = new BookDaoImpl();
        ArrayList<Book> arrayList = bookDao.queryAll();
        if (arrayList.size() != 0) {
            for (Book book : arrayList) {
                String str;
                if (book.getObtained() == 0)
                    str = "否";
                else
                    str = "是";
                System.out.println("ISBN=" + book.getIsbn() + "\t\t\t" +
                        "书名=" + "book.getBookname()" + "\t\t\t" +
                        "作者=" + book.getAuthor() + "\t\t\t" +
                        "出版社=" + book.getPublisher() + "\t\t\t" +
                        "出版日期=" + book.getPubdate() + "\t\t\t" +
                        "价格=" + book.getPrice() + "\t\t\t" +
                        "剩余库存=" + book.getRemaining() + "\t\t\t" +
                        "是否可借：" + str + "\t\t\t");
            }
        } else
            System.out.println("图书馆正在努力进货中，请等待图书上架。");
    }

    public static void queryByISBN() {
        System.out.println("请输入书本编号");
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        Book book = new Book();
        book.setIsbn(str);
        BookDao bookDao = new BookDaoImpl();
        book = bookDao.queryOne(book);
        if (book != null) {
            String str1;
            if (book.getObtained() == 0)
                str1 = "否";
            else
                str1 = "是";
            System.out.println("ISBN=" + book.getIsbn() + "\t\t\t" +
                    "书名=" + book.getBookname() + "\t\t\t" +
                    "作者=" + book.getAuthor() + "\t\t\t" +
                    "出版社=" + book.getPublisher() + "\t\t\t" +
                    "出版日期=" + book.getPubdate() + "\t\t\t" +
                    "价格=" + book.getPrice() + "\t\t\t" +
                    "剩余库存=" + book.getRemaining() + "\t\t\t" +
                    "是否可借：" + str1 + "\t\t\t");
        } else {
            System.out.println("未找到你要的图书，你可以联系我们进购相关图书");
        }
    }
    public static void queryByName(){
      System.out.println("请输入书名");
      String bookName=new Scanner(System.in).nextLine();
      bookName="%"+bookName+"%";
      Book book1 = new Book();
      book1.setBookname(bookName);
      BookDao bookDao=new BookDaoImpl();
      ArrayList<Book> arrayList=bookDao.queryByBookName(book1);
      for (Book book :arrayList)
        if (book != null) {
            String str1;
            if (book.getObtained() == 0)
                str1 = "否";
            else
                str1 = "是";
            System.out.println("ISBN=" + book.getIsbn() + "\t\t\t" +
                    "书名=" + book.getBookname() + "\t\t\t" +
                    "作者=" + book.getAuthor() + "\t\t\t" +
                    "出版社=" + book.getPublisher() + "\t\t\t" +
                    "出版日期=" + book.getPubdate() + "\t\t\t" +
                    "价格=" + book.getPrice() + "\t\t\t" +
                    "剩余库存=" + book.getRemaining() + "\t\t\t" +
                    "是否可借：" + str1 + "\t\t\t");
        } else {
            System.out.println("未找到你要的图书，你可以联系我们进购相关图书");
        }
    }
}
