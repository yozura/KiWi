package kiwi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import kiwi.dto.Review;

public class ReviewDAO extends KiWiDAO {
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
