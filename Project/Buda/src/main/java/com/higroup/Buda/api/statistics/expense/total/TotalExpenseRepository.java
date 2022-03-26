package com.higroup.Buda.api.statistics.expense.total;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class TotalExpenseRepository {
    @PersistenceContext
    EntityManager entityManager;
}
