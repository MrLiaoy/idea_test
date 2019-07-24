package com.llib.cn.pojo;

import java.util.Date;

public class Book {
    private String isbn;//书籍的编号
    private String bookname;//书名
    private double price;//价格
    private String author;//作者名
    private String publisher;//出版社
    private Date pubdate;//出版日期
    private int remaining;//剩余库存
    private int counter;//借出次数
    private int total;//图书馆拥有书籍总量
    private int obtained=1;
    public Book() {
    }
    public Book(String isbn, String bookname, double price, String author, String publisher, Date pubdate, int remaining, int counter, int total, int obtained) {
        this.isbn = isbn;
        this.bookname = bookname;
        this.price = price;
        this.author = author;
        this.publisher = publisher;
        this.pubdate = pubdate;
        this.remaining = remaining;
        this.counter = counter;
        this.total = total;
        this.obtained = obtained;
    }

    public Book(String isbn, String bookname, double price, String author, String publisher, Date pubdate, int total) {

        this.isbn = isbn;
        this.bookname = bookname;
        this.price = price;
        this.author = author;
        this.publisher = publisher;
        this.pubdate = pubdate;
        this.remaining = total;
        this.total = total;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getObtained() {
        return obtained;
    }

    public void setObtained(int obtained) {
        this.obtained = obtained;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (Double.compare(book.price, price) != 0) return false;
        if (remaining != book.remaining) return false;
        if (counter != book.counter) return false;
        if (total != book.total) return false;
        if (obtained != book.obtained) return false;
        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        if (bookname != null ? !bookname.equals(book.bookname) : book.bookname != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (publisher != null ? !publisher.equals(book.publisher) : book.publisher != null) return false;
        return pubdate != null ? pubdate.equals(book.pubdate) : book.pubdate == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = isbn != null ? isbn.hashCode() : 0;
        result = 31 * result + (bookname != null ? bookname.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (pubdate != null ? pubdate.hashCode() : 0);
        result = 31 * result + remaining;
        result = 31 * result + counter;
        result = 31 * result + total;
        result = 31 * result + obtained;
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", bookname='" + bookname + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", pubdate=" + pubdate +
                ", remaining=" + remaining +
                ", counter=" + counter +
                ", total=" + total +
                ", obtained=" + obtained +
                '}';
    }
}
