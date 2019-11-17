//Chase Anzelc
//3/13/19

public class SubstringMatching {          
	static String uniqueChars = "";  
	
	public static void main(String[] args) {                  
		String pattern = "ABABAC";        
		String text = "AABACAABABACAA";
		
		// The last row is for the characters that          
		// are not defined in the pattern         
		int[][] dfa = {{1,1,3,1,5,1}, {0,2,0,4,0,4},                         
				{0,0,0,0,0,6}, {0,0,0,0,0,0}};   
		
		/*
		  		A B A B A C
		  		0 1 2 3 4 5
   				--------
		  A 0 | 1 1 3 1 5 1
		  B 1 | 0 2 0 4 0 4
	      C 2 | 0 0 0 0 0 6
	  Other 3 | 0 0 0 0 0 0
		 
		 */
		
		
		
		uniqueChars = UniqueCharacters(pattern);
		int k = FindSubstringMatch(dfa, text);         
		if (k == -1) System.out.println("Substring " + pattern + " cannot be found in " + text);         
		else System.out.println(text.substring(k, k+pattern.length()) + " at position " + k);     
	}
	
	public static int FindSubstringMatch(int[][] dfa, String text) {
		
		int state = 0;
		
		//loop through text string
		for(int i = 0; i < text.length(); i++) {	
					//loop through unique characters 
					for(int j = 0; j < uniqueChars.length(); j++) {
						//if they equal
						if(uniqueChars.charAt(j) == text.charAt(i)) {
						//change state
						state = dfa[j][state];
						}
					}
				//when you arrive at state 6 you found MATCH
				if(state == 6) {
					//return index position of pattern string in text string
					return (i - dfa.length) - 1;
				}
		}
		
		
		return -1;
	}
	
	
	
	
	
	
	public static String UniqueCharacters(String pattern) {
		
		uniqueChars += pattern.charAt(0);
		
		//loop through pattern
		for (int i = 1; i < pattern.length(); i++) {
			boolean alreadyadded = false;
			
			//loop through unique characters
			for(int j = 0; j < uniqueChars.length(); j++){
			if(pattern.charAt(i) == uniqueChars.charAt(j)) {
				//the character has already been added
				alreadyadded = true;
			}
			}
			if(alreadyadded == false) {
				//character has been added, adding
				uniqueChars += pattern.charAt(i);
			}
         }
		
		return uniqueChars;
	}
	
	

}