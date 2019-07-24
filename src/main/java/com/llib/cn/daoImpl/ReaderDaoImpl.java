package com.llib.cn.daoImpl;

import com.llib.cn.dao.ReaderDao;
import com.llib.cn.pojo.Reader;
import com.llib.cn.util.ConnectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReaderDaoImpl implements ReaderDao {
    Connection connection = null;


    public ReaderDaoImpl() {
        connection = ConnectUtil.getConnect();
    }

    public boolean regist(Reader reader) {
        if(queryOne(reader)==null) {
            String sql="insert INTO tb_reader(rid,rname,gender,tel,regdate,availble)" +
                    "VALUES(?,?,?,?,SYSDATE(),1);";
            PreparedStatement preparedStatement=null;
            try {
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setInt(1,reader.getRid());
                preparedStatement.setString(2,reader.getRname());
                preparedStatement.setString(3,reader.getGender());
                preparedStatement.setString(4,reader.getTel());
                int flag =preparedStatement.executeUpdate();
                if (flag>0){
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if (preparedStatement!=null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return false;
    }

    public void update(Reader reader) {
        String sql="update tb_reader SET rname= ?,gender=?,tel=? ,availble=? WHERE rid=?";
        int flag=0;
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,reader.getRname());
            preparedStatement.setString(2,reader.getGender());
            preparedStatement.setString(3,reader.getTel());
            preparedStatement.setInt(4,reader.getAvailble());
            preparedStatement.setInt(5,reader.getRid());
            flag=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public ArrayList<Reader> queryAll() {
        String sql="SELECT * from tb_reader";
        ArrayList<Reader> list=new ArrayList<Reader>();
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(new Reader(resultSet.getInt("rid"),
                        resultSet.getString("rname"),
                        resultSet.getString("gender"),
                        resultSet.getString("tel"),
                        resultSet.getDate("regdate"),
                        resultSet.getInt("availble")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }

    public Reader queryOne(Reader reader) {
        String sql = "SELECT * from tb_reader where rid=?";
        PreparedStatement preparedStatement = null;
        try {
           preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, reader.getRid());
            ResultSet resultSet = preparedStatement.executeQuery();
            Reader reader1=null;
            if (resultSet.next()) {
//                    public Reader(int rid, String rname, String gender, Date regdate, int availble)
                reader1 = new Reader(resultSet.getInt("rid"),
                        resultSet.getString("rname"),
                        resultSet.getString("gender"),
                        resultSet.getString("tel"),
                        resultSet.getDate("regdate"),
                        resultSet.getInt("availble"));
            }
            return reader1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
