package dec2019.silver;

import java.io.*;
import java.util.*;

class Farm {
	char milkType;
	List<Integer> neighbors;

	public Farm(char milkType){
		this.milkType = milkType;
		neighbors = new ArrayList<>();
	}
}
public class MilkVisits {
	int n, m;
	int[] preference;
	Farm[] farms;

	public void run() throws IOException {
		Scanner scanner = new Scanner(new File("/Users/jling/git/usaco/src/dec2019/silver/milkvisits.in"));
		n = scanner.nextInt();
		m = scanner.nextInt();
		preference = new int[m];
		farms =  new Farm[n+1];

		scanner.nextLine();
		String milktypestr = scanner.nextLine();
		System.out.print(milktypestr);
		for(int i=1; i<=n; i++) {
			farms[i] = new Farm( milktypestr.charAt(i-1));
		}
		for(int i=0; i<n-1; i++){
			int begin = scanner.nextInt();
			int end = scanner.nextInt();
			farms[begin].neighbors.add(end);
			farms[end].neighbors.add(begin);
		}

		StringBuilder sb = new StringBuilder();
		scanner.nextLine();
		for(int i=0; i<m; i++){
			String str = scanner.nextLine();
			String[] tokens = str.split(" ");
			int begin = Integer.parseInt(tokens[0]);
			int end = Integer.parseInt((tokens[1]));
			char milkType = tokens[2].charAt(0);
//			int begin = scanner.nextInt();
//			int end = scanner.nextInt();
//			char milkType = scanner.nextLine().charAt(0);
			boolean isHappy = bfs(begin, end, milkType);
			sb.append(isHappy?'1':'0');
		}
		scanner.close();

		BufferedWriter out = new BufferedWriter(new FileWriter("/Users/jling/git/usaco/src/dec2019/silver/milkvisits.out"));
		out.write(sb.toString());
		out.close();
	}

	private boolean bfs(int begin, int end, char milkType){
		boolean[] visited = new boolean[n+1];
		boolean[] isRightMilkType = new boolean[n+1];

		Queue<Integer> queue = new LinkedList<>();
		queue.add(begin);

		while(!queue.isEmpty()){
			int pos = queue.poll();
			visited[pos]=true;
			if(!isRightMilkType[pos] && farms[pos].milkType==milkType) {
				isRightMilkType[pos]=true;
			}
			if(pos == end){
				return isRightMilkType[pos];
			}
			for(int i : farms[pos].neighbors){
				if(!visited[i]) {
					isRightMilkType[i] = isRightMilkType[pos];
					queue.add(i);
				}
			}
		}
		return false;
	}

	public static void main(String[] args){
		MilkVisits milkVisits = new MilkVisits();
		try {
			milkVisits.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
