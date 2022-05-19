package kiwi.mgr;

import java.util.Vector;

import kiwi.dao.MovieDAO;
import kiwi.dto.Movie;

public class MovieMgr {
	public static MovieMgr instance = new MovieMgr();
	public Vector<Movie> vecMovie;
	
	public static MovieMgr getInstance() {
		return instance;
	}
	
	public void load() {
		MovieDAO dao = new MovieDAO();
		vecMovie = dao.selectAll();
	}
}
