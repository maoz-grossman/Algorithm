import java.util.LinkedList;
import java.util.Queue;


public class BFS {
	private String color[];
	private Graph.Vertex parent[];
	private int dist[];
	private Queue<Graph.Vertex>Q;
	private Graph.Vertex source;
	
	
	public BFS(Graph G,Graph.Vertex _s) {
		int n=G.Adj.size();
		color=new String[n];
		parent=new Graph.Vertex[n];
		dist=new int[n];
		for(int i=0;i<n;i++) {
			color[i]="WHIT";
			dist[i]=G.infinity;
			parent[i]=null;
		}
		Q= new LinkedList<Graph.Vertex>();
		source=_s;
		color[source.name()]="GRAY";
		dist[source.name()]=0;
		parent[source.name()]=null;
		Q.add(source);
		initiate(G);
	}
	
	private void initiate(Graph G) {
		while(!Q.isEmpty()) {
			Graph.Vertex u= Q.poll();
			for(Graph.Vertex v: G.Adj.get(u.name())) {//like Adj[u]
				if(color[v.name()].equals("WHIT")) {
					color[v.name()]="GRAY";
					dist[v.name()]=dist[u.name()]+1;
					parent[v.name()]=u;
					Q.add(v);
				}
			}
			color[u.name()]="BLACK";
		}
		
	}
	
	
	public String Get_path(Graph.Vertex v) {
		String path="";
		Graph.Vertex par=parent[v.name()];
		if(par==null)return "No path from source to v";
		path+=par.name()+"->"+v.name();
		while(source.name()!=par.name()) {
			par=parent[par.name()];
			path=par.name()+"->"+path;
		}
		return path;
	}
	
	
	
	public static void main(String[] args) {
		int[][]w= {{0,1,6,Graph.infinity},{Graph.infinity,0,4,1},{Graph.infinity,Graph.infinity,0,Graph.infinity},{Graph.infinity,Graph.infinity,1,0}};
		Graph g=new Graph(w);
		Graph.Vertex _s= g.Adj.get(0).element();
		BFS bfs=new BFS(g, _s);
		String path = bfs.Get_path(g.Adj.get(2).element());
		System.out.println(path);
	}

}
