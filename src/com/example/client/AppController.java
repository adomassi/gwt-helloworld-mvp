package com.example.client;
import com.example.client.presenter.PageOnePresenter;
import com.example.client.presenter.Presenter;
import com.example.client.view.PageOneView;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private final Storage stockStorage;
	private HasWidgets container;
	private PageOneView pageOneView = null;		
	
	public AppController(Storage stockStore) {		
		stockStorage = stockStore;	
	}

	@Override
	public void go(HasWidgets container) {
		this.container = container;	
		
		if ("".equals(History.getToken())) {
			History.newItem("pageone");
		} 		
		
		if ("pageone".equals(History.getToken())) {
			if (pageOneView == null) {
				pageOneView = new PageOneView();
			}
			new PageOnePresenter(pageOneView,stockStorage).go(container);			
		}	
	}
	
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			if (token.equals("pageone")) {
				if (pageOneView == null) {
					pageOneView = new PageOneView();
				}
				new PageOnePresenter(pageOneView, stockStorage).go(container);
			}	
		}
	}	
}
