//***************************************************************//
//                           Graph                               //   
//_______________________________________________________________//
// The is given as a vector of LinkedList                        //
// each LinkedList contains main vertex (Adj[v]),                //
// and all vertices that are connected to it.                    //
// it helps in some algorithms such as Dijkstra,BFS,DFS etc      //
//***************************************************************//
//---------------------------------------------------------------//





import java.util.LinkedList;
import java.util.Vector;

public class Graph {
	
	final static public int infinity= Integer.MAX_VALUE;
	
	
	//-----------------//
	// 	vertex class   //
	//-----------------//
	public class Vertex{
		 private int name;
		 private int weight;
		 public Vertex(int _name){
			 name= _name;
			 weight=1;
		 }
		 public Vertex(int _name, int _weight) {
			 weight= _weight;
			 name=_name;
			
		 }
		
		public int name() {
			return name;
		}
		public int weight() {
			return weight;
		}
		
		public String toString() {
			return "[name:"+name+",weight:"+weight+"]";
		}
	}
	
	
	    //-----------------//
		// 	Graph methods  //
		//-----------------//
	
	// adjacent vertices list 
	public Vector<LinkedList<Vertex>> Adj;
	
	public Graph(int graph[][]) {
		Adj= new Vector<LinkedList<Vertex>>(graph.length);

		for(int i=0;i < graph.length;i++) {
			LinkedList<Vertex> list= new LinkedList<>();
			for(int j=0;j< graph.length; j++) {
				if(graph[i][j]!=infinity) {
					Vertex v= new Vertex(j,graph[i][j]);
					list.add(v);
				}
			}
			Adj.add(list);
		}
	}
	
	public String toString() {
		String s="";
		for(LinkedList<Vertex>list:Adj) {
			for(Vertex v:list) {
				s+=v+"-> ";
			}
			s+="\n";
		}
		return s;
	}

	
	public static void main(String[] args) {
		int[][]w= {{0,1,6,infinity},{infinity,0,4,1},{infinity,infinity,0,infinity},{infinity,infinity,1,0}};
		Graph g=new Graph(w);
		System.out.println(g);
	}

}
