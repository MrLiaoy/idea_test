package com.llib.cn.service;

import com.llib.cn.dao.ReaderDao;
import com.llib.cn.daoImpl.ReaderDaoImpl;
import com.llib.cn.pojo.Reader;

import java.util.Scanner;

public class LoginWindowService {
    public static void regist() {
        ReaderDao readerDao = new ReaderDaoImpl();
        Scanner in = new Scanner(System.in);
        System.out.println("请填写注册信息");
        System.out.print("（必填）用户ID：");
        String id = in.nextLine();
        System.out.print("（必填）请填写你的姓名：");
        String rname = in.nextLine();
        System.out.print("（必填）请填写你的性别：");
        String gender = in.nextLine();
        System.out.print("（必填）请填写你的电话：");
        String tel = in.nextLine();
//      public Reader(int rid, String rname, String gender, String tel, Date regdate, int availble)
        int rid = Integer.parseInt(id);
        Reader reader = new Reader(rid, rname, gender, tel, null, 1);
        if (readerDao.regist(reader)) {
            System.out.println("注册成功");
        } else {
            System.out.println("注册失败");
        }
    }

    public static Reader Login() {
        ReaderDao readerDao = new ReaderDaoImpl();
        Scanner in = new Scanner(System.in);
        System.out.print("（必填）用户ID：");
        String id = in.nextLine();
        int rid=Integer.parseInt(id);
        Reader reader =new Reader();
        reader.setRid(rid);
        reader = readerDao.queryOne(reader);
        return reader;
    }
}
