package hello.hellospring.repository;

import hello.hellospring.domain.Classes;
import hello.hellospring.domain.Credit;
import hello.hellospring.domain.Member;
import hello.hellospring.domain.Subject;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class creditsRepository {

    private final DataSource dataSource;

    public creditsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Credit> find(int semester) {
        String sql = "SELECT class_id, credit FROM class_list WHERE semester = ? AND member_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, semester);
            pstmt.setInt(2, 12);
            rs = pstmt.executeQuery();
            List<Credit> credits = new ArrayList<>();
            while (rs.next()) {
                Credit credit = new Credit();
                credit.setCid(rs.getInt("class_id"));
                credit.setCredit(rs.getInt("credit"));
                credits.add(credit);
            }
            return credits;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public List<Classes> find_class(int cid) {
        String sql = "SELECT name, credit, sid FROM class WHERE cid = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cid);
            rs = pstmt.executeQuery();
            List<Classes> classes = new ArrayList<>();
            while (rs.next()) {
                Classes classes1 = new Classes();
                classes1.setName(rs.getString("name"));
                classes1.setCredit(rs.getInt("credit"));
                classes1.setSid(rs.getInt("sid"));
                classes.add(classes1);
            }
            return classes;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public List<Subject> find_subject(int sid) {
        String sql;
        if(sid == -1){sql = "SELECT name FROM subject";}
        else{sql = "SELECT name FROM subject WHERE sid = ?";}
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            if (sid != -1) {
                pstmt.setInt(1, sid);
            }
            rs = pstmt.executeQuery();
            List<Subject> subjects = new ArrayList<>();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setName(rs.getString("name"));
                subjects.add(subject);
            }
            return subjects;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
    {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
