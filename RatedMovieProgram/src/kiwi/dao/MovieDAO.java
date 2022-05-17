package kiwi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kiwi.dto.Movie;

public class MovieDAO extends KiWiDAO {
	public void insert(Movie movie) {
		try {
			Connection con = makeConnection();
			String sql = "insert into kiwidb.movies values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, movie.getTitle());
			pstmt.setString(2, movie.getDirector());
			pstmt.setString(3, movie.getActors());
			pstmt.setString(4, movie.getGenre());
			pstmt.setDouble(5, movie.getRate());
			pstmt.setDate(6, movie.getReleaseDate());
			pstmt.setInt(7, movie.getRunningTime());
			pstmt.setInt(8, movie.getAgeLimit());
			pstmt.setString(9, movie.getSummary());
			
			int rt = pstmt.executeUpdate();
			if (rt >= 1) System.out.println("영화 정보가 등록되었습니다.");
			else System.out.println("영화 정보 등록에 실패했습니다.");
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
}
