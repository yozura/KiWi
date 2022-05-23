package kiwi.gui.process;

import java.io.File;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import kiwi.dao.MovieDAO;
import kiwi.dto.Movie;

public class AdminProcess {
	 public void addMovie(Movie movie) {
		 
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
		 if (!Pattern.matches("^[a-zA-Z가-힣0-9!@#$:,\\s]{1,100}$", strings[0])) {
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
		 
		 // ageLimit
		 if (!Pattern.matches("^[0-9]{1,3}$", strings[6])) {
			JOptionPane.showMessageDialog(null, "연령 제한은 숫자로 입력하셔야 합니다.");
			System.out.println("연령 제한은 숫자로 입력하셔야 합니다.");
			return false;
		 }

		 // summary
		 if (!Pattern.matches("^[a-zA-Z가-힣0-9\\s,!@#$:]{1,500}$", strings[7])) {
			JOptionPane.showMessageDialog(null, "줄거리는 500자 이내로 입력해야 합니다.");
			System.out.println("줄거리는 500자 이내로 입력해야 합니다.");
			return false;
		 }
		 
		 // poster
		 if (poster == null) {
			return false;
		 }

		 return true;
	 }
}