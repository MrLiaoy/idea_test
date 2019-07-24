package com.llib.cn.service;

import com.llib.cn.dao.BookDao;
import com.llib.cn.dao.ReaderDao;
import com.llib.cn.dao.RecDao;
import com.llib.cn.daoImpl.BookDaoImpl;
import com.llib.cn.daoImpl.ReaderDaoImpl;
import com.llib.cn.daoImpl.RecDaoImpl;
import com.llib.cn.pojo.Book;
import com.llib.cn.pojo.Reader;
import com.llib.cn.pojo.Rec;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReaderWindowService {
    public static void borrowBook(Reader reader) {
        RecDao recDao = new RecDaoImpl();
        System.out.println("请输入你要的书的isbn号");
        String string = new Scanner(System.in).nextLine();
        Book book = new Book();
        book.setIsbn(string);
        int flag = 0;
        try {
            flag = recDao.record(reader, book);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("-----系统开小差了请重新尝试-----");
        }
        BookDao bookDao = new BookDaoImpl();
        book = bookDao.queryOne(book);
        if (flag == 1) {
            System.out.println("------你接到的书是" + book.getBookname() + "-----");
        } else {
            System.out.println("-----借书失败请检查你输入的ISBN是否可用----");
        }
    }

    public static void record(Reader reader) {
        RecDao recDao = new RecDaoImpl();
        ArrayList<Rec> arrayList = recDao.queryAll();
        if (arrayList.size() == 0)
            System.out.println("---您还没有借过任何书籍---");
        else
            for (Rec rec : arrayList) {
                if (reader.getRid() == rec.getReader().getRid()) {
                    System.out.println("借书编号=" + rec.getRec_id() + "\t\t" +
                            "书名=" + rec.getBook().getBookname() + "\t\t" +
                            "借书日期=" + rec.getLdate() + "\t\t" +
                            "归还日期=" + rec.getRdate() + "\t\t" +
                            "需要交多少罚金=" + rec.getPunish() + "\t\t"
                    );
                }
                System.out.println("如果归还日期为null则您还未归还图书请看完后尽快归还\n\r");

            }
    }
    public static void recordNull(Reader reader) {
        RecDao recDao = new RecDaoImpl();
        ArrayList<Rec> arrayList = recDao.queryReaderNotReturn(reader);
        if (arrayList.size() == 0)
            System.out.println("---您还没有借过任何书籍---");
        else
            for (Rec rec : arrayList) {
                if (reader.getRid() == rec.getReader().getRid()) {
                    System.out.println("借书编号=" + rec.getRec_id() + "\t\t" +
                            "书名=" + rec.getBook().getBookname() + "\t\t" +
                            "借书日期=" + rec.getLdate() + "\t\t" +
                            "归还日期=" + rec.getRdate() + "\t\t" +
                            "需要交多少罚金=" + rec.getPunish() + "\t\t"
                    );
                }
                System.out.println("\n\r");

            }
    }
    public static void returnBook() {
        System.out.println("请输入你的借书编号（不是书的isbn编号）");
        int i=new Scanner(System.in).nextInt();
        RecDao recDao = new RecDaoImpl();
        Rec rec=new Rec();
        rec.setRec_id(i);

        if(recDao.returnBook(rec)!=0)
            System.out.println("图书归还成功");
        else
            System.out.println("没有找到次条借书记录，或者您已归还");
    }

    public static void updateReader(Reader reader){
        System.out.println("请输入新的电话号码");
        long l=new Scanner(System.in).nextLong();
        String s=new String().valueOf(l);
        if (s==null||s=="")
        {
            System.out.println("更改号码失败，请输入有效号码");
            return;
        }

        reader.setTel(s);
        ReaderDao readerDao=new ReaderDaoImpl();
        readerDao.update(reader);
        System.out.println("电话号码更改成功");
    }

    public static  void logout(Reader reader){
        reader.setAvailble(0);
        ReaderDao readerDao=new ReaderDaoImpl();
        readerDao.update(reader);
        System.out.println("注销成功");
    }
}
