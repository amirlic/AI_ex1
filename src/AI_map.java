import java.util.ArrayList;

/**
 * Created by Amir Lichter on 05/12/2017.
 */
public class AI_map implements ISearchable{
    private String[][] map;
    private int size;

    public AI_map(ArrayList<String> board){
        this.size = board.size();
        this.map = new String[this.size][this.size];
        for (int i = 0; i < this.size; i++){
            for (int j = 0; j < this.size; j++){
                String s = String.valueOf(board.get(j).charAt(i));
                this.map[i][j] = s;
            }
        }
    }

    @Override
    public State getInitialState() {
        return new State(new AI_point(0,0,"S"));
    }

    @Override
    public State getGoalState() {
        State state = new State(new AI_point(this.size-1,this.size-1,"G"));
        state.setDepth(0);
        return state;
    }

    @Override
    public ArrayList<State> getAllPossibleStates(State s) {
        ArrayList<State> all = new ArrayList<>();
        int x = s.getState().x();
        int y = s.getState().y();
        if (inRange(x + 1, y) && notWater(map[x + 1][y])) {
            State state = new State(new AI_point(x + 1, y, this.map[x + 1][y]));
            if (this.map[x + 1][y].equals("R")) {
                state.setCost(1);
            }
            if (this.map[x + 1][y].equals("D")) {
                state.setCost(3);
            }
            if (this.map[x + 1][y].equals("H")) {
                state.setCost(10);
            }
            if (this.map[x + 1][y].equals("W")) {
                state.setCost(0);
            }
            state.setDepth(s.getDepth() + 1);
            state.setCameFrom(s);
            state.setCost(s.getCost() + state.getCost());
            all.add(state);
        }

        if (inRange(x + 1, y + 1) && notWater(map[x + 1][y + 1])  && notNearWater(x, y, x+1 ,y+1)) {
            State state = new State(new AI_point(x + 1, y + 1, this.map[x + 1][y + 1]));
            if (this.map[x + 1][y + 1].equals("R")) {
                state.setCost(1);
            }
            if (this.map[x + 1][y + 1].equals("D")) {
                state.setCost(3);
            }
            if (this.map[x + 1][y + 1].equals("H")) {
                state.setCost(10);
            }
            if (this.map[x + 1][y + 1].equals("W")) {
                state.setCost(0);
            }
            state.setDepth(s.getDepth() + 1);
            state.setCameFrom(s);
            state.setCost(s.getCost() + state.getCost());
            all.add(state);
        }

        if (inRange(x, y+1) && notWater(map[x][y+1])) {
            State state = new State(new AI_point(x,y+1,this.map[x][y+1]));
            if (this.map[x][y+1].equals("R")) {
                state.setCost(1);
            }
            if (this.map[x][y+1].equals("D")) {
                state.setCost(3);
            }
            if (this.map[x][y+1].equals("H")) {
                state.setCost(10);
            }
            if (this.map[x][y+1].equals("W")) {
                state.setCost(0);
            }
            state.setDepth(s.getDepth()+1);
            state.setCameFrom(s);
            state.setCost(s.getCost() + state.getCost());
            all.add(state);
        }

        if (inRange(x-1, y+1) && notWater(map[x-1][y+1]) && notNearWater(x, y, x-1, y+1)) {
            State state = new State(new AI_point(x-1,y+1,this.map[x-1][y+1]));
            if (this.map[x-1][y+1].equals("R")) {
                state.setCost(1);
            }
            if (this.map[x-1][y+1].equals("D")) {
                state.setCost(3);
            }
            if (this.map[x-1][y+1].equals("H")) {
                state.setCost(10);
            }
            if (this.map[x-1][y+1].equals("W")) {
                state.setCost(0);
            }
            state.setDepth(s.getDepth()+1);
            state.setCameFrom(s);
            state.setCost(s.getCost() + state.getCost());
            all.add(state);
        }

        if (inRange(x-1, y) && notWater(map[x-1][y])) {
            State state = new State(new AI_point(x-1,y,this.map[x-1][y]));
            if (this.map[x-1][y].equals("R")) {
                state.setCost(1);
            }
            if (this.map[x-1][y].equals("D")) {
                state.setCost(3);
            }
            if (this.map[x-1][y].equals("H")) {
                state.setCost(10);
            }
            if (this.map[x-1][y].equals("W")) {
                state.setCost(0);
            }
            state.setDepth(s.getDepth()+1);
            state.setCameFrom(s);
            state.setCost(s.getCost() + state.getCost());
            all.add(state);
        }

        if (inRange(x-1, y-1) && notWater(map[x-1][y-1])  && notNearWater(x, y, x-1, y-1)) {
            State state = new State(new AI_point(x-1,y-1,this.map[x-1][y-1]));
            if (this.map[x-1][y-1].equals("R")) {
                state.setCost(1);
            }
            if (this.map[x-1][y-1].equals("D")) {
                state.setCost(3);
            }
            if (this.map[x-1][y-1].equals("H")) {
                state.setCost(10);
            }
            if (this.map[x-1][y-1].equals("W")) {
                state.setCost(0);
            }
            state.setDepth(s.getDepth()+1);
            state.setCameFrom(s);
            state.setCost(s.getCost() + state.getCost());
            all.add(state);
        }

        if (inRange(x, y-1) && notWater(map[x][y-1])) {
            State state = new State(new AI_point(x,y-1,this.map[x][y-1]));
            if (this.map[x][y-1].equals("R")) {
                state.setCost(1);
            }
            if (this.map[x][y-1].equals("D")) {
                state.setCost(3);
            }
            if (this.map[x][y-1].equals("H")) {
                state.setCost(10);
            }
            if (this.map[x][y-1].equals("W")) {
                state.setCost(0);
            }
            state.setDepth(s.getDepth()+1);
            state.setCameFrom(s);
            state.setCost(s.getCost() + state.getCost());
            all.add(state);
        }

        if (inRange(x+1, y-1) && notWater(map[x+1][y-1])  && notNearWater(x, y, x+1, y-1)) {
            State state = new State(new AI_point(x+1,y-1,this.map[x+1][y-1]));
            if (this.map[x+1][y-1].equals("R")) {
                state.setCost(1);
            }
            if (this.map[x+1][y-1].equals("D")) {
                state.setCost(3);
            }
            if (this.map[x+1][y-1].equals("H")) {
                state.setCost(10);
            }
            if (this.map[x+1][y-1].equals("W")) {
                state.setCost(0);
            }
            state.setDepth(s.getDepth()+1);
            state.setCameFrom(s);
            state.setCost(s.getCost() + state.getCost());
            all.add(state);
        }
        return all;
    }

    public boolean inRange(int x, int y) {
        if (x < this.size && y < this.size && x >= 0 && y >= 0) {
            return true;
        }
        return false;
    }

    public boolean notWater(String s) {
        if (!s.equals("W")) {
            return true;
        }
        return false;
    }

    public boolean notNearWater(int oldX, int oldY, int newX, int newY) {
        if (inRange(oldX, newY) && !notWater(map[oldX][newY])) {
            return false;
        }

        if (inRange(newX, oldY) && !notWater(map[newX][oldY])) {
            return false;
        }
        return true;
    }

    public int getSize() {
        return this.size;
    }
}
