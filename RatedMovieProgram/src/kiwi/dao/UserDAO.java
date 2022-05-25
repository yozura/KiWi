package kiwi.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.imageio.ImageIO;

import kiwi.dto.Movie;
import kiwi.dto.User;

public class UserDAO extends KiWiDAO  {
	public void insert(User user) {
		try {
			Connection con = getConnection();
			String sql = "insert into kiwidb.users values(?, md5(?), ?, ?, ?, ?, now())";
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
	
	public User findUser(String id, String password) {
		User user = null;
		try {
			Connection con = getConnection();
			String sql = "select nickname, birthday, email, tel from kiwidb.users where id = ? and password = md5(?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User(id, password, rs.getString(1), rs.getDate(2), rs.getString(3), rs.getString(4));
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
			Connection con = getConnection();
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
				try {
					vecBookmark.add(new Movie(
							rs.getInt(1)
							, rs.getString(2)
							, rs.getString(3)
							, rs.getString(4)
							, rs.getString(5)
							, rs.getInt(6)
							, rs.getDate(7)
							, rs.getInt(8)
							, rs.getInt(9)
							, rs.getString(10)
							, ImageIO.read(rs.getBinaryStream(11))));
				} catch (IOException e) {
					e.printStackTrace();
				}
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
	
	public boolean updatePassword(String email, String password) {
		boolean isGood = true;
		try {
			Connection con = getConnection();
			String sql = "update kiwidb.users set password = md5(?) where email = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, email);
			
			int rt = pstmt.executeUpdate();
			if (rt <= 0) {
				isGood = false;
			}
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isGood;
	}
	
	public boolean checkExistById(String id) {
		boolean exists = true;
		try {
			Connection con = getConnection();
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
	
	public boolean checkExistByEmail(String email) {
		boolean exists = true;
		try {
			Connection con = getConnection();
			String sql = "select email from kiwidb.users where email = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next() ) {
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