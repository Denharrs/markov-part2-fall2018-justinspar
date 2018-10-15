import java.util.*;
/**
 * More efficient class for Markov text generation. Instead of rescanning the entire text of N characters as in BaseMarkov, 
 * this class stores each unique k-gram as a key in the instance variable myMap, 
 * with the characters/Single-char strings that follow the k-gram in a list associated as the value of the key
 * 
 * This makes EfficientMarkov text generation an O(N+T) 
 * operation instead of the O(N*T) for BaseMarkov
 * 
 * 
 * @author Justin Spar
 *
 */
public class EfficientMarkov extends BaseMarkov{
	private  Map<String,ArrayList<String>> myMap;
	
	/**
	 * Constructor that has the order of the markov model 
	 * as a parameter. Sets myOrder to order and declares myMap.
	 * 
	 * @param order of markov model - to be stored in myOrder
	 */
	public EfficientMarkov(int order)
	{
		super(order);
		myMap = new HashMap<String,ArrayList<String>>();
	}
	
	/**
	 * Default constructor that always sets myOrder to order to 3 (using super) and declares myMap.
	 */
	public EfficientMarkov()
	{
		super(3);
		myMap = new HashMap<String,ArrayList<String>>();
	}
	
	/**
	 * Stores the text parameter, clears the map, and then initializes myMap.
	 * Will need to generate every k-gram in the text (represented by a String of length myOrder) 
	 * as a possible key in myMap
	 * and add each of the following single-character strings to the ArrayList value associated with the k-gram key.
	 * 
	 * @param String text (training text)
	 **/
	public void setTraining(String text) {
		myMap.clear();
		myText = text;
		char charAdd = ' ';
		
		for(int i=0; i<myText.length()-myOrder+1; i++)
		{
			String toAdd = myText.substring(i, i+myOrder);
			
			if(i+myOrder-1 != myText.length()-1)
			{
				charAdd = myText.charAt(i+myOrder);
			}
		
			if(!(myMap.containsKey(toAdd)))
    			{
				ArrayList<String> forMapNew = new ArrayList<>();
					
				if(i+myOrder-1 == myText.length()-1)
				{
					forMapNew.add(PSEUDO_EOS);
				}
					
				else
				{
					forMapNew.add(String.valueOf(charAdd));
				}
				
				myMap.put(toAdd, forMapNew);
    			}
    
			else
			{
				ArrayList<String> forMapExists = new ArrayList<>(myMap.get(toAdd));
				
				if(i+myOrder-1 == myText.length()-1)
				{
					forMapExists.add(PSEUDO_EOS);
				}
				
				else
				{
					forMapExists.add(String.valueOf(charAdd));
				}
			
				myMap.put(toAdd, forMapExists);
    			}
		}
	}
	
	/**
	 * Looks up the key in a map and returns the associated value: 
	 * an ArrayList of single-character strings that was created when setTraining is called
	 * 
	 * @param String key to be searched for in myMap
	 * @return ArrayList of single-character strings (for the given key) that was created when setTraining is called.
	 * Throws an exception if key is not in myMap
	 **/
	public ArrayList<String> getFollows(String key){
		if(myMap.containsKey(key))
		{
			return myMap.get(key);
		}
		
		throw new NoSuchElementException(key+" not in map");
	}
}
