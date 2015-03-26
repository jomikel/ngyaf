package io.theoperator.service;

import java.util.List;
import java.util.Map;

/**
 * Created by andreas on 2/19/15.
 */
public interface GenericService<T> {
    public List<T> list();
    public List<T> getPageList(String sidx, String sord, Integer page, Integer rows);
    public T getBean(Long id);
    public T save(T bean);
    public void delete(Long id);
}
