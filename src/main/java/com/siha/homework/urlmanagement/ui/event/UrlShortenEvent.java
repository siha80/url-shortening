package com.siha.homework.urlmanagement.ui.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Url shortening을 요청하는 Event Object
 * @author 하성일
 * @since 2017.08.08
 */
public class UrlShortenEvent {
    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Request extends UrlShortenEvent {
        String url;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Response extends UrlShortenEvent {
        String shortened;
    }
}
