//------------------------------------------------------------//
//************************************************************//
//                 The Bottle problem2                        //   
//___________________________________________________________ //
// Let's say we have the following problem:                   //
// We have two water tanks,                                   //
// one of M liters, and the other of N liters                 //
// and let's assume that M> N.                                //
// There is only one container that can fill the two tanks    //
// and it is M+N liters.                                      // 
// We need to fill a quantity of D (D <M)                     //
// in the shortest way possible.                              //
// in the previous part of the bottle problem we show only    //
// a way to see the connection between the nodes              //
// now we will see the shortest path to node D                //
//************************************************************//
//------------------------------------------------------------//


public class Bottle_2 {
	
	
	
	    //--------------------------//
	   //  The  nodes of the graph //
	  //--------------------------//
	
	
	public static class Bottles{
		// A pair of bottles looks like (A,B)
		 private int a,b;
		public Bottles(int m,int n) {
			this.b=n;
			this.a=m;
		}
		public String toString() {
			return "("+a+","+b+")";
		}
	}

	static int infinity = Integer.MAX_VALUE;
	
	private static int index(int i, int j, int n){
		return (n+1)*i + j;
	}
	
	
	     //--------------------------//
		//    Building the graph    // 
	   //--------------------------//
	
	private static int[][] initMatrBottleWeight(int m, int n){
		int dim = (m+1)*(n+1); // matrix dimension
		int [][]mat = new int[dim][dim];
		for (int i=0; i<dim; i++){
			for (int j=0; j<dim; j++){
				mat[i][j]= infinity;
			}
		}
		 /**
		 * using the following method:
		 * we compare two nodes(the pair(A,B))
		 * i1- represents the node we have
		 * index(...) or i2- represent the node we compare to
		 * and mat[i1][index(...)] is the intersection between the values
		 * and we get true in the same index if :
		 * we can empty the bottle A or B and to get the other node
		 * or if we can fill up A or B and to get the other node
		 * or we can pour from A to B or from B to A and to get the other node
		 * else  we get false
		 */
		for (int i=0; i<=m; i++){
			for (int j=0; j<=n; j++){
				int ind = index(i,j,n);
				mat[ind][index(0,j,n)]=1;//we empty A
				mat[ind][index(i,0,n)]=1;//we empty B
				mat[ind][index(i,n,n)]=1;//we fill B up
				mat[ind][index(m,j,n)]=1;//we fill A up
				int i1=index(Math.max(0,i+j-n),Math.min(n,i+j) ,n);//we pour A -> B
				mat[ind][i1]=1;
				i1 = index(Math.min(m,i+j),Math.max(0,j+i-m) ,n);//we pour A <- B
				mat[ind][i1]=1;
			}
		}
		for (int i = 0; i < mat.length; i++) {
			mat[i][i] = infinity;
		}
		return mat;		
	}
	
	
	
		 //--------------------//
		//        main        //
	   //--------------------//
	
	public static void main(String[] args) {
		int m=3, n=5;//m-the first bottle, n-the second bottle
		int D=4; //D is (0,4)
		System.out.println("A = "+ m + ",  B = "+n+", D="+D+"\n");
		int w[][]=initMatrBottleWeight(m, n);
		int ind=0;
		 Bottles[]index=new Bottles[(m+1)*(n+1)];
		
		 for (int i=0; i<=m; i++)
			for (int j=0; j<=n; j++) 
				index[ind++]=new Bottles(i,j);
			
		System.out.println();
		Graph g=new Graph(w);
		Graph.Vertex _s= g.Adj.get(0).element();// D=(0,4)
		BFS bfs=new BFS(g, _s);
		String pathBFS = bfs.Get_path(g.Adj.get(D).element());
		System.out.println("---the BFS shortest path---\n");
		System.out.println(pathBFS+"\n\n");
		String strMat[]=pathBFS.split("->");
		String path="";
		for(String str:strMat) {
			int i1=Integer.parseInt(str);
			path+=index[i1]+"->";
			
		}
		//cut '->' from the end  of the string
		path=path.substring(0, path.length() - 2);
		System.out.println("-----how it looks like-----\n");
		System.out.println(path);
		
	}
	
}
