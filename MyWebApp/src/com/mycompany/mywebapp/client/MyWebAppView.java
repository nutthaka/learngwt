package com.mycompany.mywebapp.client;

import com.google.gwt.user.client.ui.*;

public class MyWebAppView {

    private final Button sendButton = new Button("Send");
    private final TextBox nameField = new TextBox();
    private final Label errorLabel = new Label();
    private final DialogBox dialogBox = new DialogBox();
    private final Button closeButton = new Button("Close");
    private final Label textToServerLabel = new Label();
    private final HTML serverResponseLabel = new HTML();

    public HTML getServerResponseLabel() {
        return serverResponseLabel;
    }

    public Label getTextToServerLabel() {
        return textToServerLabel;
    }

    public Button getCloseButton() {
        return closeButton;
    }

    public Button getSendButton() {
        return sendButton;
    }

    public TextBox getNameField() {
        return nameField;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public DialogBox getDialogBox() {
        return dialogBox;
    }

    public MyWebAppView() {

        nameField.setText("GWT User");

        // We can add style names to widgets
        sendButton.addStyleName("sendButton");
    }

    private void createHiddenDialogBox() {
        // Create the popup dialog box
        dialogBox.setText("Remote Procedure Call");
        dialogBox.setAnimationEnabled(true);
        // We can set the id of a widget by accessing its Element
        closeButton.getElement().setId("closeButton");
        VerticalPanel dialogVPanel = new VerticalPanel();
        dialogVPanel.addStyleName("dialogVPanel");
        dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
        dialogVPanel.add(textToServerLabel);
        dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
        dialogVPanel.add(serverResponseLabel);
        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
        dialogVPanel.add(closeButton);
        dialogBox.setWidget(dialogVPanel);
    }

    public void drawOnScreen() {
        // Add the nameField and sendButton to the RootPanel
        // Use RootPanel.get() to get the entire body element
        RootPanel.get("nameFieldContainer").add(nameField);
        RootPanel.get("sendButtonContainer").add(sendButton);
        RootPanel.get("errorLabelContainer").add(errorLabel);

        // Focus the cursor on the name field when the app loads
        nameField.setFocus(true);
        nameField.selectAll();

        createHiddenDialogBox();
    }
}
