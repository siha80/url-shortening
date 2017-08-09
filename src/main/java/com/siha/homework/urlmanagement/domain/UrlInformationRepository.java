package com.siha.homework.urlmanagement.domain;

import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

/**
 * UrlInformation을 조회, 저장하는 persistance를 추상화하는 객체
 * @author 하성일
 * @since 2017.08.08
 */
@Repository
public interface UrlInformationRepository {
    Optional<UrlInformation> findById(String key);
    Optional<UrlInformation> findByOriginUrl(String originUrl);
    public UrlInformation save(UrlInformation u);
}
