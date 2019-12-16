package dec2019.bronze;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CowGymns {
    private static int[][] sessionPerf;
    private static int[][] cowsPerf;
    private static int k;
    private static int n;

    public static void main(String[] args) {
        populateInput();
        List<Integer> current = new ArrayList<>();
        List<List<Integer>> results = new ArrayList<>();

        dfs(current, results, 0);
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter("gymnastics.out"));
            out.write(String.valueOf(results.size()));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void dfs(List<Integer> current, List<List<Integer>> results, int pos){
        if(current.size()==2){
            if(consistent(current)) {
                List<Integer> result = new ArrayList<>(current);
                results.add(result);
            }
            return;
        }

        for(int i=pos; i<n; i++){
            current.add(i);
            dfs(current, results, i+1);
            current.remove(current.size()-1);
        }
    }

    private static boolean consistent(List<Integer> current) {
        int cow1 = current.get(0);
        int cow2 = current.get(1);

        boolean higherRank=false;
        boolean lowerRank=false;

        for(int i=0; i<k; i++){
            if(cowsPerf[cow1][i] < cowsPerf[cow2][i]) higherRank = true;
            if(cowsPerf[cow1][i] > cowsPerf[cow2][i]) lowerRank = true;
        }

        return (!higherRank || !lowerRank);
    }

    public static void populateInput(){
        File file = new File("/Users/jling/git/personalizedpnservice-jling/personalizedpnservice/src/main/java/com/ebay/app/personalizedpn/dedupe/gymnastics.in");
        Scanner scanner;
        try {
            scanner = new Scanner(file, StandardCharsets. UTF_8. name());
            Scanner scanner2 = new Scanner(scanner.nextLine());
            k = scanner2.nextInt();
            n = scanner2.nextInt();
            sessionPerf = new int[k][n];
            cowsPerf = new int[n][k];

            for(int i=0; i<k; i++){
                scanner2 = new Scanner(scanner.nextLine());
                for(int j=0; j<n; j++){
                    sessionPerf[i][j] = scanner2.nextInt();
                    cowsPerf[sessionPerf[i][j]-1][i] = j;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
