package app;

import core.Mean;
import core.Patterns;
import tools.Reader;

public class DTWMean {

    //### OPTIONS ##############################################################

    //--- dir to directory containing data time series datasets
    String dir = "./data/";

    //--- directory containing dataset
    String data = "Coffee";

    //--- options
    String opts = "-A SSG -T 5 -t 2 -o 1";

    //#########################################################################

    public static void main(String[] args) {
        DTWMean app = new DTWMean();
        app.run(args);
    }

    public void run(String[] args) {
        // parse commandline
        parse(args);

        // load data
        Patterns X = getData(dir, data);

        // compute mean
        double v = 0;
        double[][] x = X.getSequences();
        Mean mean = new Mean(opts);
        mean.compute(x);
        v += mean.variation();

        // print results
        System.out.println("Result:");
        System.out.println("\t options   = " + mean.toString());
        System.out.format("\t variation = %7.4f%n", v);
    }

    private Patterns getData(String path, String data) {
        String prefix = path + data + "/" + data;
        Patterns X = Reader.load(prefix + "_TRAIN");
        X.addAll(Reader.load(prefix + "_TEST"));
        return X;
    }

    private void parse(String[] args) {
        if(args == null ||args.length == 0) {
            return;
        }
        if(args.length < 2) {
            System.err.println("Error: At least two parameters are required: directory and name of UCR dataset.");
        }
        dir = args[0];
        data = args[1];

        opts = "";
        for(int i = 2; i < args.length; i++) {
            opts += args[i] + " ";
        }
    }
}
