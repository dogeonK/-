package tukorea.web.club.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tukorea.web.club.domain.UserVO;


public class UserDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;

	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost/jspdb?allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";

	void connect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "jspbook", "passwd");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void disconnect() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean add(UserVO vo) {
		connect();
		String sql = "insert into user values (?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getUsername());
			pstmt.setString(4, vo.getAnum());
			pstmt.setString(5, vo.getMobile());
			pstmt.setString(6, vo.getEmail());
			pstmt.setInt(7, vo.getUsertype());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	public boolean update(UserVO vo) {
		connect();
		String sql = "update user set passwd = ?, username = ?, anum = ?, mobile = ?, email = ?, usertype = ? where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPasswd());
			pstmt.setString(2, vo.getUsername());
			pstmt.setString(3, vo.getAnum());
			pstmt.setString(4, vo.getMobile());
			pstmt.setString(5, vo.getEmail());
			pstmt.setInt(6, vo.getUsertype());
			pstmt.setString(7, vo.getUserid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	public UserVO read(String strId) {
		UserVO vo = new UserVO();
		connect();
		String sql = "select * from user where userid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				vo.setUserid(rs.getString("userid"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setUsername(rs.getString("username"));
				vo.setAnum(rs.getString("anum"));
				vo.setMobile(rs.getString("mobile"));
				vo.setEmail(rs.getString("email"));
				vo.setUsertype(rs.getInt("usertype"));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}
		return vo;
	}

	public boolean delete(String strId) {
		connect();
		String sql = "delete from user where userid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	public ArrayList<UserVO> getStudentList() {
		connect();
		ArrayList<UserVO> userlist = new ArrayList<UserVO>();
		String sql = "select * from student ";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserVO vo = new UserVO();
				vo.setUserid(rs.getString("userid"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setUsername(rs.getString("username"));
				vo.setAnum(rs.getString("anum"));
				vo.setMobile(rs.getString("mobile"));
				vo.setEmail(rs.getString("email"));
				vo.setUsertype(rs.getInt("usertype"));
				userlist.add(vo);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return userlist;
	}
}
