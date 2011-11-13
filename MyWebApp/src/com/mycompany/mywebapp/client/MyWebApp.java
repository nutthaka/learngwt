package com.mycompany.mywebapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyWebApp implements EntryPoint {


    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        MyWebAppView view = new MyWebAppView();
        MyWebAppPresenter presenter = new MyWebAppPresenter(view);
    }
}
