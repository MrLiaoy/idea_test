package com.llib.cn.pojo;

import java.util.Date;

public class Reader {
    private int rid;//读者编号
    private String rname;//读者名称
    private String gender;//读者性别
    private String tel;
    private Date regdate;//注册时间
    private int availble=1;//是否已被注销，默认1，注销为0

    public Reader() {
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public int getAvailble() {
        return availble;
    }

    public void setAvailble(int availble) {
        this.availble = availble;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reader reader = (Reader) o;

        if (rid != reader.rid) return false;
        if (availble != reader.availble) return false;
        if (rname != null ? !rname.equals(reader.rname) : reader.rname != null) return false;
        if (gender != null ? !gender.equals(reader.gender) : reader.gender != null) return false;
        if (tel != null ? !tel.equals(reader.tel) : reader.tel != null) return false;
        return regdate != null ? regdate.equals(reader.regdate) : reader.regdate == null;
    }

    @Override
    public int hashCode() {
        int result = rid;
        result = 31 * result + (rname != null ? rname.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (regdate != null ? regdate.hashCode() : 0);
        result = 31 * result + availble;
        return result;
    }

    public Reader(int rid, String rname, String gender, String tel, Date regdate, int availble) {
        this.rid = rid;
        this.rname = rname;
        this.gender = gender;
        this.tel = tel;
        this.regdate = regdate;
        this.availble = availble;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "rid=" + rid +
                ", rname='" + rname + '\'' +
                ", gender='" + gender + '\'' +
                ", tel='" + tel + '\'' +
                ", regdate=" + regdate +
                ", availble=" + availble +
                '}';
    }

}
