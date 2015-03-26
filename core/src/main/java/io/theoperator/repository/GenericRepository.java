package io.theoperator.repository;

import io.theoperator.model.GenericBean;

import java.util.List;
import java.util.Map;

/**
 * Created by andreas on 2/19/15.
 */
public interface GenericRepository<T> {

    public List<T> list();
    public T getBean(Long id);
    public List<T> getPageList(String idx, String sord, Integer page, Integer rows);
    public T save(T bean);
    public void delete(Long id);

}
