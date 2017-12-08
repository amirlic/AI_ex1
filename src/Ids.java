import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Amir Lichter on 06/12/2017.
 */
public class Ids extends Searcher {
    private ISearchable searchable;
    private ArrayList<State> openList;

    /**
     * constructor.
     */
    public Ids() {
        this.searchable = null;
        this.openList = new ArrayList<State>();
    }

    @Override
    public PrintSolution search(ISearchable searchable) {
        ArrayList<State> arr = new ArrayList<>();
        int cost = 0;

        this.searchable = searchable;
        int size = searchable.getSize();
        State root = searchable.getInitialState();

        State found = null;
        for (int depth = 0; depth < size*size; depth++) {
            // init
            this.openList.clear();
            this.openList.add(root);

            // find
            found = DLS(root, depth);
            if (found != null) {
                cost = found.getCost();
                break;
            }
        }

        // add the states of the solution to the list
        while (found != null) {
            arr.add(found);
            found = found.getCameFrom();
        }
        Collections.reverse(arr);

        return new PrintSolution("-", cost, arr);
    }

    public State DLS(State node, int depth) {
        if (depth == 0 && node.getState().getKind().equals("G")){
            return node;
        }
        if (depth > 0) {
            // get neighbors
            ArrayList<State> neighbors = this.searchable.getAllPossibleStates(node);

            // run the algorithm
            for (State child : neighbors) {
                // try a child that is not in the open list
                if (this.openList.indexOf(child) == -1) {
                    this.openList.add(child);

                    State found = DLS(child, depth - 1);
                    if (found != null) {
                        return found;
                    }
                }
            }
        }
        this.openList.remove(node);
        return null;
    }
}
