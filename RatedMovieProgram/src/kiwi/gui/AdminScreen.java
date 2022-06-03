package kiwi.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;

import kiwi.dto.Movie;
import kiwi.dto.Review;
import kiwi.dto.User;
import kiwi.gui.process.AdminProcess;
import kiwi.mgr.MovieMgr;
import kiwi.mgr.ReviewMgr;

public class AdminScreen extends JPanel {
	private static final long serialVersionUID = 7503498065709037810L;

	private JComboBox<String> cbSelect;
	private String actions[] = { "없음", "영화 추가", "영화 삭제", "리뷰 삭제", "회원 삭제" };

	private JLabel lGuide;
	private JPanel pFlowForm;

	private File poster;

	private JButton btnAddMovie;
	private JButton btnResetMovie;

	private String selectReviewId;
	private String selectUserId;

	public AdminScreen() {
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
				switch (e.getItem().toString()) {
				case "없음":
					lGuide.setText("원하는 행동을 선택해주세요.");

					pFlowForm.setVisible(false);
					break;
				case "영화 추가":
					lGuide.setText("새 영화 추가");
					pFlowForm.removeAll();

					pFlowForm.add(getAddMovie());

					pFlowForm.setVisible(true);
					break;
				case "영화 삭제":
					lGuide.setText("삭제할 영화의 타이틀을 클릭해주세요.");
					pFlowForm.removeAll();

					pFlowForm.add(getDeleteMovie());

					pFlowForm.setVisible(true);
					break;
				case "리뷰 삭제":
					lGuide.setText("리뷰 삭제");
					pFlowForm.removeAll();

					pFlowForm.add(getDeleteReview());

					pFlowForm.setVisible(true);
					break;
				case "회원 삭제":
					lGuide.setText("회원 삭제");
					pFlowForm.removeAll();

					pFlowForm.add(getDeleteUser());

					pFlowForm.setVisible(true);
					break;
				}
			}
		});

		lGuide = new JLabel("원하는 행동을 선택해주세요.");
		lGuide.setForeground(new Color(189, 198, 208));
		lGuide.setFont(new Font("ONE 모바일고딕 Regular", Font.PLAIN, 32));
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

	public JPanel getDeleteUser() {
		JPanel pBoxUser = new JPanel();
		pBoxUser.setLayout(new BoxLayout(pBoxUser, BoxLayout.Y_AXIS));
		pBoxUser.setBackground(new Color(12, 14, 18));
		pBoxUser.setVisible(false);
				
		AdminProcess ap = new AdminProcess();
		Vector<User> vecUser = ap.findAllUser();
		if (vecUser == null) {
			return pBoxUser;
		}
		
		Vector<String> vecHeaders = new Vector<String>();
		Vector<Vector<String>> vecContents = new Vector<Vector<String>>();
		for (User user : vecUser) {
			// 어드민은 보여주지 않는다.
			if (user.getId().equals("administrator")) {
				continue;
			}
			
			Vector<String> content = new Vector<String>();
			content.add(user.getId());
			content.add(user.getNickname());
			content.add(user.getBirthDate().toString());
			content.add(user.getEmail());
			content.add(user.getTel());
			content.add(user.getJoinDate().toString());
			
			vecContents.add(content);
		}
		
		vecHeaders.add("아이디");
		vecHeaders.add("닉네임");
		vecHeaders.add("생년월일");
		vecHeaders.add("이메일");
		vecHeaders.add("연락처");
		vecHeaders.add("가입일자");
		
		JTable tblUser = new JTable(vecContents, vecHeaders) {
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		tblUser.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		tblUser.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tblUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblUser.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JTable tbl = (JTable)e.getSource();
				TableModel model = (TableModel)tbl.getModel();
				selectUserId = model.getValueAt(tbl.getSelectedRow(), 0).toString();
			}
		});
		
		
		JScrollPane spUser = new JScrollPane(tblUser);
		spUser.setBackground(new Color(12, 14, 18));
		spUser.setPreferredSize(new Dimension(800, 500));
		spUser.setMaximumSize(new Dimension(800, 500));
		spUser.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JButton btnDeleteReview = new JButton("선택한 회원 삭제하기");
		btnDeleteReview.setPreferredSize(new Dimension(410, 25));
		btnDeleteReview.setMaximumSize(new Dimension(410, 25));
		btnDeleteReview.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnDeleteReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminProcess ap = new AdminProcess();
				if (selectUserId == null || !(selectUserId.length() > 0)) {
					JOptionPane.showMessageDialog(null, "다시 선택해주세요.");
					return;
				}
				
				ap.deleteUser(selectUserId, btnDeleteReview);
			}
		});

		pBoxUser.add(Box.createVerticalStrut(10));
		pBoxUser.add(spUser);
		pBoxUser.add(btnDeleteReview);
		pBoxUser.add(Box.createVerticalGlue());
		pBoxUser.setVisible(true);
		
		return pBoxUser;
	}

	public JPanel getDeleteReview() {
		JPanel pBoxReview = new JPanel();
		pBoxReview.setLayout(new BoxLayout(pBoxReview, BoxLayout.Y_AXIS));
		pBoxReview.setBackground(new Color(12, 14, 18));
		pBoxReview.setVisible(false);

		ReviewMgr.getInstance().reloadReview();
		LinkedHashMap<Integer, Vector<Review>> mapReview = ReviewMgr.getInstance().getMapReview();
		if (mapReview == null) {
			return pBoxReview;
		}

		Vector<String> vecHeaders = new Vector<String>();
		Vector<Vector<String>> vecContents = new Vector<Vector<String>>();
				
		for (int movieId : mapReview.keySet()) {
			Vector<Review> vecReview = mapReview.get(movieId);
			for (Review review : vecReview) {
				Vector<String> content = new Vector<String>();
				content.add(String.valueOf(review.getId()));
				content.add(MovieMgr.getInstance().getTitleByMovieId(movieId));
				content.add(review.getUserId());
				content.add(review.getContent());

				vecContents.add(content);
			}
		}
		vecHeaders.add("리뷰 아이디");
		vecHeaders.add("영화 제목");
		vecHeaders.add("유저 아이디");
		vecHeaders.add("리뷰 내용");

		JTable tblUser = new JTable(vecContents, vecHeaders) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		tblUser.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		tblUser.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		tblUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblUser.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JTable tbl = (JTable) e.getSource();
				TableModel model = (TableModel) tbl.getModel();
				selectReviewId = model.getValueAt(tbl.getSelectedRow(), 0).toString();
			}
		});

		JScrollPane spUser = new JScrollPane(tblUser);
		spUser.setBackground(new Color(12, 14, 18));
		spUser.setPreferredSize(new Dimension(800, 500));
		spUser.setMaximumSize(new Dimension(800, 500));
		spUser.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JButton btnDeleteReview = new JButton("선택한 리뷰 삭제하기");
		btnDeleteReview.setPreferredSize(new Dimension(410, 25));
		btnDeleteReview.setMaximumSize(new Dimension(410, 25));
		btnDeleteReview.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnDeleteReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminProcess ap = new AdminProcess();
				if (selectReviewId == null || !(selectReviewId.length() > 0)) {
					JOptionPane.showMessageDialog(null, "다시 선택해주세요.");
					return;
				}

				ap.deleteReview(Integer.parseInt(selectReviewId), btnDeleteReview);
			}
		});

		pBoxReview.add(Box.createVerticalStrut(10));
		pBoxReview.add(spUser);
		pBoxReview.add(btnDeleteReview);
		pBoxReview.add(Box.createVerticalGlue());
		pBoxReview.setVisible(true);

		return pBoxReview;
	}

	public JPanel getDeleteMovie() {
		JPanel pFlowBody = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pFlowBody.setBackground(new Color(12, 14, 18));
		pFlowBody.setPreferredSize(new Dimension(1080, 700));
		pFlowBody.setMaximumSize(new Dimension(1080, 700));
		pFlowBody.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		Vector<String> vecMovieTitles = MovieMgr.getInstance().getVecMovieTitles();
		if (vecMovieTitles.size() == 0) {
			return pFlowBody;
		}

		for (String title : vecMovieTitles) {
			JButton btnTitle = new JButton(String.format("#%s", title));
			btnTitle.setName(title);
			btnTitle.setFont(new Font("ONE 모바일고딕 Regular", Font.PLAIN, 13));
			btnTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			btnTitle.setOpaque(true);
			btnTitle.setBackground(new Color(12, 14, 18));
			btnTitle.setForeground(new Color(71, 143, 250));
			btnTitle.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					// Brighting in focus
					btnTitle.setFont(new Font("ONE 모바일고딕 Regular", Font.BOLD, 16));
					btnTitle.setForeground(btnTitle.getForeground().brighter());
				}

				public void mouseExited(MouseEvent e) {
					// Return default color out focus
					btnTitle.setFont(new Font("ONE 모바일고딕 Regular", Font.PLAIN, 13));
					btnTitle.setForeground(new Color(71, 143, 250));
				}
			});
			btnTitle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AdminProcess ap = new AdminProcess();
					JButton btn = (JButton) e.getSource();

					int ret = JOptionPane.showConfirmDialog(null, String.format("'%s' 영화를 삭제하시겠습니까?", btn.getName()),
							"삭제 메시지", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (ret == JOptionPane.YES_OPTION) {
						ap.deleteMovie(btn.getName(), btn);
					}
				}
			});

			pFlowBody.add(btnTitle);
		}

		return pFlowBody;
	}

	public JPanel getAddMovie() {
		JLabel lTitle = new JLabel("영화 제목");
		lTitle.setForeground(new Color(189, 198, 208));
		lTitle.setFont(new Font("ONE 모바일고딕 Regular", Font.PLAIN, 15));
		lTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JLabel lDirector = new JLabel("감독");
		lDirector.setForeground(new Color(189, 198, 208));
		lDirector.setFont(new Font("ONE 모바일고딕 Regular", Font.PLAIN, 15));
		lDirector.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JLabel lActors = new JLabel("배우");
		lActors.setForeground(new Color(189, 198, 208));
		lActors.setFont(new Font("ONE 모바일고딕 Regular", Font.PLAIN, 15));
		lActors.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JLabel lGenre = new JLabel("장르");
		lGenre.setForeground(new Color(189, 198, 208));
		lGenre.setFont(new Font("ONE 모바일고딕 Regular", Font.PLAIN, 15));
		lGenre.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JLabel lReleaseDate = new JLabel("개봉일");
		lReleaseDate.setForeground(new Color(189, 198, 208));
		lReleaseDate.setFont(new Font("ONE 모바일고딕 Regular", Font.PLAIN, 15));
		lReleaseDate.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		lReleaseDate.setToolTipText("개봉일은 yyyy-mm-dd의 형식으로 작성되야 합니다.");

		JLabel lRunningTime = new JLabel("상영 시간(분)");
		lRunningTime.setForeground(new Color(189, 198, 208));
		lRunningTime.setFont(new Font("ONE 모바일고딕 Regular", Font.PLAIN, 15));
		lRunningTime.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JLabel lAgeLimit = new JLabel("연령 제한");
		lAgeLimit.setForeground(new Color(189, 198, 208));
		lAgeLimit.setFont(new Font("ONE 모바일고딕 Regular", Font.PLAIN, 15));
		lAgeLimit.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JLabel lPoster = new JLabel("포스터");
		lPoster.setForeground(new Color(189, 198, 208));
		lPoster.setFont(new Font("ONE 모바일고딕 Regular", Font.PLAIN, 15));
		lPoster.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		lPoster.setToolTipText("포스터의 크기는 16MB 이하의 .jpg, .png, .jpeg 파일 이어야 합니다.");

		JLabel lSummary = new JLabel("줄거리");
		lSummary.setPreferredSize(new Dimension(50, 100));
		lSummary.setMaximumSize(new Dimension(50, 100));
		lSummary.setForeground(new Color(189, 198, 208));
		lSummary.setFont(new Font("ONE 모바일고딕 Regular", Font.PLAIN, 15));
		lSummary.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		lSummary.setToolTipText("줄거리는 500자 이내로 작성할 수 있습니다.");

		JTextField tfTitle = new JTextField();
		tfTitle.setPreferredSize(new Dimension(280, 25));
		tfTitle.setMaximumSize(new Dimension(280, 25));
		tfTitle.setBackground(new Color(12, 14, 18));
		tfTitle.setForeground(new Color(189, 198, 208));
		tfTitle.setCaretColor(new Color(189, 198, 208));
		tfTitle.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfTitle.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField tfSrc = (JTextField) e.getSource();
				if (tfSrc.getText().length() >= 100)
					e.consume();
			}
		});

		JTextField tfDirector = new JTextField();
		tfDirector.setPreferredSize(new Dimension(280, 25));
		tfDirector.setMaximumSize(new Dimension(280, 25));
		tfDirector.setBackground(new Color(12, 14, 18));
		tfDirector.setForeground(new Color(189, 198, 208));
		tfDirector.setCaretColor(new Color(189, 198, 208));
		tfDirector.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfDirector.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField tfSrc = (JTextField) e.getSource();
				if (tfSrc.getText().length() >= 100)
					e.consume();
			}
		});

		JTextField tfActors = new JTextField();
		tfActors.setPreferredSize(new Dimension(280, 25));
		tfActors.setMaximumSize(new Dimension(280, 25));
		tfActors.setBackground(new Color(12, 14, 18));
		tfActors.setForeground(new Color(189, 198, 208));
		tfActors.setCaretColor(new Color(189, 198, 208));
		tfActors.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfActors.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField tfSrc = (JTextField) e.getSource();
				if (tfSrc.getText().length() >= 100)
					e.consume();
			}
		});

		JTextField tfGenre = new JTextField();
		tfGenre.setPreferredSize(new Dimension(280, 25));
		tfGenre.setMaximumSize(new Dimension(280, 25));
		tfGenre.setBackground(new Color(12, 14, 18));
		tfGenre.setForeground(new Color(189, 198, 208));
		tfGenre.setCaretColor(new Color(189, 198, 208));
		tfGenre.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfGenre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField tfSrc = (JTextField) e.getSource();
				if (tfSrc.getText().length() >= 100)
					e.consume();
			}
		});

		JTextField tfReleaseDate = new JTextField();
		tfReleaseDate.setPreferredSize(new Dimension(280, 25));
		tfReleaseDate.setMaximumSize(new Dimension(280, 25));
		tfReleaseDate.setBackground(new Color(12, 14, 18));
		tfReleaseDate.setForeground(new Color(189, 198, 208));
		tfReleaseDate.setCaretColor(new Color(189, 198, 208));
		tfReleaseDate.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfReleaseDate.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField tfSrc = (JTextField) e.getSource();
				if (tfSrc.getText().length() >= 10)
					e.consume();
			}
		});

		JTextField tfRunningTime = new JTextField();
		tfRunningTime.setPreferredSize(new Dimension(280, 25));
		tfRunningTime.setMaximumSize(new Dimension(280, 25));
		tfRunningTime.setBackground(new Color(12, 14, 18));
		tfRunningTime.setForeground(new Color(189, 198, 208));
		tfRunningTime.setCaretColor(new Color(189, 198, 208));
		tfRunningTime.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfRunningTime.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField tfSrc = (JTextField) e.getSource();
				if (tfSrc.getText().length() >= 3)
					e.consume();
			}
		});

		JComboBox<Integer> cbAge = new JComboBox<Integer>(new Integer[] { 0, 12, 15, 19 });
		cbAge.setPreferredSize(new Dimension(280, 25));
		cbAge.setMaximumSize(new Dimension(280, 25));

		JFileChooser fcPoster = new JFileChooser();
		fcPoster.setFileFilter(new FileNameExtensionFilter("png", "jpeg", "jpg"));
		fcPoster.setMultiSelectionEnabled(false);

		JLabel lPosterPath = new JLabel();
		lPosterPath.setBackground(new Color(12, 14, 18));
		lPosterPath.setOpaque(true);
		lPosterPath.setForeground(new Color(189, 198, 208));
		lPosterPath.setPreferredSize(new Dimension(200, 25));
		lPosterPath.setMaximumSize(new Dimension(200, 25));
		lPosterPath.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));

		JButton btnPoster = new JButton("열기");
		btnPoster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fcPoster.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					poster = fcPoster.getSelectedFile();
					lPosterPath.setText(poster.getPath());
				}
			}
		});

		JPanel pFlowPoster = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pFlowPoster.setBackground(new Color(18, 21, 26));
		pFlowPoster.add(lPosterPath);
		pFlowPoster.add(btnPoster);

		JTextArea taSummary = new JTextArea();
		taSummary.setLineWrap(true);

		JScrollPane spSummary = new JScrollPane(taSummary);
		spSummary.setPreferredSize(new Dimension(280, 100));
		spSummary.setMaximumSize(new Dimension(280, 100));

		btnAddMovie = new JButton("영화 추가");
		btnAddMovie.setPreferredSize(new Dimension(180, 40));
		btnAddMovie.setMaximumSize(new Dimension(180, 40));
		btnAddMovie.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnAddMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminProcess ap = new AdminProcess();
				boolean isGood = ap.checkValidationMovie(poster,
						new String[] { tfTitle.getText(), tfDirector.getText(), tfActors.getText(), tfGenre.getText(),
								tfReleaseDate.getText(), tfRunningTime.getText(), taSummary.getText() });

				if (isGood) {
					Date releaseDate = Date.valueOf(tfReleaseDate.getText());
					BufferedImage posterImg = null;
					try {
						posterImg = ImageIO.read(poster);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					Movie newMovie = new Movie(0, tfTitle.getText(), tfDirector.getText(), tfActors.getText(),
							tfGenre.getText(), 0, releaseDate, Integer.parseInt(tfRunningTime.getText()),
							(int) cbAge.getSelectedItem(), taSummary.getText(), posterImg);

					ap.addMovie(newMovie, btnAddMovie);
				}
			}
		});

		btnResetMovie = new JButton("다시 쓰기");
		btnResetMovie.setPreferredSize(new Dimension(180, 40));
		btnResetMovie.setMaximumSize(new Dimension(180, 40));
		btnResetMovie.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnResetMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfTitle.setText("");
				tfDirector.setText("");
				tfActors.setText("");
				tfGenre.setText("");
				tfReleaseDate.setText("");
				tfRunningTime.setText("");
				cbAge.setSelectedItem(0);
				lPosterPath.setText("");
				taSummary.setText("");
				poster = null;
			}
		});

		JPanel pBoxLabel = new JPanel();
		pBoxLabel.setLayout(new BoxLayout(pBoxLabel, BoxLayout.Y_AXIS));
		pBoxLabel.setBackground(new Color(12, 14, 18));
		pBoxLabel.add(Box.createVerticalStrut(15));
		pBoxLabel.add(lTitle);
		pBoxLabel.add(Box.createVerticalStrut(18));
		pBoxLabel.add(lDirector);
		pBoxLabel.add(Box.createVerticalStrut(17));
		pBoxLabel.add(lActors);
		pBoxLabel.add(Box.createVerticalStrut(17));
		pBoxLabel.add(lGenre);
		pBoxLabel.add(Box.createVerticalStrut(17));
		pBoxLabel.add(lReleaseDate);
		pBoxLabel.add(Box.createVerticalStrut(17));
		pBoxLabel.add(lRunningTime);
		pBoxLabel.add(Box.createVerticalStrut(17));
		pBoxLabel.add(lAgeLimit);
		pBoxLabel.add(Box.createVerticalStrut(30));
		pBoxLabel.add(lPoster);
		pBoxLabel.add(Box.createVerticalStrut(17));
		pBoxLabel.add(lSummary);
		pBoxLabel.add(Box.createVerticalStrut(15));
		pBoxLabel.add(btnResetMovie);

		JPanel pBoxField = new JPanel();
		pBoxField.setLayout(new BoxLayout(pBoxField, BoxLayout.Y_AXIS));
		pBoxField.setBackground(new Color(12, 14, 18));
		pBoxField.add(Box.createVerticalStrut(10));
		pBoxField.add(tfTitle);
		pBoxField.add(Box.createVerticalStrut(10));
		pBoxField.add(tfDirector);
		pBoxField.add(Box.createVerticalStrut(10));
		pBoxField.add(tfActors);
		pBoxField.add(Box.createVerticalStrut(10));
		pBoxField.add(tfGenre);
		pBoxField.add(Box.createVerticalStrut(10));
		pBoxField.add(tfReleaseDate);
		pBoxField.add(Box.createVerticalStrut(10));
		pBoxField.add(tfRunningTime);
		pBoxField.add(Box.createVerticalStrut(10));
		pBoxField.add(cbAge);
		pBoxField.add(Box.createVerticalStrut(10));
		pBoxField.add(pFlowPoster);
		pBoxField.add(Box.createVerticalStrut(10));
		pBoxField.add(spSummary);
		pBoxField.add(Box.createVerticalStrut(20));
		pBoxField.add(btnAddMovie);

		JPanel pFlowBody = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pFlowBody.setBackground(new Color(12, 14, 18));
		pFlowBody.add(pBoxLabel);
		pFlowBody.add(pBoxField);

		return pFlowBody;
	}
}