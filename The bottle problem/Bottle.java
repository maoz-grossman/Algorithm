//------------------------------------------------------------//
//************************************************************//
//                 The Bottle Algorithm                       //   
//___________________________________________________________ //
// Let's say we have the following problem:                   //
// We have two water tanks,                                   //
// one of M liters, and the other of N liters                 //
// and let's assume that M> N.                                //
// There is only one container that can fill the two tanks    //
// and it is M+N liters.                                      // 
// We need to fill a quantity of D (D <M)                     //
// in the shortest way possible.                              //
//************************************************************//
//------------------------------------------------------------//




public class Bottle {
	
	 //-------------------------//
	// The  nodes of the graph //
       //-------------------------//
	
	public class Bottles{
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
	
	
	 //--------------------------//
	//    Building the graph    // 
       //--------------------------//
	
	
		private int n,m,dim;
		private Object[][]matt;
		public Bottle(int m,int n) {
			this.n=n;
			this.m=m;
		    dim= (m+1)*(n+1);//the dimension of the matrix
			matt= new Object[dim+1][dim+1];
			fill_matrix();
		}
		
		private int index(int i,int j,int n) {return (n+1)*i +j;}
		
		private void fill_matrix() {
			
			boolean [][]mat = new boolean[dim][dim];
			for (int i=0; i<dim; i++){
				for (int j=0; j<dim; j++){ 
					//Fills  Matt  with false
					mat[i][j]= false;
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
					int i1 = index(i,j,n);
					mat[i1][index(0,j,n)]=true;//we empty A 
					mat[i1][index(i,0,n)]=true;//we empty B
					mat[i1][index(i,n,n)]=true;//we fill B up
					mat[i1][index(m,j,n)]=true;//we fill A up 
					int i2=index(Math.max(0,i+j-n),Math.min(n,i+j) ,n);//we pour A -> B
					mat[i1][i2]=true;
					i1 = index(Math.min(m,i+j),Math.max(0,j+i-m) ,n);// we pour A <- B
					mat[i1][i2]=true;
				}
			}
			
			for (int j=0; j<dim; j++){
				mat[j][j]= false; //fills the diagonal with false 
			}
	
			
			
			int k=0;
			for (int i=0; i<=m; i++){
				for (int j=0; j<=n; j++) {
					matt[0][++k]=new Bottles(i,j);
					matt[k][0]=matt[0][k];
					
				}
			}
			//copy the boolean matrix to the main matrix
			for(int i=1;i<matt.length;i++)
				for(int j=1;j<matt.length;j++)
					matt[i][j]=mat[i-1][j-1];
		}
		
		public String toString() {
			String print=" ";
			for(int i=0;i<matt.length;i++) {
				for(int j=0;j<matt.length;j++) {
					if(i==0&&j==0)
						print+="      ";
					else if(i==0)
						print+=matt[i][j]+"	";
					else
					print+=matt[i][j]+"\t";
					
				}
				print+="\n";
			}
			return print;
		}
	
	
	
	 //--------------------//
	//        main        //
       //--------------------//
	
	
	public static void main(String[] args) {
		Bottle b= new Bottle(1,3);
		System.out.println(b);
	}

}
