package cn.qjm253.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/3/003.
 */
public interface BaseDao<T, PK extends Serializable> {
//    /**
//     * persist the newInstance into database
//     *
//     * @param newInstance
//     * @return
//     */
//    PK create(T newInstance);


    /**
     * get a object form database by id
     *
     * @param id
     * @return
     */
    T get(PK id);


    /**
     * save change made to a persist object
     * @param hql
     * @param paramsName
     * @param params
     * @return
     */
    boolean update(String hql, String[] paramsName, Object... params);

    /**
     * Remove an object form persistent storage in the database
     *
     * @param persistObject
     */
    boolean delete(T persistObject);


    T query(String hql, String[] paramsName, Object... params);

    boolean save(T transientObject);

    List<T> queryAll(String hql, String[] paramsName, Object... params);
}
