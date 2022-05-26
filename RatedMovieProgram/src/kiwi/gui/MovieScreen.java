package kiwi.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import kiwi.dto.Movie;
import kiwi.dto.Review;
import kiwi.mgr.MovieMgr;
import kiwi.mgr.ResourceMgr;
import kiwi.mgr.ReviewMgr;

public class MovieScreen extends JPanel {
	public MovieScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(12, 14, 18));

		JSeparator hr = new JSeparator(JSeparator.HORIZONTAL);
		hr.setBackground(new Color(189, 198, 208));
		hr.setForeground(new Color(189, 198, 208));
		hr.setPreferredSize(new Dimension(1080, 5));
		hr.setMaximumSize(new Dimension(1080, 5));
		
		Movie curMovie = MovieMgr.getInstance().getCurMovie();
		this.add(getHeader(curMovie.getTitle()));
		this.add(hr);
		this.add(Box.createVerticalGlue());
		this.add(getBody(curMovie));
		this.add(Box.createVerticalGlue());
	}

	public JPanel getBody(Movie movie) {
		JLabel lPoster = new JLabel();
		try {
			BufferedImage resizedPoster = ResourceMgr.getInstance().resizeImage(movie.getPoster(), 210, 297);
			lPoster.setIcon(new ImageIcon(resizedPoster));
		} catch (IOException e) {
			e.printStackTrace();
		}
		lPoster.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		lPoster.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));
		
		JLabel lNumberInfo = new JLabel(String.format("%d세 관람가 | %s 개봉 | %d분 ", movie.getAgeLimit(), movie.getReleaseDate().toString(), movie.getRunningTime()));
		lNumberInfo.setFont(new Font("Arial", Font.PLAIN, 15));
		lNumberInfo.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		 
		JLabel lCharInfo = new JLabel(String.format("감독 [%s] | 배우 [%s] | %s", movie.getDirector(), movie.getActors(), movie.getGenre()));
		lCharInfo.setFont(new Font("Arial", Font.PLAIN, 15));
		lCharInfo.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		
		JLabel lSummary = new JLabel(" " + movie.getSummary());
		lSummary.setPreferredSize(new Dimension(300, 20));
		lSummary.setMaximumSize(new Dimension(300, 20));
		lSummary.setFont(new Font("Arial", Font.PLAIN, 15));
		lSummary.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		
		JPanel pBoxDescription = new JPanel();
		pBoxDescription.setLayout(new BoxLayout(pBoxDescription, BoxLayout.Y_AXIS));
		pBoxDescription.setBackground(new Color(189, 198, 208));
		pBoxDescription.add(Box.createVerticalStrut(20));
		pBoxDescription.add(lNumberInfo);
		pBoxDescription.add(lCharInfo);
		pBoxDescription.add(Box.createVerticalStrut(10));
		pBoxDescription.add(lSummary);
		pBoxDescription.add(Box.createVerticalGlue());

		JLabel lRatingIcon = new JLabel();
		lRatingIcon.setIcon(ResourceMgr.getInstance().resizeImageIcon("res/images/fresh.png", 50, 50));
		lRatingIcon.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JLabel lFreshRate = new JLabel(String.valueOf(movie.getRate()) + "%");
		lFreshRate.setForeground(new Color(12, 14, 18));
		lFreshRate.setFont(new Font("Arial", Font.PLAIN, 35));
		lFreshRate.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JPanel pBoxRating = new JPanel();
		pBoxRating.setLayout(new BoxLayout(pBoxRating, BoxLayout.Y_AXIS));
		pBoxRating.setBackground(new Color(235, 245, 240));
		pBoxRating.setPreferredSize(new Dimension(200, 200));
		pBoxRating.setMaximumSize(new Dimension(200, 200));
		pBoxRating.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.orange), "Fresh", TitledBorder.CENTER, TitledBorder.TOP));
		pBoxRating.add(Box.createVerticalGlue());
		pBoxRating.add(lRatingIcon);
		pBoxRating.add(lFreshRate);
		pBoxRating.add(Box.createVerticalGlue());
		
		JPanel pBoxInfo = new JPanel();
		pBoxInfo.setLayout(new BoxLayout(pBoxInfo, BoxLayout.X_AXIS));
		pBoxInfo.setBackground(new Color(189, 198, 208));
		pBoxInfo.setPreferredSize(new Dimension(780, 297));
		pBoxInfo.setMaximumSize(new Dimension(780, 297));
		pBoxInfo.add(Box.createHorizontalStrut(20));
		pBoxInfo.add(pBoxDescription);
		pBoxInfo.add(Box.createHorizontalStrut(20));
		pBoxInfo.add(pBoxRating);
		pBoxInfo.add(Box.createHorizontalStrut(40));

		JPanel pFlowInfo = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pFlowInfo.setBackground(new Color(12, 14, 18));
		pFlowInfo.setBorder(BorderFactory.createEmptyBorder(30, 80, 0, 80));
		pFlowInfo.add(lPoster);
		pFlowInfo.add(pBoxInfo);

		JPanel pGridReview = new JPanel();
		pGridReview.setLayout(new GridLayout(0, 3, 30, 30));
		pGridReview.setBackground(new Color(12, 14, 18));
		
		Vector<Review> vecReview = ReviewMgr.getInstance().findReviewByMovieId(MovieMgr.getInstance().getCurMovie().getId());
		if (vecReview != null) {
			int reviewCount = vecReview.size();
			for (int i = 0; i < reviewCount; ++i) {
				Review review = vecReview.get(i);
				
				JPanel pReview = new JPanel();
				pReview.setLayout(new BoxLayout(pReview, BoxLayout.Y_AXIS));
				pReview.setBackground(new Color(255, 250, 245));
				pReview.setPreferredSize(new Dimension(200, 250));
				pReview.setMaximumSize(new Dimension(200, 250));
				pReview.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(
						EtchedBorder.RAISED), "리뷰", TitledBorder.RIGHT,
						TitledBorder.TOP, new Font("Arial", Font.ITALIC, 18), Color.RED));
				
				// 리뷰 콘텐트 + 작성일 + 닉네임
				JTextArea taContent = new JTextArea();	
				taContent.setText(String.format("%s - %s, %s일에 작성됨.", review.getContent(), review.getUserId(), review.getReviewDate().toString()));
				taContent.setEditable(false);
				taContent.setLineWrap(true);
				taContent.setWrapStyleWord(true);
				taContent.setFont(new Font("Arial", Font.PLAIN, 13));
			
				pReview.add(taContent);
				pGridReview.add(pReview);
			}
		}
		
		// 리뷰 작성 칸
		JPanel pReview = new JPanel();
		pReview.setLayout(new BoxLayout(pReview, BoxLayout.Y_AXIS));
		pReview.setBackground(new Color(255, 250, 245));
		pReview.setPreferredSize(new Dimension(200, 250));
		pReview.setMaximumSize(new Dimension(200, 250));
		pReview.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(
				EtchedBorder.RAISED), "리뷰 작성", TitledBorder.RIGHT,
				TitledBorder.TOP, new Font("Arial", Font.ITALIC, 18), Color.RED));
		
		// 리뷰 콘텐트
		JTextArea taContent = new JTextArea();
		taContent.setBackground(new Color(12, 14, 18));
		taContent.setForeground(new Color(189, 198, 208));
		taContent.setCaretColor(new Color(189, 198, 208));
		taContent.setFont(new Font("Arial", Font.PLAIN, 13));
		taContent.setLineWrap(true);
		taContent.setWrapStyleWord(true);
		
		JButton btnAddReview = new JButton("등록");
		btnAddReview.setPreferredSize(new Dimension(260, 25));
		btnAddReview.setMaximumSize(new Dimension(260, 25));
		btnAddReview.setForeground(new Color(12, 14, 18));
		btnAddReview.setBackground(new Color(12, 14, 18));
		btnAddReview.setOpaque(true);
		btnAddReview.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnAddReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO :: 리뷰 등록
				
			}
		});
	
		pReview.add(taContent);
		pReview.add(btnAddReview);
		pGridReview.add(pReview);
		
		JScrollPane spBody = new JScrollPane(pGridReview, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		spBody.setBorder(BorderFactory.createEmptyBorder());

		JPanel pBoxGrid = new JPanel();
		pBoxGrid.setLayout(new BoxLayout(pBoxGrid, BoxLayout.X_AXIS));
		pBoxGrid.setBackground(new Color(12, 14, 18));
		pBoxGrid.add(Box.createHorizontalGlue());
		pBoxGrid.add(spBody);
		pBoxGrid.add(Box.createHorizontalGlue());
		
		JPanel pBoxBody = new JPanel();
		pBoxBody.setLayout(new BoxLayout(pBoxBody, BoxLayout.Y_AXIS));
		pBoxBody.setBackground(new Color(12, 14, 18));
		pBoxBody.add(pFlowInfo);
		pBoxBody.add(pBoxGrid);
		
		return pBoxBody;
	}
	
	public JPanel getHeader(String title) {
		JButton btnBookmark = new JButton();
		btnBookmark.setIcon(new ImageIcon("res/icons48/bookmark.png"));
		
		JLabel lTitle = new JLabel(title);
		lTitle.setForeground(new Color(189, 198, 208));
		lTitle.setFont(new Font("Arial", Font.PLAIN, 32));
		lTitle.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 0));
		
		JPanel pBoxHeader = new JPanel();
		pBoxHeader.setLayout(new BoxLayout(pBoxHeader, BoxLayout.X_AXIS));
		pBoxHeader.setBackground(new Color(12, 14, 18));
		pBoxHeader.add(btnBookmark);
		pBoxHeader.add(Box.createHorizontalStrut(10));
		pBoxHeader.add(lTitle);
		pBoxHeader.add(Box.createHorizontalGlue());
		
		return pBoxHeader;
	}
}
