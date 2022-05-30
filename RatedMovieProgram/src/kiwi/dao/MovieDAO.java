package kiwi.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import javax.imageio.ImageIO;

import kiwi.dto.Movie;
import kiwi.header.Pair;

public class MovieDAO extends KiWiDAO {
	public void insert(Movie movie) {
		try {
			Connection con = getConnection();
			String sql = "insert into kiwidb.movies values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				ImageIO.write(movie.getPoster(), "jpeg", baos);
			} catch (IOException e) {
				e.printStackTrace();
			}
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			pstmt.setString(1, movie.getTitle());
			pstmt.setString(2, movie.getDirector());
			pstmt.setString(3, movie.getActors());
			pstmt.setString(4, movie.getGenre());
			pstmt.setDouble(5, movie.getRate());
			pstmt.setDate(6, movie.getReleaseDate());
			pstmt.setInt(7, movie.getRunningTime());
			pstmt.setInt(8, movie.getAgeLimit());
			pstmt.setString(9, movie.getSummary());
			pstmt.setBinaryStream(10, bais);
			
			int rt = pstmt.executeUpdate();
			if (rt >= 1) System.out.println("영화 정보가 등록되었습니다.");
			else System.out.println("영화 정보 등록에 실패했습니다.");
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateFresh(Pair<Integer, Integer> pairFreshId) {
		try {
			Connection con = getConnection();
			String sql = "update kiwidb.movies set freshrate = ? where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pairFreshId.first);
			pstmt.setInt(2, pairFreshId.second);
			
			int rt = pstmt.executeUpdate();
			if (rt >= 1) System.out.println("점수 갱신 성공...");
			else System.out.println("점수 갱신 실패...");
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int selectFreshByMovieId(int movieId) {
		int freshRate = -1;
		try {
			Connection con = getConnection();
			String sql = "select freshrate from kiwidb.movies where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, movieId);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				freshRate = rs.getInt(1);
			}
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return freshRate;
	}
	
	public boolean checkExistByTitle(String title) {
		boolean exists = true;
		try {
			Connection con = getConnection();
			String sql = "select title from kiwidb.movies where title = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			
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
	
	public HashMap<Integer, Movie> selectAll() {
		HashMap<Integer, Movie> mapMovie = null;
		try {
			Connection con = getConnection();
			String sql = "select * from kiwidb.movies";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (mapMovie == null) {
					mapMovie = new HashMap<Integer, Movie>();
				}
				try {
					int movie_id = rs.getInt(1);
					mapMovie.put(movie_id,
							new Movie(
							movie_id
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
			
			if (mapMovie != null) {
				System.out.println("영화 불러오기 성공...");
			}
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mapMovie;
	}
	
	// 전체 영화 점수 동기화
	public boolean updateSyncMovieFresh() {
		boolean isGood = false;
		try {
			Connection con = getConnection();
			String sql = "call synchronizeFreshRate()";
			PreparedStatement pstmt = con.prepareStatement(sql);
			isGood = pstmt.execute();
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isGood;
	}
}
