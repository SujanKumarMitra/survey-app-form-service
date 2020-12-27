package com.github.mitrakumarsujan.formservice.dao;

import com.github.mitrakumarsujan.formmodel.model.form.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CacheableFormDao implements FormDao {

    public static final String CACHE_NAME = "forms";

    @Autowired
    @Qualifier("formDaoImpl")
    private FormDao proxyFormDao;

    @Override
    @CachePut(cacheNames = {CACHE_NAME}, key = "#form.id")
    public Form save(Form form) {
        return proxyFormDao.save(form);
    }

    @Override
    @Cacheable(cacheNames = {CACHE_NAME}, key = "#formId", unless = "#result == null")
    public Optional<Form> find(String formId) {
        return proxyFormDao.find(formId);
    }
}
