package kiwi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kiwi.dto.Movie;

public class BookmarkDAO extends KiWiDAO {
	public ArrayList<Movie> findBookmarkByUserId(String id) {
		ArrayList<Movie> listOfBookmark = null;
		try {
			Connection con = makeConnection();
			String sql = "select m.* from movies as m"
					+ " inner join bookmarks as bm"
					+ " on bm.user_id = ? and bm.movie_id = m.id";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (listOfBookmark == null) {
					listOfBookmark = new ArrayList<Movie>();
				}
				listOfBookmark.add(new Movie(
						rs.getString(1)
						, rs.getString(2)
						, rs.getString(3)
						, rs.getString(4)
						, rs.getDouble(5)
						, rs.getDate(6)
						, rs.getInt(7)
						, rs.getInt(8)
						, rs.getString(9)));
			}

			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return listOfBookmark;
	}
}
