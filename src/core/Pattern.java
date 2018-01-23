package core;

import java.util.Arrays;

/**
 * Created by jain on 12/07/16.
 */
public class Pattern {

    // time series
    double[] x;
    // label
    private int y;


    public Pattern(double[] x, int y) {
        this.x = x;
        this.y = y;
    }


    public double[] x() {
        return x;
    }

    public Pattern cp() {
        return new Pattern(Arrays.copyOf(x, x.length), y);
    }

}
