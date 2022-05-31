package kiwi.gui.process;

import java.io.File;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import kiwi.dao.MovieDAO;
import kiwi.dao.ReviewDAO;
import kiwi.dao.UserDAO;
import kiwi.dto.Movie;
import kiwi.dto.User;
import kiwi.mgr.MovieMgr;
import kiwi.mgr.ReviewMgr;
import kiwi.mgr.ScreenMgr;
import kiwi.mgr.UserMgr;

public class AdminProcess {
	 public void addMovie(Movie movie, JComponent comp) {
		 MovieDAO mDAO = new MovieDAO();
		 mDAO.insert(movie);
		 
		 MovieMgr.getInstance().reloadMovie();
		 ScreenMgr.getInstance().redirectWithSideBar(comp);
	 }
	 
	 public void deleteMovie(String title, JComponent comp) {
		 Movie movie = MovieMgr.getInstance().getMovieByTitle(title);
		 if (movie == null) {
			 JOptionPane.showMessageDialog(null, String.format("'%s' 영화를 찾지 못했습니다. 다시 시도해주세요.", title));
			 return;
		 }
		 
		 MovieDAO mDAO = new MovieDAO();
		 mDAO.delete(movie.getId());
		 
		 MovieMgr.getInstance().reloadMovie();
		 UserMgr.getInstance().reloadBookmark();
		 ReviewMgr.getInstance().reloadReview();
		 ScreenMgr.getInstance().redirectWithSideBar(comp);
	 }
	 
	 public void deleteReview(int reviewId, JComponent comp) {
		 ReviewDAO rDAO = new ReviewDAO();
		 rDAO.delete(reviewId);
		 
		 MovieMgr.getInstance().reloadMovie();
		 UserMgr.getInstance().reloadBookmark();
		 ReviewMgr.getInstance().reloadReview();
		 ScreenMgr.getInstance().redirectWithSideBar(comp);
	 }
	 
	 public void deleteUser(String userId, JComponent comp) {
		 UserDAO uDAO = new UserDAO();
		 uDAO.delete(userId);
		 
		 MovieMgr.getInstance().reloadMovie();
		 UserMgr.getInstance().reloadBookmark();
		 ReviewMgr.getInstance().reloadReview();
		 ScreenMgr.getInstance().redirectWithSideBar(comp);
	 }
	 
	 public Vector<User> findAllUser() {
		 Vector<User> vecUser = null;
		 
		 UserDAO uDAO = new UserDAO();
		 vecUser = uDAO.selectAll();
		  
		 return vecUser;
	 }
	 
	 public boolean checkExistByTitle(String title) {
		 if (new MovieDAO().checkExistByTitle(title)) {
				JOptionPane.showMessageDialog(null, "이미 있는 영화입니다.", "영화 중복 메시지", JOptionPane.PLAIN_MESSAGE);
				System.out.println("이미 있는 영화입니다.");
				return false;
			}
			return true;
	 }
	 
	 public boolean checkValidationMovie(File poster, String...strings) {
		 // Title
		 if (!Pattern.matches("^[a-zA-Z가-힣0-9!@#$:?,\\s]{1,100}$", strings[0])) {
			 JOptionPane.showMessageDialog(null, "영화 제목은 한글, 영문, 숫자, 일부 특수문자로 구성되야 합니다.");
			 System.out.println("영화 제목은 한글, 영문, 숫자, 일부 특수문자로 구성되야 합니다.");
			 return false;
		 }
		 
		 // Director
		 if (!Pattern.matches("^[a-zA-Z가-힣,\\s]{1,100}$", strings[1])) {
			 JOptionPane.showMessageDialog(null, "감독명은 한글, 영문으로 구성되야 합니다.");
			 System.out.println("감독명은 한글, 영문으로 구성되야 합니다.");
			 return false;
		 }
		 
		 // Actors
		 if (!Pattern.matches("^[a-zA-Z가-힣,\\s]{1,100}$", strings[2])) {
			 JOptionPane.showMessageDialog(null, "배우명은 한글, 영문으로 구성되야 합니다.");
			 System.out.println("배우명은 한글, 영문으로 구성되야 합니다.");
			 return false;
		 }
		 
		 // Genre
		 if (!Pattern.matches("^[a-zA-Z가-힣,\\s]{1,100}$", strings[3])) {
			 JOptionPane.showMessageDialog(null, "장르명은 한글, 영문으로 구성되야 합니다.");
			 System.out.println("장르명은 한글, 영문으로 구성되야 합니다.");
			 return false;
		 }
		 
		 // ReleaseDate
		 if (!Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", strings[4])) {
			JOptionPane.showMessageDialog(null, "개봉일은 yyyy-mm-dd의 형식으로 구성되어야 합니다.");
			System.out.println("개봉일은 yyyy-mm-dd의 형식으로 구성되어야 합니다.");
			return false;
		 }
		 
		 // running time
		 if (!Pattern.matches("^[0-9]{1,3}$", strings[5])) {
			JOptionPane.showMessageDialog(null, "상영 시간은 분으로 환산하여 기입해야 합니다.");
			System.out.println("상영 시간은 분으로 환산하여 기입해야 합니다.");
			return false;
		 }

		 // summary
		 if (!Pattern.matches("^[a-zA-Z가-힣0-9\\s,'\"!@#$:?.]{1,500}$", strings[6])) {
			JOptionPane.showMessageDialog(null, "줄거리는 500자 이내로 입력해야 합니다.");
			System.out.println("줄거리는 500자 이내로 입력해야 합니다.");
			return false;
		 }
		 
		 // poster
		 if (poster == null) {
			JOptionPane.showMessageDialog(null, "포스터를 업로드해주세요.");
			return false;
		 }
		 
		 if (poster.length() > 16777215) {
			JOptionPane.showMessageDialog(null, "포스터의 크기가 16MB를 초과합니다.");
			return false;
		 }

		 return true;
	 }
}