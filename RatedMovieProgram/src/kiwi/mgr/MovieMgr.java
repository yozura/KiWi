package kiwi.mgr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Vector;

import javax.imageio.ImageIO;

import kiwi.dao.MovieDAO;
import kiwi.dao.ReviewDAO;
import kiwi.dto.Movie;
import kiwi.header.Pair;

public class MovieMgr {
	public static MovieMgr instance = new MovieMgr();
	public Vector<Movie> vecMovie;
	public Movie curMovie;
	
	public static MovieMgr getInstance() {
		return instance;
	}
	
	public MovieMgr() {
		BufferedImage poster = null;
		try {
			poster = ImageIO.read(new File("res/dogs.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		curMovie = new Movie(
				0
				, "저수지의 개들"
				, "쿠엔틴 타란티노"
				, "마이클 잭슨"
				, "코미디"
				, 80
				, Date.valueOf("2021-01-01")
				, 130
				, 15
				, "대충 세상이 멸망한 뒤"
				, poster
				);
	}
	
	public void load() {
		MovieDAO dao = new MovieDAO();
		vecMovie = dao.selectAll();
	}
	
	public void updateCalculateFresh(int movieId) {
		MovieDAO mDAO = new MovieDAO();
		ReviewDAO rDAO = new ReviewDAO();
		Pair<Integer, Integer> pairRate = rDAO.selectFreshByMovieId(movieId);
		System.out.println("리뷰 수 : " + pairRate.first + ", 총점 : " + pairRate.second);
		
		// (리뷰 총 점수) / (리뷰수 * 100) * 100 이 해당 영화의 점수.
		int freshRate = (pairRate.second / (pairRate.first * 100) * 100);
		System.out.println("갱신된 점수 : " + freshRate + ", 대상 영화 : " + movieId);
		if (freshRate < 0) {
			return;
		}
		
		Pair<Integer, Integer> pairFreshId = new Pair<>(freshRate, movieId);
		
		mDAO.updateFresh(pairFreshId);
	}
	
	public Vector<Movie> getMovies() {
		return vecMovie;
	}
	
	public int getMoviesCount() {
		return vecMovie.size();
	}
	
	public Movie getCurMovie() {
		return curMovie;
	}
	
	public void setCurMovie(int index) {
		curMovie = vecMovie.get(index);
	}
}
