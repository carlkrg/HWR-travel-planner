package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import keeptoo.KGradientPanel;

/**
 * diese Klasse erstellt stellt das NewJourney Panel dar, auf welchem der User
 * die Moeglichkeit hat eine Anzahl an Reisezielen, sowie die gewuenschten
 * Destinationen anzuwaehlen
 * 
 * @author Jan Kanz
 *
 */
@SuppressWarnings("serial")
public class NewJourneyPanel extends JPanel {

	private HomePanel homeP;

	private final String[] CHOICES_S = { "Choose Country", "Afghanistan", "Åland Islands", "Albania", "Algeria",
			"American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina",
			"Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados",
			"Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia (Plurinational State of)",
			"Bonaire, Sint Eustatius and Saba", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory",
			"Virgin Islands (British)", "Virgin Islands (U.S.)", "Brunei Darussalam", "Bulgaria", "Burkina Faso",
			"Burundi", "Cambodia", "Cameroon", "Canada", "Cabo Verde", "Cayman Islands", "Central African Republic",
			"Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo",
			"Congo (Democratic Republic of the)", "Cook Islands", "Costa Rica", "Croatia", "Cuba", "Curaçao", "Cyprus",
			"Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt",
			"El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)",
			"Faroe Islands", "Fiji", "Finland", "France", "French Guiana", "French Polynesia",
			"French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece",
			"Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guernsey", "Guinea", "Guinea-Bissau", "Guyana",
			"Haiti", "Heard Island and McDonald Islands", "Holy See", "Honduras", "Hong Kong", "Hungary", "Iceland",
			"India", "Indonesia", "Côte d'Ivoire", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Isle of Man",
			"Israel", "Italy", "Jamaica", "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait",
			"Kyrgyzstan", "Lao People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya",
			"Liechtenstein", "Lithuania", "Luxembourg", "Macao", "Macedonia (the former Yugoslav Republic of)",
			"Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique",
			"Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia (Federated States of)", "Moldova (Republic of)",
			"Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru",
			"Nepal", "Netherlands", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue",
			"Norfolk Island", "Korea (Democratic People's Republic of)", "Northern Mariana Islands", "Norway", "Oman",
			"Pakistan", "Palau", "Palestine, State of", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines",
			"Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Republic of Kosovo", "Réunion", "Romania",
			"Russian Federation", "Rwanda", "Saint Barthélemy", "Saint Helena, Ascension and Tristan da Cunha",
			"Saint Kitts and Nevis", "Saint Lucia", "Saint Martin (French part)", "Saint Pierre and Miquelon",
			"Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia",
			"Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Sint Maarten (Dutch part)", "Slovakia",
			"Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands",
			"Korea (Republic of)", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard and Jan Mayen",
			"Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan", "Tajikistan",
			"Tanzania, United Republic of", "Thailand", "Timor-Leste", "Togo", "Tokelau", "Tonga",
			"Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda",
			"Ukraine", "United Arab Emirates", "United Kingdom", "United States of America", "Uruguay", "Uzbekistan",
			"Vanuatu", "Venezuela (Bolivarian Republic of)", "Viet Nam", "Wallis and Futuna", "Western Sahara", "Yemen",
			"Zambia", "Zimbabwe", };

	private final String[] AMOUNT_S = { "Amount of Destinations", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

	private JComboBox<String> countryAmountJCB;
	private ArrayList<JComboBox<String>> countryChoicesJCBList;
	private ArrayList<String> countryChoicesSList;
	private String countryAmountS;

	private KGradientPanel rightPanel;
	private JPanel leftPanel;

	private JButton nextJB;
	private JButton goJB;
	private JButton backJB;

	/**
	 * der Konstruktor initialisiert das left und right Panel mitsamt Buttons
	 * (nextxJB, backJB), sowie die Combobox
	 * 
	 * @param homeP uebergibt ein Objekt der Klasse HomePanel
	 */
	public NewJourneyPanel(HomePanel homeP) {
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

		countryAmountJCB = new JComboBox<String>(AMOUNT_S);
		countryAmountJCB.setMaximumSize(new Dimension(300, 50));

		nextJB = new JButton("Next");
		nextJB.setAlignmentX(Component.CENTER_ALIGNMENT);
		nextJB.setPreferredSize(new Dimension(300, 100));
		nextJB.addActionListener(new nextJBL());

		backJB = new JButton("Back");
		backJB.setAlignmentX(Component.CENTER_ALIGNMENT);
		backJB.setPreferredSize(new Dimension(300, 100));
		backJB.addActionListener(new backJBL());

		add(leftPanel);
		add(rightPanel);

		leftPanel.add(Box.createHorizontalGlue());
		leftPanel.add(Box.createRigidArea(new Dimension(0, 255)));
		leftPanel.add(countryAmountJCB);
		leftPanel.add(Box.createHorizontalGlue());

		rightPanel.add(Box.createRigidArea(new Dimension(0, 360)));
		rightPanel.add(nextJB);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		rightPanel.add(backJB);

	}

	/**
	 * dieser Action Listner aendert das Layout der Panels und erstellt je nach
	 * Auswahl des Users, dynamisch, entsprechend viele Comboboxes in denen ein
	 * Reiseziel angewaehlt werden kann.
	 */
	public class nextJBL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			countryAmountS = (String) countryAmountJCB.getSelectedItem();

			if (countryAmountS == "Amount of Destinations") {
				JOptionPane.showMessageDialog(homeP, "Please choose a Number!", "Input Error",
						JOptionPane.WARNING_MESSAGE);

			}

			else {

				leftPanel.removeAll();
				leftPanel.remove(nextJB);
				leftPanel.revalidate();

				rightPanel.removeAll();
				rightPanel.remove(nextJB);
				rightPanel.revalidate();

				goJB = new JButton("GO");
				goJB.setAlignmentX(Component.CENTER_ALIGNMENT);
				goJB.setPreferredSize(new Dimension(300, 100));

				int i = 0;
				try {
					i = Integer.parseInt(countryAmountS);
				}

				catch (NumberFormatException nfe) {
					nfe.printStackTrace();
				}

				countryChoicesJCBList = new ArrayList<>();
				while (i > 0) {
					countryChoicesJCBList.add(new JComboBox<String>(CHOICES_S));
					i--;
				}

				leftPanel.add(Box.createRigidArea(new Dimension(0, 255 - countryChoicesJCBList.size() * 5)));

				for (JComboBox<String> jcb : countryChoicesJCBList) {

					jcb.setMaximumSize(new Dimension(300, 40));
					leftPanel.add(jcb);
					leftPanel.add(Box.createHorizontalGlue());
				}

				goJB.addActionListener(new goJBL());
				rightPanel.add(Box.createRigidArea(new Dimension(0, 360)));
				rightPanel.add(goJB);
				rightPanel.add(Box.createRigidArea(new Dimension(0, 40)));
				rightPanel.add(backJB);

				leftPanel.remove(countryAmountJCB);

				homeP.revalidate();
				leftPanel.revalidate();

			}

		}

	}

	/**
	 * dieser ActionListner oeffnet das WorldMapPanel mit den zuvor angewaehlten
	 * Reisezielen
	 */
	public class goJBL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			countryChoicesSList = new ArrayList<>();
			for (JComboBox<String> jcb : countryChoicesJCBList) {

				if ((String) jcb.getSelectedItem() == "Choose Country") {
					continue;
				}

				countryChoicesSList.add((String) jcb.getSelectedItem());

			}

			if (countryChoicesSList.size() <= 1) {
				JOptionPane.showMessageDialog(homeP, "You should enter at least two valid destinations!", "Input Error",
						JOptionPane.WARNING_MESSAGE);
			}

			else {
				homeP.removeNewJourneyPanel();
				homeP.openWorldMapPanel(countryChoicesSList);
				homeP.revalidate();
			}

		}

	}

	/**
	 * dieser ActionListner entfernt das NewJourneyPanel vom HomePanel (Controler)
	 * und ruft erneut das MenuPanel auf vom dem der User nun erneut durch das
	 * Programm navigieren kann
	 */
	public class backJBL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			homeP.removeNewJourneyPanel();
			homeP.openMenuPanel();
			homeP.revalidate();
		}
	}
}
