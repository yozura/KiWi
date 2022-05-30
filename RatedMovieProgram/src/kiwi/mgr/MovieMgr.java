package kiwi.mgr;

import java.util.HashMap;

import kiwi.dao.MovieDAO;
import kiwi.dao.ReviewDAO;
import kiwi.dto.Movie;
import kiwi.header.Pair;

public class MovieMgr {
	public static MovieMgr instance = new MovieMgr();
	public HashMap<Integer, Movie> mapMovie;
	public Movie curMovie;
	
	public static MovieMgr getInstance() {
		return instance;
	}

	public void load() {
		MovieDAO dao = new MovieDAO();
		mapMovie = dao.selectAll();
	}
	
	public void updateCalculateFresh(int movieId) {
		MovieDAO mDAO = new MovieDAO();
		ReviewDAO rDAO = new ReviewDAO();
		Pair<Integer, Integer> pairRate = rDAO.selectFreshByMovieId(movieId);
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
	
	public HashMap<Integer, Movie> getMapMovie() {
		return mapMovie;
	}
	
	public int getMoviesCount() {
		return mapMovie.size();
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
