package tukorea.web.club.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tukorea.web.club.domain.ReservationVO;


public class ReservationDAO {

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

	public boolean add(ReservationVO vo) {
		connect();
		String sql = "insert into reservation values (NULL, ?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getMovieid());
			pstmt.setString(3, vo.getMoviename());
			pstmt.setString(4, vo.getSeatnumber());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	public boolean update(ReservationVO vo) {
		connect();
		String sql = "update reservation set userid = ?, movieid = ?, moviename = ?, seatnumber = ? where reserveid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getMovieid());
			pstmt.setString(3, vo.getMoviename());
			pstmt.setString(4, vo.getSeatnumber());
			pstmt.setInt(5, vo.getReserveid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	public ReservationVO read(int intId) {
		ReservationVO vo = new ReservationVO();
		connect();
		String sql = "select * from reservation where reserveid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, intId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				vo.setReserveid(rs.getInt("reserveid"));
				vo.setUserid(rs.getString("userid"));
				vo.setMovieid(rs.getString("movieid"));
				vo.setMoviename(rs.getString("moviename"));
				vo.setSeatnumber(rs.getString("seatnumber"));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}
		return vo;
	}
	
	public ArrayList<String> reserveSeat(String movieid) {
		ArrayList<String> seatList = new ArrayList<String>();
		connect();
		String sql = "select seatnumber from reservation where movieid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movieid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				seatList.add(rs.getString("seatnumber"));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			disconnect();
		}
		return seatList;
	}

	public boolean delete(int intId) {
		connect();
		String sql = "delete from reservation where reserveid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, intId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	public ArrayList<ReservationVO> getReservationList() {
		connect();
		ArrayList<ReservationVO> reservationList = new ArrayList<ReservationVO>();
		String sql = "select * from reservation";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ReservationVO vo = new ReservationVO();
				vo.setReserveid(rs.getInt("reserveid"));
				vo.setUserid(rs.getString("userid"));
				vo.setMovieid(rs.getString("movieid"));
				vo.setMoviename(rs.getString("moviename"));
				vo.setSeatnumber(rs.getString("seatnumber"));
				reservationList.add(vo);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return reservationList;
	}
}
