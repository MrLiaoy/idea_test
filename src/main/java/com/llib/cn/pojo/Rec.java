package com.llib.cn.pojo;


import java.util.Date;

public class Rec {
    private int rec_id;//借书记录号
    private Reader reader;//借阅者
    private Book book;//借阅书籍
    private Date ldate;//借出日期
    private Date rdate;//归还日期
    private double punish;//超时惩罚

    public Rec() {
    }

    public Rec(int rec_id, Reader reader, Book book, Date ldate, Date rdate, double punish) {
        this.rec_id = rec_id;
        this.reader = reader;
        this.book = book;
        this.ldate = ldate;
        this.rdate = rdate;
        this.punish = punish;
    }

    public int getRec_id() {
        return rec_id;
    }

    public void setRec_id(int rec_id) {
        this.rec_id = rec_id;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getLdate() {
        return ldate;
    }

    public void setLdate(Date ldate) {
        this.ldate = ldate;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    public double getPunish() {
        return punish;
    }

    public void setPunish(double punish) {
        this.punish = punish;
    }

    @Override
    public String toString() {
        return "Rec{" +
                "rec_id=" + rec_id +
                ", reader=" + reader +
                ", book=" + book +
                ", ldate=" + ldate +
                ", rdate=" + rdate +
                ", punish=" + punish +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rec rec = (Rec) o;

        if (rec_id != rec.rec_id) return false;
        if (Double.compare(rec.punish, punish) != 0) return false;
        if (reader != null ? !reader.equals(rec.reader) : rec.reader != null) return false;
        if (book != null ? !book.equals(rec.book) : rec.book != null) return false;
        if (ldate != null ? !ldate.equals(rec.ldate) : rec.ldate != null) return false;
        return rdate != null ? rdate.equals(rec.rdate) : rec.rdate == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = rec_id;
        result = 31 * result + (reader != null ? reader.hashCode() : 0);
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + (ldate != null ? ldate.hashCode() : 0);
        result = 31 * result + (rdate != null ? rdate.hashCode() : 0);
        temp = Double.doubleToLongBits(punish);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
