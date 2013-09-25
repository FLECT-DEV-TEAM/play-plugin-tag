package tags.html;


import java.util.LinkedHashMap;
import java.util.Map;

import play.exceptions.TagInternalException;

/**
 * HTML img タグです。
 * 
 * @author n-shinya
 *
 */
public class ImageTag extends AbstractTag {

    public ImageTag() {
        attributes = new LinkedHashMap<String, Object>();
        attributes.put("src", null);
        attributes.put("alt", null);
        attributes.put("width", null);
        attributes.put("height", null);
        attributes.put("ismap", null);
        attributes.put("longdesc", null);
        attributes.put("usemap", null);
        setStandardAttributes();
        setEventAttributes();
    }

    @Override
    protected boolean hasBody() {
        return false;
    }

    @Override
    public String getTagName() {
        return "img";
    }
    
    @Override
    public void validate() {
        if(attributes.get("src") == null) {
            throw new TagInternalException("src  is required attribute.");
        }
    }
}
