package dec2019.bronze;

import java.io.*;
import java.util.Scanner;

public class WhereAmI {
    public static boolean detectDuplicate(String origStr, String subStr){
        int idx1 = origStr.indexOf(subStr);
        String str2 = origStr.substring(idx1+1);
        if(str2.indexOf(subStr) == -1)
            return false;
        else
            return true;
    }

    public static void main(String[] main){
        try {
            Scanner scanner = new Scanner(new File("/Users/jling/git/usaco/src/dec2019/bronze/whereami.in"));
//            Scanner scanner = new Scanner(new File("./whereami.in"));
            int size = Integer.parseInt(scanner.nextLine().trim());
            String s = scanner.nextLine().trim();
            int[] numsOfUniquePattern = new int[26];

            for(int i=0; i<size; i++){
                for(int j=1; i+j<size; j++){
                    String substr = i+j==s.length()?s.substring(i):s.substring(i, i+j);
                    if(!detectDuplicate(s, substr)){
                        int currentcount = numsOfUniquePattern[s.charAt(i) - 'A'];
                        numsOfUniquePattern[s.charAt(i) - 'A']=Math.max(currentcount, j);
                        break;
                    }
                }
            }

            int k=0;
            for(int i=0; i<26; i++){
                k = Math.max(numsOfUniquePattern[i], k);
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter("./whereami.out"));
            bw.write(String.valueOf(k));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
