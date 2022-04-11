import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TripSearcher {
	public String tripSearch(String timeToSearchFor) throws IOException {
		String lineToReturn = "";
		ArrayList<String> stops = new ArrayList<String>();

		FileReader fr = new FileReader("stop_times.txt");
		BufferedReader br = new BufferedReader(fr);
		String[] categories = br.readLine().split(",");

		if (timeToSearchFor.charAt(0) == '0') {
			timeToSearchFor = " " + timeToSearchFor.substring(1, timeToSearchFor.length());
		}
		while ((lineToReturn = br.readLine()) != null) {
			if (lineToReturn.split(",")[1].equals(timeToSearchFor)) {
				stops.add(lineToReturn);
			}
		}
		stops = quickSortStops(stops);
		for (int i = 0; i < stops.size(); i++) {
			for (int j = 0; j < categories.length; j++) {
				lineToReturn += categories[j] + ": ";
				lineToReturn += stops.get(i).split(",")[j] + ", ";
			}
			lineToReturn += "\n";
		}
		br.close();
		return lineToReturn;
	}

	public static ArrayList<String> quickSortStops(ArrayList<String> arrayListToBeSorted) {
		if (arrayListToBeSorted.isEmpty()) {
			return arrayListToBeSorted;
		}
		ArrayList<String> sortedStops;
		String pivot = arrayListToBeSorted.get(0);
		ArrayList<String> lessThanPivot = new ArrayList<String>();
		ArrayList<String> moreThanPivot = new ArrayList<String>();

		int i;
		String j;
		for (i = 1; i < arrayListToBeSorted.size(); i++) {
			j = arrayListToBeSorted.get(i);
			if ((Integer.parseInt(j.split(",", 2)[0])) < (Integer.parseInt(pivot.split(",", 2)[0]))) {
				lessThanPivot.add(j);
			} else {
				moreThanPivot.add(j);
			}
		}
		lessThanPivot = quickSortStops(lessThanPivot);
		moreThanPivot = quickSortStops(moreThanPivot);
		lessThanPivot.add(pivot);
		lessThanPivot.addAll(moreThanPivot);
		sortedStops = lessThanPivot;

		return sortedStops;
	}
}
