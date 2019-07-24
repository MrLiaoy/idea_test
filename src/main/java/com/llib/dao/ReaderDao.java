package com.llib.dao;

import com.llib.pojo.Reader;

import java.util.ArrayList;

public interface ReaderDao {
     public boolean regist(Reader reader);//读者注册
     public void update(Reader reader);//更新读者信息
     public ArrayList<Reader> queryAll();//查询所有读者
     public Reader queryOne(Reader reader);//查询某一个读者信息
}
