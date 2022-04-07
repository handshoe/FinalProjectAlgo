import java.io.*;
import java.util.*;

public class BusSystem {
	
	public static void main(String[] args){
		Scanner userInput = new Scanner(System.in);
		
		
	}
	
	private static String stopSearch() throws IOException {
		String lineToReturn = "";
		FileReader fr = new FileReader("stops.txt");
		BufferedReader br = new BufferedReader(fr);
		
		while((lineToReturn = br.readLine()) != null) {
			
		}
		
		br.close();	
		return lineToReturn;
	}

}
