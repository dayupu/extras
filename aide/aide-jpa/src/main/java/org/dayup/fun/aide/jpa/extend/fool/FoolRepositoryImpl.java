
package org.dayup.fun.aide.jpa.extend.fool;

import org.dayup.fun.aide.jpa.extend.fool.supplier.FoolCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FoolRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements FoolRepository<T, ID> {


    private Class<T> domainClass;

    public FoolRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        domainClass = entityInformation.getJavaType();
    }

    @Override
    public List<T> search(Collection<FoolCondition> conditions) {
        return findAll(prepareSpec(conditions));
    }

    @Override
    public Page<T> search(Collection<FoolCondition> conditions, Pageable pageable) {
        return findAll(prepareSpec(conditions), pageable);
    }

    private Specification<T> prepareSpec(Collection<FoolCondition> conditions) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                for (FoolCondition condition : conditions) {
                    predicates.add(prePredicate(cb, root, condition));
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            }

            private Predicate prePredicate(CriteriaBuilder cb, Root<T> root, FoolCondition condition) {
                Expression exp = wrapExp(root, condition);
                Object value = condition.value()[0];
                switch (condition.operate()) {
                    case eq:
                        return cb.equal(exp, value);
                    case like:
                        return cb.like(exp, value.toString());
                    case isEmpty:
                        return cb.isEmpty(exp);
                    case isNotEmpty:
                        return cb.isNotEmpty(exp);
                    case lessThan:
                        return cb.lessThan(exp, (Comparable) value);
                    case lessThanEq:
                        return cb.lessThanOrEqualTo(exp, (Comparable) value);
                    case greaterThan:
                        return cb.greaterThan(exp, (Comparable) value);
                    case greaterThanEq:
                        return cb.greaterThanOrEqualTo(exp, (Comparable) value);
                    case between:
                        return cb.between(exp, (Comparable) value, (Comparable) condition.value()[1]);
                    case in:
                        return cb.in(exp).in(condition.value());
                }
                return null;
            }

            private Expression wrapExp(Root<T> root, FoolCondition condition) {

                Field field = getField(domainClass, condition);
                if (field == null) {
                    throw new IllegalArgumentException("Not found the field [" + condition.name() + "] of " +
                            domainClass);
                }
                return root.get(field.getName()).as(field.getType());
            }

            private Field getField(Class<?> clazz, FoolCondition condition) {
                while (clazz != Object.class) {
                    try {
                        return clazz.getDeclaredField(condition.name());
                    } catch (NoSuchFieldException e) {

                    }
                    clazz = clazz.getSuperclass();
                }
                return null;
            }


        };
    }
}
