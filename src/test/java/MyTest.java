import cn.qjm253.entity.FileInfo;
import cn.qjm253.entity.FollowInfo;
import cn.qjm253.entity.User;
import cn.qjm253.utils.HibernateUtil;
import org.apache.shiro.codec.Base64;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by qjm3662 on 2017/3/2 0002.
 */
public class MyTest {

    private Session session;
    Transaction t;

    @Before
    public void init() {
//        session = HibernateUtil.currentSession();
//        t = session.beginTransaction();
    }
//    @Test
//    public void addUserInfo() {
////        FileInfo f1 = new FileInfo("《你还好吗》1", "1024", "sdfvse", System.currentTimeMillis(), System.currentTimeMillis(), 1, true, "ada");
////        FileInfo f2 = new FileInfo("《你还好吗》2", "1024", "sdfvse", System.currentTimeMillis(), System.currentTimeMillis(), 1, true, "ada");
////        FileInfo f3 = new FileInfo("《你还好吗》3", "1024", "sdfvse", System.currentTimeMillis(), System.currentTimeMillis(), 1, true, "ada");
//
////        Set<FileInfo> fs = new HashSet<FileInfo>();
////        fs.add(f1);
////        fs.add(f2);
////        fs.add(f3);
//
//        User u1 = new User("Robbin1", "123456", "null", "The better day", 1);
//        User u2 = new User("Robbin2", "123456", "null", "The better day", 1);
//        User u3 = new User("Robbin3", "123456", "null", "The better day", 1);
//
////        Set<User> us = new HashSet<User>();
////        us.add(u2);
////        us.add(u3);
////        FollowInfo followInfo = new FollowInfo();
////        followInfo.setFollowing(us);
////
////        u1.setShares(fs);
////        u1.setFollowInfo(followInfo);
////
////        session.save(f1);
////        session.save(f2);
////        session.save(f3);
//
//        session.save(u2);
//        session.save(u3);
////        session.save(followInfo);
//        session.save(u1);
//    }

//    @Test
//    public void queryUserInfo() {
////        List list = session.createQuery("from User u where u.username = :name")
////                .setParameter("name", "Robbin1")
////                .list();
////        System.out.println(Arrays.toString(list.toArray()));
//
//        String hql = "select new FileInfo(fileName, fileSize, identifyCode, createTime, updateTime) from FileInfo f where f.identifyCode = ?";
////        String hql = "select new FileInfo(fileName, fileSize, identifyCode, createTime, updateTime, owner) from FileInfo f where f.identifyCode = ?";
//
//        List list = session.createQuery(hql)
//                .setParameter(0, "sdfvse")
//                .list();
//        System.out.println(Arrays.toString(list.toArray()));
//    }

    @Test
    public void test(){
        FollowInfo followInfo = session.get(FollowInfo.class, 4);
        User user = followInfo.getOther();
        System.out.println(followInfo.toString());
        System.out.println(user.toString());
    }

    @After
    public void destroy() {
//        if (t != null) {
//            t.commit();
//        }

    }
}
