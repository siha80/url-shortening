package com.siha.homework.urlmanagement.application;

import com.siha.homework.urlmanagement.domain.UrlInformation;
import com.siha.homework.urlmanagement.domain.UrlInformationRepositoryImpl;
import com.siha.homework.urlmanagement.ui.event.UrlShortenEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Url Manager Unit test
 */
public class UrlManagementEventHandlerTest {
    @InjectMocks
    UrlManagementEventHandler urlManagementEventHandler;
    @Mock
    UrlInformationRepositoryImpl urlInformationRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(urlManagementEventHandler, "BASE_URL", "http://localhost:9090/");
    }

    @Test
    public void testToShortened() throws Exception {
        UrlShortenEvent.Request req = new UrlShortenEvent.Request();
        req.setUrl("https:/www.kakao.com");

        when(urlInformationRepository.findByOriginUrl(anyString())).thenReturn(Optional.empty());
        when(urlInformationRepository.save(any(UrlInformation.class))).thenCallRealMethod();
        UrlShortenEvent.Response res = urlManagementEventHandler.toShortened(req);
        assertNotNull(res);
    }

    @Test
    public void testGetOriginUrlWithNotExistShortUrl() throws Exception {
        when(urlInformationRepository.findById(anyString())).thenReturn(Optional.empty());

        String value = urlManagementEventHandler.getOriginUrl("ABC");
        assertNotNull(value);
        assertEquals(value, "http://localhost:9090/error/errorpage");
    }

    @Test
    public void testGetOriginUrl() throws Exception {
        UrlInformation u = new UrlInformation();
        u.setKey("A");
        u.setOriginUrl("https://www.kakao.com");
        when(urlInformationRepository.findById(eq(u.getKey()))).thenReturn(Optional.of(u));

        String value = urlManagementEventHandler.getOriginUrl("A");
        assertNotNull(value);
        assertEquals(u.getOriginUrl(), value);
    }
}