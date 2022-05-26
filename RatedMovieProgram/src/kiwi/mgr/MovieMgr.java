package kiwi.mgr;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Vector;

import javax.imageio.ImageIO;

import kiwi.dao.MovieDAO;
import kiwi.dto.Movie;

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
