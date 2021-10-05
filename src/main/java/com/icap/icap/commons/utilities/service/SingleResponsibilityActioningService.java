package com.icap.icap.commons.utilities.service;

@FunctionalInterface
public interface SingleResponsibilityActioningService<O, I> {

    O execute(I i);

}
