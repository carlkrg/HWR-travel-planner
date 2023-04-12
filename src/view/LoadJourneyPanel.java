package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import dao.MyDAO;
import keeptoo.KGradientPanel;

/**
 * diese Klasse erstellt das LoadJourneyPanel, welches zum auswaehlen und laden
 * bereits erstellter Reisen dient.
 */
@SuppressWarnings("serial")
public class LoadJourneyPanel extends JPanel {

	private HomePanel homeP;

	private JComboBox<String> savedJourneysJCB;
	private ArrayList<String> countryChoicesSList;

	private KGradientPanel rightPanel;
	private JPanel leftPanel;

	private JButton goJB;
	private JButton deleteJB;
	private JButton backJB;

	/**
	 * der Konstruktor setzt das entsprechende Layout in Form vom linken
	 * ("leftPanel") und rechten ("rightPanel"). auf diesen wird das Logo/Combobox,
	 * bzw. die entsprechenden JButtons ("goJB", "deleteJB", "backJB") plaziert.
	 * 
	 * @param homeP            uebergibt ein HomePanel Objekt welches als Controler
	 *                         dient
	 * @param journeyNamesList uebergibt ein Array aus Strings mit den Namen der
	 *                         bisher gespeicherten Reisen
	 */
	public LoadJourneyPanel(HomePanel homeP, String[] journeyNamesList) {
		super();
		this.homeP = homeP;

		setLayout(new GridLayout(0, 2));
		setSize(1080, 644);
		setLocation(0, 0);

		leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

		rightPanel = new KGradientPanel();
		rightPanel.kBorderRadius = 0;
		Color startColor = new Color(55, 211, 195);
		Color endColor = new Color(63, 61, 86);
		rightPanel.setkStartColor(startColor);
		rightPanel.setkEndColor(endColor);

		savedJourneysJCB = new JComboBox<String>(journeyNamesList);
		savedJourneysJCB.setMaximumSize(new Dimension(300, 50));

		goJB = new JButton("GO");
		goJB.setAlignmentX(Component.CENTER_ALIGNMENT);
		goJB.setPreferredSize(new Dimension(300, 100));
		goJB.addActionListener(new goJBL());

		deleteJB = new JButton("Delete");
		deleteJB.setAlignmentX(Component.CENTER_ALIGNMENT);
		deleteJB.setPreferredSize(new Dimension(300, 100));
		deleteJB.addActionListener(new deleteJBL());

		backJB = new JButton("Back");
		backJB.setAlignmentX(Component.CENTER_ALIGNMENT);
		backJB.setPreferredSize(new Dimension(300, 100));
		backJB.addActionListener(new backJBL());

		add(leftPanel);
		add(rightPanel);

		leftPanel.add(Box.createHorizontalGlue());
		leftPanel.add(Box.createRigidArea(new Dimension(0, 255)));
		leftPanel.add(savedJourneysJCB);
		leftPanel.add(Box.createHorizontalGlue());

		rightPanel.add(Box.createRigidArea(new Dimension(0, 250)));
		rightPanel.add(goJB);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		rightPanel.add(deleteJB);
		rightPanel.add(backJB);
	}

	/**
	 * der goJBL ActionListner greift ueber die MyDAO auf die Oracle Datenbank zu und
	 * uebergibt der readJourneyDestinations Methode einen String in Form des Namens
	 * der Reise, welche der User in der Combobox zuvor angewaehlt hat. Diese Methode
	 * gibt eine ArrayList aus Strings zurueck, welche die Reisezeile der Reise
	 * enthaelt, mit der im Anschluss die openWorldMapPanel Methode aufgerufen wird.
	 */
	public class goJBL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			countryChoicesSList = new ArrayList<>();
			try {
				MyDAO myDao = MyDAO.getInstance();
				countryChoicesSList = myDao.readJourneyDestinations((String) savedJourneysJCB.getSelectedItem());
			}

			catch (SQLException i) {
				i.printStackTrace();
			}

			homeP.removeLoadJourneyPanel();
			homeP.openWorldMapPanel(countryChoicesSList);
			homeP.revalidate();
		}
	}

	/**
	 * ueber den deleteJBL ActionListner wrid die angewaehlte Reise aus der Datenbank
	 * geloescht. Dass erfolgt ueber den Aufruf der deleteJourney Methode in der MyDAO
	 * Klasse, welche anhand des ausgewaehlten Reisenamens, in Form eines Strings die
	 * entsprechende Reise durch einen SQL-Command entfernt
	 */
	public class deleteJBL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			homeP.deleteJourney((String) savedJourneysJCB.getSelectedItem());
		}
	}

	/**
	 * der backJBL ActionListner entfernt dass LoadJourneyPanel vom HomePanel und
	 * oeffnet dass MenuePanel von wo aus der User erneut Navigieren kann.
	 */
	public class backJBL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			homeP.removeLoadJourneyPanel();
			homeP.openMenuPanel();
			homeP.revalidate();
		}
	}
}
