package client.frontend;

import client.backend.models.Card;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Stack;

public class CardView {

    private final Parser parser = Parser.builder().build();
    private final HtmlRenderer renderer = HtmlRenderer.builder().build();
    private final WebView webView = new WebView();

    public Scene createCardView(Card card) {
        StackPane mainStack = new StackPane();
        VBox mainVBox = new VBox();

        HBox top = new HBox();
        top.setAlignment(Pos.TOP_CENTER);
        top.setSpacing(10);
        top.setPadding(new Insets(5,5,5,5));

        AnchorPane forTitleTexts = new AnchorPane();
        HBox titleTexts = new HBox();
        Label title = new Label(card.getTitle());
        title.setAlignment(Pos.CENTER);
        Label mode = new Label("Preview Mode");
        mode.setAlignment(Pos.CENTER);

        HBox forButtons = new HBox();
        forButtons.setAlignment(Pos.CENTER_RIGHT);
        forButtons.setSpacing(10);
        Button edit = new Button("Edit card");
        Button read = new Button("Preview card");

        StackPane textField = this.setupReadView(card);

        edit.setOnAction(event -> {
            mode.setText("Edit Mode");
            mainVBox.getChildren().set(2, this.setupEditView(card));
        });

        read.setOnAction(event -> {
            mode.setText("Preview Mode");
            mainVBox.getChildren().set(2, this.setupReadView(card));
        });

        Separator vSep = new Separator(Orientation.VERTICAL);
        titleTexts.setSpacing(5);
        titleTexts.getChildren().addAll(mode, vSep, title);

        HBox.setHgrow(forTitleTexts, Priority.ALWAYS);
        forTitleTexts.getChildren().add(titleTexts);
        forButtons.getChildren().addAll(edit, read);
        top.getChildren().addAll(forTitleTexts, forButtons);

        Separator hSep = new Separator(Orientation.HORIZONTAL);

        mainVBox.setSpacing(5);
        mainVBox.setPadding(new Insets(5,5,5,5));
        VBox.setVgrow(textField, Priority.ALWAYS);
        mainVBox.getChildren().addAll(top, hSep, textField);
        mainStack.setPadding(new Insets(3,3,3,3));
        mainStack.getChildren().add(mainVBox);

        return new Scene(mainStack, 900, 520);
    }

    private StackPane setupReadView(Card card) {
        String htmlCard = renderer.render(parser.parse(card.getDescription()));
        webView.getEngine().loadContent(htmlCard);
        StackPane mainField = new StackPane();
        mainField.getChildren().add(webView);
        mainField.setPadding(new Insets(3,3,3,3));

        return mainField;
    }

    private StackPane setupEditView(Card card) {
        String cardContent = card.getDescription();
        TextArea textArea = new TextArea();
        textArea.setText(cardContent);
        textArea.setWrapText(true);
        textArea.setEditable(true);

        // Bind the TextArea's text property to itself to preserve new lines
        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals(oldValue)) {
                textArea.setText(newValue);
            }
        });

        Button save = new Button("Save card");
        save.setOnAction(event -> {
            card.setDescription(textArea.getText());
            // TODO save to files and server!
        });

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER_RIGHT);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(3,3,3, 3));
        VBox.setVgrow(textArea, Priority.ALWAYS);
        vbox.getChildren().addAll(textArea, save);

        StackPane mainField = new StackPane();
        mainField.setAlignment(Pos.CENTER);
        mainField.getChildren().add(vbox);

        return mainField;
    }

}