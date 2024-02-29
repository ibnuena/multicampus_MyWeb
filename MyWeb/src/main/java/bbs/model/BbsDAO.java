package bbs.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import common.db.DBUtil;

//게시판 관련 crud 수행 => data layer
public class BbsDAO {
   private DataSource ds;
   private Connection con;
   private PreparedStatement ps;
   private ResultSet rs;
   
   public BbsDAO() {
	   System.out.println("BbsDAO() 생성");
	   try {
		   Context ctx = new InitialContext();
//		   ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
		   ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
	   }catch(NamingException e) {
		   e.printStackTrace();
	   }
   }
   
   // 게시글 쓰기 (시퀀스-Bbs_seq)
   public int insertBbs(BbsVO vo) throws SQLException{
      try {
//         con = DBUtil.getCon();
    	  con = ds.getConnection();
         // oracle
         String sql = "insert into bbs(no, title, writer, content, wdate) values("
               + "bbs_seq.nextval, ?, ?, ?, sysdate)";

         // mysql -> 글번호(no) auto_increment
         String sql2 = "insert into bbs(no, title, writer, content, wdate) values("
        		 + "bbs_seq.nextval, ?, ?, ?, curdate)";
         ps = con.prepareStatement(sql);
         ps.setString(1, vo.getTitle());
         ps.setString(2, vo.getWriter());
         ps.setString(3, vo.getContent());

         return ps.executeUpdate();
      }finally {
         close();
      }
   }
   
   public ArrayList<BbsVO> listBbs() throws SQLException {
	   try {
//		   con = DBUtil.getCon();
		   con = ds.getConnection();
		   String sql = "SELECT no, title, writer, content, wdate FROM bbs ORDER BY no DESC";
		   ps = con.prepareStatement(sql);
		   rs = ps.executeQuery();
		   
		   return makeList(rs);
	   } finally {
		   close();
	   }
   }
   
   public ArrayList<BbsVO> makeList(ResultSet rs) throws SQLException {
	   ArrayList<BbsVO> arr = new ArrayList<>();
	   while(rs.next()) {
		   int no = rs.getInt("no");
		   String title = rs.getString("title");
		   String writer = rs.getString("writer");
		   String content = rs.getString("content");
		   java.sql.Date wdate = rs.getDate("wdate");
		   
		   BbsVO vo = new BbsVO(no, title, writer, content, wdate);
		   arr.add(vo);
	   }
	   return arr;
   }
   
   
   public void close() {
      try {
         if(rs!=null) rs.close();
         if(ps!=null) ps.close();
         if(con!=null) con.close();         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }//———————————————
}