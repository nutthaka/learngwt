package com.mycompany.mywebapp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mycompany.mywebapp.shared.FieldVerifier;

public class MyWebAppPresenter {
    /**
     * The message displayed to the user when the server cannot be reached or
     * returns an error.
     */
    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";


    /**
     * Create a remote service proxy to talk to the server-side Greeting service.
     */
    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    public MyWebAppPresenter(final MyWebAppView view) {

        // Add a handler to close the DialogBox
        view.getCloseButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                view.getDialogBox().hide();
                view.getSendButton().setEnabled(true);
                view.getSendButton().setFocus(true);
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
                view.getErrorLabel().setText("");
                String textToServer = view.getNameField().getText();
                if (!FieldVerifier.isValidName(textToServer)) {
                    view.getErrorLabel().setText("Please enter at least four characters");
                    return;
                }

                // Then, we send the input to the server.
                view.getSendButton().setEnabled(false);
                view.getTextToServerLabel().setText(textToServer);
                view.getServerResponseLabel().setText("");
                greetingService.greetServer(textToServer, new AsyncCallback<String>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        view.getDialogBox().setText("Remote Procedure Call - Failure");
                        view.getServerResponseLabel().addStyleName("serverResponseLabelError");
                        view.getServerResponseLabel().setHTML(SERVER_ERROR);
                        view.getDialogBox().center();
                        view.getCloseButton().setFocus(true);
                    }

                    public void onSuccess(String result) {
                        view.getDialogBox().setText("Remote Procedure Call");
                        view.getServerResponseLabel().removeStyleName("serverResponseLabelError");
                        view.getServerResponseLabel().setHTML(result);
                        view.getDialogBox().center();
                        view.getCloseButton().setFocus(true);
                    }
                });
            }
        }

        // Add a handler to send the name to the server
        MyHandler handler = new MyHandler();
        view.getSendButton().addClickHandler(handler);
        view.getNameField().addKeyUpHandler(handler);
    }
}
