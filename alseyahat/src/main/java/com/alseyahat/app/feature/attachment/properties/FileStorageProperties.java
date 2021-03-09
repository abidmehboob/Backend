package com.alseyahat.app.feature.attachment.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "attachment")
public class FileStorageProperties {
    String ip;
    String userName;
    String password;
    String accessUrl;
    String feignUrl;
    Directory directory;

    @Setter
    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Directory{
        String hotelImages;
        String sightSeeingImages;
        String dealImages;
        String privateHiredImages;
        
    }
}
