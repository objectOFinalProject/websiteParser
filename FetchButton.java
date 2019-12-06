

import java.net.URI;
import java.awt.Desktop;

/**
 * 
 * @author Payal
 * This class deals with the "Fetch" button,
 * which reads in the url from the text field
 * and opens the appropriate web page.
 *
 */
public class FetchButton {
	public static void clickButton(URI uri) {
		Desktop desktop = Desktop.getDesktop();
		
		try {
			desktop.browse(uri);
		} catch (Exception e) {
			System.out.println("Something went wrong");
		}
	}
}
