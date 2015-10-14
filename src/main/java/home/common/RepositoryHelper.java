package home.common;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RepositoryHelper {

    private static SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public static Criteria createCriteria(Class clazz) {
        Session session = sessionFactory.openSession();
        return session.createCriteria(clazz);
    }

    public static Session getSession() {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException ex) {
            session = sessionFactory.openSession();
        }
        return session;
    }


}
