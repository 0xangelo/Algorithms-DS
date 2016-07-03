/******************************************************************************
 *  Compilation:  javac LinkFinder.java In.java
 *  Execution:    java LinkFinder url
 *  
 *  Downloads the web page and prints out all urls on the web page.
 *  Gives an idea of how Google's spider crawls the web. Instead of
 *  looking for hyperlinks, we just look for patterns of the form:
 *  http:// followed by any non-whitespace characters except ".
 *
 *  % java LinkFinder http://www.cs.princeton.edu
 *  http://www.w3.org/TR/REC-html40/loose.dtd 
 *  http://www.cs.princeton.edu/
 *  http://maps.yahoo.com/py/maps.py?addr=35+Olden+St&csz=Princeton,+NJ,+08544-2087
 *  http://www.princeton.edu/
 *  http://www.Princeton.EDU/Siteware/WeatherTravel.shtml
 *  http://ncstrl.cs.Princeton.EDU/
 *  http://www.genomics.princeton.edu/
 *  http://www.math.princeton.edu/PACM/
 *  http://libweb.Princeton.EDU/
 *  http://libweb2.princeton.edu/englib/
 *  http://search.cs.princeton.edu/query.html
 *
 ******************************************************************************/

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import edu.princeton.cs.algs4.*;

public class LinkFinder { 


    public static void main(String[] args) { 
        if (args.length == 1) {
            In in = new In(args[0]);
            String input = in.readAll();
    
            /*************************************************************
            *  \\S for not whitespace characters
            *  ^\" for all characters except "     (add ? to list...)
            *  && for intersection of two character classes
            *  + for one or more occurrences
            *************************************************************/
            String regexp = "((href=\")|(http))[[\\S]&&[^\"]]+";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(input);
            String prefix = args[0].substring(0, args[0].length()-1);
          
            // find and print all matches
            while (matcher.find()) {
                String saida = matcher.group();
                if (saida.startsWith("href=\"")) {
                    saida = saida.substring(6);
                    if (!saida.startsWith("http"))
                        StdOut.println(prefix + saida);
                    else
                        StdOut.println(saida);
                }
                else {
                    if (!saida.contains(".")) continue;
                        StdOut.println(saida);
                }
            }
        }
        else {
            In in = new In(args[1]);
            RedBlackBST<String, Integer> tree = new RedBlackBST<String, Integer>();
            String input = in.readAll();
            
            /*************************************************************
            *  \\S for not whitespace characters
            *  ^\" for all characters except "     (add ? to list...)
            *  && for intersection of two character classes
            *  + for one or more occurrences
            *************************************************************/
            String regexp = "((href=\")|(http))[[\\S]&&[^\"]]+";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(input);
            String prefix = args[1].substring(0, args[1].length()-1);
            
            // find and print all matches
            while (matcher.find()) {
                String saida = matcher.group();
                if (saida.startsWith("href=\"")) {
                    saida = saida.substring(6);
                    if (!saida.startsWith("http"))
                        tree.put(prefix + saida, 1);
                    else
                        tree.put(saida, 1);
                }
                else {
                    if (!saida.contains(".")) continue;
                    tree.put(saida, 1);
                }
            }
            for (String out : tree.keys())
                StdOut.println(out);
        }
    }
}
