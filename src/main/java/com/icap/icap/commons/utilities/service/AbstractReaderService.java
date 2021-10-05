package com.icap.icap.commons.utilities.service;

import com.icap.icap.commons.utilities.jpa.BaseDao;
import com.icap.icap.commons.utilities.jpa.CustomSpecificationTemplateImplBuilder;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.NoSuchElementException;

import static java.util.Objects.isNull;

public abstract class AbstractReaderService<T> {

    protected final BaseDao<T> repository;

    public AbstractReaderService(BaseDao<T> repository) {
        this.repository = repository;
    }

    public T findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Record not found for the given id : " + id));
    }

    public Page<T> findAll(Pageable pageable, String searchQuery) {
        if (isNull(searchQuery))
            return repository.findAll(pageable);
        val spec = new CustomSpecificationTemplateImplBuilder<T>()
                .buildSpecification(searchQuery);
        return repository.findAll(spec, pageable);
    }

    public Collection<T> findAll() {
        return repository.findAll();
    }

    public boolean existById(long id) {
        return repository.existsById(id);
    }

    public Collection<T> findByIdsIn(Collection<Long> ids) {
        return repository.findAllById(ids);
    }

}
