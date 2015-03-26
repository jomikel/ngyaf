package io.theoperator.repository.impl;

import io.theoperator.model.GenericBean;
import io.theoperator.model.Person;
import io.theoperator.repository.GenericRepository;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * Created by andreas on 2/19/15.
 */

@Component
public abstract class GenericRepositoryImpl<T> implements GenericRepository<T> {

    private Class persistantBean;
    private SessionFactory sessionFactory;

    public GenericRepositoryImpl(Class<T> persistantBean) {
        this.persistantBean = persistantBean;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {

        return this.sessionFactory.openSession();

    }

    public T getBean(Long id) throws EntityNotFoundException {

        Session s = getSession();
        IdentifierLoadAccess byId = s.byId(this.persistantBean);
        T bean = (T)byId.load(id);
        if(bean == null)
            throw new EntityNotFoundException();

        return bean;
    }

    public List<T> list() {
        Session s = getSession();
        Query q = s.createQuery("from " + this.persistantBean.getName());
        return q.list();
    }

    //public GenericBean save(GenericBean bean) {

    //}

    //public Boolean delete(GenericBean bean) {

    //}

    @Override
    public List<T> getPageList(String sortField, String sortOrder, Integer page, Integer pageSize) {

        if(sortField.isEmpty())
            sortField = "id";

        Session s = this.getSession();

        String countQ = "Select count (p.id) from " + this.persistantBean.getName() + " p";
        Query countQuery = s.createQuery(countQ);
        Long countResults = (Long) countQuery.uniqueResult();
        if(countResults == null)
            countResults = 0l;
        int lastPageNumber = (int) ((countResults / pageSize) + 1);

        Criteria c = s.createCriteria(this.persistantBean.getName());

        c.setMaxResults(pageSize);
        c.setFirstResult((lastPageNumber - 1) * pageSize);

        if(sortOrder.equals("asc"))
            c.addOrder(Order.asc(sortField));
        else
            c.addOrder(Order.desc(sortField));

        List<T> lst = c.list();
        s.close();
        return lst;
    }

    public T save(T bean) {

        Session s = getSession();
        Transaction t = s.beginTransaction();

        T obj = (T) s.merge(bean);
        t.commit();
        return obj;
    }

    public void delete(Long id) {

        Session s = getSession();
        Query q = s.createQuery("delete from " + this.persistantBean.getName() + " where id = :id");
        q.setParameter("id", id);
        q.executeUpdate();
    }

}
