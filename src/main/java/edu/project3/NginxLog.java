package edu.project3;

import java.time.OffsetDateTime;
import java.util.regex.Matcher;
import lombok.Getter;

@Getter
public class NginxLog {

    private static final int IP_GROUP = 1;
    private static final int RESOURCE_GROUP = 5;
    private static final int RESPONSE_CODE_GROUP = 7;
    private static final int RESPONSE_SIZE_GROUP = 8;

    private final String ipAddress;
    private final int responseCode;
    private final int responseSize;
    private final String resource;
    private final OffsetDateTime dateTime;

    public NginxLog(Matcher matcher, OffsetDateTime offsetDateTime) {
        ipAddress = matcher.group(IP_GROUP);
        dateTime = offsetDateTime;
        resource = matcher.group(RESOURCE_GROUP);
        responseCode = Integer.parseInt(matcher.group(RESPONSE_CODE_GROUP));
        responseSize = Integer.parseInt(matcher.group(RESPONSE_SIZE_GROUP));
    }

    public NginxLog(
        String resource,
        int responseCode,
        int responseSize,
        OffsetDateTime offsetDateTime,
        String ipAddress
    ) {
        this.resource = resource;
        this.responseCode = responseCode;
        this.responseSize = responseSize;
        this.dateTime = offsetDateTime;
        this.ipAddress = ipAddress;
    }
}
