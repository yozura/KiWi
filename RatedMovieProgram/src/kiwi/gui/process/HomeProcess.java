package kiwi.gui.process;

import java.util.LinkedHashMap;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import kiwi.dto.Movie;
import kiwi.header.Define.SCREEN_TYPE;
import kiwi.header.Pair;
import kiwi.mgr.MovieMgr;
import kiwi.mgr.ScreenMgr;

public class HomeProcess {
	// 영화 검색
	public Vector<Pair<String, Integer>> searchMovie(String src) {
		Vector<Pair<String, Integer>> similarTitles = null;
		LinkedHashMap<Integer, Movie> mapMovie = MovieMgr.getInstance().getMapMovie();
		for (int movieId : mapMovie.keySet()) {
			String title = mapMovie.get(movieId).getTitle();
			if (title.contains(src)) {
				if (similarTitles == null) {
					similarTitles = new Vector<Pair<String, Integer>>();
				}
				
				similarTitles.add(new Pair<String, Integer>(title, movieId));
			}
		}
		return similarTitles;
	}
	
	// 영화 스크린으로 이동	
	public void moveToMovieScreen(String title, JComponent comp) {	
		Movie movie = MovieMgr.getInstance().getMovieByTitle(title);
		if (movie != null) {
			MovieMgr.getInstance().setCurMovie(movie.getId());
			ScreenMgr.getInstance().changeCurScreenWithBar(SCREEN_TYPE.MOVIE, comp);
		} else {
			JOptionPane.showMessageDialog(null, String.format("'%s'이라는 영화가 없습니다. 다시 입력해주세요.", title));
		}
	}
}