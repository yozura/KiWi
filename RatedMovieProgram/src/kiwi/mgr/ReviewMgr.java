package kiwi.mgr;

import java.util.HashMap;
import java.util.Vector;

import kiwi.dao.ReviewDAO;
import kiwi.dto.Review;

public class ReviewMgr {
	private static ReviewMgr instance = new ReviewMgr();
	private HashMap<Integer, Vector<Review>> mapReview;
	
	public static ReviewMgr getInstance() {
		return instance;
	}
	
	public void load() {
		ReviewDAO rDAO = new ReviewDAO();
		mapReview = rDAO.selectAll();
	}
	
	public Vector<Review> findReviewByMovieId(int movieId) {
		if (mapReview.containsKey(movieId)) {
			return mapReview.get(movieId);
		}
		
		ReviewDAO rDAO = new ReviewDAO();
		mapReview.put(movieId, rDAO.selectReviewsByMovieId(movieId));
		return mapReview.get(movieId);
	}
	
	public HashMap<Integer, Vector<Review>> getMapReview() {
		return mapReview;
	}
}