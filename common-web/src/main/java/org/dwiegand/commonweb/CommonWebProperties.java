package org.dwiegand.commonweb;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "dwiegand.libs.web")
@Getter
@Setter
public class CommonWebProperties {

    Boolean cacheResponse;

    Boolean cacheRequest;
}
