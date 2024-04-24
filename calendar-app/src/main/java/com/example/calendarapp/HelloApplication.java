package com.example.calendarapp;

import client.frontend.LoginView;
import client.frontend.MarkdownParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(LoginView.class.getResource("login-view.fxml"));
        // TODO webview
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        String markdown = "# Markdown Example\n" +
                "\n" +
                "This is a paragraph of text written in Markdown. Markdown is a lightweight markup language with plain-text formatting syntax.\n" +
                "\n" +
                "## Headings\n" +
                "\n" +
                "You can create headings using the `#` symbol. The number of `#` symbols indicates the level of the heading.\n" +
                "\n" +
                "### Lists\n" +
                "\n" +
                "- You can create unordered lists using `-`, `*`, or `+`.\n" +
                "- Like this one.\n" +
                "  - You can also create nested lists.\n" +
                "\n" +
                "1. Ordered lists are created using numbers.\n" +
                "2. Just like this.\n" +
                "\n" +
                "### Formatting\n" +
                "\n" +
                "You can make text **bold** using `**`, *italic* using `*`, and `code` using backticks.\n" +
                "\n" +
                "### Links and Images\n" +
                "\n" +
                "[This is a link to OpenAI's website](https://openai.com)\n" +
                "\n" +
                "![OpenAI Logo](https://openai.com/favicon.ico)\n" +
                "\n" +
                "### Blockquotes\n" +
                "\n" +
                "> This is a blockquote. You can use it to highlight important information or quotes from other sources.\n" +
                "\n" +
                "### Code Blocks\n" +
                "\n" +
                "```python\n" +
                "def greet(name):\n" +
                "    print(\"Hello, \" + name + \"!\")\n" +
                "```\n" +
                "\n" +
                "### Horizontal Rule\n" +
                "\n" +
                "---\n" +
                "\n" +
                "That's it for this Markdown example.\n";
        webEngine.loadContent(MarkdownParser.parseToHtml(markdown));

        Font font = new Font("file:/resources/client.frontend/Roboto-Medium.tff", 22);

        Scene scene = new Scene(fxmlLoader, 400, 300);
        scene.getStylesheets().add(LoginView.class.getResource("login-view.css").toExternalForm());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}