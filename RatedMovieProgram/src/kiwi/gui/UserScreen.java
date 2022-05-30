package kiwi.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import kiwi.dto.Bookmark;
import kiwi.dto.Movie;
import kiwi.dto.User;
import kiwi.gui.process.BookmarkProcess;
import kiwi.header.Define.SCREEN_TYPE;
import kiwi.mgr.MovieMgr;
import kiwi.mgr.ResourceMgr;
import kiwi.mgr.ScreenMgr;
import kiwi.mgr.UserMgr;

public class UserScreen extends JPanel {
	private static final long serialVersionUID = 6670267929674394820L;
	
	private JComboBox<String> cbSelect;
	private String actions[] = { "없음", "내 정보", "내가 추가한 북마크", "내가 작성한 리뷰" };
	
	private JPanel pFlowForm;
	private JLabel lGuide;
	
	public UserScreen() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(12, 14, 18));
		this.setBorder(BorderFactory.createLineBorder(new Color(189, 198, 208), 1));
		
		cbSelect = new JComboBox<String>(actions);
		cbSelect.setBackground(new Color(189, 198, 208));
		cbSelect.setOpaque(true);
		cbSelect.setPreferredSize(new Dimension(1280, 35));
		cbSelect.setMaximumSize(new Dimension(1280, 35));
		cbSelect.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		cbSelect.setAlignmentY(JComponent.TOP_ALIGNMENT);
		cbSelect.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				pFlowForm.setVisible(false);
				
				switch (e.getItem().toString()) {
				case "없음":
					lGuide.setText("원하는 행동을 선택해주세요.");
					pFlowForm.removeAll();
					
					pFlowForm.setVisible(false);
					break;
				case "내 정보":
					lGuide.setText("내 정보");
					pFlowForm.removeAll();
					
					User user = UserMgr.getInstance().getCurUser();
					pFlowForm.add(getUserInfoBody(user));
					
					pFlowForm.setVisible(true);
					break;
				case "내가 추가한 북마크":
					lGuide.setText("내 북마크");
					pFlowForm.removeAll();
					
					pFlowForm.add(getBookmarkBody());
					
					pFlowForm.setVisible(true);
					break;
				case "내가 작성한 리뷰":
					lGuide.setText("내가 작성한 리뷰");
					pFlowForm.removeAll();
					
					pFlowForm.add(getReviewBody());
					
					pFlowForm.setVisible(true);
					break;
				}
			}
		});
		
		lGuide = new JLabel("원하는 행동을 선택해주세요.");
		lGuide.setForeground(new Color(189, 198, 208));
		lGuide.setFont(new Font("Arial", Font.PLAIN, 32));
		lGuide.setHorizontalAlignment(SwingConstants.CENTER);
		lGuide.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		// Merge Boxes
		pFlowForm = new JPanel();
		pFlowForm.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
		pFlowForm.setBackground(new Color(12, 14, 18));
		pFlowForm.setVisible(false);
				
		this.add(cbSelect);
		this.add(Box.createVerticalStrut(10));
		this.add(Box.createVerticalGlue());
		this.add(lGuide);
		this.add(Box.createVerticalStrut(10));
		this.add(pFlowForm);
		this.add(Box.createVerticalGlue());
	}
	
	public JPanel getUserInfoBody(User user) {		
		String contents[][] = {
				{ "아이디", user.getId() },
				{ "닉네임", user.getNickname() },
				{ "생년월일", user.getBirthDate().toString() },
				{ "이메일", user.getEmail() },
				{ "전화번호", user.getTel() }
		};
		
		JTable tblUser = new JTable(contents, new String[] { "분류", "정보" }) {
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
				if (columnIndex == 1) {
					setFont(new Font("Arial", Font.PLAIN, 13));
				} else {
					setFont(new Font("Arial", Font.BOLD, 13));
				}
				
				return super.prepareRenderer(renderer, rowIndex, columnIndex);
			}
		};
		tblUser.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		tblUser.setShowGrid(false);
		tblUser.setEnabled(false);
		tblUser.setTableHeader(null);
		tblUser.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JScrollPane spUser = new JScrollPane(tblUser);
		spUser.setBackground(new Color(12, 14, 18));
		spUser.setPreferredSize(new Dimension(400, 85));
		spUser.setMaximumSize(new Dimension(400, 85));
		spUser.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JButton btnChangePassword = new JButton("비밀번호 변경하기");
		btnChangePassword.setPreferredSize(new Dimension(410, 25));
		btnChangePassword.setMaximumSize(new Dimension(410, 25));
		btnChangePassword.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RenewalPasswordFrame();
			}
		});
		
		JButton btnChangeUserInfo = new JButton("유저 정보 변경하기");
		btnChangeUserInfo.setPreferredSize(new Dimension(410, 25));
		btnChangeUserInfo.setMaximumSize(new Dimension(410, 25));
		btnChangeUserInfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnChangeUserInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RenewalUserInfoFrame();
			}
		});
		
		JPanel pBoxUser = new JPanel();
		pBoxUser.setLayout(new BoxLayout(pBoxUser, BoxLayout.Y_AXIS));
		pBoxUser.setBackground(new Color(12, 14, 18));
		pBoxUser.add(Box.createVerticalStrut(10));
		pBoxUser.add(spUser);
		pBoxUser.add(btnChangePassword);
		pBoxUser.add(btnChangeUserInfo);
		pBoxUser.add(Box.createVerticalGlue());
		
		return pBoxUser;
	}
	
	public JPanel getBookmarkBody() {
		
		JPanel pBoxBookmark = new JPanel();
		pBoxBookmark.setLayout(new BoxLayout(pBoxBookmark, BoxLayout.Y_AXIS));
		pBoxBookmark.setBackground(new Color(12, 14, 18));

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
				lPoster.setBorder(BorderFactory.createEmptyBorder());
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
				lTitle.setFont(new Font("Arial", Font.BOLD, 15));
				lTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);
				
				JLabel lGrade = new JLabel(String.format("신선도 %d%%", (movie.getRate())));
				lGrade.setForeground(new Color(189, 198, 208));
				lGrade.setFont(new Font("Arial", Font.BOLD, 15));
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
		
		pBoxBookmark.add(Box.createVerticalStrut(40));
		pBoxBookmark.add(pBoxPane);
		
		return pBoxBookmark;
	}
	
	public JPanel getReviewBody() {
		
		
		return null;
	}
}