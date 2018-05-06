package com.example.client;
import com.example.helpers.BrowserCloseDetector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class helloworldmvp implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	private final Storage stockStore;	
	
	private helloworldmvp()
	{
		stockStore = Storage.getLocalStorageIfSupported();	
	}
	
	public void onModuleLoad() {		
			
		if (BrowserCloseDetector.get().wasClosed()) {	
			if (stockStore != null)
			{
				stockStore.clear();	
			}
		}	
	
		AppController appViewer = new AppController(stockStore);
		appViewer.go(RootPanel.get());	
	}
}
