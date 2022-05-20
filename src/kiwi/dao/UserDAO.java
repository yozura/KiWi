package kiwi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import kiwi.dto.Movie;
import kiwi.dto.User;

public class UserDAO extends KiWiDAO {
	public void insert(User user) {
		try {
			Connection con = makeConnection();
			String sql = "insert into kiwidb.users values(?, md5(?), ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getNickname());
			pstmt.setDate(4, user.getBirthDate());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getTel());
			
			int rt = pstmt.executeUpdate();
			if (rt >= 1) {
				System.out.println("계정 정보가 저장되었습니다.");
			} else {
				System.out.println("저장에 실패했습니다.");
			}
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User selectId(String id, String password) {
		User user = null;
		try {
			Connection con = makeConnection();
			String sql = "select nickname, birthday, email, tel from kiwidb.users where id = ? and password = md5(?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User(id, password, rs.getString(1), rs.getDate(2), rs.getString(3), rs.getString(4));
			} else {
				pstmt.close();
				con.close();
				return null;
			}
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public Vector<Movie> findBookmarkByUserId(String id) {
		Vector<Movie> vecBookmark = null;
		try {
			Connection con = makeConnection();
			String sql = "select m.* from movies as m"
					+ " inner join bookmarks as bm"
					+ " on bm.user_id = ? and bm.movie_id = m.id";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (vecBookmark == null) {
					vecBookmark = new Vector<Movie>();
				}
				vecBookmark.add(new Movie(
						rs.getInt(1)
						, rs.getString(2)
						, rs.getString(3)
						, rs.getString(4)
						, rs.getString(5)
						, rs.getDouble(6)
						, rs.getDate(7)
						, rs.getInt(8)
						, rs.getInt(9)
						, rs.getString(10)));
			}
			
			if (vecBookmark != null)
				System.out.println("북마크 불러오기 성공");

			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return vecBookmark;
	}
	
	public boolean checkDuplicateById(String id) {
		boolean exists = true;
		try {
			Connection con = makeConnection();
			String sql = "select id from kiwidb.users where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				exists = false;
			}
		
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exists;
	}
}