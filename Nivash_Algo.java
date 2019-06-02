import java.util.Stack;

public class Nivash_Algo {
	/**
	 * the basic algorithm of cycle detection with stack
	 * it works on O(n)
	 * 
	 */
	interface Foo<T>{  // function we work on 
		 T f( T x);	
	}
	
	static class StacktNodes{ // what gets into the stack
		 public int x;// value of F(xi)
		public int time;// the index ,when it appeared
	public StacktNodes(int x, int time) {
		this.x=x;
		this.time= time;
	}	
	}
	/**
	 * detects if the sequence is  become periodic
	 * and finds the length of the cycle
	 * @param n the length of the sequence
	 * @param x0 the starting point
	 * @param f the function 
	 * @return the length of the cycle or 0 if there is no repetition  
	 */
	
	public static  int  CycleDetection(int n, int x0, Foo<Integer> f) {
		 int x= x0;
		 int time=1;
		 StacktNodes X = new StacktNodes(x,0);
		 Stack<StacktNodes> S = new Stack<>();
		 S.push(X);
		 x=  f.f(x);
		 boolean flag= true;
		 while(flag) {//O(n)
			if(x==S.peek().x&&!S.isEmpty()) {
				return time- S.peek().time; //the length of the cycle
			}
			while(x<S.peek().x&&!S.isEmpty()) {//O(1)
				S.pop();// until we find s.x that is smaller then x
				if(x==S.peek().x&&!S.isEmpty()) {
					return time- S.peek().time; 
				}
			}
			
			S.push(new StacktNodes(x, time));
			x=(Integer) f.f(x);
			time++;
			if(S.size()==n) {
				System.out.println("no no repetition was detected");
				return 0;//the sequence has not become periodic 
			}
		 }
		 
		return 0;
	}
	

	public static void main(String[] args) {
		Foo<Integer>foo= (Integer x)->{
		int ans= (x+1)%4;
			return ans;
		};
		
		int cycle= CycleDetection(5, 0, foo);
		System.out.println("the length of the cycle is: "+cycle);
		

	}

}
