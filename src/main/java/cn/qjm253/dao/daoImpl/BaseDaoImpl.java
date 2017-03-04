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
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {
    private Class<T> tClass;

    /**
     * 保存对象到数据库
     *
     * @param transientObject
     * @return
     */
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
        T t = session.get(tClass, id);
        transaction.commit();
        return t;
    }

    public boolean update(T transientObject) {
        try {
            Session session = HibernateUtil.currentSession();
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(T persistObject) {
        try {

            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public T query(String hql, String[] paramsName, Object... params) {
        try {
            List list = query_(hql, paramsName, params);
            return (T) list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<T> queryAll(String hql, String[] paramsName, Object... params) {
        try {
            List<T> list = query_(hql, paramsName, params);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List query_(String hql, String[] paramsName, Object... params) throws Exception {
        Session session = HibernateUtil.currentSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery(hql);
        if(params.length != paramsName.length){
            return null;
        }
        for (int i = 0; i < params.length; i++) {
            query.setParameter(paramsName[i], params[i]);
        }
        List list = query.list();
        t.commit();
        if (list.size() == 0) {
            return null;
        }
        return list;
    }

}
