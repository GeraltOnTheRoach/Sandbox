package breadcrumbGenerator;

import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

class BreadcrumbGenerator{

    private static final String MID = "<a href=\"/%s/\">%s</a>";
    private static final String END = "<span class=\"active\">%s</span>";
    private static final List<String> IGNORED = List.of("the","of","in","from","by","with","and", "or", "for", "to", "at", "a");

    static String generate_bc(String url, String separator) {

        StringTokenizer reader = new StringTokenizer (strip(url), "/");
        if(reader.countTokens() == 1) return String.format(END, "HOME");

        StringJoiner result = new StringJoiner(separator, "<a href=\"/\">HOME</a>" + separator, "");
        reader.nextToken();

        StringJoiner globalState = new StringJoiner("/");
        while (reader.countTokens() > 1){
            String current = reader.nextToken();
            globalState.add(current);
            result.add(String.format(MID, globalState, acronymizing(current)));
        }

        String last = reader.nextToken();
        return result.add(String.format(END, acronymizing(removeDelim(last, '.'))))
                .toString();
    }

    private static String strip(String url) {
        String deleteHttp = url.replace("https://", "").replace("http://", "");
        String deleteIndex = deleteHttp.lastIndexOf('/') > 0
                && deleteHttp.substring(deleteHttp.lastIndexOf('/'), deleteHttp.length()-1).contains("index") ?
                deleteHttp.substring(0, deleteHttp.lastIndexOf('/')) : deleteHttp;
        return deleteIgnoredParts(deleteIndex);
    }

    private static String deleteIgnoredParts(String str){
        return removeDelim(removeDelim(str, '#'), '?');
    }
    private static String removeDelim(String str, char delim) {
        return str.indexOf(delim) < 0 ? str : str.substring(0, str.indexOf(delim));
    }
    private static String acronymizing(String str){
        if(str.length() > 30){
            StringTokenizer reader = new StringTokenizer(str, "-");
            StringBuilder result = new StringBuilder();
            while (reader.hasMoreTokens()){
                String current = reader.nextToken();
                if(!IGNORED.contains(current)) result.append(current.charAt(0));
            } return result.toString().toUpperCase();
        }
        else return str.replace('-', ' ').toUpperCase();
    }
}