import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;


public class Writer {
    public boolean writeToText(String file, String string) {
    	
		try {
			//creates text-output stream
			PrintWriter writer = new PrintWriter(new BufferedWriter
					(new FileWriter(file)));
			
			//for(Course c: string) {
				writer.println(string);
			//}
			
			writer.close();
			return true;
		}
		catch(Exception e) {
			return false;
		}		
	}
    public boolean writeToJSON(String file, String string) {
    	
		try {
			//creates text-output stream
			PrintWriter writer = new PrintWriter(new BufferedWriter
					(new FileWriter(file)));
			
			//for(Course c: string) {
				writer.println(string);
			//}
			
			writer.close();
			return true;
		}
		catch(Exception e) {
			return false;
		}		
	}
}
