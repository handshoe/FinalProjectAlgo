import java.io.*;
import java.util.*;

public class BusSystem {

	public static void main(String[] args) throws IOException {
		Scanner userInput = new Scanner(System.in);
		boolean programContinuing = true;
		boolean functionContinuing = false;
		int functionalityNumber = 1;// 1 = Shortest Path, 2 = Bus Stop Name Search, 3 = Trip Info Search

		while (programContinuing) {
			System.out.println("Select an option from below by entering the corresponding number:\n"
					+ "---------------------------------------------------\n"
					+ "(1)Find the shortest path between two stops.\n" + "(3)Find trip information\n"
					+ "or type 'exit' to end program.");
			if (userInput.hasNextInt()) {
				functionalityNumber = userInput.nextInt();
				functionContinuing = true;
			} else if (userInput.hasNext()) {
				if (userInput.next().equalsIgnoreCase("exit")) {
					programContinuing = false;
					System.out.println("Goodbye :)");
				}
			} else {
				System.out.println("INVALID INPUT");
			}

			// 1
			DijkstraCalculator shortestPathCalc = new DijkstraCalculator();
			while (functionContinuing) {
				switch (functionalityNumber) {
				case 1:
					FileReader fr = new FileReader("transfers.txt");
					BufferedReader br = new BufferedReader(fr);
					String lineBeingChecked = br.readLine();
					ArrayList<GraphNode> stopIds = new ArrayList<GraphNode>();

					while ((lineBeingChecked = br.readLine()) != null) {
						if (Integer.parseInt(lineBeingChecked.split(",")[2]) == 0) {
							GraphNode stopId = new GraphNode(lineBeingChecked.split(",")[0]);
							GraphNode endStopId = new GraphNode(lineBeingChecked.split(",")[1]);
							stopId.addNewDirection(endStopId, 2);
							if (!stopIds.contains(stopId)) {
								stopIds.add(stopId);
							}
							if (!stopIds.contains(endStopId)) {
								stopIds.add(endStopId);
							}
						} else if (Integer.parseInt(lineBeingChecked.split(",")[2]) == 2) {
							GraphNode stopId = new GraphNode(lineBeingChecked.split(",")[0]);
							GraphNode endStopId = new GraphNode(lineBeingChecked.split(",")[1]);
							stopId.addNewDirection(endStopId, (Integer.parseInt(lineBeingChecked.split(",")[3])) / 100);
							if (!stopIds.contains(stopId)) {
								stopIds.add(stopId);
							}
							if (!stopIds.contains(endStopId)) {
								stopIds.add(endStopId);
							}
						}
					}
					FileReader fr2 = new FileReader("stop_times.txt");
					BufferedReader br2 = new BufferedReader(fr2);
					lineBeingChecked = br2.readLine();

					String tempString;
					GraphNode source = null;
					GraphNode destination = null;

					System.out.println("What is the first stop you are looking for?");
					for (int i = 0; i < stopIds.size(); i++) {
						if (stopIds.get(i).getNodeName().equals(tempString = userInput.next())) {
							source = stopIds.get(i);
						}
					}
					System.out.println("What is the second stop you are looking for?");
					for (int i = 0; i < stopIds.size(); i++) {
						if (stopIds.get(i).getNodeName().equals(tempString = userInput.next())) {
							destination = stopIds.get(i);
						}
					}

					System.out.println("Shortest path:\n" + shortestPathCalc.shortestPath(source, destination));
					System.out.println("Press ENTER to continue to the menu");
					System.out.println("------------------------------------");
					while (!userInput.next().equalsIgnoreCase("continue")) {
					}
					break;

				/*
				 * 2 while (functionContinuing) { FileReader fr = new FileReader("stops.txt");
				 * BufferedReader br = new BufferedReader(fr); String[] categories =
				 * br.readLine().split(","); TernarySearchTree stopsTST = new
				 * TernarySearchTree(); String lineToInsert = "";
				 * 
				 * while ((lineToInsert = br.readLine()) != null) {
				 * System.out.println(lineToInsert.split(",")[2]);
				 * stopsTST.insert(lineToInsert.split(",")[2]); }
				 * 
				 * System.out.println("What stop are you looking for?"); String searchStopName =
				 * userInput.next(); System.out.println(stopsTST.search(searchStopName));
				 * System.out.println("Search again? (Y/N)");
				 * 
				 * String continueCheck = userInput.next(); while (true) { if
				 * (continueCheck.equalsIgnoreCase("Y")) { break; } else if
				 * (continueCheck.equalsIgnoreCase("N")) { functionContinuing = false; break; }
				 * else { System.out.println("Invalid input! Please enter Y or N"); } }
				 * functionalityNumber = 0; break; }
				 */

				// 3
				case 3:
					TripSearcher ts = new TripSearcher();

					System.out.println("Enter a arrival time in the format hh:mm:ss");
					if (userInput.hasNext()) {
						String timeToSearchFor = userInput.next();
						if (timeToSearchFor.matches("\\d{2}:\\d{2}:\\d{2}")) {
							if (Integer.parseInt(timeToSearchFor.substring(0, 2)) <= 23
									&& Integer.parseInt(timeToSearchFor.substring(3, 5)) <= 59
									&& Integer.parseInt(timeToSearchFor.substring(6, 8)) <= 59) {
								System.out.println(ts.tripSearch(timeToSearchFor));
							} else {
								System.out.println("Invalid time!");
							}
						} else {
							System.out.println("Invalid input!");
						}
					}
					System.out.println("Please enter 'continue' to continue to the menu");
					System.out.println("------------------------------------");
					while (!userInput.next().equalsIgnoreCase("continue")) {
					}
					functionContinuing = false;
					break;
				}
			}
		}
		userInput.close();
	}

}
