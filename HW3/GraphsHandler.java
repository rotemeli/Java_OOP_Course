import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class GraphsHandler {

    public static void main(String[] args) throws IOException {
        List<IGraph<String>> list = new ArrayList<>();
        SortedSet<IGraph<String>> sortedSet = new TreeSet<>(Comparator.comparing(IGraph<String>::numOfVertices).thenComparing(list::indexOf));

        File file = new File(args[0]);
        Scanner sc = new Scanner(file);
        int currentLine = 0;
        String myErr = "";
        FileWriter errorFile = new FileWriter("errorsGraphs.txt");
        while(sc.hasNext()) {
            currentLine++;
            String err = "line number=" + currentLine + ", input line=";
            String line = sc.nextLine();

            try {
                if (line.length() == 0) {
                    throw new HW3Exception(err + ", Error message = null");
                }
                if (!line.startsWith("DirectedGraph") && !line.startsWith("UndirectedGraph")) {
                    throw new HW3Exception(err + line + ", Error message = The graph type is not valid");
                }

                for (int i = 0; i < line.length(); i++) {
                    List<Character> lst = new ArrayList<>();
                    lst.add('{');
                    lst.add('}');
                    lst.add(':');
                    lst.add('\t');
                    lst.add('\n');
                    lst.add(',');
                    lst.add(' ');
                    if (!Character.isAlphabetic(line.charAt(i)) && !Character.isDigit(line.charAt(i)) && !lst.contains(line.charAt(i))) {
                        throw new HW3Exception(err + line + ", Error message = Exception in scanning the graph string");
                    }
                }

                IGraph<String> graph = GraphUtils.graphMake(line);
                list.add(0 , graph);
                sortedSet.add(graph);

            } catch (Exception e){
                myErr = e.getMessage();
                if(sc.hasNext()) {
                    errorFile.write(myErr + '\n');
                }
            }

        }
        errorFile.write(myErr);
        errorFile.close();

        FileWriter outList = new FileWriter("GraphsOutList.txt");
        indexList(list, outList);

        FileWriter outSet = new FileWriter("GraphsSortOutSet.txt");
        for (IGraph<String> Graph : sortedSet) {
            outSet.write(Graph.toString());
            if(sortedSet.last().equals(Graph)) {
                break;
            }
            outSet.write("\n");
        }
        outSet.close();

        FileWriter outSortList = new FileWriter("GraphsSortOutList.txt");
        list.sort(Comparator.comparing(IGraph<String>::numOfVertices).thenComparing(IGraph::numOfEdges));
        indexList(list, outSortList);
    }

    private static void indexList(List<IGraph<String>> list, FileWriter outList) throws IOException {
        for (IGraph<String> Graph : list) {
            outList.write(Graph.toString());
            int index = list.size()-1;
            if(list.get(index).equals(Graph)) {
                break;
            }
            outList.write("\n");
        }
        outList.close();
    }
}
