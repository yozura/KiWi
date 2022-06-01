package kiwi.mgr;

import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.Vector;

import kiwi.dao.MovieDAO;
import kiwi.dao.ReviewDAO;
import kiwi.dto.Movie;
import kiwi.header.Pair;

public class MovieMgr {
	public static MovieMgr instance = new MovieMgr();
	public LinkedHashMap<Integer, Movie> mapMovie;
	public Vector<String> vecMovieTitles;
	public Movie curMovie;
	
	public static MovieMgr getInstance() {
		return instance;
	}

	public void reloadMovie() {
		MovieDAO dao = new MovieDAO();
		mapMovie = dao.selectAll();
		vecMovieTitles = new Vector<String>();
		for (int movieId : mapMovie.keySet()) {
			String title = mapMovie.get(movieId).getTitle();
			vecMovieTitles.add(title);
		}
	}
	
	public void updateCalculateFresh(int movieId) {
		MovieDAO mDAO = new MovieDAO();
		ReviewDAO rDAO = new ReviewDAO();
		Pair<Integer, Integer> pairRate = rDAO.selectFreshByMovieId(movieId);
		if (pairRate == null) {
			mDAO.updateFresh(new Pair<>(0, movieId));
			return;
		}
		
		System.out.println("리뷰 수 : " + pairRate.second + ", 총점 : " + pairRate.first);
		
		// (리뷰 총 점수) / (리뷰수 * 100) * 100 이 해당 영화의 점수.
		float freshRate = (((float)pairRate.first / ((float)pairRate.second * 100)) * 100);
		System.out.println("갱신된 점수 : " + freshRate + ", 대상 영화 : " + movieId);
		if (freshRate < 0) {
			System.out.println("접수가 0점보다 낮습니다.");
			return;
		}
		
		Pair<Integer, Integer> pairFreshId = new Pair<>((int)freshRate, movieId);
		mDAO.updateFresh(pairFreshId);
	}
	
	public BufferedImage findPosterByMovieId(int movieId) {
		if (mapMovie.containsKey(movieId)) {
			return mapMovie.get(movieId).getPoster();
		}
		
		return null;
	}
	
	public LinkedHashMap<Integer, Movie> getMapMovie() {
		return mapMovie;
	}
	
	public int getMoviesCount() {
		return mapMovie.size();
	}
	
	public Vector<String> getVecMovieTitles() {
		if (vecMovieTitles == null) {
			return null;
		}
		
		return vecMovieTitles;
	}
	
	public String getTitleByMovieId(int movieId) {
		if (mapMovie.containsKey(movieId)) {
			return mapMovie.get(movieId).getTitle();
		}
		
		return null;
	}
	
	public Movie getMovieByTitle(String title) {
		if (mapMovie == null) {
			return null;
		}
		
		for (int movieId : mapMovie.keySet()) {
			if (mapMovie.get(movieId).getTitle().equals(title)) {
				return mapMovie.get(movieId);
			}
		}
		
		return null;
	}
	
	public Movie getCurMovie() {
		return curMovie;
	}

	public void setCurMovie(int movieId) {
		if (mapMovie.containsKey(movieId)) {
			curMovie = mapMovie.get(movieId);
		}
	}
}
