package com.llib.main;

import com.llib.pojo.Reader;
import com.llib.service.AdminWindowService;
import com.llib.service.BookWindowService;
import com.llib.service.LoginWindowService;
import com.llib.service.ReaderWindowService;

import java.util.Scanner;

class FeaturesWindows {
    FeaturesWindows() {
        mainWindow();
    }

    private void mainWindow() {
        System.out.println("主页——————————————————————");
        System.out.println("1.管理图书");
        System.out.println("2.读者登录");
        System.out.println("3.退出系统");
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        switch (i) {
            case 1:
                bookManageWindow();
            case 2:
                loginWindow();
            case 3:
                System.exit(0);
            default:
                System.out.println("----输入错误请重新选择----");
                mainWindow();
        }

    }

    private void bookManageWindow() {
        System.out.println("图书管理————————————————————");
        System.out.println("1.查看图书信息");
        System.out.println("2.添加新的图书");
        System.out.println("3.更改图书是否上下架");
        System.out.println("4.查看借阅记录");
        System.out.println("5.激活/注销用户");
        System.out.println("6.返回主页");
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        switch (i) {
            case 1://查看图书信息并返回此页
                AdminWindowService.query();
                bookManageWindow();
            case 2://添加书籍的代码并返回页面
                AdminWindowService.addBook();
                bookManageWindow();
            case 3://修改图书是否上下架并返回此页面
                AdminWindowService.bookObtian();
                bookManageWindow();
            case 4://查看记录并返回此页面
                AdminWindowService.queryRec();
                bookManageWindow();
            case 5:
                AdminWindowService.setReader();
                mainWindow();
            case 6:
                mainWindow();
            default:
                mainWindow();

        }
    }

    private void loginWindow() {
        System.out.println("登录选择界面————————————————————");
        System.out.println("1.注册用户");
        System.out.println("2.用户登录");
        System.out.println("3.返回主页");
        System.out.println("4.退出系统");
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        switch (i) {
            case 1: //用户注册，在用户注册界面选择跳转回此页面
                LoginWindowService.regist();
                loginWindow();
            case 2://跳转至reader界面，在登录业务中跳转，登录成功跳转到reader页面，不成功提示后跳转回此页面
                Reader reader = LoginWindowService.Login();
                if (reader == null) {
                    System.out.println("登录失败");
                    loginWindow();
                } else if (reader.getAvailble() == 0) {
                    System.out.println("用户已注销");
                } else
                    readerWindow(reader);
            case 3:
                mainWindow();
            case 4:
                System.exit(0);
            default:
                System.out.println("----输入错误请重新选择----");
                loginWindow();
        }


    }

    private void readerWindow(Reader reader) {
//        System.out.println(reader.getRname()+"的主页————————————————————");
        System.out.println("0.进入图书查找页面");
        System.out.println("1.借阅图书");
        System.out.println("2.查看借阅记录");
        System.out.println("3.查看未归还图书");
        System.out.println("4.选择归还图书");
        System.out.println("5.修改用户电话");
        System.out.println("6.注销用户");
        System.out.println("7.退出登录");
        System.out.println("8.退出系统");
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        switch (i) {
            case 0:
                bookWindow(reader);
            case 1:
                ReaderWindowService.borrowBook(reader);
                readerWindow(reader);
            case 2:
                //查看借书记录的代码
                ReaderWindowService.record(reader);
                readerWindow(reader);
            case 3:
                //查看未归还图书的代码
                ReaderWindowService.recordNull(reader);
                readerWindow(reader);
            case 4:
                //归还图书代码
                ReaderWindowService.returnBook();
                readerWindow(reader);
            case 5:
                ReaderWindowService.updateReader(reader);
                readerWindow(reader);
            case 6:
                //注销用户
                ReaderWindowService.logout(reader);
                loginWindow();
            case 7:
                mainWindow();
            case 8:
                System.exit(0);
            default:
                System.out.println("----输入错误请重新选择----");
                readerWindow(reader);
        }
    }

    private void bookWindow(Reader reader) {
        System.out.println("图书查询————————————————————");
        System.out.println("1.查看所有书籍");
        System.out.println("2.搜索书籍名字");
        System.out.println("3.搜书书籍编号");
        System.out.println("4.返回用户界面");
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        switch (i) {
            case 1://查看所有书籍的代码
                BookWindowService.query();
                bookWindow(reader);
            case 2://通过书名查找书
                BookWindowService.queryByName();
                bookWindow(reader);
            case 3://通过书的编号查找书
                BookWindowService.queryByISBN();
                bookWindow(reader);
            case 4:
                readerWindow(reader);
            default:
                System.out.println("----输入错误请重新选择----");
                bookWindow(reader);
        }
    }
}
