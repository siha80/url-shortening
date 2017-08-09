package com.siha.homework.urlmanagement.domain;

import lombok.Data;

/**
 * Url정보를 저장하는 도메인 객체
 */
@Data
public class UrlInformation {
    private String key;
    private String originUrl;
}
