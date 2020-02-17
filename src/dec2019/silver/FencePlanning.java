package dec2019.silver;

import com.sun.tools.javac.util.Pair;

import java.io.*;
import java.util.*;

public class FencePlanning {
    int m, n;
    List<Pair<Integer, Integer>> cows;
    List<Set<Integer>> moos;
    Map<Integer, List<Integer>> borders; // minX, minY, maxX, maxY

    public void populate(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        m = scanner.nextInt();
        n = scanner.nextInt();
        for(int i=0; i<m; i++){
            cows.add(new Pair<>(scanner.nextInt(), scanner.nextInt()));
        }
        for(int i=0; i<n-1; i++){
            int cow1 = scanner.nextInt();
            int cow2 = scanner.nextInt();
            boolean existingMoo = false;
            for(int j=0; j<moos.size(); j++){
                Set<Integer> moo = moos.get(j);
                if(moo.contains(cow1)){
                    moo.add(cow2);
                    existingMoo = true;
                    updateBorders(j, cow1, cow2);
                    break;
                }
                if(moo.contains(cow2)){
                    moo.add(cow1);
                    existingMoo = true;
                    updateBorders(j, cow1, cow2);
                    break;
                }
            }
            if(!existingMoo){
                Set<Integer> newMoo = new HashSet<>();
                newMoo.add(cow1);
                newMoo.add(cow2);
                moos.add(newMoo);
                updateBorders(moos.size()-1, cow1, cow2);
            }
        }
    }

    private void updateBorders(int j, int cow1, int cow2) {
        List<Integer> border = borders.get(j);
        int minX = Math.min(cows.get(cow1).fst, cows.get(cow2).fst);
        int maxX = Math.max(cows.get(cow1).fst, cows.get(cow2).fst);

        int minY = Math.min(cows.get(cow1).snd, cows.get(cow2).snd);
        int maxY = Math.max(cows.get(cow1).snd, cows.get(cow2).snd);
        if(border==null){
            List<Integer> newBorder = new ArrayList<>();
            newBorder.add(minX);
            newBorder.add(minY);
            newBorder.add(maxX);
            newBorder.add(maxY);
            borders.put(j, newBorder);
        }
    }

    public int calculateMinFence() {
        int minLength=999999;
        int minHeight=999999;
        for(Integer key : borders.keySet()){
            List<Integer> border = borders.get(key);
            minLength = Math.min(minLength, border.get(2)-border.get(0));
            minHeight = Math.min(minLength, border.get(3)-border.get(1));
        }

        return 2*minHeight+2*minLength;
    }

    public static void main(String[] args){
        FencePlanning fp = new FencePlanning();
        try {
            fp.populate("./fenceplan.in");
            int minPerimeter = fp.calculateMinFence();

            BufferedWriter out = new BufferedWriter(new FileWriter("fenceplan.out"));
            out.write(minPerimeter);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
