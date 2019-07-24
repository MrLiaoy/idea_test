package com.llib.daoImpl;

import com.llib.dao.RecDao;
import com.llib.pojo.Book;
import com.llib.pojo.Reader;
import com.llib.pojo.Rec;
import com.llib.util.ConnectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class RecDaoImpl implements RecDao {
    Connection connection = null;

    public RecDaoImpl() {
        connection = ConnectUtil.getConnect();
    }

    //记录借书
    public int record(Reader reader, Book book) {
        String sql = "insert into tb_rec(rec_id,rid,isbn,ldate,rdate,punish) VALUES(default ,?," +
                "?," +
                "SYSDATE()," +
                "null," +
                "0)";
        PreparedStatement preparedStatement = null;
        if (new BookDaoImpl().queryOne(book).getObtained() == 1 && new BookDaoImpl().queryOne(book).getRemaining() > 0)
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, reader.getRid());
                preparedStatement.setString(2, book.getIsbn());
                int flag = preparedStatement.executeUpdate();
                if (flag > 0) {
                    String booksql = "update tb_book set remaining=remaining-1,counter =counter+1 where isbn=?";
                    preparedStatement = connection.prepareStatement(booksql);
                    preparedStatement.setString(1, book.getIsbn());
                    return preparedStatement.executeUpdate();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        return 0;
    }

    public ArrayList<Rec> queryAll() {
        String sql = "SELECT * from tb_rec;";
        ArrayList<Rec> arrayList = new ArrayList<Rec>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Reader reader = new Reader();
                reader.setRid(resultSet.getInt("rid"));
                Book book = new Book();
                book.setIsbn(resultSet.getString("isbn"));
                Rec rec = new Rec(
                        resultSet.getInt("rec_id"),
                        new ReaderDaoImpl().queryOne(reader),
                        new BookDaoImpl().queryOne(book),
                        resultSet.getDate("ldate"),
                        resultSet.getDate("rdate"),
                        resultSet.getDouble("punish")
                );
                arrayList.add(rec);
            }

            return arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Rec> queryByReader(Reader reader) {
        String sql = "SELECT * from tb_rec where rid=?";
        ArrayList<Rec> arrayList = new ArrayList<Rec>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, reader.getRid());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setIsbn(resultSet.getString("isbn"));
                Rec rec = new Rec(
                        resultSet.getInt("rec_id"),
                        new ReaderDaoImpl().queryOne(reader),
                        new BookDaoImpl().queryOne(book),
                        resultSet.getDate("ldate"),
                        resultSet.getDate("rdate"),
                        resultSet.getDouble("punish")
                );
                arrayList.add(rec);
            }
            return arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public ArrayList<Rec> queryByBook(Book book) {
        String sql = "SELECT * from tb_rec where isbn=?";
        ArrayList<Rec> arrayList = new ArrayList<Rec>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getIsbn());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Reader reader = new Reader();
                reader.setRid(resultSet.getInt(2));
                Rec rec = new Rec(
//                         public Rec(int rec_id, Reader reader, Book book, Date ldate, Date rdate, double punish)
                        resultSet.getInt("rec_id"),
                        new ReaderDaoImpl().queryOne(reader),
                        new BookDaoImpl().queryOne(book),
                        resultSet.getDate("ldate"),
                        resultSet.getDate("rdate"),
                        resultSet.getDouble("punish")
                );
                arrayList.add(rec);
            }

            return arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int returnBook(Rec rec) {


        String sql = "update tb_rec set rdate=SYSDATE()  WHERE rec_id=?";
        String sql2 = "select * from tb_rec WHERE rec_id=?";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1, rec.getRec_id());
            //查询是否有此条借书记录
            ResultSet resultSet = preparedStatement.executeQuery();
            Date rdate = null;
            boolean f = resultSet.next();
            if (f)
                rdate = resultSet.getDate("rdate");
            //判断是否已经被还了
            if (rdate == null) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, rec.getRec_id());
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.setInt(1, rec.getRec_id());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    long rl = resultSet.getDate("rdate").getTime();
                    long ll = resultSet.getDate("ldate").getTime();
                    int punish;
                    long month = 2592000000l / 2;
                    //System.out.println(rl-ll);
                    punish = (int) ((rl - ll) / month);
                    // System.out.println(punish);
                    String sql3 = "update tb_rec set punish=? WHERE rec_id=?";
                    preparedStatement = connection.prepareStatement(sql3);
                    preparedStatement.setInt(1, punish);
                    preparedStatement.setInt(2, rec.getRec_id());
                    preparedStatement.executeUpdate();
                }
                if (preparedStatement.executeUpdate() == 1) {
                    try {
                        preparedStatement = connection.prepareStatement("update tb_book set remaining=remaining+1 where isbn=?");
                        preparedStatement.setString(1, resultSet.getString("ISBN"));
                        return preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Rec> queryReaderNotReturn(Reader reader) {
        String sql = "SELECT * from tb_rec where rid=? and isnull(rdate)";
        ArrayList<Rec> arrayList = new ArrayList<Rec>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, reader.getRid());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Book book = new Book();
                book.setIsbn(resultSet.getString("isbn"));
                Rec rec = new Rec(
                        resultSet.getInt("rec_id"),
                        new ReaderDaoImpl().queryOne(reader),
                        new BookDaoImpl().queryOne(book),
                        resultSet.getDate("ldate"),
                        resultSet.getDate("rdate"),
                        resultSet.getDouble("punish")
                );
                arrayList.add(rec);
            }
            return arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
