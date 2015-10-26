package home.domain;

import home.common.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

import static home.common.RepositoryHelper.createCriteria;

@Repository
public class TaskRepository {

    public List<Task> getAll() {
        Criteria criteria = createCriteria(Task.class);
        return criteria.list();
    }

    public Task getById(String id) {
        if(id==null || id.isEmpty()){
            return null;
        }
        Criteria criteria = createCriteria(Task.class);
        criteria.add(Restrictions.eq("id", id));
        return (Task) criteria.uniqueResult();
    }

    public void saveOrUpdate(Task task) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(task);
        session.getTransaction().commit();
    }

    public void delete(Task task) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(task);
        session.getTransaction().commit();
    }
}
