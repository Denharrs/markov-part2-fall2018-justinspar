import java.util.*;
public class EfficientMarkov extends BaseMarkov{
	private  Map<String,ArrayList<String>> myMap;
	
	public EfficientMarkov(int order)
	{
		super(order);
		myMap = new HashMap<String,ArrayList<String>>();
	}
	
	public EfficientMarkov()
	{
		super(3);
		myMap = new HashMap<String,ArrayList<String>>();
	}
	
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
	
	public ArrayList<String> getFollows(String key){
		if(myMap.containsKey(key))
		{
			return myMap.get(key);
		}
		
		throw new NoSuchElementException(key+" not in map");
	}
}
