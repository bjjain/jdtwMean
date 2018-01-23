package tools;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * Handles options. An option is a flag-value pair of the form:
 * <p>
 * <pre>
 *     -flag value
 * </pre>
 * where flag is an alphanumeric string and value is of type integer, double
 * or String.
 * <p>
 * Examples of String representations of option:
 * <ul>
 * <li> <code>"-l 0.5"</code>
 * <li> <code>"-S 2 -l 0.5"</code>
 * </ul>
 *
 * @author jain
 */
@SuppressWarnings("serial")
public class Options extends HashMap<String, String> {

    /**
     * Returns instance of <code>Options</code> specified by <code>opts</code>.
     *
     * @param opts list of options
     */
    public Options(String opts) {
        super();
        parse(opts);
    }

    /**
     * Adds options specified by <code>opts</code>
     *
     * @param opts list of options
     */
    public void add(String opts) {
        parse(opts);
    }

    /**
     * Returns all flags of this collection.
     *
     * @return array of flags
     */
    private String[] getFlags() {
        String[] flags = new String[size()];
        Set<String> keys = keySet();
        int i = 0;
        for (Iterator<String> it = keys.iterator(); it.hasNext(); ) {
            flags[i] = it.next();
            i++;
        }
        return flags;
    }

    /**
     * Returns integer value associated with <code>flag</code>. Before
     * calling this method, test existence of <code>flag</code> using method
     * <code>containsKey(String)</code>. An exception will be thrown if the
     * associated value is not a number. Double values are converted to
     * integers.
     *
     * @param flag
     * @return associated integer value
     */
    public int getInt(String flag) {
        String val = get(flag);
        return (int) Double.parseDouble(val);
    }

    /**
     * Returns double value associated with <code>flag</code>. Before
     * calling this method, test existence of <code>flag</code> using method
     * <code>containsKey(String)</code>. An exception will be thrown if the
     * associated value is not a number.
     *
     * @param flag
     * @return associated double value
     */
    public double getDouble(String flag) {
        String val = get(flag);
        return Double.parseDouble(val);
    }

    /**
     * Returns String value associated with <code>flag</code>. Before
     * calling this method, test existence of <code>flag</code> using method
     * <code>containsKey(String)</code>.
     *
     * @param flag
     * @return associated String value
     */
    public String getString(String flag) {
        return get(flag);
    }

    /**
     * Returns a string representation of this collection.
     */
    @Override
    public String toString() {
        String[] keys = getFlags();
        StringBuilder sb = new StringBuilder();
        for (String k : keys) {
            sb.append(k).append(" ").append((get(k))).append(" ");
        }
        return sb.toString();
    }

    private void parse(String opts) {
        if (opts == null) {
            return;
        }
        Scanner sc = new Scanner(opts);
        while (sc.hasNext()) {
            String flag = sc.next();
            if ((flag.length() <= 1) || !flag.startsWith("-")) {
                exit("Invalid flag format for option: %s", flag);
            }
            if (sc.hasNext()) {
                String val = sc.next();
                put(flag, val);
            } else {
                exit("Missing value for option %s.", flag);
            }
        }
        sc.close();
    }

    private void exit(String msg, String flag) {
        System.err.printf(msg, flag);
        System.err.printf("%n  usage: {-flag value}*");
        System.exit(1);
    }
}
