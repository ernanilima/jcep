package br.com.ernanilima.jcep.builder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PageableBuilder {

    public static Pageable create() {
        return PageRequest.of(0, 12);
    }

    public static MultiValueMap<String, String> getMultiPageable() {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("page", String.valueOf(create().getPageNumber()));
        multiValueMap.add("size", String.valueOf(create().getPageSize()));
        return multiValueMap;
    }
}
