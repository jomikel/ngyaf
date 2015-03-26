package io.theoperator.service.impl;

import io.theoperator.repository.GenericRepository;
import io.theoperator.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by andreas on 2/19/15.
 */
public class GenericServiceImpl<T> implements GenericService<T> {

    private GenericRepository genericRepository;

    public GenericServiceImpl() {

    }

    @Autowired
    public GenericServiceImpl(GenericRepository repository) {
        this.genericRepository = repository;
    }

    @Override
    public List<T> list() {
        return this.genericRepository.list();
    }

    @Override
    public List<T> getPageList(String sidx, String sord, Integer page, Integer rows) {
        return this.genericRepository.getPageList(sidx, sord, page, rows);
    }

    @Override
    public T getBean(Long id) {
        return (T)this.genericRepository.getBean(id);
    }

    @Override
    public T save(T bean) {
        return (T)this.genericRepository.save(bean);
    }

    @Override public void delete(Long id) {
        this.genericRepository.delete(id);
    }
}
