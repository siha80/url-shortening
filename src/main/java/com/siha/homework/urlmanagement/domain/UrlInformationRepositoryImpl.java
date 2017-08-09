package com.siha.homework.urlmanagement.domain;

import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

/**
 * UrlInformation을 조회, 저장하는 persistence를 추상화하는 객체
 * @author 하성일
 * @since 2017.08.08
 */
@Repository
public class UrlInformationRepositoryImpl implements UrlInformationRepository {
    private static Set<UrlInformation> urlInformationList = new LinkedHashSet<>();

    @Override
    public Optional<UrlInformation> findById(final String key) {
        return find(u -> key.equals(u.getKey()));
    }

    @Override
    public Optional<UrlInformation> findByOriginUrl(final String originUrl) {
        return find(u -> originUrl.equals(u.getOriginUrl()));
    }

    @Override
    public UrlInformation save(final UrlInformation u) {
        urlInformationList.add(u);
        return u;
    }

    Optional<UrlInformation> find(Predicate<UrlInformation> test) {
        return urlInformationList.stream().filter(u -> test.test(u)).map(Optional::of).findFirst().orElse(Optional.empty());
    }
}
