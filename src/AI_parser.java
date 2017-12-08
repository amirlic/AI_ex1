import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Amir Lichter on 04/12/2017.
 */
public class AI_parser {
    private FileReader input;
    private BufferedReader bufRead;
    private String algorithm;
    private int size;
    private AI_map map;

    public AI_parser(String file){
        this.input = null;
        try {
            input = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.bufRead = new BufferedReader(input);
        this.map = null;
    }
    public void read(){
        String myLine = null;
        ArrayList<String> str = new ArrayList<>();
        int i =  0;
        try {
            while ( (myLine = this.bufRead.readLine()) != null)
            {
                str.add(i, myLine);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.algorithm = str.get(0);
        this.size = Integer.parseInt(str.get(1));
        ArrayList<String> board = new ArrayList<>();
        for (int j = 2; j < this.size + 2; j++){
            board.add(j-2, str.get(j));
        }
        this.map = new AI_map(board);
    }

    public AI_map getMap() {
        return this.map;
    }

    public int getSize() {
        return this.size;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }
}
