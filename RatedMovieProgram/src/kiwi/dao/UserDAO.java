package kiwi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kiwi.dto.User;

public class UserDAO extends KiWiDAO  {
	public void insert(User user) {
		try {
			Connection con = makeConnection();
			String sql = "insert into kiwidb.users values(?, md5(?), ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getNickname());
			pstmt.setDate(4, user.getBirthDate());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getTel());
			
			int rt = pstmt.executeUpdate();
			if (rt >= 1) {
				System.out.println("계정 정보가 저장되었습니다.");
			} else {
				System.out.println("저장에 실패했습니다.");
			}
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void findAccountById(String id) {
		try {
			Connection con = makeConnection();
			String sql = "select * from kiwidb.users where = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			// TODO :: Accout정보 싹다 긁어서 로그인할 때 전달하
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean findDuplicateById(String id) {
		boolean exists = true;
		try {
			Connection con = makeConnection();
			String sql = "select id from kiwidb.users where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				exists = false;
			}
		
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exists;
	}
}