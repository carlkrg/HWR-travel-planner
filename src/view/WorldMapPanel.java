package view;

import java.awt.Color;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import keeptoo.KGradientPanel;
import module.CountryPositionButton;

/**
 * @author Carl Krogmann
 *
 */
@SuppressWarnings("serial")
public class WorldMapPanel extends JPanel {

	private HomePanel homeP;

	private MapPanel mapPanel;
	private KGradientPanel bottomPanel;
	private JButton backJB;
	private JButton saveJB;
	private JButton switchJB;
	private ArrayList<String> countryChoicesSList;
	private int counter;

	/**
	 * @param homeP
	 * @param cpbList
	 * @param countryChoicesSList
	 */
	public WorldMapPanel(HomePanel homeP, ArrayList<CountryPositionButton> cpbList,
			ArrayList<String> countryChoicesSList) {
		super();
		this.homeP = homeP;
		this.countryChoicesSList = countryChoicesSList;

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		mapPanel = new MapPanel(cpbList);
		mapPanel.setMinimumSize(new Dimension(1080, 544));
		mapPanel.setPreferredSize(new Dimension(1080, 544));

		bottomPanel = new KGradientPanel();
		bottomPanel.kBorderRadius = 0;

		Color startColor = new Color(55, 211, 159);
		Color endColor = new Color(63, 61, 86);
		bottomPanel.setkStartColor(startColor);
		bottomPanel.setkEndColor(endColor);

		backJB = new JButton("Back");
		backJB.setPreferredSize(new Dimension(100, 70));
		backJB.setAlignmentX(Component.LEFT_ALIGNMENT);
		backJB.addActionListener(new backJBL());

		switchJB = new JButton("Switch");
		switchJB.setPreferredSize(new Dimension(100, 70));
		switchJB.setAlignmentX(Component.CENTER_ALIGNMENT);
		switchJB.addActionListener(new switchJB());

		saveJB = new JButton("Save");
		saveJB.setPreferredSize(new Dimension(100, 70));
		saveJB.setAlignmentX(Component.RIGHT_ALIGNMENT);
		saveJB.addActionListener(new saveJBL());

		counter = 0;

		add(mapPanel);
		add(bottomPanel);
		bottomPanel.add(backJB);
		bottomPanel.add(Box.createHorizontalGlue());
		bottomPanel.add(switchJB);
		bottomPanel.add(Box.createHorizontalGlue());
		bottomPanel.add(saveJB);
	}

	/**
	 * 
	 *
	 */
	public class backJBL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			homeP.removeWorldMapPanel();
			homeP.openMenuPanel();
			homeP.revalidate();
		}
	}

	/**
	 * 
	 *
	 */
	public class switchJB implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			counter = counter + 1;
			System.out.println(counter);
			mapPanel.repaint();
			mapPanel.revalidate();
		}
	}

	/**
	 *
	 *
	 */
	public class saveJBL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			homeP.removeWorldMapPanel();
			homeP.saveJourney();
			homeP.openMenuPanel();
			homeP.revalidate();
		}
	}
	
	/**
	 * @return countryChoicesSList;
	 */
	public ArrayList<String> getCountryChoicesSList() {
		return countryChoicesSList;
	}

	/**
	 * 
	 *
	 */
	public class MapPanel extends JPanel {

		private ArrayList<CountryPositionButton> myCpbList;
		private Image worldImage;
		private Image politicalImage;

		public MapPanel(ArrayList<CountryPositionButton> cpbList) {
			super();

			this.myCpbList = cpbList;

			setLayout(null);
			setMinimumSize(new Dimension(1080, 664));

			worldImage = Toolkit.getDefaultToolkit().createImage("./resources/worldMap.jpg");
			System.out.println(worldImage.toString());
			politicalImage = Toolkit.getDefaultToolkit().createImage("./resources/politicalMap.jpg");

			for (CountryPositionButton cpb : cpbList) {
				add(cpb);
			}
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			System.out.println(counter);
			int test = counter % 2;

			switch (test) {
			case 0:
				g.drawImage(worldImage, 0, 0, this);
				break;
			case 1:
				g.drawImage(politicalImage, 0, 0, this);
				break;
			}

			for (int j = 0; j < myCpbList.size() - 1; j++) {

				int x = myCpbList.get(j).getIntx() + 4;
				int y = myCpbList.get(j).getInty() + 4;
				int xNext = myCpbList.get(j + 1).getIntx() + 4;
				int yNext = myCpbList.get(j + 1).getInty() + 4;

				g.drawLine(x, y, xNext, yNext);
			}
			int x = myCpbList.get(0).getIntx() + 4;
			int y = myCpbList.get(0).getInty() + 4;
			int xNext = myCpbList.get(myCpbList.size() - 1).getIntx() + 4;
			int yNext = myCpbList.get(myCpbList.size() - 1).getInty() + 4;
			g.drawLine(x, y, xNext, yNext);
		}
	}
}
