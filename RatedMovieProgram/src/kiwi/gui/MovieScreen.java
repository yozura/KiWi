package kiwi.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import kiwi.dto.Bookmark;
import kiwi.dto.Movie;
import kiwi.dto.Review;
import kiwi.gui.process.MovieProcess;
import kiwi.mgr.MovieMgr;
import kiwi.mgr.ResourceMgr;
import kiwi.mgr.ReviewMgr;
import kiwi.mgr.UserMgr;

public class MovieScreen extends JPanel {
	private static final long serialVersionUID = 6124173310210976631L;

	private JTextArea taAddContent;
	private JSlider sFreshRate;

	public MovieScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(12, 14, 18));
		this.setBorder(BorderFactory.createLineBorder(new Color(189, 198, 208), 1));

		Movie curMovie = MovieMgr.getInstance().getCurMovie();
		this.add(getMovieBody(curMovie));
		this.add(Box.createVerticalGlue());
	}

	public JPanel getMovieBody(Movie movie) {
		JLabel lPoster = new JLabel();
		try {
			BufferedImage resizedPoster = ResourceMgr.getInstance().resizeImage(movie.getPoster(), 210, 297);
			lPoster.setIcon(new ImageIcon(resizedPoster));
		} catch (IOException e) {
			e.printStackTrace();
		}
		lPoster.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		lPoster.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));

		// header ----

		JButton btnAddBookmark = new JButton(movie.getTitle());
		if (UserMgr.getInstance().checkBookmarkByMovieId(movie.getId())) {
			btnAddBookmark.setIcon(new ImageIcon("res/icons/bookmark_remove.png"));
		} else {
			btnAddBookmark.setIcon(new ImageIcon("res/icons/bookmark_add.png"));
		}
		btnAddBookmark.setFont(new Font("ONE 모바일고딕 Regular", Font.PLAIN, 24));
		btnAddBookmark.setToolTipText(String.format("%s - 클릭 시 북마크에 추가됩니다.", movie.getTitle()));
		btnAddBookmark.setBorder(BorderFactory.createEmptyBorder());
		btnAddBookmark.setOpaque(true);
		btnAddBookmark.setBackground(new Color(189, 198, 208));
		btnAddBookmark.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		btnAddBookmark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				MovieProcess mp = new MovieProcess();
				Bookmark bookmark = new Bookmark(UserMgr.getInstance().getCurUser().getId(), movie.getId());
				if (UserMgr.getInstance().checkBookmarkByMovieId(movie.getId())) {
					mp.deleteBookmark(bookmark, btn);
					btn.setIcon(new ImageIcon("res/icons/bookmark_add.png"));
				} else {
					mp.addBookmark(bookmark, btn);
					btn.setIcon(new ImageIcon("res/icons/bookmark_remove.png"));
				}					
			}
		});
		btnAddBookmark.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				JButton btn = (JButton)e.getSource();
				btn.setForeground(new Color(71, 143, 250));
			}
			
			public void mouseExited(MouseEvent e) {
				JButton btn = (JButton)e.getSource();
				btn.setForeground(new Color(12, 14, 18));
			}
		});

		// -----------

		JLabel lNumberInfo = new JLabel(String.format("%d세 관람가 | %s 개봉 | %d분 ", movie.getAgeLimit(),
				movie.getReleaseDate().toString(), movie.getRunningTime()));
		lNumberInfo.setFont(new Font("ONE 모바일고딕 Regular", Font.PLAIN, 15));
		lNumberInfo.setToolTipText(String.format("%d세 관람가 | %s 개봉 | %d분 ", movie.getAgeLimit(),
				movie.getReleaseDate().toString(), movie.getRunningTime()));
		lNumberInfo.setPreferredSize(new Dimension(500, 25));
		lNumberInfo.setMaximumSize(new Dimension(500, 25));
		lNumberInfo.setBackground(Color.ORANGE);
		lNumberInfo.setOpaque(true);
		lNumberInfo.setAlignmentX(JComponent.LEFT_ALIGNMENT);

		JLabel lCharInfo = new JLabel(
				String.format("감독 [%s] | 배우 [%s] | %s", movie.getDirector(), movie.getActors(), movie.getGenre()));
		lCharInfo.setPreferredSize(new Dimension(500, 25));
		lCharInfo.setMaximumSize(new Dimension(500, 25));
		lCharInfo.setToolTipText(String.format("감독 [%s] | 배우 [%s] | %s", movie.getDirector(), movie.getActors(), movie.getGenre()));
		lCharInfo.setFont(new Font("ONE 모바일고딕 Regular", Font.PLAIN, 15));
		lCharInfo.setBackground(Color.CYAN);
		lCharInfo.setOpaque(true);
		lCharInfo.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		
		JTextArea taSummary = new JTextArea(" " + movie.getSummary());
		taSummary.setPreferredSize(new Dimension(500, 200));
		taSummary.setMaximumSize(new Dimension(500, 200));
		taSummary.setBackground(new Color(189, 198, 208));
		taSummary.setFont(new Font("ONE 모바일고딕 Regular", Font.PLAIN, 15));
		taSummary.setEditable(false);
		taSummary.setLineWrap(true);
		taSummary.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		taSummary.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int max = 500;
				if (taSummary.getText().length() > max + 1) {
					e.consume();
					String shortened = taSummary.getText().substring(0, max);
					taSummary.setText(shortened);
				} else if (taSummary.getText().length() > max) {
					e.consume();
				}
			}
		});

		JPanel pBoxDescription = new JPanel();
		pBoxDescription.setLayout(new BoxLayout(pBoxDescription, BoxLayout.Y_AXIS));
		pBoxDescription.setBackground(new Color(189, 198, 208));
		pBoxDescription.add(Box.createVerticalStrut(20));
		pBoxDescription.add(btnAddBookmark);
		pBoxDescription.add(Box.createVerticalStrut(10));
		pBoxDescription.add(lNumberInfo);
		pBoxDescription.add(lCharInfo);
		pBoxDescription.add(Box.createVerticalStrut(10));
		pBoxDescription.add(taSummary);
		pBoxDescription.add(Box.createVerticalGlue());

		JLabel lRatingIcon = new JLabel();
		lRatingIcon.setIcon(ResourceMgr.getInstance().resizeImageIcon("res/images/fresh.png", 50, 50));
		lRatingIcon.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JLabel lFreshRate = new JLabel(String.format("%d%%", movie.getRate()));
		lFreshRate.setForeground(new Color(12, 14, 18));
		lFreshRate.setFont(new Font("ONE 모바일고딕 Regular", Font.PLAIN, 35));
		lFreshRate.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JPanel pBoxRating = new JPanel();
		pBoxRating.setLayout(new BoxLayout(pBoxRating, BoxLayout.Y_AXIS));
		pBoxRating.setBackground(new Color(235, 245, 240));
		pBoxRating.setPreferredSize(new Dimension(200, 200));
		pBoxRating.setMaximumSize(new Dimension(200, 200));
		pBoxRating.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.orange), "신선도",
				TitledBorder.CENTER, TitledBorder.TOP));
		pBoxRating.add(Box.createVerticalGlue());
		pBoxRating.add(lRatingIcon);
		pBoxRating.add(lFreshRate);
		pBoxRating.add(Box.createVerticalGlue());

		JPanel pBoxInfo = new JPanel();
		pBoxInfo.setLayout(new BoxLayout(pBoxInfo, BoxLayout.X_AXIS));
		pBoxInfo.setBackground(new Color(189, 198, 208));
		pBoxInfo.setPreferredSize(new Dimension(750, 297));
		pBoxInfo.setMaximumSize(new Dimension(750, 297));
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

		// Review -----------

		JPanel pGridReview = new JPanel();
		pGridReview.setLayout(new GridLayout(0, 3, 30, 30));
		pGridReview.setBackground(new Color(12, 14, 18));

		if (ReviewMgr.getInstance().getMapReview() != null) {
			Vector<Review> vecReview = ReviewMgr.getInstance()
					.findReviewByMovieId(MovieMgr.getInstance().getCurMovie().getId());
			if (vecReview != null) {
				int reviewCount = vecReview.size();
				for (int i = 0; i < reviewCount; ++i) {
					Review review = vecReview.get(i);

					JPanel pReview = new JPanel();
					pReview.setLayout(new BoxLayout(pReview, BoxLayout.Y_AXIS));
					pReview.setBackground(new Color(255, 250, 245));
					pReview.setPreferredSize(new Dimension(200, 250));
					pReview.setMaximumSize(new Dimension(200, 250));
					pReview.setBorder(BorderFactory.createTitledBorder(
							BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "리뷰", TitledBorder.RIGHT,
							TitledBorder.TOP, new Font("ONE 모바일고딕 Regular", Font.ITALIC, 18), Color.RED));

					// 리뷰 콘텐트 + 작성일 + 닉네임 + 점수
					JTextArea taContent = new JTextArea();
					taContent.setText(String.format("%s - [%d%%] %s, %s에 작성됨.", review.getContent(),
							review.getFreshRate(), UserMgr.getInstance().findNicknameByUserId(review.getUserId())
							, review.getReviewDate().toString()));
					taContent.setEditable(false);
					taContent.setLineWrap(true);
					taContent.setWrapStyleWord(true);
					taContent.setFont(new Font("ONE 모바일고딕 Regular", Font.PLAIN, 13));

					pReview.add(taContent);
					pGridReview.add(pReview);
				}
			}
		}

		// 리뷰 작성 칸
		JPanel pAddReview = new JPanel();
		pAddReview.setLayout(new BoxLayout(pAddReview, BoxLayout.Y_AXIS));
		pAddReview.setBackground(new Color(255, 250, 245));
		pAddReview.setPreferredSize(new Dimension(200, 250));
		pAddReview.setMaximumSize(new Dimension(200, 250));
		pAddReview.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
				"리뷰 작성", TitledBorder.RIGHT, TitledBorder.TOP, new Font("ONE 모바일고딕 Regular", Font.ITALIC, 18), Color.RED));

		sFreshRate = new JSlider(0, 100, 50);
		sFreshRate.setMajorTickSpacing(10);
		sFreshRate.setPaintTicks(true);
		sFreshRate.setPaintLabels(true);

		taAddContent = new JTextArea();
		taAddContent.setBackground(new Color(12, 14, 18));
		taAddContent.setForeground(new Color(189, 198, 208));
		taAddContent.setCaretColor(new Color(189, 198, 208));
		taAddContent.setFont(new Font("ONE 모바일고딕 Regular", Font.PLAIN, 13));
		taAddContent.setLineWrap(true);
		taAddContent.setWrapStyleWord(true);
		taAddContent.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int max = 200;
				if (taAddContent.getText().length() > max + 1) {
					e.consume();
					String shortened = taAddContent.getText().substring(0, max);
					taAddContent.setText(shortened);
				} else if (taAddContent.getText().length() > max) {
					e.consume();
				}
			}
		});

		JButton btnAddReview = new JButton("등록");
		btnAddReview.setPreferredSize(new Dimension(260, 25));
		btnAddReview.setMaximumSize(new Dimension(260, 25));
		btnAddReview.setForeground(new Color(12, 14, 18));
		btnAddReview.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnAddReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MovieProcess mp = new MovieProcess();
				JButton btn = (JButton) e.getSource();
				boolean isGood = mp.checkValidationReview(taAddContent.getText())
								&& mp.checkCurrentUser();
				if (isGood) {
					Review review = new Review(0, UserMgr.getInstance().getCurUser().getId(),
							MovieMgr.getInstance().getCurMovie().getId(), taAddContent.getText(), sFreshRate.getValue(),
							null);
					mp.addReview(review, btn);
				}
			}
		});
		
		if (!UserMgr.getInstance().checkReviewByMovieId(movie.getId())) {
			pAddReview.add(taAddContent);
			pAddReview.add(sFreshRate);
			pAddReview.add(btnAddReview);
			pGridReview.add(pAddReview);
		}

		JScrollPane spBody = new JScrollPane(pGridReview, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		spBody.setBorder(BorderFactory.createEmptyBorder());

		JPanel pBoxGrid = new JPanel();
		pBoxGrid.setLayout(new BoxLayout(pBoxGrid, BoxLayout.X_AXIS));
		pBoxGrid.setBackground(new Color(12, 14, 18));
		pBoxGrid.add(Box.createHorizontalGlue());
		pBoxGrid.add(spBody);
		pBoxGrid.add(Box.createHorizontalGlue());

		// ------------------

		JPanel pBoxBody = new JPanel();
		pBoxBody.setLayout(new BoxLayout(pBoxBody, BoxLayout.Y_AXIS));
		pBoxBody.setBackground(new Color(12, 14, 18));
		pBoxBody.add(pFlowInfo);
		pBoxBody.add(pBoxGrid);

		return pBoxBody;
	}
}