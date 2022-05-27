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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import kiwi.dto.Movie;
import kiwi.gui.process.AdminProcess;

public class AdminScreen extends JPanel {
	private static final long serialVersionUID = 7503498065709037810L;
	
	private JComboBox<String> cbSelect;
	private String actions[] = { "없음", "영화 추가" };

	private JLabel lGuide;
	private JPanel pBoxLabel;
	private JPanel pBoxField;
	private JPanel pFlowForm;
	
	private File poster;
	
	private JButton btnAddMovie;
	private JButton btnResetMovie;
	
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
					
					pFlowForm.add(getAddMovieLabel());
					pFlowForm.add(getAddMovieField());
					pFlowForm.add(btnAddMovie);
					pFlowForm.add(btnResetMovie);
					
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
		pFlowForm.setPreferredSize(new Dimension(600, 465));
		pFlowForm.setMaximumSize(new Dimension(600, 465));
		pFlowForm.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		pFlowForm.setBackground(new Color(18, 21, 26));
		pFlowForm.setVisible(false);
				
		this.add(cbSelect);
		this.add(Box.createVerticalStrut(10));
		this.add(Box.createVerticalGlue());
		this.add(lGuide);
		this.add(Box.createVerticalStrut(10));
		this.add(pFlowForm);
		this.add(Box.createVerticalGlue());
	}
	
	public JPanel getAddMovieField() {
		JTextField tfTitle = new JTextField();
		tfTitle.setPreferredSize(new Dimension(280, 25));
		tfTitle.setMaximumSize(new Dimension(280, 25));
		tfTitle.setBackground(new Color(12, 14, 18));
		tfTitle.setForeground(new Color(189, 198, 208));
		tfTitle.setCaretColor(new Color(189, 198, 208));
		tfTitle.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		tfTitle.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField tfSrc = (JTextField)e.getSource();
				if (tfSrc.getText().length() >= 100) e.consume();
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
				JTextField tfSrc = (JTextField)e.getSource();
				if (tfSrc.getText().length() >= 100) e.consume();
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
				JTextField tfSrc = (JTextField)e.getSource();
				if (tfSrc.getText().length() >= 100) e.consume();
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
				JTextField tfSrc = (JTextField)e.getSource();
				if (tfSrc.getText().length() >= 100) e.consume();
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
				JTextField tfSrc = (JTextField)e.getSource();
				if (tfSrc.getText().length() >= 10) e.consume();
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
				JTextField tfSrc = (JTextField)e.getSource();
				if (tfSrc.getText().length() >= 3) e.consume();
			}
		});
		
		JComboBox<Integer> cbAge = new JComboBox<Integer>(new Integer[] { 0, 12, 15, 19 });
		cbAge.setPreferredSize(new Dimension(280, 25));
		cbAge.setMaximumSize(new Dimension(280, 25));

		JFileChooser fcPoster = new JFileChooser();
		fcPoster.setFileFilter(new FileNameExtensionFilter("png", "jpeg", "jpg"));
		fcPoster.setMultiSelectionEnabled(false);

		JLabel lPoster = new JLabel();
		lPoster.setBackground(new Color(12, 14, 18));
		lPoster.setOpaque(true);
		lPoster.setForeground(new Color(189, 198, 208));
		lPoster.setPreferredSize(new Dimension(200, 25));
		lPoster.setMaximumSize(new Dimension(200, 25));
		lPoster.setBorder(BorderFactory.createLineBorder(new Color(26, 30, 35), 3));
		
		JButton btnPoster = new JButton("열기");
		btnPoster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fcPoster.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					poster = fcPoster.getSelectedFile();
					lPoster.setText(poster.getPath());
				}
			}
		});
		
		JPanel pFlowPoster = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pFlowPoster.setBackground(new Color(18, 21, 26));
		pFlowPoster.add(lPoster);
		pFlowPoster.add(btnPoster);
		
		JTextArea taSummary = new JTextArea();
		taSummary.setPreferredSize(new Dimension(280, 100));
		taSummary.setMaximumSize(new Dimension(280, 100));
		taSummary.setLineWrap(true);
		
		pBoxField = new JPanel();
		pBoxField.setLayout(new BoxLayout(pBoxField, BoxLayout.Y_AXIS));
		pBoxField.setBackground(new Color(18, 21, 26));
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
		pBoxField.add(taSummary);
		pBoxField.add(Box.createVerticalStrut(10));
		
		btnAddMovie = new JButton("영화 추가");
		btnAddMovie.setPreferredSize(new Dimension(180, 40));
		btnAddMovie.setMaximumSize(new Dimension(180, 40));
		btnAddMovie.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		btnAddMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminProcess ap = new AdminProcess();
				boolean isGood = ap.checkValidationMovie(poster,
						new String[] { 
							tfTitle.getText(),
							tfDirector.getText(),
							tfActors.getText(),
							tfGenre.getText(),
							tfReleaseDate.getText(),
							tfRunningTime.getText(),
							taSummary.getText()
						});
				
				if (isGood) {
					Date releaseDate = Date.valueOf(tfReleaseDate.getText());
					BufferedImage posterImg = null;
					try {
						posterImg = ImageIO.read(poster);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					Movie newMovie = new Movie(
							0
							, tfTitle.getText()
							, tfDirector.getText()
							, tfActors.getText()
							, tfGenre.getText()
							, 0
							, releaseDate
							, Integer.parseInt(tfRunningTime.getText())
							, (int)cbAge.getSelectedItem()
							, taSummary.getText()
							, posterImg
							);
					
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
				lPoster.setText("");
				taSummary.setText("");
				poster = null;
			}
		});
		
		return pBoxField;
	}
	
	public JPanel getAddMovieLabel() {
		JLabel lTitle = new JLabel("영화 제목");
		lTitle.setForeground(new Color(189, 198, 208));
		lTitle.setFont(new Font("Arial", Font.PLAIN, 15));
		lTitle.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JLabel lDirector = new JLabel("감독");
		lDirector.setForeground(new Color(189, 198, 208));
		lDirector.setFont(new Font("Arial", Font.PLAIN, 15));
		lDirector.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JLabel lActors = new JLabel("배우");
		lActors.setForeground(new Color(189, 198, 208));
		lActors.setFont(new Font("Arial", Font.PLAIN, 15));
		lActors.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JLabel lGenre = new JLabel("장르");
		lGenre.setForeground(new Color(189, 198, 208));
		lGenre.setFont(new Font("Arial", Font.PLAIN, 15));
		lGenre.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JLabel lReleaseDate = new JLabel("개봉일");
		lReleaseDate.setForeground(new Color(189, 198, 208));
		lReleaseDate.setFont(new Font("Arial", Font.PLAIN, 15));
		lReleaseDate.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JLabel lRunningTime = new JLabel("상영 시간(분)");
		lRunningTime.setForeground(new Color(189, 198, 208));
		lRunningTime.setFont(new Font("Arial", Font.PLAIN, 15));
		lRunningTime.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JLabel lAgeLimit = new JLabel("연령 제한");
		lAgeLimit.setForeground(new Color(189, 198, 208));
		lAgeLimit.setFont(new Font("Arial", Font.PLAIN, 15));
		lAgeLimit.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JLabel lPoster = new JLabel("포스터");
		lPoster.setForeground(new Color(189, 198, 208));
		lPoster.setFont(new Font("Arial", Font.PLAIN, 15));
		lPoster.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		JLabel lSummary = new JLabel("줄거리");
		lSummary.setPreferredSize(new Dimension(40, 100));
		lSummary.setMaximumSize(new Dimension(40, 100));
		lSummary.setForeground(new Color(189, 198, 208));
		lSummary.setFont(new Font("Arial", Font.PLAIN, 15));
		lSummary.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		pBoxLabel = new JPanel();
		pBoxLabel.setLayout(new BoxLayout(pBoxLabel, BoxLayout.Y_AXIS));
		pBoxLabel.setBackground(new Color(18, 21, 26));
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
		
		return pBoxLabel;
	}
}