package kiwi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import kiwi.dto.Review;
import kiwi.header.Pair;

public class ReviewDAO extends KiWiDAO {
	public void insert(Review review) {
		try {
			Connection con = getConnection();
			String sql = "insert into kiwidb.reviews values(null, ?, ?, ?, ?, now())";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, review.getUserId());
			pstmt.setInt(2, review.getMovieId());
			pstmt.setString(3, review.getContent());
			pstmt.setInt(4, review.getFreshRate());
			
			int rt = pstmt.executeUpdate();
			if (rt > 0) {
				System.out.println("리뷰 저장 성공...");
			} else {
				System.out.println("리뷰 저장 실패...");
			}
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Pair<Integer, Integer> selectFreshByMovieId(int movieId) {
		Pair<Integer, Integer> pairRate = null;
		try {
			Connection con = getConnection();
			String sql = "select sum(freshRate), count(freshRate) from kiwidb.reviews group by movie_id having movie_id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, movieId);
				
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (pairRate == null) {
					// t1 = sum (총점), t2 = count (리뷰수)
					pairRate = new Pair<>(rs.getInt(1), rs.getInt(2));
				}
			}
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pairRate;
	}
	
	
	public HashMap<Integer, Vector<Review>> selectAll() {
		HashMap<Integer, Vector<Review>> mapReviews = null;
		try {
			Connection con = getConnection();
			String sql = "select * from kiwidb.reviews";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (mapReviews == null) {
					mapReviews = new HashMap<Integer, Vector<Review>>();
				}
				
				Review review = new Review(
						rs.getInt(1)
						, rs.getString(2)
						, rs.getInt(3)
						, rs.getString(4)
						, rs.getInt(5)
						, rs.getDate(6));
				
				// 이미 키가 있다면 기존 벡터에 추가, 아니라면 새로 생성
				if (mapReviews.containsKey(review.getMovieId())) {
					mapReviews.get(review.getMovieId()).add(review);
				} else {
					Vector<Review> vecReview = new Vector<Review>();
					vecReview.add(review);
					mapReviews.put(review.getMovieId(), vecReview);
				}
			}
			
			if (mapReviews != null) {
				System.out.println("리뷰 불러오기 성공...");
			}
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mapReviews;
	}
	
	public Vector<Review> selectReviewsByMovieId(int movieID) {
		Vector<Review> vecReview = null;
		try {
			Connection con = getConnection();
			String sql = "select user_id, content, freshrate, reviewdate from kiwidb.reviews where movie_id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, movieID);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (vecReview == null) {
					vecReview = new Vector<Review>();
				}
				
				vecReview.add(new Review(
						rs.getInt(1)
						, rs.getString(2)
						, rs.getInt(3)
						, rs.getString(4)
						, rs.getInt(5)
						, rs.getDate(6)
						));
			}
			
			if (vecReview != null) {
				System.out.println("리뷰 불러오기 성공...");
			}
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vecReview;
	}
}
