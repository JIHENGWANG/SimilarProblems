package solution;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.lang.reflect.Array;
import java.util.*;



class Point {
   public int x;
   public int y;
   public Point(int x, int y) {
     this.x = x;
     this.y = y;
   }
  }

public class Line {
	
	/*
	 * Assumptions:
	 * Case 1: if array is null or empty, then there is 0 most points in a same line
	 * Case 2: if array is not empty, then we can count the slope to find how many points have same slope
	 * 			Follow up: two points are exactly the same slope
	 * 					   two points share same x has the same slope
	 * 					   two points with different x and y but (y1 - y2) / (x1 - x2) is their slope, find if there
	 * 					   is other points have same slope.
	 * 					   two points with same X and two points have same slope are different lines. we need get the max of them.
	 * 			Use hashMap to save the slope, and the number of points that share the same slope.
	 * 
	 * Maintain and return the max value of points in the same line.
	 * 
	 * Time complexity:O(n*n) Space Complexity: O(n): 
	 * 
	 */
	
	public static int mostPointsinLine(Point[] points) {
	    // Write your solution here.
		if(points == null || points.length== 0){
			return 0;
		}
		int globalMax = 0;
		for(int i = 0; i < points.length; i++){
			Point check = points[i];
			int sameXY = 0;
			int sameX = 0;
			int sameSlope = 0;
			Map<Double, Integer> map = new HashMap<> ();
			for(int j = 0; j < points.length; j++){
				Point temp = points[j];
				if(temp.x == check.x && temp.y == check.y){
					sameXY++;
				} else if(temp.x == check.x){
					sameX++;
				} else {
					double slope = ((temp.y - check.y) + 0.0) / (temp.x - check.x);
					if(map.containsKey(slope)){
						map.put(slope, map.get(slope) + 1);
					} else {
						map.put(slope, 1);
					}
					sameSlope = Math.max(sameSlope, map.get(slope));
				}
			}
			globalMax = Math.max(globalMax, Math.max(sameX, sameSlope) + sameXY);
		}
	    return globalMax;
	}
	
	/*
	 * Assumptions:
	 * Case 1: if array of points is null or empty, then the maximum set of points is zero.
	 * Case 2: if array is null empty, then we should use ((y2 - y1) + 0.0) / (x2 - x1) > 0 to find 
	 * 		   which set of points has positive slope.
	 * 		   Step 1: Sort the array by compare the value of X, so that we can only compare to y. This takes O(nlogn).
	 * 		   Trick for step 1: After sorted, if points[0].x == points[point.length - 1].x, then all elements in points share
	 * 		                     same x, then we can return 0.
	 * 		   Step 2: Use Dynamic programming to fill the 1d array and find the maximum set of points have positive points
	 * 		           maximum[i] represent the maximum set from 0 to ith points.
	 * 
	 * Induction rules: maximum[i] = Math.max(maximum[i], maximum[j]).
	 * 
	 * Maintain a global Max save the current max set.
	 * 
	 * Return global Max which is the maximum set from 0 to last point. 
	 * Time Complexity: O(nlogn) + O(n*n). Space complexity: O(n)
	 */
	
	public static int mostPointsPositiveSlope(Point[] points) {
		// Write your solution here.
		if(points == null || points.length == 0){
			return 0;
		}
		int[] maximum = new int [points.length];
		int sol = 0;
		Arrays.sort(points, new Comparator<Point>() {
			@Override
			public int compare(Point one, Point two){
				if(one.x == two.x){
					return 0;
				}
				return one.x < two.x ? -1 : 1;
			}
		});
		for(int i = 0; i < maximum.length; i++){
			for(int j = 0; j < i; j++){
				if(points[j].y < points[i].y){
					maximum[i] = Math.max(maximum[i], maximum[j]);
					
				} 
			}
			maximum[i]++;
			sol = Math.max(sol, maximum[i]);
		}
		return sol == 1 ? 0 : sol;
	}
	
	/*
	 * Assumptions:
	 * 	1. Three Arrays must be at least two elements.
	 *  2. K must be smaller than a.length * b.length * c.length and k >= 1.
	 *  
	 *  Step: Explore each points from three arrays and  use sqrt(a^2 + b^2 + c^2) to calculate the distance from <0,0,0>.
	 *  Use minHeap to pop K - 1 times to find the kth closest point. 
	 *  
	 *  Return a list that conains x, y, z of kth closest point.
	 *  
	 *  Time Complexity: O(klogk) Space Complexity: O(k).
	 *  	
	 */
	static class Point{
		private int x;
		private int y;
		private int z;
		private Long distance;

		Point(int x, int y, int z, long dis){
			this.x = x;
			this.y = y;
			this.z = z;
			this.distance = dis;
		}

	}
	public static List<Integer> closestFromOrigin(int[] a, int[] b, int[] c, int k) {
		// Write your solution here.
		if(k < 1 || k > a.length * b.length * c.length){
			return new ArrayList<Integer> ();
		}
		PriorityQueue <Point> minHeap = new PriorityQueue(11, new Comparator<Point>(){
			@Override
			public int compare(Point one, Point two){
				return one.distance.compareTo(two.distance);
			}
		});
		Set<List<Integer>> set = new HashSet<> ();
		long dis = (long)a[0] * a[0] + b[0] * b[0] + c[0] * c[0];
		minHeap.offer(new Point(0, 0, 0, dis));
		set.add(Arrays.asList(a[0], b[0], c[0]));
		for(int i = 0; i < k - 1; i++){
			Point curr = minHeap.poll();
			System.out.print(a[curr.x] + " " + b[curr.y] + " " + c[curr.z]);
			System.out.println(" ");
			if(curr.x + 1 < a.length){
				if(set.add(Arrays.asList(a[curr.x + 1], b[curr.y], c[curr.z]))){
					dis = (long)a[curr.x + 1] * a[curr.x + 1] + 
							b[curr.y] * b[curr.y] + c[curr.z] * c[curr.z];
					minHeap.offer(new Point(curr.x + 1, curr.y, curr.z, dis));
				}
			}
			if(curr.y + 1 < b.length){
				if(set.add(Arrays.asList(a[curr.x], b[curr.y + 1], c[curr.z]))){
					dis = (long)a[curr.x] * a[curr.x] + 
							b[curr.y + 1] * b[curr.y + 1] + c[curr.z] * c[curr.z];
					minHeap.offer(new Point(curr.x, curr.y + 1, curr.z, dis));
				}
			}
			if(curr.z + 1 < c.length){
				if(set.add(Arrays.asList(a[curr.x], b[curr.y], c[curr.z + 1]))){
					dis = (long)a[curr.x] * a[curr.x] + 
							b[curr.y] * b[curr.y] + c[curr.z + 1] * c[curr.z + 1];
					minHeap.offer(new Point(curr.x, curr.y, curr.z + 1, dis));
				}
			}
		}
		Point kth = minHeap.peek();
		return Arrays.asList(a[kth.x], b[kth.y], c[kth.z]);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = {2, 3, 5}, B = {2, 4,6}, C = {1, 3};
		List<Integer> sol = closestFromOrigin(A, B, C, 18);
		for(int num : sol){
			System.out.print(num + " ");
		}
		System.exit(0);

	}

}
