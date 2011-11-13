package com.mycompany.mywebapp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mycompany.mywebapp.shared.FieldVerifier;

public class MyWebAppPresenter {


    /**
     * Create a remote service proxy to talk to the server-side Greeting service.
     */
    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    public MyWebAppPresenter(final MyWebAppView view) {

        // Add a handler to close the DialogBox
        view.getCloseButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                view.closeDialogBox();
            }
        });

        // Create a handler for the sendButton and nameField
        class MyHandler implements ClickHandler, KeyUpHandler {
            /**
             * Fired when the user clicks on the sendButton.
             */
            public void onClick(ClickEvent event) {
                sendNameToServer();
            }

            /**
             * Fired when the user types in the nameField.
             */
            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    sendNameToServer();
                }
            }

            /**
             * Send the name from the nameField to the server and wait for a response.
             */
            private void sendNameToServer() {
                // First, we validate the input.
                view.clearErrors();
                String textToServer = view.getEnteredName();
                if (!FieldVerifier.isValidName(textToServer)) {
                    view.setErrorText("Please enter at least four characters");
                    return;
                }

                // Then, we send the input to the server.
                view.prepareDialogBox(textToServer);
                greetingService.greetServer(textToServer, new AsyncCallback<String>() {
                    public void onFailure(Throwable caught) {
                        view.showDialogBoxOnFailure();

                    }

                    public void onSuccess(String result) {
                        view.showDialogBoxOnSuccess(result);
                    }
                });
            }




        }

        // Add a handler to send the name to the server
        MyHandler handler = new MyHandler();
        view.getSendButton().addClickHandler(handler);
        view.getNameField().addKeyUpHandler(handler);

        view.drawOnScreen();
    }

}
