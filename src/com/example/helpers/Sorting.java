package com.example.helpers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sorting {	

	public static List<String> SortIt(String inputText)
	{			
	     List<List<String>> res =  new ArrayList<List<String>>();		
		
		 for (String item : Arrays.asList(inputText.split("\n", -1)))
         {	
			 List<String> firstItem = Arrays.asList(item.split("\\t"));		
			 res.add(firstItem);
         }         
		
		 Sort(res, 0); 		 
		 
		 List<String> finalres = new ArrayList<String>();
		 for (List<String> item : res)
		 {
			 String s = Arrays.deepToString(item.toArray()).replace(",", "\t");				
			 String resz = s.substring(1, s.length()-1);			 
			 finalres.add(resz);
		 }
		 
		 return finalres;
	}
	
    private static void Sort(List<List<String>> numbers, int iteration)
    {   
        String regex = "^-?[0-9]\\d*(\\.\\d+)?$";

        for (int i = numbers.size() - 1; i > 0; i--)
        {
            for (int j = 0; j <= i - 1; j++)
            {
                if (numbers.get(j).size() >= iteration + 1 && numbers.get(j + 1).size() >= iteration + 1)
                {
                    
                    boolean pass = true;

                    for (int k = 0; k < iteration; k++)
                    {
                        String ss = numbers.get(j).get(k);
                        String ss2 = numbers.get(j + 1).get(k);

                        if (ss != ss2)
                        {
                            pass = false;
                        }
                    }
                    
                    if (numbers.get(j).get(iteration).matches(regex) && numbers.get(j + 1).get(iteration).matches(regex) && pass)
                    {                       
                        if(Double.parseDouble(numbers.get(j).get(iteration)) > Double.parseDouble(numbers.get(j + 1).get(iteration)))
                    	{
                            List<String> highValue = numbers.get(j);
                            numbers.set(j, numbers.get(j + 1));
                            numbers.set(j + 1, highValue);
                        }
                    }
                    else if (!numbers.get(j).get(iteration).matches(regex) && pass)
                    {                           
                            if (numbers.get(j).get(iteration).compareTo(numbers.get(j + 1).get(iteration)) > 0 || numbers.get(j + 1).get(iteration).matches(regex))
                            {
                                List<String> highValue = numbers.get(j);
                                numbers.set(j, numbers.get(j + 1));
                                numbers.set(j + 1, highValue);
                            }                                 
                    }
                }
            }

            if (iteration < numbers.get(i- 1).size() && i == 1)
            {
                  iteration = iteration + 1;
                  Sort(numbers, iteration);
            }
        }
    }	
}
