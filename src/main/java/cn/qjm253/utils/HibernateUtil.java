package cn.qjm253.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by qjm3662 on 2017/1/20.
 */
public class HibernateUtil {
    public static final SessionFactory sessionFactory;
    //创建sessionFactory
    static {
        try{
            //采用默认的hibernate.hbm.xml来启动一个Configuration
            Configuration cfg = new Configuration().configure("/hibernate.cfg.xml");
//        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            sessionFactory = cfg.buildSessionFactory();
        }catch (Throwable ex){
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    //ThreadLocal可以隔离多个线程的数据共享，因此不需要再对线程同步
    public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

    //创建session
    public static Session currentSession() throws HibernateException {
        //通过线程对象.get()方法安全创建Session
        Session s = session.get();
        if(s == null){
            s = sessionFactory.openSession();
            session.set(s);
        }
        return s;
    }

    /**
     * 关闭session
     * @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        Session s = session.get();
        if(s != null){
            s.close();
        }
        session.set(null);
    }

}
