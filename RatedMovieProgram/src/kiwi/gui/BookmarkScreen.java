package kiwi.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import kiwi.dto.Bookmark;
import kiwi.dto.Movie;
import kiwi.gui.process.BookmarkProcess;
import kiwi.header.Define.SCREEN_TYPE;
import kiwi.mgr.MovieMgr;
import kiwi.mgr.ResourceMgr;
import kiwi.mgr.ScreenMgr;
import kiwi.mgr.UserMgr;

public class BookmarkScreen extends JPanel {
	private static final long serialVersionUID = -807717172091339569L;
	
	public BookmarkScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(12, 14, 18));
		this.setBorder(BorderFactory.createLineBorder(new Color(189, 198, 208), 1));
		
		// Header -------------------------
		
		JLabel lWelcome = new JLabel(String.format("%s's Favorite Movies", UserMgr.getInstance().getCurUser().getNickname()));
		lWelcome.setForeground(new Color(189, 198, 208));
		lWelcome.setFont(new Font("Arial", Font.PLAIN, 32));
		lWelcome.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		lWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lWelcome.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JSeparator hr = new JSeparator(JSeparator.HORIZONTAL);
		hr.setForeground(new Color(189, 198, 208));
		hr.setPreferredSize(new Dimension(1080, 7));
		hr.setMaximumSize(new Dimension(1080, 7));
		
		// Body -------------------------
		
		JPanel pGridBody = new JPanel(new GridLayout(0, 4, 30, 30));
		pGridBody.setBackground(new Color(12, 14, 18));
		
		JLabel lGuide = null;
		
		HashMap<Integer, Movie> mapBookmark = UserMgr.getInstance().getMapBookmark();
		if (mapBookmark != null) {
			for (int movieId : mapBookmark.keySet()) {
				Movie movie = mapBookmark.get(movieId);
				
				JPanel pMovie = new JPanel();
				pMovie.setLayout(new BoxLayout(pMovie, BoxLayout.Y_AXIS));
				pMovie.setBackground(new Color(12, 14, 18));
				pMovie.setPreferredSize(new Dimension(210, 397));
				pMovie.setMaximumSize(new Dimension(210, 397));
				
				BufferedImage iconChangedLogo = null;
				try {
					iconChangedLogo = ResourceMgr.getInstance().resizeImage(movie.getPoster(), 210, 297);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				JButton lPoster = new JButton();
				lPoster.setName(String.valueOf(movie.getId()));
				lPoster.setIcon(new ImageIcon(iconChangedLogo));
				lPoster.setAlignmentX(JComponent.CENTER_ALIGNMENT);
				lPoster.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 무비 스크린으로 이동
						JButton btn = (JButton)e.getSource();
						
						MovieMgr.getInstance().setCurMovie(Integer.parseInt(btn.getName()));
						ScreenMgr.getInstance().changeCurScreenWithBar(SCREEN_TYPE.MOVIE, btn);
					}				
				});
				
				JLabel lTitle = new JLabel(movie.getTitle());
				lTitle.setForeground(new Color(189, 198, 208));
				lTitle.setFont(new Font("Arial", Font.PLAIN, 15));
				lTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);
				
				JLabel lGrade = new JLabel(String.valueOf(movie.getRate()));
				lGrade.setForeground(new Color(189, 198, 208));
				lGrade.setFont(new Font("Arial", Font.PLAIN, 15));
				lGrade.setAlignmentX(JComponent.CENTER_ALIGNMENT);
				
				JButton btnCancelBookmark = new JButton("북마크 해제");
				btnCancelBookmark.setName(String.valueOf(movie.getId()));
				btnCancelBookmark.setPreferredSize(new Dimension(210, 25));
				btnCancelBookmark.setMaximumSize(new Dimension(210, 25));
				btnCancelBookmark.setAlignmentX(JComponent.CENTER_ALIGNMENT);
				btnCancelBookmark.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						BookmarkProcess bp = new BookmarkProcess();
						JButton btn = (JButton)e.getSource();
						
						Bookmark bookmark = new Bookmark(UserMgr.getInstance().getCurUser().getId(), movie.getId());
						bp.DeleteBookmark(bookmark, btn);
					}
				});
				
				pMovie.add(lPoster);
				pMovie.add(lTitle);
				pMovie.add(lGrade);
				pMovie.add(btnCancelBookmark);
				
				pGridBody.add(pMovie);
			}
		} else {
			lGuide = new JLabel("당신의 북마크가 비어있습니다.");
			lGuide.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			lGuide.setHorizontalAlignment(SwingConstants.CENTER);
			lGuide.setForeground(new Color(189, 198, 208));
			lGuide.setFont(new Font("Arial", Font.PLAIN, 32));
		}
		
		JScrollPane scPane = new JScrollPane(pGridBody, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scPane.setBackground(new Color(12, 14, 18));
		scPane.setBorder(BorderFactory.createEmptyBorder());
		
		JPanel pBoxPane = new JPanel();
		pBoxPane.setLayout(new BoxLayout(pBoxPane, BoxLayout.X_AXIS));
		pBoxPane.setBackground(new Color(12, 14, 18));
		pBoxPane.add(Box.createHorizontalGlue());
		if (UserMgr.getInstance().getMapBookmark() != null) pBoxPane.add(scPane);
		else pBoxPane.add(lGuide);
		pBoxPane.add(Box.createHorizontalGlue());

		// -------------------------
		
		this.add(lWelcome);
		this.add(hr);
		this.add(Box.createVerticalStrut(40));
		this.add(pBoxPane);
	}
}
