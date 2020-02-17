package dec2019.bronze;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GymnasticsCow {
    private static int k;
    private static int n;
    private static int[][] contestItemRanking;
    private static int[][] cowsItemRanking;

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

        boolean better=false;
        boolean worse=false;

        for(int i=0; i<k; i++){
            if(cowsItemRanking[cow1][i] < cowsItemRanking[cow2][i]) better = true;
            if(cowsItemRanking[cow1][i] > cowsItemRanking[cow2][i]) worse = true;
        }
        return (!better || !worse);
    }

    public static void readFileAndData(){
//        File file = new File("./gymnastics.in");
        File file = new File("/Users/jling/git/usaco/src/dec2019/bronze/gymnastics.in");
        Scanner scanner;
        try {
            scanner = new Scanner(file, StandardCharsets.UTF_8.name());
            Scanner scanner2 = new Scanner(scanner.nextLine());
            k = scanner2.nextInt();
            n = scanner2.nextInt();
            contestItemRanking = new int[k][n];
            cowsItemRanking = new int[n][k];

            for(int i=0; i<k; i++){
                scanner2 = new Scanner(scanner.nextLine());
                for(int j=0; j<n; j++){
                    contestItemRanking[i][j] = scanner2.nextInt();
                    cowsItemRanking[contestItemRanking[i][j]-1][i] = j;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readFileAndData();
        List<Integer> curList = new ArrayList<>();
        List<List<Integer>> results = new ArrayList<>();

        dfs(curList, results, 0);
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter("gymnastics.out"));
            out.write(String.valueOf(results.size()));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}