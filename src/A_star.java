import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Amir Lichter on 07/12/2017.
 */
public class A_star extends Searcher {
    private ArrayList<State> openList;

    /**
     * constructor.
     */
    public A_star() {
        this.openList = new ArrayList();
    }

    /**
     * sort the open list according to their cost and distance from goal position.
     *
     * @param goal goal point.
     */
    private void sortList(AI_point goal) {
        // sort the list
        this.openList.sort((first, second) -> {
            double f = first.getCost() + first.getState().distanceTo(goal);
            double s = second.getCost() + second.getState().distanceTo(goal);
            int fTime = first.getDiscoverTime();
            int sTime = second.getDiscoverTime();
            return f < s ? -1 : f > s ? 1 : fTime < sTime ? -1 : fTime > sTime ? 1 : 0;
        });
    }

    @Override
    public PrintSolution search(ISearchable searchable) {
        ArrayList<State> stateList = new ArrayList();
        int cost = 0;
        int discoverTime = 1;

        // init
        State root = searchable.getInitialState();
        State goal = searchable.getGoalState();
        AI_point goalPoint = goal.getState();
        this.openList.add(root);

        State found = null;
        while (!this.openList.isEmpty()) {
            // get the best state
            this.sortList(goalPoint);
            State current = this.openList.get(0);
            if (current.equals(goal)) {
                // found a match
                found = current;
                cost = found.getCost();
                break;
            }
            this.openList.remove(current);

            searchNeighborsOf(current, searchable, discoverTime);
            ++discoverTime;
        }

        // add the states of the solution to the list
        while (found != null) {
            stateList.add(found);
            found = found.getCameFrom();
        }
        Collections.reverse(stateList);

        return new PrintSolution("-", cost, stateList);
    }

    /**
     * search over the neighbors of the current state in the given searchable.
     *
     * @param current      state.
     * @param searchable   search-problem.
     * @param discoverTime discover time of the new states.
     */
    private void searchNeighborsOf(State current, ISearchable searchable,
                                        int discoverTime) {
        int currentDepth = current.getDepth();
        if (currentDepth + 1 > (searchable.getSize()*searchable.getSize())) {
            return;
        }

        // get neighbors
        ArrayList<State> neighbors = searchable.getAllPossibleStates(current);
        for (State neighbor : neighbors) {
            // duplicate pruning
            if (this.openList.indexOf(neighbor) == -1) {
                this.openList.add(neighbor);
            }

            // search better cost
            int newCost = current.getCost() + neighbor.getCost();
            if (newCost > neighbor.getCost()) {
                continue;
            }

            // the better solution
            neighbor.setCost(newCost);
            neighbor.setCameFrom(current);
            neighbor.setDiscoverTime(discoverTime);
            neighbor.setDepth(currentDepth + 1);
        }
    }
}
