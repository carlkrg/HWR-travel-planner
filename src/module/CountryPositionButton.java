package module;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class CountryPositionButton extends JButton {

	private double x;
	private double y;
	private int intx;
	private int inty;
	private CountryJson myCountryJson;

	public CountryPositionButton(double lat, double lng, CountryJson countryJson) {
		super();
		this.x = lat;
		this.y = lng;
		this.myCountryJson = countryJson;

		x = (lng + 180) * (1080 / 360);
		y = 544 / 2 - (lat * 544) / 180;

		intx = (int) Math.round(x);
		inty = (int) Math.round(y);

		intx = intx - 4;
		inty = inty - 4;

		System.out.println(intx);
		System.out.println(inty);

		setBounds(intx, inty, 8, 8);

		addActionListener(
				new CustomizedAction(myCountryJson.getFlag(), myCountryJson.getName(), myCountryJson.getNativeName(),
						myCountryJson.getCapital(), myCountryJson.getPopulation(), myCountryJson.getArea(),
						myCountryJson.getRegion(), myCountryJson.getSubregion(), myCountryJson.getGini()));
	}

	public int getIntx() {
		return intx;
	}

	public int getInty() {
		return inty;
	}

	public class CustomizedAction extends AbstractAction {
		String name;
		String flag;
		String nativeName;
		String capital;
		String population;
		String area;
		String region;
		String subregion;
		String gini;
		ImageIcon countryIcon;

		CustomizedAction(String flag, String name, String nativeName, String capital, String population, String area,
				String region, String subregion, String gini) {
			
			super(name);
			this.name = name;
			this.nativeName = nativeName;
			this.flag = flag;
			this.capital = capital;
			this.population = population;
			this.area = area;
			this.region = region;
			this.subregion = subregion;
			this.gini = gini;
			this.flag = this.flag.substring(this.flag.length() - 7) + ".png";

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String currentName = (String) getValue(NAME);
			ImageIcon icon = new ImageIcon("./resources/flags/" + flag);
			System.out.println(this.flag);

			JOptionPane.showMessageDialog(null,
					"Name: " + name + "\n" + "Hauptstadt: " + capital + "\n" + "Population: " + population + "\n"
							+ "Fläche: " + area + " km2" + "\n" + "Region: " + region + "\n" + "Unterregion: "
							+ subregion + "\n" + "Gini-Index: " + gini,
					currentName, JOptionPane.INFORMATION_MESSAGE, icon);
		}
	}
}
