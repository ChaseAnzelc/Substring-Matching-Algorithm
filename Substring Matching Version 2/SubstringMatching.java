import java.io.*;

//Chase Anzelc
//700675123

class ReadData {

	public String text;
	public String pattern;

	public ReadData(String filename) {
		File file = new File(filename);
        BufferedReader buf;
		try {
			buf = new BufferedReader(new FileReader(file));
			text = buf.readLine();
			pattern = buf.readLine();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

public class SubstringMatching {
    
    static String uniqueChars = "";
    
    public static void main(String[] args) {
        
        ReadData data = new ReadData(args[0]);
        String text = data.text;
        String pattern = data.pattern;
        
        //System.out.println("Text = " + text);
        //System.out.println("Pattern = " + pattern);
        
        uniqueChars = UniqueCharacters(pattern);
        
        //System.out.println("UniqueChars = "+ uniqueChars);
        
        int[][] dfa = BuildDFA(pattern);
        
        int k = FindSubstringMatch(dfa, text);
        if (k == -1) System.out.println("Substring " + pattern + " cannot be found in " + text);
        else System.out.println(text.substring(k, k+pattern.length()) + " at position " + k);

        
        for (int i = 0; i < dfa.length; i++) {
        	  System.out.println("\n");
            // Loop through all elements of current row 
            for (int j = 0; j < dfa[i].length; j++) 
                System.out.print(dfa[i][j] + " "); 
        }
    }
    

    
    
private static int[][] BuildDFA(String pattern) {
		
		int[][] dfa = new int[uniqueChars.length() + 1][pattern.length()]; 
		int state = 0;
		//loop through pattern
		for(int x=0; x<pattern.length();x++) {
			//loop through Unique Chars
			for(int y=0; y <uniqueChars.length(); y++) {
				//increase state if matched
				if(pattern.charAt(x) == uniqueChars.charAt(y)) {
					state += 1;
					dfa[y][x] = state;
				}
				else {
					int ns, i;
					int count = 0;
					String findmatch = "";
					//loop through the states backwards
					for (ns = x ; ns > 0; ns--) 
				    { 
						count+=1;
						//once a state is equivalent see if remaining pattern is a match
						//if found go to that state
				        if (pattern.charAt(ns-1) == uniqueChars.charAt(y)) 
				        { 
				        	boolean matched = true;
				        	for (i = 0; i < ns - 1; i++) {
				        		findmatch += pattern.charAt(i + count);
				        		if (pattern.charAt(i) != pattern.charAt(i + count)) {
				                    matched = false;
				                }
				        	}
				        	int k = FindSubstringMatch(dfa, findmatch);
				        	if(matched == true) {
				        		dfa[y][x] = ns;
				        		break;
				        	} 
				        } 
				   } 
				}
				         
			}
		}
	
	
		return dfa;
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
					
				
				//when you arrive at last state you found MATCH
				if(state == dfa[0].length) {
					//return index position of pattern string in text string
					return (i - dfa[0].length) + 1 ;
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