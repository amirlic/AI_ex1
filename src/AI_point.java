import static java.lang.Double.max;
import static java.lang.Math.abs;

/**
 * Created by Amir Lichter on 04/12/2017.
 */

public final class AI_point {
    private final int x;    // x-coordinate
    private final int y;    // y-coordinate
    private String kind;

    // point initialized from parameters
    public AI_point(int x, int y, String kind) {
        this.x = x;
        this.y = y;
        this.kind = kind;
    }

    // accessor methods
    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public String getKind() {
        return this.kind;
    }

    public double r() {
        return Math.sqrt(x * x + y * y);
    }

    public double theta() {
        return Math.atan2(y, x);
    }

    // Euclidean distance between this point and that point
    public double distanceTo(AI_point that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        // return Math.sqrt(dx * dx + dy * dy);
        return max(abs(dx),abs(dy));
    }

    public String getInstructionTo(AI_point other) {
        if (this.x < other.x() && this.y < other.y()) {
            return "RD";
        }
        if (this.x > other.x() && this.y < other.y()) {
            return "RU";
        }
        if (this.x > other.x() && this.y > other.y()) {
            return "LU";
        }
        if (this.x < other.x() && this.y > other.y()) {
            return "LD";
        }
        if (this.x < other.x()) {
            return "R";
        }
        if (this.x > other.x()) {
            return "L";
        }
        if (this.y < other.y()) {
            return "D";
        }
        if (this.y > other.y()) {
            return "U";
        }
        // this section is unreachable
        return "Error!";
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof AI_point) {
            AI_point otherPoint = (AI_point) other;
            return this.x == otherPoint.x() && this.y == otherPoint.y();
        }
        return false;
    }


    // return a string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
