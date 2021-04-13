package com.wush.cars.dto.mapper;

import java.util.Map;

/**
 * Defines contract for mapping beans from source to destination.
 * @param <S>
 * @param <D>
 */
public interface BeanMapper<S, D> {
    /**
     * Maps a source bean of type S to a destination bean of type D.
     * This acts as a reusable mapper- write once and use everywhere.
     * @param source
     * @return destination
     */
    D map(S source);
    D map(S source, Map<String, String> headers);
}