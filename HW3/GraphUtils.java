import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GraphUtils {
	private static double PRECISION = 1.0e-2;

	/**
	 * @param d : double
	 * @return String represents d with 2 places after the decimal point.
	 */
	public static String formatDouble(double d) {
		String res = String.format("%.2f", d);
		if (res.equals("-0.00"))
			res = "0.00";
		return res;
	}

	public static boolean areEqual(double d1, double d2) {
		return Math.abs(d1 - d2) < PRECISION;
	}

	public static void graphMaker(IGraph<String> graph,String str, String key, String value) {
		int length = str.length();
		for(int i = key.length(); i < length; i++) {
			if(Character.isAlphabetic(str.charAt(i)) || Character.isDigit(str.charAt(i))) {
				value += str.charAt(i);
			}
			if(Character.toString(str.charAt(i)).equals(",") || Character.toString(str.charAt(i)).equals("}")) {
				if(value.length() > 0) {
					graph.addEdge(key, value);
				}
				value = "";
			}
		}
		if(value.length() > 0) {
			graph.addEdge(key, value);
		}
	}

	public static IGraph<String> graphMake(String graph) {

		DirectedGraph<String> dGraph = new DirectedGraph<>();
		UndirectedGraph<String> undGraph = new UndirectedGraph<>();

		Scanner sc = new Scanner(graph);
		sc.useDelimiter("\\t");
		String myStr = sc.next();

		int check = 0;

		if(myStr.equals("DirectedGraph:")) {
			myStr = sc.next();
			check = 1;
		}

		if(myStr.equals("UndirectedGraph:")) {
			myStr = sc.next();
		}


		Scanner sc2 =  new Scanner(myStr);

		String str;
		String key = "";
		String value = "";

		while(sc2.hasNext()) {
			str = sc2.next();

			int length = str.length();
			int j = 0;
			while(!Character.toString(str.charAt(j)).equals(":") && j<length) {
				key += str.charAt(j);
				j++;
			}

			if(check == 1) {
				dGraph.addVertex(key);
				graphMaker(dGraph, str, key , value);
			}
			else {
				undGraph.addVertex(key);
				graphMaker(undGraph, str, key , value);
			}
			key = "";
		}
		sc.close();
		sc2.close();
		if(check == 1) {
			return dGraph;
		}
		return undGraph;
	}
}
