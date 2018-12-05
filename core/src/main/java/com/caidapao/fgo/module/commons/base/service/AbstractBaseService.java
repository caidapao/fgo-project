package com.caidapao.fgo.module.commons.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caidapao on 2017/11/18.
 */
@Transactional(readOnly = true)
public abstract class AbstractBaseService <T,ID extends Serializable> {
    public static final String SORT_DESC = "desc";
    public static final String SORT_ASC = "asc";
    protected abstract JpaRepository<T,ID> getRepository();

    public List<T> findAll(){
        return getRepository().findAll();
    }

    public List<T> findAll(Sort var1){
        return getRepository().findAll(var1);
    }

    public List<T> findAll(Iterable<ID> var1){
        return getRepository().findAll(var1);
    }

    public Page<T> findAll(Pageable var1){
        return getRepository().findAll(var1);
    }

    @Transactional
    public <S extends T> S save(S var1){
        return getRepository().save(var1);
    }

    public T findOne(ID var1){
        return getRepository().findOne(var1);
    }

    public boolean exists(ID var1){
        return getRepository().exists(var1);
    }

    public long count(){
        return getRepository().count();
    }

    @Transactional
    public void delete(ID var1){
        getRepository().delete(var1);
    }

    /**
     * 创建一个分页查询对象
     * @param page 页码
     * @param size 分页大小
     * @param orderMap 排序列表 key：propertyName ， value：desc（降） 或者 asc（升）
     */
    protected PageRequest buildPageRequest(Integer page, Integer size, LinkedHashMap<String, String> orderMap){
        page = null == page || page < 0 ? 0 : page;
        size = null == size || size < 0 ? Integer.MAX_VALUE : size;

        if (null == orderMap || orderMap.isEmpty()){
            return new PageRequest(page, size);
        }

        List<Sort.Order> orders = new ArrayList<>(orderMap.size());
        for (Map.Entry<String, String> orderEntry : orderMap.entrySet()) {
            String property = orderEntry.getKey();
            String dir = orderEntry.getValue();
            if (SORT_DESC.equals(dir)){
                orders.add(new Sort.Order(Sort.Direction.DESC, property));
            }else {
                orders.add(new Sort.Order(Sort.Direction.ASC, property));
            }
        }
        return new PageRequest(page, size , new Sort(orders));
    }
}
