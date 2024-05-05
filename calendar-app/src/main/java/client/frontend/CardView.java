package client.frontend;

import client.backend.models.Card;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class CardView {

    Parser parser = Parser.builder().build();
    HtmlRenderer renderer = HtmlRenderer.builder().build();
    WebView webView = new WebView();
    HTMLEditor htmlEditor = new HTMLEditor();

    public Scene createCardView(Card card) {
        StackPane mainStack = new StackPane();
        VBox mainVBox = new VBox();

        HBox top = new HBox();
        //top.setAlignment(Pos.TOP_CENTER);
        top.setSpacing(10);
        top.setPadding(new Insets(5,5,5,5));

        AnchorPane forTitle = new AnchorPane();
        Label title = new Label(card.getTitle());
        title.setAlignment(Pos.CENTER);

        AnchorPane forButtons = new AnchorPane();
        Button edit = new Button("Edit card");
        Button read = new Button("Preview card");

        StackPane textField = this.setupReadView(card);

        edit.setOnAction(event -> {
            // TODO enter edit mode
        });

        read.setOnAction(event -> {
            textField.getChildren().set(0, this.setupReadView(card));
        });

        HBox.setHgrow(forButtons, Priority.ALWAYS);
        forTitle.getChildren().add(title);
        forButtons.getChildren().addAll(edit, read);
        top.getChildren().addAll(forTitle, forButtons);

        Separator sep = new Separator();

        mainVBox.setSpacing(5);
        mainVBox.setPadding(new Insets(5,5,5,5));
        mainVBox.getChildren().addAll(top, sep, textField);
        mainStack.setPadding(new Insets(3,3,3,3));
        mainStack.getChildren().add(mainVBox);

        return new Scene(mainStack, 1280, 720);
    }

    private StackPane setupReadView(Card card) {
        StackPane mainStack = new StackPane();
        VBox mainVBox = new VBox();
        mainVBox.setSpacing(5);

        String htmlCard = renderer.render(parser.parse(card.getDescription()));
        webView.getEngine().loadContent(htmlCard);
        StackPane mainField = new StackPane();
        mainField.getChildren().add(webView);

        mainVBox.getChildren().addAll(mainField);
        mainStack.setPadding(new Insets(3,3,3,3));
        mainStack.getChildren().add(mainVBox);

        return mainStack;
    }
/*
    private StackPane setupEditView(Card card) {

    }
*/
}