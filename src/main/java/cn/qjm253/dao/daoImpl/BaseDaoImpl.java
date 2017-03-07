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

import javax.annotation.Resource;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/3/003.
 */
@Transactional
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {
    private Class<T> tClass;
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    /**
     * 保存对象到数据库
     *
     * @param transientObject
     * @return
     */
    public boolean save(T transientObject) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.saveOrUpdate(transientObject);
            t.commit();
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            t.commit();
            return false;
        }
    }


    public T get(PK id) {
        Session session = sessionFactory.openSession();
        T t = session.get(tClass, id);
        return t;
    }

    public boolean update(String hql, String[] paramsName, Object... params) {
        Session session = sessionFactory.openSession();
        try {
            if(params.length != paramsName.length){
                return false;
            }
            Query query = session.createQuery(hql);
            for (int i = 0; i < paramsName.length; i++) {
                query.setParameter(paramsName[i], params[i]);
            }
            int result = query.executeUpdate();     //返回持久层中被修改的实体数目
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(T persistObject) {
        return false;
    }

    public boolean delete(String hql, String[] paramsName, Object... params) {
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
            if(list == null || list.size() == 0){
                return null;
            }
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
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery(hql);
        if(params.length != paramsName.length){
            return null;
        }
        for (int i = 0; i < params.length; i++) {
            query.setParameter(paramsName[i], params[i]);
        }
        List list = query.list();
        if (list.size() == 0) {
            return null;
        }
        return list;
    }

}
