package ots.com.test.ydata.data;

import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;


public class Utils {
//	http://stackoverflow.com/questions/4591988/how-to-compare-two-hash-maps
	/**
	 * Works with any two maps with common key / value types.
	 * The key type must implement Comparable though (for sorting).
	 * Returns a map containing all keys that appear in either of the supplied maps.
	 * The values will be true if and only if either
	 *   - map1.get(key)==map2.get(key) (values may be null) or
	 *   - map1.get(key).equals(map2.get(key)).
	 * @throws DataCompareException 
	 */
	public static <K extends Comparable<? super K>, V>
	Map<K, Boolean> compareEntries(final Map<K, V> map1,  final Map<K, V> map2) {
	   
		
		final Collection<K> allKeys = new HashSet<K>();
	    
	    allKeys.addAll(map1.keySet());
	//    allKeys.addAll(map2.keySet());
	   
	    
	    final Map<K, Boolean> result = new TreeMap<K, Boolean>();
	   
	    for(final K key : allKeys){
	    	
	    	String  value1 =  (String) map1.get(key)  ;
    		V value2 = map2.get(key)  ;
    		System.out.println ("value1*"+value1 + "*") ; 
    		System.out.println ("value2*"+value2+ "*") ;  
    	
    		
    		
	    	result.put(key,
	            map1.containsKey(key) == map2.containsKey(key) &&
	            Boolean.valueOf(equal(map1.get(key), map2.get(key))));
	    }
	    return result;
	    
	    /*//***************************************************************************************
		for(final Entry<K, Boolean> entry : result.entrySet()){
			//System.out.println("Entry:" + entry.getKey() + ", value: " + entry.getValue());
	        if  (!entry.getValue() ) { 
	        	
	        	throw new DataCompareException( " Πεδίο : " + entry.getKey() +  "  -- Τιμή Record: " + map1.get(entry.getKey() ) + "  ,  Καταγεγραμμένη Τιμή:  " + map2.get(entry.getKey() ) )  ;  
	        
	        }
	    }
	    //***************************************************************************************
*/	    
	    
	}

	private static boolean equal(final Object obj1, final Object obj2){
	    return obj1 == obj2 || (obj1 != null && obj1.equals(obj2));
	}
	

//**********************************************************************************************************************************************************************
	/*public static 
	Map<String, Boolean> compareEntries1(final Map<String, String> map1,  final Map<String, String> map2){
	   
		
		final Collection<String> allKeys = new HashSet<String>();
	    
	    allKeys.addAll(map1.keySet());
	//    allKeys.addAll(map2.keySet());
	   
	    
	    final Map<String, Boolean> result = new TreeMap<String, Boolean>();
	    
	    for(final String key : allKeys){
	    	System.out.println ("_____________" +  key + "_______________") ; 		
	    	
	   	if ( !map2.containsKey(key) ) {
	    		   result.put(key,false ) ; 
	    	}else{
	    		String  value1 =  map1.get(key).trim()  ;
	    		String value2 = map2.get(key).trim()  ;
	    		
	    		System.out.println ("value1*"+value1 + "*") ; 
	    		System.out.println ("value2*"+value2+ "*") ;  
	    		
	    		
	    		//result.put(key,  Boolean.valueOf(equal( value1 ,  value2 ) )  ) ; 
	    		 result.put(key,  Boolean.valueOf(value1.equals(  value2 ) )  ) ; 
	    		 
	    	}
	   	
	    }
		return result;
	    
	}
	*/
	
	//**********************************************************************************************************************************************************************
	
	public static Boolean  compareDetais (List<Map<String,String>> rowValues  , List<DetailRecord>  details ,  String criterio  )  throws Exception{ 
		
		if (rowValues == null && details == null ) 				return true ;
		if (rowValues.size() == 0  && details == null   ) 		return true ;
		if (rowValues == null && details.size() == 0  ) 			return true ;
		if (rowValues.size() == 0  && details.size() == 0  ) 	return true ;
		
		if ( rowValues.size() !=  details.size()   ) { 
			throw new DataCompareException( "Διαφορετικός αριθμός details :  Αριθμός Details Records " + details.size() + " , Αριθμός Καταγεγραμμένων details : " + rowValues.size()  ) ; 
			//return false;
		}
		for ( Map<String,String> rowValue :rowValues   ) { 
			for (DetailRecord detail : details ) { 
				Boolean lbExists = false ; 
				if (detail.getValue(criterio ).equals(rowValue.get(criterio) ) ){ 
					lbExists = true; 
					
					
					//final Map<String, Boolean> comparisonResult =compareEntries( detail.getFieldsMap(), rowValue  );
					final Map<String, Boolean> comparisonResult =compareEntries( detail.getFieldsMapForCheckingValues(), rowValue  );
					
					for(final Entry<String, Boolean> entry : comparisonResult.entrySet()){
		    			System.out.println("Entry:" + entry.getKey() + ", value: " + entry.getValue());
					}
					for(final Entry<String, Boolean> entry : comparisonResult.entrySet()){
		    			
		    			if  (!entry.getValue() ) { 
		    	        	
		    	        	throw new DataCompareException( "Detail με βάση το κριτήριο : " + criterio + "  έχει Τιμή : " + detail.getValue(criterio ) + "  , Πεδίο : " + entry.getKey() 
		    	        																			+ "  -- Τιμή Record: " + detail.getFieldsMap().get(entry.getKey()) + "  ,  Καταγεγραμμένη Τιμή:  " + rowValue.get( entry.getKey()) ); 
		    	        	//return false;
		    	        }
		    	    }
					details.remove(detail);
					break;
				}
				if (  !lbExists ){ 
					throw new DataCompareException( "Δεν μπόρεσε να γίνει ταυτοποίηση της εγγραφής με βάση το κριτήριο : " + criterio + " -- Τιμή : " + detail.getValue(criterio ) ) ; 
				//	return false;
				}
			}
		}
		
		return true;
}
//**********************************************************************************************************************************************************************
	
	
	

	















}


/*for (Map.Entry<String,String> entry : pageValues.entrySet()) {
System.out.println( entry.getKey() + "   ----- - >   " +  entry.getValue() ) ; 
}
*/    	
/*public boolean mapsAreEqual(Map<String, String> mapA, Map<String, String> mapB) {

try{
    for (String k : mapB.keySet())
    {
        if (mapA.get(k) != mapB.get(k)) {
            return false;
        }
    } 
    for (String y : mapA.keySet())
    {
        if (!mapB.containsKey(y)) {
            return false;
        }
    } 
} catch (NullPointerException np) {
    return false;
}
return true;
}
*/    
