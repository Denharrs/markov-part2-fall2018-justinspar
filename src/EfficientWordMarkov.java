import java.util.*;
/**
 * Similar to EfficientMarkov, but uses WordGram objects instead of String
 * objects to generate random text.
 * 
 * @author Justin Spar
 *
 */
public class EfficientWordMarkov extends BaseWordMarkov{
	private  Map<WordGram,ArrayList<String>> myMap;
	
	/**
	 * Constructor that has the order of the markov model 
	 * as a parameter. Sets myOrder to order and declares myMap.
	 * 
	 * @param order of markov model - to be stored in myOrder
	 */
	public EfficientWordMarkov(int order)
	{
		super(order);
		myMap = new HashMap<WordGram,ArrayList<String>>();
	}
	
	/**
	 * Default constructor that always sets myOrder to order to 3 (using super) and declares myMap.
	 */
	public EfficientWordMarkov()
	{
		super(3);
		myMap = new HashMap<WordGram,ArrayList<String>>();
	}
	
	/**
	 * Stores the text parameter in the String array myWords (where each word occupies its own position), clears the map, and then initializes myMap.
	 * Will need to generate every k-gram in myWords (represented by a WordGram object of size myOrder) 
	 * as a possible key in myMap 
	 * and add each of the following strings to the ArrayList value associated with the k-gram key.
	 * 
	 * @param String text (training text)
	 **/
	public void setTraining(String text) {
		myMap.clear();
		myWords = text.split("\\s+");
		String wordAdd = "";
		
		for(int i=0; i<myWords.length-myOrder+1; i++)
		{
			WordGram toAdd = new WordGram(myWords, i, myOrder);
			
			if(i+myOrder-1 != myWords.length-1)
			{
				wordAdd = myWords[i+myOrder];
			}
		
			if(!(myMap.containsKey(toAdd)))
    			{
				ArrayList<String> forMapNew = new ArrayList<>();
					
				if(i+myOrder-1 == myWords.length-1)
				{
					forMapNew.add(PSEUDO_EOS);
				}
					
				else
				{
					forMapNew.add(wordAdd);
				}
				
				myMap.put(toAdd, forMapNew);
    			}
    
			else
			{
				ArrayList<String> forMapExists = new ArrayList<>(myMap.get(toAdd));
				
				if(i+myOrder-1 == myWords.length-1)
				{
					forMapExists.add(PSEUDO_EOS);
				}
				
				else
				{
					forMapExists.add(wordAdd);
				}
			
				myMap.put(toAdd, forMapExists);
    			}
		}
	}
	
	/**
	 * Looks up the WordGram key in a map and returns the associated value: 
	 * an ArrayList of strings that was created when setTraining is called
	 * 
	 * @param WordGram kgram to be searched for in myMap
	 * @return ArrayList of strings (for the given key) that was created when setTraining is called.
	 * Throws an exception if key is not in myMap
	 **/
	public ArrayList<String> getFollows(WordGram kgram){
		if(myMap.containsKey(kgram))
		{
			return myMap.get(kgram);
		}
		
		throw new NoSuchElementException(kgram+" not in map");
	}
}
