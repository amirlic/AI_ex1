/**
 * amir lichter
 * 316129881
 * 03
 * lichtea3
 * Created by Amir Lichter on 04/12/2017.
 */

public class java_ex1 {
    public static void main(String [ ] args){
        AI_parser parser = new AI_parser("input.txt");
        parser.read();
        ISearchable map = parser.getMap();
        String algorithm = parser.getAlgorithm();
        if (algorithm.equals("IDS")) {
            Searcher searcher = new Ids();
            PrintSolution sol = searcher.search(map);
            sol.writePathTo("output.txt");
        }
        if (algorithm.equals("A*")) {
            Searcher searcher = new A_star();
            PrintSolution sol = searcher.search(map);
            sol.writePathTo("output.txt");
        }
    }
}
