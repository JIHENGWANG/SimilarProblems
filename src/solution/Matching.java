package solution;

public class Matching {
	/*
	 * Assumptions:
	 * Case 1: if input is null, then return false.
	 * Case 2: if pattern's length larger than input's length, then return true. else return false.
	 * 
	 * Assume that bother input and pattern are not null.
	 * 
	 * Steps: Fill out a 2D boolean array to find if there exists a matching wildcard. array[i][j] repqresents if
	 *        there exist a wildcard matching from first element of input and pattern to ith element of input and jth element
	 *        from pattern. 
	 *        
	 * Induction rules: if(pattern.charAt(i - 1) == input.charAt(j - 1) || pattern.charAt(i - 1) == '?'), then
	 * 						ifMatched[j][i] = ifMatched[j][i] || ifMatched[j- 1][i - 1];
	 * 					else if(pattern.charAt(i - 1) == '*'), then 
	 * 						ifMatched[j][i] = ifMatched[j - 1][i] || ifMatched[j][i - 1];
	 * 
	 * return array[input.length][pattern.length].
	 * 
	 * Time Complexity:o(n*m)  Space Complexity:o(n*m) 
	 */
	
	public static boolean match(String input, String pattern) {
		 // Write your solution here.
		boolean[][] ifMatched = new boolean[1 + input.length()][1 + pattern.length()];
		ifMatched[0][0] = true;
		for(int i = 1; i <= pattern.length(); i++){
			if(pattern.charAt(i - 1) != '*'){
				break;
			}
			ifMatched[0][i] = true;
		}
		for(int i = 1; i <= input.length(); i++){
			for(int j = 1; j <= pattern.length(); j++){
				if(pattern.charAt(j - 1) == '*'){
					ifMatched[i][j] = ifMatched[i - 1][j] || ifMatched[i][j - 1];
				} else if(pattern.charAt(j - 1) == input.charAt(i - 1) || pattern.charAt(j - 1) == '?'){
					ifMatched[i][j] = ifMatched[i - 1][j - 1];
				}	
			}
		}
		return ifMatched[input.length()][pattern.length()];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(match("z", "ac*"));
		System.exit(0);
	}

}
