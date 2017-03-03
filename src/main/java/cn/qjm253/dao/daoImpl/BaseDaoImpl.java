package cn.qjm253.dao.daoImpl;

import cn.qjm253.dao.BaseDao;
import cn.qjm253.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/3/003.
 */
@Transactional
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK>{
    private Class<T> tClass;


    public boolean save(T transientObject) {
        try {
            Session session = HibernateUtil.currentSession();
            Transaction t = session.beginTransaction();
            session.save(transientObject);
            t.commit();
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }



    public T get(PK id) {
        Session session = HibernateUtil.currentSession();
        Transaction transaction = session.beginTransaction();
        T t= session.get(tClass, id);
        return t;
    }

    public boolean update(T transientObject) {
        try {
            Session session = HibernateUtil.currentSession();
//            session.saveOrUpdate();
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(T persistObject) {
        try {
//            hibernateTemplate.delete(persistObject);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public T findOne(String hql, Object... params) {
            return null;
    }


    public List<T> findAll(String hql, Object... params) {
        return null;
    }

    public List<T> queryAll(String hql, Object... params) {
        return null;
    }

}
