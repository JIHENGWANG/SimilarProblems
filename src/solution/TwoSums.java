package solution;

import java.util.ArrayList;
import java.util.*;


public class TwoSums {
	public boolean existSum(int[] array, int target) {
	    // Write your solution here.
		if(array.length == 2){
			return array[0] + array[1] == target ? true : false;
		}
		HashSet<Integer> set = new HashSet<> ();
		for(int num: array){
			if(set.contains(target - num)){
				return true;
			}
			set.add(num);
		}
	    return false;
	}
	
	public static List<List<Integer>> allTriples(int[] array, int target) {
		// Write your solution here.
		List<List<Integer>> sol = new ArrayList<> ();
		if(array == null || array.length == 0){
			return sol;
		}
		Arrays.sort(array);
		for(int i = 0; i < array.length; i++){
			if(i > 0 && array[i] == array[i - 1]){
				continue;
			}
			int left = i + 1;
			int right = array.length - 1;
			while(left < right){
				int temp = array[left] + array[right];
				if(temp + array[i] == target){
					sol.add(Arrays.asList(array[i], array[left++], array[right]));
					while(left < right && array[left] == array[left - 1]){
						left++;
					}
				} else if(temp + array[i] > target){
					right--;
				} else {
					left++;
				}
			}

		}
		return sol;
	}
	
	public static List<List<Integer>> allPairsI(int[] array, int target) {
		// Write your solution here.
		List<List<Integer>> sol = new ArrayList<> ();
		if(array == null || array.length == 0){
			return sol;
		}
		Map<Integer, List<Integer>> map = new HashMap<> ();
		for(int i = 0; i < array.length; i++){
			List<Integer> pair = map.get(target - array[i]);
			if(pair != null){
				for(int num : pair){
					sol.add(Arrays.asList(num, i));
				}
			}
			if(!map.containsKey(array[i])){
				map.put(array[i], new ArrayList<Integer>());
			}
			map.get(array[i]).add(i);
		}
		return sol;
	}
	
	public static List<List<Integer>>  allPairsII(int[] array, int target) {
		// Write your solution here.
		List<List<Integer>> allPaire = new ArrayList<> ();
		if(array == null){
			return allPaire;
		}
		Arrays.sort(array);
		int left = 0;
		int right = array.length - 1;
		while(left < right){
			if(left > 0 && array[left - 1] == array[left]){
				left++;
				continue;
			}
			if(array[left] + array[right] == target){
				allPaire.add(Arrays.asList(array[left], array[right]));
				left++;
				right--;
			} else if(array[left] + array[right] > target){
				right--;
			} else {
				left++;
			}
		}
		return allPaire;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {-1, 0, 0, 2, 3, 4, 5};
		List<List<Integer>> sol = allTriples(array, 4);
		for(List list : sol){
			System.out.print(list + " ");
		}
		System.exit(0);
	}

}
