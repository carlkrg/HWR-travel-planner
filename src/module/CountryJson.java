package module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/*
Beispiel Json:
[
  {
    "name": "Germany",
    "topLevelDomain": [
      ".de"
    ],
    "alpha2Code": "DE",
    "alpha3Code": "DEU",
    "callingCodes": [
      "49"
    ],
    "capital": "Berlin",
    "altSpellings": [
      "DE",
      "Federal Republic of Germany",
      "Bundesrepublik Deutschland"
    ],
    "region": "Europe",
    "subregion": "Western Europe",
    "population": 81770900,
    "latlng": [
      51,
      9
    ],
    "demonym": "German",
    "area": 357114,
    "gini": 28.3,
    "timezones": [
      "UTC+01:00"
    ],
    "borders": [
      "AUT",
      "BEL",
      "CZE",
      "DNK",
      "FRA",
      "LUX",
      "NLD",
      "POL",
      "CHE"
    ],
    "nativeName": "Deutschland",
    "numericCode": "276",
    "currencies": [
      {
        "code": "EUR",
        "name": "Euro",
        "symbol": "€"
      }
    ],
    "languages": [
      {
        "iso639_1": "de",
        "iso639_2": "deu",
        "name": "German",
        "nativeName": "Deutsch"
      }
    ],
    "translations": {
      "de": "Deutschland",
      "es": "Alemania",
      "fr": "Allemagne",
      "ja": "ドイツ",
      "it": "Germania",
      "br": "Alemanha",
      "pt": "Alemanha",
      "nl": "Duitsland",
      "hr": "Njemačka",
      "fa": "آلمان"
    },
    "flag": "https://restcountries.eu/data/deu.svg",
    "regionalBlocs": [
      {
        "acronym": "EU",
        "name": "European Union",
        "otherAcronyms": [],
        "otherNames": []
      }
    ],
    "cioc": "GER"
  }
]
*/

public class CountryJson {

	private String name;
	private String json;

	private String alpha2Code;
	private String alpha3Code;
	private String capital;
	private String region;
	private String subregion;
	private String population;
	private String demonym;
	private String area;
	private String gini;
	private String nativeName;
	private String numericCode;
	private String flag;
	private String cioc;

	private double lat;
	private double lng;

	private JsonElement topLevelDomain;
	private JsonElement callingCodes;
	private JsonElement altSpellings;
	private JsonArray latlng;
	private JsonElement timezones;
	private JsonElement borders;
	private JsonElement currencies;
	private JsonElement languages;
	private JsonElement translations;
	private JsonElement regionalBlocs;

	public CountryJson(String name) {
		super();
		this.name = name;

		try {
			json = getJson(name);
		}

		catch (IOException o) {
		}

		JsonArray jsonArray = new Gson().fromJson(json, JsonArray.class);

		if (jsonArray != null) {
			JsonObject countryJson = (JsonObject) jsonArray.get(0);

			this.alpha2Code = countryJson.get("alpha2Code").toString();
			this.alpha3Code = countryJson.get("alpha3Code").toString();
			this.capital = countryJson.get("capital").toString();
			this.region = countryJson.get("region").toString();
			this.subregion = countryJson.get("subregion").toString();
			this.population = countryJson.get("population").toString();
			this.demonym = countryJson.get("demonym").toString();
			this.area = countryJson.get("area").toString();
			this.gini = countryJson.get("gini").toString();
			this.nativeName = countryJson.get("nativeName").toString();
			this.numericCode = countryJson.get("numericCode").toString();
			this.flag = countryJson.get("flag").toString();
			this.flag = flag.substring(1, flag.length() - 1);
			this.cioc = countryJson.get("cioc").toString();

			this.topLevelDomain = countryJson.get("topLevelDomain");
			this.callingCodes = countryJson.get("callingCodes");
			this.altSpellings = countryJson.get("altSpellings");
			this.latlng = countryJson.getAsJsonArray("latlng");

			this.timezones = countryJson.get("timezones");
			this.borders = countryJson.get("borders");
			this.currencies = countryJson.get("currencies");
			this.languages = countryJson.get("languages");
			this.translations = countryJson.get("translations");
			this.regionalBlocs = countryJson.get("regionalBlocs");

			this.lat = latlng.get(0).getAsDouble();
			this.lng = latlng.get(1).getAsDouble();
		}

		else {
		}

	}

	public String getName() {
		return name;
	}

	public String getJson() {
		return json;
	}

	public String getAlpha2Code() {
		return alpha2Code;
	}

	public String getAlpha3Code() {
		return alpha3Code;
	}

	public String getCapital() {
		return capital;
	}

	public String getRegion() {
		return region;
	}

	public String getSubregion() {
		return subregion;
	}

	public String getPopulation() {
		return population;
	}

	public String getDemonym() {
		return demonym;
	}

	public String getArea() {
		return area;
	}

	public String getGini() {
		return gini;
	}

	public String getNativeName() {
		return nativeName;
	}

	public String getNumericCode() {
		return numericCode;
	}

	public String getFlag() {
		return this.flag;
	}

	public String getCioc() {
		return cioc;
	}

	public JsonElement getTopLevelDomain() {
		return topLevelDomain;
	}

	public JsonElement getCallingCodes() {
		return callingCodes;
	}

	public JsonElement getAltSpellings() {
		return altSpellings;
	}

	public JsonElement getTimezones() {
		return timezones;
	}

	public JsonElement getBorders() {
		return borders;
	}

	public JsonElement getCurrencies() {
		return currencies;
	}

	public JsonElement getLanguages() {
		return languages;
	}

	public JsonElement getTranslations() {
		return translations;
	}

	public JsonElement getRegionalBlocs() {
		return regionalBlocs;
	}

	public double getlat() {
		return lat;
	}

	public double getlng() {
		return lng;
	}

	/**
	 * Methode wird aufgerufen, um eine Verbindung mit der RestCountry API
	 * aufzubauen und den entsprechenden JSON String eines Landes zurueck zu geben.
	 * 
	 * @author Shashank Bodkhe
	 * @see https://dzone.com/articles/how-to-implement-get-and-post-request-through-simp
	 * @param name
	 * @return json
	 * @throws IOException
	 */
	public static String getJson(String name) throws IOException {

		URL urlForGetRequest = new URL("https://restcountries.eu/rest/v2/name/" + name);

		String readLine = null;

		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();

		conection.setRequestMethod("GET");

		conection.setRequestProperty("userId", "a1bcdef");

		int responseCode = conection.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) {

			BufferedReader in = new BufferedReader(

					new InputStreamReader(conection.getInputStream()));

			StringBuffer response = new StringBuffer();

			while ((readLine = in.readLine()) != null) {

				response.append(readLine);

			}
			in.close();

			String json = response.toString();
			System.out.println(json);
			return json;

		} else {

			String json = "";
			System.out.println("GET NOT WORKED");
			return json;
		}
	}
}
