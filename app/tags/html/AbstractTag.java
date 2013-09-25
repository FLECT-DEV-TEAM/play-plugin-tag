package tags.html;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * HTMLタグの抽象です。
 * 
 * @author n-shinya
 *
 */
public abstract class AbstractTag {
    
    protected Map<String, Object> attributes;
    
    protected String body = "";
    
    protected abstract String getTagName();
    
    protected abstract void validate();
    
    protected abstract boolean hasBody();

    /**
     * 一般的な属性を設定します。 
     */
    protected void setStandardAttributes() {
        if(attributes == null) {
            attributes = new LinkedHashMap<String, Object>();
        }
        attributes.put("class", null);
        attributes.put("dir", null);
        attributes.put("id", null);
        attributes.put("lang", null);
        attributes.put("style", null);
        attributes.put("title", null);
        attributes.put("xml:lang", null);
    }
    
    /**
     * イベント属性を設定します。
     */
    protected void setEventAttributes() {
        if(attributes == null) {
            attributes = new LinkedHashMap<String, Object>();
        }
        attributes.put("onclick", null);
        attributes.put("ondblclick", null);
        attributes.put("onmousedown", null);
        attributes.put("onmousemove", null);
        attributes.put("onmouseout", null);
        attributes.put("onmouseover", null);
        attributes.put("onmouseup", null);
        attributes.put("onkeydown", null);
        attributes.put("onkeypress", null);
        attributes.put("onkeyup", null);
    }
    
    /**
     * 属性を設定します。
     * 
     * @param name 属性名
     * @param value 属性値
     */
    public void setAttributes(String name, Object value) {
        attributes.put(name, value);
    }

    /**
     * 属性を返却します。
     * 
     * @return 属性
     */
    public Map<String, Object> getAttributes() {
        return attributes;
    }
    
    /**
     * ボディを設定します。
     * 
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }
    
    /**
     * ボディを返却します。
     * 
     * @return
     */
    public String getBody() {
        return body;
    }

    /**
     * 属性と属性値の文字列を組み立てます。
     * 
     * @param name 属性名
     * @param value 属性値
     * @param doubleQuote 属性値をダブルクオートで囲むか
     * @return 組み立てた文字列 
     */
    @Override
    public String toString() {
        validate();
        StringBuilder sb = new StringBuilder();
        sb.append('<')
            .append(getTagName())
            .append(' ');
        for(Map.Entry<String, Object> entry : attributes.entrySet()) {
            if(entry.getValue() != null) {
                sb.append(entry.getKey())
                    .append("=\"")
                    .append(entry.getValue())
                    .append("\" ");
            }
        }
        if(hasBody()) {
            sb.append('>')
                .append(getBody())
                .append("</")
                .append(getTagName())
                .append('>');
        } else {
            sb.append("/>");    
        }
        return sb.toString();
    }
}
