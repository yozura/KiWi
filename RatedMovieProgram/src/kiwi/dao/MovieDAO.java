package kiwi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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
	
	public Vector<Movie> selectAll() {
		Vector<Movie> vecMovie = null;
		try {
			Connection con = makeConnection();
			String sql = "select * from kiwidb.movies";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (vecMovie == null) {
					vecMovie = new Vector<Movie>();
				}
				
				vecMovie.add(new Movie(
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
			
			if (vecMovie != null) {
				System.out.println("selectAll 성공");
			}
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vecMovie;
	}
}
