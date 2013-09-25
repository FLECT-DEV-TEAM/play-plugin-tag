package tags.html;

import java.util.LinkedHashMap;

import play.exceptions.TagInternalException;

/**
 * HTML scriptタグです。
 * 
 * @author n-shinya
 *
 */
public class ScriptTag extends AbstractTag {

    public ScriptTag() {
        attributes = new LinkedHashMap<String, Object>();
        attributes.put("type", null);
        attributes.put("src", null);
        attributes.put("charset", null);
        attributes.put("defer", null);
    }

    @Override
    protected String getTagName() {
        return "script";
    }

    @Override
    protected void validate() {
        if(attributes.get("type") == null) {
            throw new TagInternalException("type  is required attribute.");
        }
    }
    
    @Override
    protected boolean hasBody() {
        return true;
    }
}
