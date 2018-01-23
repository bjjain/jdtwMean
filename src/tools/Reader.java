package tools;

import core.Pattern;
import core.Patterns;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Implements reader for time series data. Each line of the data file must have
 * the following format:
 * <p>
 * <pre>
 *    y x_1 x_2 ... x_n
 * </pre>
 * The first entry is an integer representing a class label. The following
 * entries are real-valued elements of a time series. All entries must be
 * separated by a space.
 *
 * @author jain
 */
public class Reader {

    /**
     * Returns a list of labeled time series contained in the specified file.
     * The first element of each time series is its class label.
     *
     * @param file name of file
     * @param lf   true if labels come first
     * @return list of labeled time series
     */
    private static Patterns load(String file, String regexp, boolean lf) {
        BufferedReader br = null;
        String line;

        Patterns X = new Patterns(file);
        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                line = line.trim();
                if (line.equals("")) {
                    continue;
                }
                String[] row = line.split(regexp);
                int index = lf ? 0 : row.length - 1;
                int label = (int) Double.parseDouble(row[index]);
                double[] x;
                if (lf) {
                    x = getFeatures_lf(row);
                } else {
                    x = getFeatures_ll(row);
                }
                X.add(new Pattern(x, label));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return X;
    }

    /**
     * Returns a list of labeled time series contained in the specified file.
     * The first element of each line is a class label. Values within a line are
     * by blank spaces. This format is used for loading datasets in the format
     * of the UCR time series datasets.
     *
     * @param filename name of file
     * @return list of labeled time series
     */
    public static Patterns load(String filename) {
        return load(filename, "\\s+", true);
    }

    private static double[] getFeatures_lf(String[] row) {
        int n = row.length;
        double[] x = new double[n - 1];
        for (int i = 1; i < n; i++) {
            x[i - 1] = Double.valueOf(row[i]);
        }
        return x;
    }

    private static double[] getFeatures_ll(String[] row) {
        int n = row.length - 1;
        double[] x = new double[n + 1];
        for (int i = 0; i < n; i++) {
            x[i] = Double.valueOf(row[i]);
        }
        return x;
    }
}