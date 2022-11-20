package tukorea.web.club.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tukorea.web.club.domain.*;

public class MovieDAO {
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

	public boolean add(MovieVO vo) {
		connect();
		String sql = "insert into user values (?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMovieid());
			pstmt.setString(2, vo.getMoviename());
			pstmt.setString(3, vo.getMoviegenre());
			pstmt.setString(4, vo.getRoom());
			pstmt.setString(5, vo.getMovietime());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	public boolean update(MovieVO vo) {
		connect();
		String sql = "update movie set moviename = ?, moviegenre = ?, room = ?, movietime = ? where movieid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMoviename());
			pstmt.setString(2, vo.getMoviegenre());
			pstmt.setString(3, vo.getRoom());
			pstmt.setString(4, vo.getMovietime());
			pstmt.setString(5, vo.getMovieid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	public MovieVO read(String strId) {
		MovieVO vo = new MovieVO();
		connect();
		String sql = "select * from movie where movieid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				vo.setMovieid(rs.getString("movieid"));
				vo.setMoviename(rs.getString("moviename"));
				vo.setMoviegenre(rs.getString("moviegenre"));
				vo.setRoom(rs.getString("room"));
				vo.setMovietime(rs.getString("movietime"));
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
		String sql = "delete from movie where movieid = ?";
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

	public ArrayList<MovieVO> getMovieList() {
		connect();
		ArrayList<MovieVO> movieList = new ArrayList<MovieVO>();
		String sql = "select * from movie";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MovieVO vo = new MovieVO();
				vo.setMovieid(rs.getString("movieid"));
				vo.setMoviename(rs.getString("moviename"));
				vo.setMoviegenre(rs.getString("moviegenre"));
				vo.setRoom(rs.getString("room"));
				vo.setMovietime(rs.getString("movietime"));
				movieList.add(vo);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return movieList;
	}
}