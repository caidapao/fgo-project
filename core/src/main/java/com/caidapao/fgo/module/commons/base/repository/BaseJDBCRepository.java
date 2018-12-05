package com.caidapao.fgo.module.commons.base.repository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

/**
 * Created by caidapao on 2017/11/28.
 */
public class BaseJDBCRepository {
    protected static final String SORT_DIRECTION_DESC = "desc";
    protected static final String SORT_DIRECTION_ASC = "asc";
    private Logger logger = Logger.getLogger(BaseJDBCRepository.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 列表查询方法，查询结果是一个bean集合
     * @param sql 语句
     * @param mappedClass 序列化对象，pojo属性字段需要与sql结果列一一对应
     * @param args sql参数值，根据sql ‘？’参数顺利传入
     * @param <T>
     * @return
     */
    protected <T> List<T> query(String sql, Class<T> mappedClass, Object... args)  {
        logger.info("from jdbc query list :"+sql);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(mappedClass), args);
    }

    /**
     * 单结果查询，查询结果只有一条并且只有一列，比如统计数量
     * @param sql 语句
     * @param mappedClass 结果类型（基本数据类型)如Long
     * @param args sql参数列表
     * @param <T>
     * @return
     */
    protected <T> T queryForObject(String sql, Class<T> mappedClass, Object... args){
        logger.info("from jdbc query list :"+sql);
        return jdbcTemplate.queryForObject(sql, mappedClass, args);
    }

    /**
     * 单列结果查询，查询结果可有多条但只有一列，比如查询ID列表
     * @param sql 语句
     * @param mappedClass 结果类型（基本数据类型） 如Long
     * @param args sql参数列表
     * @param <T>
     * @return
     */
    protected <T> List<T> queryForList(String sql, Class<T> mappedClass, Object... args){
        logger.info("from jdbc query list :"+sql);
        return jdbcTemplate.queryForList(sql, mappedClass, args);
    }


    /**
     * 列表查询方法
     * @param sql 语句
     * @param mappedClass 序列化对象，pojo属性字段需要与sql结果列一一对应
     * @param args sql参数值
     * @param <T>
     * @param sort 排序
     * @return
     */
    protected <T> List<T> query(String sql, Class<T> mappedClass, Sort sort, Object... args)  {
        String newSql = processSort(sql,sort);
        logger.info("from jdbc query list sort:"+newSql);
        return jdbcTemplate.query(newSql, new BeanPropertyRowMapper(mappedClass), args);
    }

    /**
     * 分页查询方法
     * @param sql
     * @param mappedClass
     * @param var1
     * @param args
     * @param <T>
     * @return
     */
    protected  <T> Page<T> queryPage(String sql, Class<T> mappedClass, Pageable var1,Object... args)  {
        String newSql = sql;
        //创建新的分页SQL
        if(var1!=null) {
            newSql = processSort(sql, var1.getSort());
            newSql = processPagination(newSql, var1.getPageNumber(), var1.getPageSize());
        }
        logger.info("from jdbc query page:"+newSql);
        logger.info("from jdbc query page:"+processCountSQL(sql));

        //查询总数
        Integer total = jdbcTemplate.queryForObject(processCountSQL(sql), Integer.TYPE, args);
        //结果集
        List<T> result = jdbcTemplate.query(newSql, new BeanPropertyRowMapper(mappedClass), args);

        return new PageImpl<T>(result,var1,total);
    }

    /**
     * 内部处理分页算法
     * @param oldSQL
     * @param pageNo
     * @param pageSize
     * @return
     */
    private String processPagination(String oldSQL,int pageNo, int pageSize){
        StringBuffer sql = new StringBuffer(oldSQL);
        if (pageSize > 0) {
            int firstResult = (pageNo - 1)*pageSize;
            if (firstResult <= 0) {
                sql.append(" limit ").append(pageSize);
            } else {
                sql.append(" limit ").append(firstResult).append(",").append(pageSize);
            }
        }
        return sql.toString();
    }

    /**
     * 处理排序算法
     * @param oldSQL
     * @param sort
     * @return
     */
    private String processSort(String oldSQL,Sort sort){
        StringBuffer sql = new StringBuffer(oldSQL);
        if(sort!=null && sort.iterator().hasNext()){
            sql.append(" order by");
            Iterator<Sort.Order> var1 = sort.iterator();
            while(var1.hasNext()) {
                Sort.Order order = var1.next();
                sql.append(" ").append(order.getProperty()).append(" ");
                sql.append(order.getDirection()!=null?order.getDirection():"");
                sql.append(var1.hasNext()?",":"");//后面还有排序条件，加个','
            }
        }
        return sql.toString();
    }

    /**
     * 处理总数count 算法
     * @param oldSQL
     * @return
     */
    private String processCountSQL(String oldSQL){
        if(oldSQL==null || oldSQL.trim().equals("")){
            throw new RuntimeException("oldSQL could not null");
        }
        return String.format("SELECT COUNT(1) FROM ( %s ) AS _CRM_TABLE_GEN_1", oldSQL);
    }


    /**
     * 创建一个分页查询对象
     * @param page 页码
     * @param size 分页大小
     * @param orderMap 排序列表 key：propertyName ， value：desc（降） 或者 asc（升）。比如：name:desc
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
            if (SORT_DIRECTION_DESC.equals(dir)){
                orders.add(new Sort.Order(Sort.Direction.DESC, toColumnName(property)));
            }else {
                orders.add(new Sort.Order(Sort.Direction.ASC, toColumnName(property)));
            }
        }
        return new PageRequest(page, size , new Sort(orders));
    }

    /**
     * 将domain的字段名转成MySQL表的列名
     * 如：sysUserId转成sys_user_id
     * @param propertyName 字段名，需要符合驼峰写法
     * @return 相应的列名
     */
    protected String toColumnName(String propertyName){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < propertyName.length(); i++){
            String tem = String.valueOf(propertyName.charAt(i));
            //遇到大写字母，前面加一个_
            if (i > 0 && tem.matches("[A-Z]")){
                stringBuilder.append("_");
            }
            stringBuilder.append(tem.toLowerCase());
        }
        return stringBuilder.toString();
    }
}
