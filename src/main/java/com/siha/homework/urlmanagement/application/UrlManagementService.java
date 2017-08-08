package com.siha.homework.urlmanagement.application;

import com.siha.homework.urlmanagement.ui.event.UrlShortenEvent;

/**
 * Url을 관리하는 서비스
 */
public interface UrlManagementService {
    /**
     * url shortening을 수행하는 business logic method
     * @param request
     * @return
     */
    UrlShortenEvent.Response toShortened(final UrlShortenEvent.Request request);

    String getOriginUrl(String shortenedUrl);
}
