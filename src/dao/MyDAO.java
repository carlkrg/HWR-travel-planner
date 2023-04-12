package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * die MyDAO Klasse stellt die Schnittstelle zur Datenbank dar. 
 * Alle read, write und delete Operationen werden ueber sie ausgefuehrt
 * 
 * @author Jakob Simonsmeier
 */
public class MyDAO implements MyDAOInterface {

	private static MyDAO myDao;

	private OracleDsSingleton ora = OracleDsSingleton.getInstance();
	private Connection con = ora.getConnection();
	private Statement stm = con.createStatement();
	private String sql;
	private String last;
	private ResultSet rs;
	private ArrayList<String> journeyNamesList;
	private List<String> countryChoicesSList;

	/**
	 * der Konstruktor greift auf das Singleton Objekt via .getInstance zu und
	 * stellt eine Verbindung zur Datenbank her
	 * 
	 * @throws SQLException Exception Handeling
	 */
	private MyDAO() throws SQLException {

		OracleDsSingleton ora = OracleDsSingleton.getInstance();
		Connection con = ora.getConnection();
		Statement stm = con.createStatement();
	}

	/**
	 * diese Methode greift auf die Datenbank zu und liest die Namen der
	 * gespeicherten Reisen in eine ArrayList aus Strings ein und gibt diese
	 * zurueck.
	 */
	@Override
	public ArrayList<String> readJourneyNames() {

		journeyNamesList = new ArrayList<String>();

		try {
			sql = "SELECT DISTINCT ROUTE FROM RoutenPos";
			rs = stm.executeQuery(sql);

			while (rs.next()) {
				last = rs.getString("Route");
				journeyNamesList.add(last);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return journeyNamesList;
	}

	/**
	 * diese Methode liest die einzelnen Laendernmane der ausgewaehlten Reise in
	 * eine ArrayList aus Strings und gibt diese zurueck
	 */
	@Override
	public ArrayList<String> readJourneyDestinations(String name) {

		ArrayList<String> countryChoicesSList = new ArrayList<String>();

		try {

			sql = "SELECT Land FROM ROUTENPOS WHERE Route = '" + name + "' ORDER BY Pos";
			rs = stm.executeQuery(sql);

			while (rs.next()) {
				last = rs.getString("Land");
				countryChoicesSList.add(last);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return countryChoicesSList;

	}

	/**
	 * diese Methode speichert eine Reise unter vom User gewaehlten Namen in die
	 * Datenbank
	 */
	@Override
	public void save(ArrayList<String> countryChoicesSList, String name) {

		this.countryChoicesSList = countryChoicesSList;

		for (int i = 0; i < countryChoicesSList.size(); i++) {

			try {
				sql = "INSERT INTO ROUTENPOS VALUES ('" + name + "', '" + countryChoicesSList.get(i) + "', " + i + ")";
				stm.executeQuery(sql);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * diese Methode loescht einen ausgewaehlten Eintrag aus der Datenbank
	 *
	 */
	@Override
	public void delete(String name) {

		try {
			sql = "DELETE FROM RoutenPos WHERE Route = '" + name + "'";

			stm.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * diese Methode ermoeglicht den Zugriff auf das MyDAO Objekt, da dieses wie ein
	 * Singleton aufgebaut ist
	 * 
	 * @return  gibt das myDAO Objekt zuruek
	 * @throws SQLException   Exception Handeling
	 */
	public static MyDAO getInstance() throws SQLException {
		if (myDao == null)
			myDao = new MyDAO();
		return myDao;
	}

}
