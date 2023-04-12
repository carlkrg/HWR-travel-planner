package dao;

import java.util.ArrayList;

public interface MyDAOInterface {

	ArrayList<String> readJourneyNames();

	ArrayList<String> readJourneyDestinations(String name);

	void save(ArrayList<String> countryChoicesSList, String name);

	void delete(String name);
	

}