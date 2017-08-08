package com.siha.homework.urlmanagement.application;

import com.siha.homework.urlmanagement.domain.UrlInformation;
import com.siha.homework.urlmanagement.domain.UrlInformationRepository;
import com.siha.homework.urlmanagement.ui.event.UrlShortenEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Url을 관리하는 구현 객체
 * @author 하성일
 * @since 2017.08.08
 */
@Service
public class UrlManagementEventHandler implements UrlManagementService {
    private final char[] baseContents = "ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    private final long base = baseContents.length;
    @Value("${url.shortened.base.addr:http://localhost:9090/}")
    private String BASE_URL;

    private static Long SEQUENCE_VALUE = 1L;

    @Autowired
    UrlInformationRepository urlInformationRepository;

    @Override
    public UrlShortenEvent.Response toShortened(UrlShortenEvent.Request request) {
        return new UrlShortenEvent.Response().setShortened(BASE_URL + toShortened(request.getUrl()));
    }

    @Override
    public String getOriginUrl(String shortenedUrl) {
        return urlInformationRepository.findById(shortenedUrl).map(u -> u.getOriginUrl()).orElseGet(() -> BASE_URL + "error/errorpage");
    }

    private String toShortened(String url) {
        UrlInformation urlInformation = urlInformationRepository.findByOriginUrl(url).orElseGet(() -> {
            UrlInformation u = new UrlInformation();
            u.setKey(to62Digits(SEQUENCE_VALUE++));
            u.setOriginUrl(url);
            return urlInformationRepository.save(u);
        });

        return urlInformation.getKey();
    }

    private String to62Digits(long i) {
        return getDigits("", i);
    }

    private String getDigits(String acc, long i) {
        if(i == 0) return acc;
        else return getDigits(acc + baseContents[(int) (i % base)], i / base);
    }

}
