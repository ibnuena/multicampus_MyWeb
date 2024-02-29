package member.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import common.db.DBUtil;
//DAO (Data Access Object) : Database에 접근하여 CRUD로직을 수행하는 객체 
//==> Data Layer (Persistence Layer) ==> Model에 해당
public class MemberDAO {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private DataSource ds;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 회원가입 처리 - C (INSERT)*/
	public int insertMember(MemberVO user) throws SQLException{
		try {
//			con=DBUtil.getCon();
			con = ds.getConnection();
			String sql="INSERT INTO java_member(id,name,pw,tel,indate)";
			       sql+=" VALUES(?,?,?,?,SYSDATE)";
			ps=con.prepareStatement(sql);
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPw());
			ps.setString(4, user.getTel());
			
			int n=ps.executeUpdate();
			return n;
		}finally {
			close();
		}
	}//--------------------
	
	
	public int deleteMember(String id) throws SQLException{
		try {
//			con = DBUtil.getCon();
			con = ds.getConnection();

			// delete문 작성
			String sql = "delete from java_member where id=?";
			// ps 얻기
			ps = con.prepareStatement(sql);
			// ? setting
			ps.setString(1, id);
			// 실행 -> 실행결과 반환
			int n = ps.executeUpdate();
			return n;
		}finally {
			close();
		}
	}
	
	/** 회원 목록 가져오기 - R (select) -> 다중행을 반환하는 경우 */
	public ArrayList<MemberVO> selectAll() throws SQLException{
		try {
//			con = DBUtil.getCon();
			con = ds.getConnection();

			String sql = "select * from java_member order by indate desc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			return makeList(rs);
			
		}finally {
			close();
		}
	}
	
	private ArrayList<MemberVO> makeList(ResultSet rs2) throws SQLException{
		ArrayList<MemberVO> arr = new ArrayList<>();
		
		while(rs.next()) {
			String id = rs.getString("id");
			String pw = rs.getString("pw");
			String name = rs.getString("name");
			String tel = rs.getString("tel");
			java.sql.Date indate = rs.getDate("indate");
			
			MemberVO record = new MemberVO(id, pw, name, tel, indate);
			arr.add(record);
		} // while -----------------------------------
		
		return arr;
	}

    // id로 회원정보 가져오기 - R (select) 
	public MemberVO findById(String id) throws SQLException{
		try {
//			con = DBUtil.getCon();
			con = ds.getConnection();

			String sql = "select * from java_member where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			ArrayList<MemberVO> arr = makeList(rs);
			if(arr != null && arr.size()==1) {
				MemberVO user = arr.get(0);
				return user; // 해당 id 정보를 반환
			}
			
			return null; // 해당 id가 없는 경우
		}finally {
			close();
		}
		
	}
	
	
	// id가 없을 경우 -1, 비번이 틀릴 경우 -2, 인증받은 경우 1 반환
	public int loginCheck(String id, String pw) throws SQLException{
		MemberVO tmpUser = this.findById(id);
		if(tmpUser==null) return -1; // id가 없는 경우
		
		// 아이디가 있는 경우는 비밀번호 일치 여부 체크
		if(! tmpUser.getPw().equals(pw)) return -2; // 비번 불일치
		
		return 1; // 회원 인증 받은 경우
	}
	
	/** 회원정보 수정 처리 - U (update) */
	public int updateMember(MemberVO user) throws SQLException{
		try {
//			con = DBUtil.getCon();
			con = ds.getConnection();

			String sql = "update java_member set name=?, tel=?, pw=? where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getTel());
			ps.setString(3, user.getPw());
			ps.setString(4, user.getId());
			
			return ps.executeUpdate();
			
		}finally {
			close();
		}
		
	}// ------------------------------------------------
	
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(con!=null) con.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//-------------------------------



}////////////////////////////////////////
