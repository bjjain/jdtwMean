package core;

import tools.Rand;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Patterns extends ArrayList<Pattern> {
    private String name;

    public Patterns(String name) {
        super();
        this.name = name;
    }

    public double[][] getSequences() {
        int N = size();
        double[][] x = new double[N][];
        for (int i = 0; i < N; i++) {
            x[i] = get(i).x;
        }
        return x;
    }


    public Patterns sample(int k) {
        Patterns X = new Patterns(name + " subsample");
        int[] idx = Rand.choose(k, size());
        for (int i = 0; i < k; i++) {
            X.add(get(idx[i]));
        }
        return X;
    }
}