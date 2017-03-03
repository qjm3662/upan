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
     *
     * @param transientObject
     */
    boolean update(T transientObject);

    /**
     * Remove an object form persistent storage in the database
     *
     * @param persistObject
     */
    boolean delete(T persistObject);


    T findOne(String hql, Object... params);

    List<T> findAll(String hql, Object... params);

    boolean save(T transientObject);

    List<T> queryAll(String hql, Object... params);
}
