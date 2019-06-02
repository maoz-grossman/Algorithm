import java.util.Stack;

public class RectaOfOne {
	
	
	/**
	 * Gets an histogram and finds the biggest overlap between its columns 
	 * Complexity: O(N)
	 * @param h
	 * @return
	 */
	public static int stackArea(int h[]) {
	int maxArea=1;
	Stack<Integer>s =new Stack<>();
	s.push(0);
	for(int i=1;i<h.length;i++) {
		int area;
		while(h[s.peek()]>h[i]&&!s.empty()) {
			int top = s.pop();
			if(s.empty()) { 
				area= h[top]*(i);
				if(area>maxArea)
					maxArea=area;
				break;
			}	
			else {
				area= h[top]*(i-s.peek());
				if(area>maxArea) {
					maxArea=area;
				}
			}
				
		}
		s.push(i);
	}
	
	return maxArea;
	}
	
	/**
	 * Finds the biggest rectangular of 1's in the matrix
	 * complexity: O(m*n)
	 * @param mat the matrix 
	 * @return max area of the rectangular
	 */
	public static int rectaArea(int mat[][]) {
	int m= mat.length;//cols
	int n= mat[0].length;//raws
	int	maxArea=1;
	int help[]= new int[n];
	for(int i=0;i<m;i++) {//O(m*n+m*n)=O(m*m)
		for (int j =0; j<n; j++) {//O(n)
			if(mat[i][j]==0)
				help[j]=0;
			else help[j]++;
		}
		int area=stackArea(help);//O(n)
		if(area>maxArea) {
			maxArea=area;
		}
	}
	return maxArea;
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
		//Example:
		int [][]mat= {{1,1,1,0,0},{1,1,0,1,1},{1,1,1,1,0},{1,1,1,1,0},{1,1,1,1,1},{0,1,1,0,0}};
		print_mat(mat);
	int max=rectaArea(mat);
	System.out.printf("\nthe max area is: %d",max);
		
	}

}
