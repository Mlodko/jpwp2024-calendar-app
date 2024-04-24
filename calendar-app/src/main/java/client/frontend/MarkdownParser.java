package client.frontend;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

abstract public class MarkdownParser {

    final static Parser parser = Parser.builder().build();
    final static HtmlRenderer renderer = HtmlRenderer.builder().build();

    public static String parseToHtml(String markdown) {
        Node document = parser.parse(markdown);
        return renderer.render(document).replaceAll("<em>", "<i>").replaceAll("</em>", "</i>");
    }
}
