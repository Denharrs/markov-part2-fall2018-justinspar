import java.util.*;
public class EfficientWordMarkov extends BaseWordMarkov{
	private  Map<WordGram,ArrayList<String>> myMap;
	
	public EfficientWordMarkov(int order)
	{
		super(order);
		myMap = new HashMap<WordGram,ArrayList<String>>();
	}
	
	public EfficientWordMarkov()
	{
		super(3);
		myMap = new HashMap<WordGram,ArrayList<String>>();
	}
	
	public void setTraining(String text) {
		myMap.clear();
		myWords = text.split(" ");
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
	
	public ArrayList<String> getFollows(WordGram kgram){
		if(myMap.containsKey(kgram))
		{
			return myMap.get(kgram);
		}
		
		throw new NoSuchElementException(kgram+" not in map");
	}
}
