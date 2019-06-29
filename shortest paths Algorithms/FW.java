
//---------------------------------------------------------------------------------------------------
//**************************************************************
//                                               Floyd-Warshall                                                                                    
// ______________________________________________________________________
//  Given a matrix representing the graph,                                                                     
//  Floyd-Warshall's algorithm is an algorithm                                                                
//  for finding shortest paths in a weighted graph with                                                   
//  positive or negative edge weights                                                                           
//  (but with no negative cycles).                                                                                  
//  The Floyd–Warshall algorithm compares all possible paths                                        
//  through the graph between each pair of vertices.                                                     
//  It is able to do this with O (|V|^3) comparisons in a graph.                                      
//                                                                                                                            
//**************************************************************
//---------------------------------------------------------------------------------------------------




public class FW {
	final static public int infinity= Integer.MAX_VALUE;
	final static public String infinity_symbol="∞";
	private int[][] dist;
	//private String[][]path;
	private int  n;
	
	FW(int[][]w){
		n=w.length;
		//path= new String[n][n];
		dist=new int[n][n];
		for (int i=0;i<n;i++)
			for(int j=0;j<n;j++) {
				dist[i][j]=w[i][j];
				if(i==j)
					dist[i][j]=0;
			}	
		
		Floyd_Warshall();
	}
	
	
	     //---------------------------------//
                  // Building the distance matrix // 
	   //---------------------------------//
		
	
	private void Floyd_Warshall(){
		
		for(int k=0;k<n;k++)
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++) 
					if(dist[i][k]!=infinity&&dist[k][j]!=infinity) {
						dist[i][j]=Math.min(dist[i][j],(dist[i][k]+dist[k][j]));
					}
	}
	public int[][] get_distance_Matrix(){
		return dist;
	}
	
	
	
	
	/**
	 * print function
	 * it's not part of the original algorithm
	 */
	
	public String  toString() {
		String s="";
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(dist[i][j]==infinity)
					s+=infinity_symbol+" ";
				else
				s+=dist[i][j]+" ";
			}
			s+="\n";
		}
			return s;		
	}
	
	
	
	
	
	
	     //--------------------//
                  //        main          //
	   //--------------------//
		

	public static void main(String[] args) {
		System.out.println("\n-------example---------\n");
		int[][]w= {{0,1,6,infinity},{infinity,0,4,1},{infinity,infinity,0,infinity},{infinity,infinity,1,0}};
		FW Floyd_Warshall= new FW(w);
		System.out.println(Floyd_Warshall);
		
		System.out.println("\n---wikipedia example---\n");
		int[][]wiki= {{0,infinity,-2,infinity},{4,0,3,infinity},{infinity,infinity,0,2},{infinity,-1,infinity,0}};
		FW Floyd_Warshall2= new FW(wiki);
		System.out.println(Floyd_Warshall2);
	}

}
