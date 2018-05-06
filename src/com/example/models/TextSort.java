package com.example.models;
import java.util.List;
import com.example.helpers.Sorting;

public class TextSort {
	private String inputText;
	
	public void setInputText(String inputText)
	{
		this.inputText = inputText;		
	}
	
	public String getInputText()
	{
		return inputText;
	}
	
	public List<String> getOutputText()
	{		
		return Sorting.SortIt(inputText);			
	}
}
