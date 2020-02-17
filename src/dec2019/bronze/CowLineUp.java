package dec2019.bronze;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CowLineUp {
    private static List<String> cownames = new ArrayList<>();
    private static Map<String, List<String>> neighbors = new HashMap<>();

    public static void dfs(List<String> current, List<List<String>> results){
        if(results.size()==1){
            return;
        }
        if(current.size()== cownames.size()){
            if(executeRules(current)) {
                List<String> result = new ArrayList<>(current);
                results.add(result);
            }
            return;
        }

        for(int i = 0; i< cownames.size(); i++){
            if(current.contains(cownames.get(i))) continue;
            current.add(cownames.get(i));
            dfs(current, results);
            current.remove(current.size()-1);
        }
    }

    private static boolean executeRules(List<String> current) {
        int pos=-1;
        for(String key : neighbors.keySet()){
            // find position of the key
            for(int i=0; i<current.size(); i++){
                if(current.get(i).equalsIgnoreCase(key)) {
                    pos = i;
                    break;
                }
            }

            // execute rules
            List<String> values = neighbors.get(key);
            if(pos == -1) return false;
            if(pos == 0){
                if(values.size()>1) return false;
                else if(!current.get(1).equalsIgnoreCase((values.get(0)))) return false;
            } else if(pos== cownames.size()-1){
                if(values.size()>1) return false;
                else if(!current.get(cownames.size()-2).equalsIgnoreCase((values.get(0)))) return false;
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

    public static void populateNeighbors(){
        cownames.add("Bessie");
        cownames.add("Buttercup");
        cownames.add("Belinda");
        cownames.add("Beatrice");
        cownames.add("Bella");
        cownames.add("Blue");
        cownames.add("Betsy");
        cownames.add("Sue");
        Collections.sort(cownames);

        File file = new File("/Users/jling/git/usaco/src/dec2019/bronze/lineup.in");
//        File file = new File("lineup.in");
        Scanner scanner;
        try {
            scanner = new Scanner(file, StandardCharsets.UTF_8.name());
            Scanner scanner2 = new Scanner(scanner.nextLine());
            int n = scanner2.nextInt();
            while(scanner.hasNextLine()){
                String s = scanner.nextLine();
                String[] strs = s.split("\\s");

                List<String> values;
                if(neighbors.containsKey(strs[0]))
                    values = neighbors.get(strs[0]);
                else
                    values = new ArrayList<String>();
                values.add(strs[strs.length-1]);
                neighbors.put(strs[0], values);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        populateNeighbors();
        List<String> curList = new ArrayList<>();
        List<List<String>> results = new ArrayList<>();
        dfs(curList, results);
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
}
