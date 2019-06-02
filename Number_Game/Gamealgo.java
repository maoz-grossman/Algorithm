
public class Gamealgo extends Thread{
	private int game[];
	public Gamealgo(int mat[]) {
	this.game=mat;
	}
	private int[][] buildMat() {
		int n= game.length;
		int [][] options= new int[game.length][game.length];
		for(int i=0;i<game.length;i++) {
			options[i][i]=game[i];
		}
		for(int i=1;i<game.length;i++) {
			options[i-1][i]= Math.max(options[i-1][i-1], options[i][i]);
		}
		for(int i=n-2;i>=0;i--)
			for(int j=i+1;j<n;j++) {
				int x= game[i]-options[i+1][j];
				int y= game[j]-options[i][j-1];
				options[i][j]= Math.max(x, y);
				
			}
		return options;
	}
	@Override
	public void run() {
		int options[][]=  buildMat();
		int end=game.length-1;
		int start=0;
		int sum=0;
		int foeSum=0;
		int choose;
		int counter;
//		int j=0;
		for( counter=0;counter<game.length/2+1;counter++) {
			System.out.printf("*******round %d*******\n",counter+1);
			System.out.println("the numbers are: ");
			for(int i=start;i<end+1;i++)
				System.out.print(game[i]+"  ");
			if(start==end) {
				foeSum+=game[end];
				System.out.println("\n end..");
				break;
			}
			System.out.printf("\nchoose a number: %d or %d\n",game[start],game[end]);
			do {
				choose=MyConsole.readInt("");
			}while (choose!=game[start]&&choose!=game[end]);
			if(choose==game[start]) start++; else  end--; 
			foeSum+=choose;
			int Ichoose = 0;
			if(start==end) {
				sum+=game[end];
				System.out.printf("\nI chose %d\n",game[end]);
			}
			else {
				if(game[start]-options[start+1][end]>game[end]-options[start][end-1])
				Ichoose= start;
				else Ichoose=end;
				sum+=game[Ichoose];
				System.out.printf("\nI chose %d\n",game[Ichoose]);
				if(Ichoose==start) start++; else end--;
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cleanscreen();
				
		}
		System.out.printf("Game over: you got %d and i got %d",foeSum,sum);
		
		
	}
	
	public void cleanscreen() {
		for (int i = 0; i < 48; ++i) System.out.println();
	}
	
	public static void print_mat(int mat[][]) {
		int	m=mat.length;
			int n= mat[0].length;
			for(int i=0; i<m; i++) {
				for(int j=0;  j< n; j++)
					System.out.print("  "+mat[i][j]);
				System.out.println();
			}
		}

	public static void main(String[] args) {
		int mat[]= {1,4,3,7,8,9,11,12,10};
		Gamealgo game= new Gamealgo(mat);
//		int matrix[][]=game.buildMat();
//		print_mat(matrix);
		System.out.println();
		game.start();

	}

}
