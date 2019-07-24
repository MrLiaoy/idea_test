package com.llib.cn.dao;

import com.llib.cn.pojo.Book;
import com.llib.cn.pojo.Reader;
import com.llib.cn.pojo.Rec;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RecDao {
    int record(Reader reader, Book book) throws SQLException;//记录借书
    ArrayList<Rec> queryAll();//查询所有借书记录
    ArrayList<Rec> queryByReader(Reader reader);//查询某一个读者借阅记录
    ArrayList<Rec> queryByBook(Book book);//查询某一本书的借阅记录
    int returnBook(Rec rec);//还书
    ArrayList<Rec> queryReaderNotReturn(Reader reader);
}
