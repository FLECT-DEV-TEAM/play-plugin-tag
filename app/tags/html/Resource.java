package tags.html;

import play.Play;
import play.exceptions.TagInternalException;

/**
 * 
 * 静的コンテンツのリソースです。
 * 
 * @author n-shinya
 *
 */
public class Resource {

    /**
     * ロケーションパスを返却します。
     * 
     * @param type リソースのタイプ
     * @param filename ファイル名 
     * @return ロケーションパス
     */
    public static String getLoacationPath(ResourceType type, String filename) {
        
        StringBuilder path = new StringBuilder();
        if(type == null) {
            throw new IllegalArgumentException("ResourceType is null.");
        }
        
        // 開発モードで動作している場合はアプリケーション内のリソースを参照します
        if(Play.mode.isDev()) {
            path.append("/public")
                .append('/')
                .append(type.getLocalDirectory())
                .append('/');
        // 本番モードで動作している場合はCDNのリソースを参照します
        } else {
            String appVersion = Play.configuration.getProperty("application.version");
            if(appVersion == null) {
                throw new TagInternalException("Not Define Version : application.version");
            }
            String domain = Play.configuration.getProperty("cloudfront.domain");
            if(domain == null) {
                throw new TagInternalException("Not Define Domain : cloudfront.domain");
            }
            path.append("http://")
                .append(domain)
                .append('/')
                .append(appVersion)
                .append('/')
                .append(type.getLocalDirectory())
                .append('/');
        }
        path.append(filename);
        return path.toString();
    }
    
    public enum ResourceType {
        IMAGE("images")
        ,CSS("stylesheets")
        ,SCRIPT("javascripts");
        
        private String localDirectory;
        
        private ResourceType(String dir) {
            this.localDirectory = dir;
        }
        public String getLocalDirectory() {
            return localDirectory;
        }
    }
}
