package tags.html;


import java.util.LinkedHashMap;

/**
 * 
 * HTML linkタグです。
 * 
 * @author n-shinya
 *
 */
public class LinkTag extends AbstractTag {

    @Override
    protected String getTagName() {
        return "link";
    }

    @Override
    protected void validate() {
        // nothing to do.
    }
    
    @Override
    protected boolean hasBody() {
        return false;
    }
    
    public LinkTag() {
        attributes = new LinkedHashMap<String, Object>();
        attributes.put("charset", null);
        attributes.put("href", null);
        attributes.put("hreflang", null);
        attributes.put("media", null);
        attributes.put("rel", null);
        attributes.put("rev", null);
        attributes.put("target", null);
        attributes.put("type", null);
        setStandardAttributes();
        setEventAttributes();
    }
}
