package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import keeptoo.*;

/**
 * Die Klasse MenuPanel dient der Navigation des Users zwischen NewJourneyPanel und LoadJourneyPanel 
 * @author Timothy Kura
 */
@SuppressWarnings("serial")
public class MenuPanel extends JPanel {

	private HomePanel homeP;
	private JPanel leftPanel;
	private KGradientPanel rightPanel;

	private Color startColor;
	private Color endColor;

	private JButton newJourneyButton;
	private JButton loadJourneyButton;

	private JLabel Jlogo;
	private JLabel Jtrasap;
	private JLabel Jcreated;
	private JLabel Jcarl;
	private JLabel Jjakob;
	private JLabel Jjan;
	private JLabel Jtimothy;
	
	/**
	 * Initialisiert eine neue Instanz dieser Klasse
	 * @param panel 
	 */
	public MenuPanel(HomePanel panel) {
		super();
		homeP = panel;

		setLayout(new GridLayout(0, 2));

		leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

		rightPanel = new KGradientPanel();
		rightPanel.kBorderRadius = 0;
		startColor = new Color(55, 211, 195);
		endColor = new Color(63, 61, 86);
		rightPanel.setkStartColor(startColor);
		rightPanel.setkEndColor(endColor);

		newJourneyButton = new JButton("New Journey");
		newJourneyButton.setAlignmentX(CENTER_ALIGNMENT);
		newJourneyButton.setPreferredSize(new Dimension(300, 100));
		newJourneyButton.addActionListener(new MenuToNewJourneyListener());

		loadJourneyButton = new JButton("Load Journey");
		loadJourneyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		loadJourneyButton.setPreferredSize(new Dimension(300, 100));
		loadJourneyButton.addActionListener(new MenuToLoadJourneyListener());

		Jlogo = new JLabel();
		Jlogo.setIcon(new ImageIcon("./resources/logo-1.png"));
		Jlogo.setAlignmentX(CENTER_ALIGNMENT);

		Jtrasap = new JLabel("TRASAP");
		Jtrasap.setFont(new Font("Verdana", 1, 20));
		Jtrasap.setAlignmentX(CENTER_ALIGNMENT);

		Jcreated = new JLabel("created by:");
		Jcreated.setFont(new Font("Verdana", 0, 20));
		Jcreated.setAlignmentX(CENTER_ALIGNMENT);

		Jcarl = new JLabel("Carl Krogmann");
		Jcarl.setFont(new Font("Verdana", 0, 20));
		Jcarl.setAlignmentX(CENTER_ALIGNMENT);

		Jjakob = new JLabel("Jakob Simonsmeier");
		Jjakob.setFont(new Font("Verdana", 0, 20));
		Jjakob.setAlignmentX(CENTER_ALIGNMENT);

		Jjan = new JLabel("Jan Kanz");
		Jjan.setFont(new Font("Verdana", 0, 20));
		Jjan.setAlignmentX(CENTER_ALIGNMENT);

		Jtimothy = new JLabel("Timothy Kura");
		Jtimothy.setFont(new Font("Verdana", 0, 20));
		Jtimothy.setAlignmentX(CENTER_ALIGNMENT);

		add(leftPanel);
		add(rightPanel);

		rightPanel.add(Box.createRigidArea(new Dimension(0, 360)));
		rightPanel.add(newJourneyButton);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		rightPanel.add(loadJourneyButton);

		leftPanel.add(Box.createRigidArea(new Dimension(0, 90)));
		leftPanel.add(Jlogo);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		leftPanel.add(Jtrasap);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		leftPanel.add(Jcreated);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		leftPanel.add(Jcarl);
		leftPanel.add(Jjakob);
		leftPanel.add(Jjan);
		leftPanel.add(Jtimothy);

	}

	/**
	 * Actionlistener des newJourneyButton ruft folgende Methoden auf
	 * @see HomePanel.netIsAvailable()
	 * @see HomePanel.removeMenuPanel();
	 * @see HomePanel.openNewJourneyPanel();
	 * 
	 * um von dem ManuPanel zu dem NewJourneyPanel zu navigieren.
	 */
	public class MenuToNewJourneyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (HomePanel.netIsAvailable() == true) {

				homeP.removeMenuPanel();
				homeP.openNewJourneyPanel();
				homeP.revalidate();
			} 
			
			else {
				JOptionPane.showMessageDialog(homeP, "This program requires a functioning network connection!",
						"Network Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Actionlistener des loadJourneyButton ruft folgende Methoden auf
	 * @see HomePanel.netIsAvailable()
	 * @see HomePanel.removeMenuPanel();
	 * @see HomePanel.openLoadJourneyPanel();
	 * 
	 * um von dem ManuPanel zu dem LoadJourneyPanel zu navigieren.
	 */
	public class MenuToLoadJourneyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (HomePanel.netIsAvailable() == true) {

				homeP.removeMenuPanel();
				homeP.openLoadJourneyPanel();
				homeP.revalidate();
			} 
			
			else {
				JOptionPane.showMessageDialog(homeP, "This program requires a functioning network connection!",
						"Network Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
