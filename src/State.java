/**
 * Created by Amir Lichter on 04/12/2017.
 */
public class State {
    // the state represented by a string
    private AI_point state;
    // cost to reach this state (set by a setter)
    private int cost;
    // the state we came from to this state (setter)
    private State cameFrom;

    private int discoverTime;

    private int depth;

    public State(AI_point state) {
        this.state = state;
        this.depth = 0;
        this.cost = 0;
        this.cameFrom = null;
        this.discoverTime = 0;
    }

    public int getCost(){
        return this.cost;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }

    public AI_point getState()
    {
        return this.state;
    }

    public State getCameFrom()
    {
        return this.cameFrom;
    }

    public void setCameFrom(State from)
    {
        this.cameFrom = from;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof State) {
            State otherState = (State) other;
            return this.state.equals(otherState.getState());
        }
        return false;
    }

    public int getDepth() {
        return this.depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getInstructionTo(State other) {
        return this.state.getInstructionTo(other.getState());
    }

    public int getDiscoverTime() {
        return this.discoverTime;
    }

    public void setDiscoverTime(int discoverTime) {
        this.discoverTime = discoverTime;
    }
}
