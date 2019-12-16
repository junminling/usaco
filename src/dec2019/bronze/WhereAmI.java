package dec2019.bronze;

import java.io.*;
import java.util.Scanner;

public class WhereAmI {
    public static void main(String[] main){
        try {
            Scanner scanner = new Scanner(new File("/Users/jling/git/personalizedpnservice-jling/personalizedpnservice/src/main/java/com/ebay/app/personalizedpn/dedupe/whereami.in"));
            int size = Integer.parseInt(scanner.nextLine().trim());
            String s = scanner.nextLine().trim();
            int[] numsOfUniquePattern = new int[26];

            for(int i=0; i<s.length(); i++){
                for(int j=1; i+j<s.length(); j++){
                    String substr = getsubstring(s, i, j);
                    if(!hasDupStr(s, substr)){
                        numsOfUniquePattern[s.charAt(i)-'A']=j;
                        break;
                    }
                }
            }

            int k=0;
            for(int i=0; i<26; i++){
                k = Math.max(numsOfUniquePattern[i], k);
            }
            System.out.println(k);

            BufferedWriter bw = new BufferedWriter(new FileWriter("./whereami.out"));
            bw.write(String.valueOf(k));
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getsubstring(String s, int pos, int numOfChars){
        if(pos+numOfChars==s.length()) return s.substring(pos);
        return s.substring(pos, pos+numOfChars);
    }

    public static boolean hasDupStr(String s, String pattern){
        int count = 0;
        int firstIdx = s.indexOf(pattern);
        String substr = s.substring(firstIdx+pattern.length());
        if(substr.indexOf(pattern) == -1)
            return false;
        else
            return true;
    }
}
