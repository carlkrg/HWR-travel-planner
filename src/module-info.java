/**
 * @author Carl Krogmann
 *
 */
module trasap {
	
	requires java.desktop;
	requires com.google.gson;
	requires KControls;
	requires ojdbc7;

	opens module to com.google.gson;

}