package com.llib.service;

import com.llib.dao.BookDao;
import com.llib.dao.ReaderDao;
import com.llib.dao.RecDao;
import com.llib.daoImpl.BookDaoImpl;
import com.llib.daoImpl.ReaderDaoImpl;
import com.llib.daoImpl.RecDaoImpl;
import com.llib.pojo.Book;
import com.llib.pojo.Reader;
import com.llib.pojo.Rec;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminWindowService {
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
                        "总共拥有此图书=" + book.getTotal() + "\t\t\t" +
                        "剩余库存=" + book.getRemaining() + "\t\t\t" +
                        "借出次数=" + book.getCounter() + "\t\t\t" +
                        "是否可借：" + str + "\t\t\t");
            }
        } else
            System.out.println("图书馆还没有图书，请添加新图书。");
    }

    public static void addBook() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("添加图书---");
        Book book = new Book();
        Scanner in = new Scanner(System.in);
        System.out.println("输入图书的ISBN：");
        String isbn = in.nextLine();
        book.setIsbn(isbn);
        System.out.println("输入图书的名字：");
        String bookname = in.nextLine();
        book.setBookname(bookname);
        System.out.println("输入图书的作者：");
        String author = in.nextLine();
        book.setAuthor(author);
        System.out.println("输入图书的出版社：");
        String publisher = in.nextLine();
        book.setPublisher(publisher);
        System.out.println("输入图书的出版日期(格式yyyy-MM-dd)：");
        String date = in.nextLine();
        try {
            book.setPubdate(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("输入图书的价格：");
        double price = in.nextDouble();
        book.setPrice(price);
        System.out.println("输入上架多少本：");
        int totle = in.nextInt();
        book.setTotal(totle);

        BookDaoImpl bookDao = new BookDaoImpl();
        if (bookDao.addBook(book) == 1)
            System.out.println("图书添加成功");
    }

    public static void queryRec() {
        RecDao recDao = new RecDaoImpl();
        ArrayList<Rec> arrayList = recDao.queryAll();
        if (arrayList.size() == 0)
            System.out.println("---您还没有借过任何书籍---");
        else
            for (Rec rec : arrayList) {
                {
                    System.out.println("借书编号=" + rec.getRec_id() + "\t\t" +
                            "书名=" + rec.getBook().getBookname() + "\t\t" +
                            "借书日期=" + rec.getLdate() + "\t\t" +
                            "归还日期=" + rec.getRdate() + "\t\t" +
                            "需要交多少罚金=" + rec.getPunish() + "\t\t"
                    );
                }

            }
    }

    public static void bookObtian() {
        Book book = new Book();
        System.out.println("请输入你要修改状态的书籍的ISBN");
        Scanner in = new Scanner(System.in);
        String isbn = in.nextLine();
        book.setIsbn(isbn);
        BookDao bookDao = new BookDaoImpl();
        book = bookDao.queryOne(book);

        if (book.getObtained() == 1) {
            System.out.println("书籍名为《"+book.getBookname()+"》状态为：上架\n\r请问确定是否修改?");
            System.out.println("确认请输入：1——\n\r取消请输入：任意的数字");
            int i = in.nextInt();
            if (i == 1) {
                book.setObtained(0);
                bookDao.obtainedBook(book);
                System.out.println("更改成功");
            } else
                System.out.println("----您已经取消更改----");
            return;
        }
        if (book.getObtained() == 0) {
            System.out.println("书籍名《"+book.getBookname()+"》状态为：下架\n\r请问确定是否修改?");
            System.out.println("确认请输入：1——\n\r取消请输入：任意的数字");
            int i = in.nextInt();
            if (i == 1) {
                book.setObtained(1);
                bookDao.obtainedBook(book);
                System.out.println("更改成功");
            } else {
                System.out.println("----您已经取消更改----");
                return;
            }
        }

    }

    public static void setReader() {
        Reader reader = new Reader();
        System.out.println("请输入用户Id");

        Scanner in = new Scanner(System.in);
        int rid = in.nextInt();
        reader.setRid(rid);
        ReaderDao readerDao = new ReaderDaoImpl();
        reader = readerDao.queryOne(reader);
        if (reader == null) {
            System.out.println("您输入的用户名有错");
        }
        if (reader.getAvailble() == 1) {
            System.out.println("用户名为："+reader.getRname()+"状态为：激活\n\r请问确定是否注销他?");
            System.out.println("确认请输入：1——\n\r取消请输入：任意的数字");
            int i = in.nextInt();
            if (i == 1) {
                reader.setAvailble(0);
                readerDao.update(reader);
                System.out.println("用户已经注销成功，用户将无法登录系统");
            } else
                System.out.println("----您已经取消更改----");
            return;
        }
        if (reader.getAvailble() == 0) {
            System.out.println("用户"+reader.getRname()+"状态为：未激活\n\r请问确定是否激活用户?");
            System.out.println("确认请输入：1——\n\r取消请输入：任意的数字");
            int i = in.nextInt();
            if (i == 1) {
                reader.setAvailble(1);
               readerDao.update(reader);
                System.out.println("用户已经激活，可以再次登录");
            } else {
                System.out.println("----您已经取消更改----");
                return;
            }
        }
    }
}
