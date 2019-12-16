package dec2019.bronze;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CowLineUp {

    private static Map<String, List<String>> rules = new HashMap<>();
    private static List<String> names = new ArrayList<>();

    public static void main(String[] args) {
        populateRules();
        List<String> current = new ArrayList<>();
        List<List<String>> results = new ArrayList<>();
        dfs(current, results);
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter("./lineup.out"));
            for(String s : results.get(0)){
                out.write(s);
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void dfs(List<String> current, List<List<String>> results){
        if(results.size()==1){
            return;
        }
        if(current.size()==names.size()){
            if(executeRules(current)) {
                List<String> result = new ArrayList<>(current);
                results.add(result);
            }
            return;
        }

        for(int i=0; i<names.size(); i++){
            if(current.contains(names.get(i))) continue;
            current.add(names.get(i));
            dfs(current, results);
            current.remove(current.size()-1);
        }
    }

    private static boolean executeRules(List<String> current) {
        int pos=-1;
        for(String key : rules.keySet()){
            // find position of the key
            for(int i=0; i<current.size(); i++){
                if(current.get(i).equalsIgnoreCase(key)) {
                    pos = i;
                    break;
                }
            }

            // execute rules
            List<String> values = rules.get(key);
            if(pos == -1) return false;
            if(pos == 0){
                if(values.size()>1) return false;
                else if(!current.get(1).equalsIgnoreCase((values.get(0)))) return false;
            } else if(pos==names.size()-1){
                if(values.size()>1) return false;
                else if(!current.get(names.size()-2).equalsIgnoreCase((values.get(0)))) return false;
            } else{
                if(values.size()>2) return false;
                else if(!values.get(0).equalsIgnoreCase(current.get(pos-1))
                        && !values.get(0).equalsIgnoreCase(current.get(pos+1))) return false;
                else if(values.size()==2 && !values.get(1).equalsIgnoreCase(current.get(pos-1))
                        && !values.get(1).equalsIgnoreCase(current.get(pos+1))) return false;
            }
        }
        return true;
    }

    public static void populateRules(){
        names.add("Bessie");
        names.add("Buttercup");
        names.add("Belinda");
        names.add("Beatrice");
        names.add("Bella");
        names.add("Blue");
        names.add("Betsy");
        names.add("Sue");

        Collections.sort(names);

        File file = new File("/Users/jling/git/personalizedpnservice-jling/personalizedpnservice/src/main/java/com/ebay/app/personalizedpn/dedupe/lineup.in");
        Scanner scanner;
        try {
            scanner = new Scanner(file, StandardCharsets. UTF_8. name());
            Scanner scanner2 = new Scanner(scanner.nextLine());
            int n = scanner2.nextInt();
            while(scanner.hasNextLine()){
                String s = scanner.nextLine();
                String[] strs = s.split("\\s");
                int size = strs.length;
                List<String> values = rules.containsKey(strs[0])?rules.get(strs[0]):new ArrayList<String>();
                values.add(strs[size-1]);
                rules.put(strs[0], values);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
