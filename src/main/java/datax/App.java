package datax;


import oi.thekraken.grok.api.Grok;
import oi.thekraken.grok.api.Match;
import oi.thekraken.grok.api.exception.GrokException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws GrokException, IOException {


        File file = new File("titles_copy_and_paste.txt");
        List<String> lines = FileUtils.readLines(file, "UTF-8");
        Grok g = new Grok();
        g.addPatternFromFile("/Users/crizz/Development/Java/playground/DataXWebDataExtraction/pattern");
//        g.compile("<td class=\"title\"><span class=\"deadmark\"></span><a href=\"%{DATA}\">%{DATA:title}</a>");
        g.compile("^%{DATA:title}.?\\(%{DATA:url}\\)");


        for (String s : lines) {
//            System.out.println(s);
            Match gm = g.match(s);
            gm.captures();
            if (gm.toMap().get("title") != null) {
                System.out.println(gm.toMap().get("title") + "#" + gm.toMap().get("url"));
            }
        }
    }
}
