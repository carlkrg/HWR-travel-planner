package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.MyDAO;
import module.CountryJson;
import module.CountryPositionButton;

/**
 * Die HomePanel Klasse ist ein Controler, welcher fuer das Ein- und Ausblenden
 * der ViewPanels zustaendig ist.
 * 
 * @see Klassendiagramm
 * @author Carl Krogmann
 */
@SuppressWarnings("serial")
public class HomePanel extends JPanel {

	private JPanel menuP, newJP, loadJP, worldMP;

	private MyDAO myDao;
	private Image loader;

	private ArrayList<String> journeyNamesList;
	private String[] journeyNamesArray;

	private ArrayList<CountryJson> countryJsonList;
	private ArrayList<String> countryChoicesSList;

	/**
	 * Initialisiert eine neue Instanz dieser Klasse.
	 */
	public HomePanel() {
		super();

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(Color.WHITE);

		menuP = new MenuPanel(this);
		loader = Toolkit.getDefaultToolkit().createImage("./resources/loader.gif");

		add(menuP);

		try {
			myDao = MyDAO.getInstance();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialisiert ueber den Konstruktor der MenuPanel Klasse eine neue Instanz
	 * der MenuPanel Klasse und fuegt diese dem HomePanel hinzu.
	 */
	public void openMenuPanel() {
		this.add(menuP = new MenuPanel(this));
	}

	/**
	 * Entfernt das ManuePanel von dem HomePanel um das ManuePanel fuer den Nutzer
	 * auszublenden.
	 */
	public void removeMenuPanel() {
		this.remove(menuP);
		this.revalidate();
		this.repaint();
	}

	/**
	 * Initialisiert ueber den Konstruktor der NewJourneyPanel Klasse eine neue
	 * Instanz der NewJourneyPanel Klasse und fuegt diese dem HomePanel hinzu.
	 */
	public void openNewJourneyPanel() {
		this.add(newJP = new NewJourneyPanel(this));
	}

	/**
	 * Entfernt das NewJourneyPanel von dem HomePanel um das NewJourneyPanel fuer
	 * den Nutzer auszublenden.
	 */
	public void removeNewJourneyPanel() {
		this.remove(newJP);
		this.revalidate();
		this.repaint();
	}

	/**
	 * Initialisiert ueber den Konstruktor der LoadJourneyPanel Klasse eine neue
	 * Instanz der LoadJourneyPanel Klasse und fuegt diese dem HomePanel hinzu.
	 * 
	 * @see LoadJourneyPanel.java Der Konstruktor der LoadJourneyPanel Klasse
	 *      benoetigt ein Array aus Strings, welches ueber das myDao Objekt von der
	 *      Oracle Datenbank abgefragt wird.
	 */
	public void openLoadJourneyPanel() {
		journeyNamesList = myDao.readJourneyNames();
		journeyNamesArray = new String[journeyNamesList.size()];

		for (int i = 0; i < journeyNamesList.size(); i++) {
			journeyNamesArray[i] = journeyNamesList.get(i);
		}

		this.add(loadJP = new LoadJourneyPanel(this, journeyNamesArray));
	}

	/**
	 * Entfernt das NewJourneyPanel von dem HomePanel um das NewJourneyPanel fuer
	 * den Nutzer auszublenden.
	 */
	public void removeLoadJourneyPanel() {
		this.remove(loadJP);
		this.revalidate();
		this.repaint();
	}

	/**
	 * Initialisiert ueber den Konstruktor der WorldMapPanel Klasse eine neue
	 * Instanz der WorldMapPanel Klasse und fuegt diese dem HomePanel hinzu.
	 * 
	 * @param countryChoicesSList Die Methode erwartet eine ArrayList aus Strings,
	 *                            da nur dann ein Objekt der Klasse WorldMapPanel
	 *                            instanziiert werden kann, wenn eine ArrayList aus
	 *                            CountryPositionButtons an den Konstruktor
	 *                            WorldMapPanel() uebergeben wird. Diese
	 *                            CountryPositionButton werden basierend auf den
	 *                            Strings in countryChoicesSList erstellt.
	 * @see WorldMapPanel.java
	 */
	public void openWorldMapPanel(ArrayList<String> countryChoicesSList) {
		countryJsonList = new ArrayList<>();

		for (int i = 0; i < countryChoicesSList.size(); i++) {
			countryJsonList.add(new CountryJson(countryChoicesSList.get(i)));
		}

		if (countryJsonList.get(0).getNativeName() != null) {

			ArrayList<CountryPositionButton> cpbList = new ArrayList<>();
			for (int i = 0; i < countryJsonList.size(); i++) {
				double lat = countryJsonList.get(i).getlat();
				double lng = countryJsonList.get(i).getlng();
				CountryPositionButton cpb = new CountryPositionButton(lat, lng, countryJsonList.get(i));
				cpbList.add(cpb);
			}

			this.add(worldMP = new WorldMapPanel(this, cpbList, countryChoicesSList));
		}

		else {
			this.openMenuPanel();
			this.revalidate();
			JOptionPane.showMessageDialog(null, "An API Error occurred, please try again later!", "API Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * entfernt das WorldMapPanel aus dem HomePanel um es fuer den Nutzer
	 * auszublenden
	 */
	public void removeWorldMapPanel() {
		this.remove(worldMP);
		this.revalidate();
		this.repaint();
	}

	/**
	 * speichert die aktuelle Reise unter dem vom Nutzer ausgewaehlten Namen in die
	 * Datenbank
	 */
	public void saveJourney() {
		countryChoicesSList = ((WorldMapPanel) worldMP).getCountryChoicesSList();
		String saveName = null;

		do {
			saveName = JOptionPane.showInputDialog(worldMP, "Please Insert Name (Max. lenght 20)");
			if (saveName == null) {
				break;
			}
		} while (saveName.length() > 20);

		if (saveName != null) {
			myDao.save(countryChoicesSList, saveName);
		}
	}

	/**
	 * loescht eine ausgewaehlte Reise aus der Datenbank
	 * 
	 * @param name uebergibt den Namen der ausgewaehlten Reise welche geloescht
	 *             werden soll
	 */
	public void deleteJourney(String name) {
		myDao.delete(name);
		this.removeLoadJourneyPanel();
		this.openMenuPanel();
	}

	public static boolean netIsAvailable() {
		try {
			final URL url = new URL("http://www.google.com");
			final URLConnection conn = url.openConnection();
			conn.connect();
			conn.getInputStream().close();
			return true;
		}

		catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}

		catch (IOException e) {
			return false;
		}
	}

	/**
	 * ueberschreibt das Graphics Objekt mit dem bild mit dem Loading Screen
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (loader != null) {
			g.drawImage(loader, 140, 0, this);
		}
	}
}
