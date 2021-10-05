package com.icap.icap.commons.utilities.jpa;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @param <T> The Entity class type that is being used, to which the specification is to be created for.
 * @author wilson
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
public class CustomSpecificationTemplateImpl<T> implements Specification<T> {

    private final SearchCriteria searchCriteria;

    CustomSpecificationTemplateImpl(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    /**
     * @param root    @see org.springframework.data.jpa.domain.Specification
     * @param query   @see org.springframework.data.jpa.domain.Specification
     * @param builder @see org.springframework.data.jpa.domain.Specification
     *
     *                <p>This method created a predication for a where clause.
     *                This determines the action and predicate to be created
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        val keys = searchCriteria.getKey().split(Pattern.quote("."));


        if (searchCriteria.getOperation().equalsIgnoreCase(Operations.GREATER_THAN.sign)) {

            return builder.greaterThanOrEqualTo(getRoot(searchCriteria.getKey(), root, builder, keys),
                    searchCriteria.getValue().toString());

        } else if (searchCriteria.getOperation().equalsIgnoreCase(Operations.LESS_THAN.sign)) {

            return builder.lessThanOrEqualTo(getRoot(searchCriteria.getKey(), root, builder, keys), searchCriteria.getValue().toString());

        } else if (searchCriteria.getOperation().equalsIgnoreCase(Operations.EQUALS.sign)) {

            val type = getRoot(searchCriteria.getKey(), root, builder, keys).getJavaType();

            if (type.equals(String.class)) {
                log.debug("### Specification binding parameter type is String");
                return builder.like(getRoot(searchCriteria.getKey(), root, builder, keys), "%" + searchCriteria.getValue() + "%");
            } else if (type.equals(Boolean.class) || type.isAssignableFrom(Boolean.class) || type.getName().equalsIgnoreCase("boolean")) {
                log.debug("### Specification binding parameter type is boolean");
                return builder.equal(getRoot(searchCriteria.getKey(), root, builder, keys), Boolean.parseBoolean(String.valueOf(searchCriteria.getValue())));
            } else {
                log.debug("### Specification binding parameter type is {}", type.getName());
                return builder.equal(getRoot(searchCriteria.getKey(), root, builder, keys), searchCriteria.getValue());
            }
        }

        return null;
    }

    private Expression getRoot(String key, Root<T> root, CriteriaBuilder builder, String... keys) {

        Arrays.asList(keys).forEach(key1 -> log.trace("### {}", key1));

        if (keys.length > 1) {

//            val clazz = root.getJavaType();
//
//            val fields = clazz.getDeclaredFields();
//
//            val field = Arrays.stream(fields).filter(field1 -> field1.getName().equals(keys[0])).findFirst()
//                    .orElseThrow(()-> new NoSuchElementException("No field with name" + keys[0] +" was found"));

//            val fieldClass = field.getType();

//            var newRoot = builder.createQuery(clazz).from(clazz).join(keys[0], JoinType.RIGHT);
//
//            Expression expression = newRoot.get(keys[1]);

            var newRoot = root.get(keys[0]);

            for (int i = 1; i < (keys.length - 1); i++) {
                newRoot = newRoot.get(keys[i]);
            }

            return newRoot.get(keys[keys.length - 1]);
        }

        return root.get(key);
    }

}
