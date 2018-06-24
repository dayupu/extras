
package org.dayup.avatar.base.plugins.markdown;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.dayup.avatar.base.utils.FileUtil;

import java.util.Arrays;

public class MarkDownHtmlWrapper {

    private static String MD_CSS = "";

    static {
        try {
            MD_CSS = FileUtil.readFile("view/css/github-markdown.css");
            MD_CSS = "<style type=\"text/css\">\n" + MD_CSS + "\n</style>\n";
        } catch (Exception e) {
            MD_CSS = "";
        }
    }


    /**
     * 直接将markdown语义的文本转为html格式输出
     *
     * @param content markdown语义文本
     * @return
     */
    public static MarkdownEntity ofContent(String content) {
        String html = parse(content);
        MarkdownEntity entity = new MarkdownEntity();
        entity.setCss(MD_CSS);
        entity.setHtml(html);
        entity.addDivStyle("class", "markdown-body ");
        return entity;
    }


    /**
     * markdown to image
     *
     * @param content markdown contents
     * @return parse html contents
     */
    public static String parse(String content) {
        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.MARKDOWN);
        options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create()));
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Node document = parser.parse(content);
        return renderer.render(document);
    }
}
