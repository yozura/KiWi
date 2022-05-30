package kiwi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kiwi.dto.Bookmark;

public class BookmarkDAO extends KiWiDAO{
	public void insert(Bookmark bookmark) {
		try {
			Connection con = getConnection();
			String sql = "insert into kiwidb.bookmarks values (?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookmark.getUserId());
			pstmt.setInt(2, bookmark.getMovieId());
		
			int rt = pstmt.executeUpdate();
			if (rt > 0) {
				System.out.println("북마크 추가 성공...");
			} else {
				System.out.println("북마크 추가 실패...");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean delete(Bookmark bookmark) {
		boolean isDeleted = true;
		try {
			Connection con = getConnection();
			String sql = "delete from kiwidb.bookmarks where user_id = ? and movie_id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookmark.getUserId());
			pstmt.setInt(2, bookmark.getMovieId());
			
			int rt = pstmt.executeUpdate();
			if (rt > 0) {
				System.out.println("북마크 삭제 성공...");
			} else {
				isDeleted = false;
				System.out.println("북마크 삭제 실패...");
			}
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isDeleted;
	}
}