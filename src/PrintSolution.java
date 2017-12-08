import java.io.*;
import java.util.ArrayList;

/**
 * solution that hold a list of instructions and a cost and print them.
 */
public class PrintSolution {
    private String separator;
    private int cost;
    private ArrayList<State> stateList;

    /**
     * constructor.
     *
     * @param separator string that represent the separator between the instructions.
     * @param cost      number that represents the cost of the path.
     * @param stateList list of states, which is the path.
     */
    public PrintSolution(String separator, int cost, ArrayList<State> stateList) {
        this.separator = separator;
        this.cost = cost;
        this.stateList = stateList;
    }

    /**
     * write the current solution to the file.
     *
     * @param filename name of the file.
     */
    public void writePathTo(String filename) {
        StringBuilder stringBuilder = new StringBuilder();

        if (this.stateList.isEmpty()) {
            stringBuilder.append("no path");
        } else {
            int size = this.stateList.size();
            State preview = this.stateList.get(0);

            for (int i = 1; i < size - 1; ++i) {
                State current = this.stateList.get(i);
                stringBuilder.append(preview.getInstructionTo(current)).append(this.separator);

                preview = current;
            }

            State current = this.stateList.get(size - 1);
            stringBuilder.append(preview.getInstructionTo(current));

            stringBuilder.append(" ").append(this.cost);
        }

        // write to file
        writeResult(filename, stringBuilder);
    }

    /**
     * write the result into the file.
     *
     * @param filename      name of the file.
     * @param stringBuilder holds the string to write.
     */
    private static void writeResult(String filename, StringBuilder stringBuilder) {
        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filename), "utf-8"))) {
            writer.write(stringBuilder.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not open the output-file");
            e.printStackTrace();
        }
    }
}
