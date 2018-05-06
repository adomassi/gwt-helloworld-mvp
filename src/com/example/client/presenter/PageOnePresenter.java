package com.example.client.presenter;
import com.google.gwt.storage.client.Storage;

import java.util.ArrayList;
import java.util.List;

import com.example.models.TextSort;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class PageOnePresenter implements Presenter {
	public interface Display {
		HasClickHandlers getSubmitButtonClickHandler();	
		TextArea getTextArea();
		HasClickHandlers getClearButtonClickHandler();
		DataGrid<String> getDataGrid();	
		HasValue<String> getinputTextValue();		
		Widget asWidget();
	}

	TextSort text;
	private final Display display;
	private final Storage stockStorage;
	private DataGrid<String> dataGrid = null;	
	
	public PageOnePresenter(Display display, Storage stockStore) {
		this.display = display;	
		this.stockStorage = stockStore;	
		bind();
	}	

	private void bind() 	
	{			
		initDataGrid();	
		
		if (stockStorage != null)
		{					
			String var = stockStorage.getItem("InputValue");
			this.display.getinputTextValue().setValue(var);	
			doValueVerify();			
		}		
		
		this.display.getTextArea().addKeyDownHandler( new KeyDownHandler() {
	          @Override
	          public void onKeyDown(KeyDownEvent e) {
	            if (e.getNativeKeyCode() == KeyCodes.KEY_TAB) {
	            	e.preventDefault();
	            	addTabToTextArea();	            
	            } 
	            }});	
	
		this.display.getSubmitButtonClickHandler().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						doValueVerify();
					}
				});
		
		this.display.getClearButtonClickHandler().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						clearAllDataOnPage();
					}
				});		
	}
	
	private void addTabToTextArea()
	{
		this.display.getTextArea().setValue(this.display.getTextArea().getValue() + "\t");
	}
	
	private TextColumn<String> valueColumn = new TextColumn<String>() {

		@Override
		public String getValue(String data) {
			return data;
		}
	};	

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}
	
	private void clearAllDataOnPage()
	{
		if (stockStorage != null)
		{
			stockStorage.clear();
		}		
		dataGrid.setRowData(new ArrayList<String>(0));
		this.display.getinputTextValue().setValue("");		
	}

	private void doValueVerify() 
	{		
		String inputText = this.display.getinputTextValue().getValue();	
		if (stockStorage != null)
		{
			stockStorage.clear();
		}			
		stockStorage.setItem("InputValue", inputText);
		
		if (inputText.length() > 0) {			
			TextSort value = new TextSort();
			value.setInputText(inputText); 
			
			List<String> outputValue = value.getOutputText();	
			setDataToDataGrid(outputValue);	
		}
	}
	
	private void setDataToDataGrid(List<String> input)
	{					
		dataGrid.setRowData(input);	
	}
	
	private void initDataGrid()
	{		
		dataGrid = display.getDataGrid();		
		dataGrid.addColumn(valueColumn, "Rezultatas");	
		dataGrid.setColumnWidth(valueColumn, 900, Unit.PX);	
		dataGrid.setRowData(new ArrayList<String>());
	}
}


	