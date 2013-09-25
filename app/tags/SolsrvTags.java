package tags;

import groovy.lang.Closure;

import java.io.PrintWriter;
import java.util.Map;

import play.exceptions.TagInternalException;
import play.templates.FastTags;
import play.templates.GroovyTemplate.ExecutableTemplate;
import play.templates.JavaExtensions;
import tags.html.ImageTag;
import tags.html.LinkTag;
import tags.html.Resource;
import tags.html.ScriptTag;

/**
 * 
 * テンプレートタグ拡張です。
 * 
 * @author n-shinya
 *
 */
public class SolsrvTags extends FastTags {

    /**
     * 画像読み込みのHTMLタグ(img)をレンダリングします。
     * 
     * @param args
     * @param body
     * @param out
     * @param template
     * @param fromLine
     */
    public static void _image(Map<?, ?> args, Closure body, PrintWriter out, ExecutableTemplate template, int fromLine) {
        
        // ファイル名は必須です
        Object file = args.get("file");
        if(file == null) {
            throw new TagInternalException("Required tag parameter : file");
        }

        // リソースパスを取得します
        String resourcePath =
            Resource.getLoacationPath(Resource.ResourceType.IMAGE, (String)file);
        ImageTag img = new ImageTag();

        // 属性をセットします
        for(String key : img.getAttributes().keySet()) {
            if(key.equals("src")) {
                img.setAttributes(key, resourcePath);
            } else {
                img.setAttributes(key, args.get(key));
            }
        }
        
        // imgタグを出力します        
        out.print(img.toString());
    }
    
    /**
     * スクリプト読み込みのHTMLタグ(script)をレンダリングします。
     * 
     * @param args
     * @param body
     * @param out
     * @param template
     * @param fromLine
     */
    public static void _js(Map<?, ?> args, Closure body, PrintWriter out, ExecutableTemplate template, int fromLine) {
        
        Object file = args.get("file");
        String resourcePath = null;
        if(file != null) {
            resourcePath =
                Resource.getLoacationPath(Resource.ResourceType.SCRIPT, (String)file);
        }
        ScriptTag script = new ScriptTag();
        if(body != null) {
            script.setBody(JavaExtensions.toString(body));
        }
        for(String key : script.getAttributes().keySet()) {
            if(key.equals("src")) {
                script.setAttributes(key, resourcePath);
            } else if (key.equals("type")){
                script.setAttributes(key, "text/javascript");
            } else {
                script.setAttributes(key, args.get(key));
            }
        }
        // scriptタグを出力します
        out.print(script.toString());
    }
    
    /**
     * スタイルシート読み込みのHTMLタグ(link)をレンダリングします。
     * 
     * @param args
     * @param body
     * @param out
     * @param template
     * @param fromLine
     */
    public static void _css(Map<?, ?> args, Closure body, PrintWriter out, ExecutableTemplate template, int fromLine) {

        Object file = args.get("file");
        if(file == null) {
            throw new TagInternalException("Required tag parameter : file");
        }
        String resourcePath =
                Resource.getLoacationPath(Resource.ResourceType.CSS, (String)file);
        LinkTag link = new LinkTag();
        for(String key : link.getAttributes().keySet()) {
            if(key.equals("href")) {
                link.setAttributes(key, resourcePath);
            } else if(key.equals("rel")){
                link.setAttributes(key, "stylesheet");
            } else if(key.equals("type")){
                link.setAttributes(key, "text/css");
            } else {
                link.setAttributes(key, args.get(key));
            }
        }
        // linkタグを出力します
        out.print(link.toString());
    }
}
