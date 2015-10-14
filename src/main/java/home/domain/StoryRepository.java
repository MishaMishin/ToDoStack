package home.domain;

import home.common.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

import static home.common.HibernateUtils.getSessionFactory;
import static home.common.RepositoryHelper.createCriteria;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.ne;

@Repository
public class StoryRepository {

    public Story getById(String id) {
        Criteria criteria = createCriteria(Story.class);
        criteria.add(eq("id", id));
        return (Story) criteria.uniqueResult();
    }

    public Story getDefaultStory() {
        Criteria criteria = createCriteria(Story.class);
        criteria.add(eq("name", "defaultStory"));
        return (Story) criteria.uniqueResult();
    }

    public List<Story> getAll() {
        Criteria criteria = createCriteria(Story.class);
        criteria.add(ne("name", "defaultStory"));
        criteria.addOrder(Order.asc("name"));
        return criteria.list();
    }

    public void saveOrUpdate(Story story) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(story);
        session.getTransaction().commit();
    }
}
