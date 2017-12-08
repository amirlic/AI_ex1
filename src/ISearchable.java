import java.util.ArrayList;

/**
 * Created by Amir Lichter on 04/12/2017.
 */
public interface ISearchable {
    State getInitialState();
    State getGoalState();
    ArrayList<State> getAllPossibleStates(State s);
    int getSize();
}
