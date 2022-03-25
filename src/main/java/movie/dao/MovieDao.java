package movie.dao;


import movie.entity.Movie;
import movie.movieRents.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

import java.util.List;

public class MovieDao {


    public void save(String title, int productionYear, int durationVideo_min) {

        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

             Movie movie=   new Movie(title, productionYear, durationVideo_min);

            session.persist(movie);


        transaction.commit();
        session.close();

    }

    public List<Movie> selectAll() {
        SessionFactory sessionFactory = movie.movieRents.HibernateUtils.getSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cr = cb.createQuery(Movie.class);
        Root<Movie> root = cr.from(Movie.class);

        cr.select(root).orderBy(cb.asc(root.get("title")));

        Query<Movie> newQuery = session.createQuery(cr);
        List<Movie> resultForQuery = newQuery.getResultList();


        transaction.commit();
        session.close();
        return resultForQuery;
    }

    public List<Movie> yearsInBetween(int afterYear, int beforeYear) {
        SessionFactory sessionFactory = movie.movieRents.HibernateUtils.getSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cr = cb.createQuery(Movie.class);
        Root<Movie> root = cr.from(Movie.class);

        cr.select(root).where(cb.between(root.get("productionYear"), afterYear, beforeYear)).orderBy(cb.asc(root.get("productionYear")));

        Query<Movie> newQuery = session.createQuery(cr);
        List<Movie> resultForQuery = newQuery.getResultList();

        transaction.commit();
        session.close();

        return resultForQuery;

    }

    public List<Movie> movieDurationLongerThan(int duration) {
        SessionFactory sessionFactory = movie.movieRents.HibernateUtils.getSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cr = cb.createQuery(Movie.class);
        Root<Movie> root = cr.from(Movie.class);

        cr.select(root).where(cb.gt(root.get("durationVideo_min"), duration)).orderBy(cb.desc(root.get("durationVideo_min")));

        Query<Movie> newQuery = session.createQuery(cr);
        List<Movie> resultForQuery = newQuery.getResultList();


        transaction.commit();
        session.close();
        return resultForQuery;

    }

    public List<Movie> movieDurationShorterThan(int duration) {
        SessionFactory sessionFactory = movie.movieRents.HibernateUtils.getSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cr = cb.createQuery(Movie.class);
        Root<Movie> root = cr.from(Movie.class);

        cr.select(root).where(cb.lt(root.get("durationVideo_min"), duration)).orderBy(cb.desc(root.get("durationVideo_min")));

        Query<Movie> newQuery = session.createQuery(cr);
        List<Movie> resultForQuery = newQuery.getResultList();


        transaction.commit();
        session.close();
        return resultForQuery;

    }

    public List<Movie> containingPhrase(String column, String value) {
        SessionFactory sessionFactory = movie.movieRents.HibernateUtils.getSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cr = cb.createQuery(Movie.class);
        Root<Movie> root = cr.from(Movie.class);

        cr.select(root).where(cb.like(cb.lower(root.get(column)), "%" + value.toLowerCase() + "%")).orderBy(cb.asc(root.get(column)));

        Query<Movie> newQuery = session.createQuery(cr);
        List<Movie> resultForQuery = newQuery.getResultList();

        transaction.commit();
        session.close();
        return resultForQuery;
    }


    public List<Movie> findName(String column, String value) {
        SessionFactory sessionFactory = movie.movieRents.HibernateUtils.getSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cr = cb.createQuery(Movie.class);
        Root<Movie> root = cr.from(Movie.class);

        cr.select(root).where(cb.lower(root.get(column)).in(value.toLowerCase()));

        Query<Movie> newQuery = session.createQuery(cr);
        List<Movie> resultForQuery = newQuery.getResultList();


        transaction.commit();
        session.close();
        return resultForQuery;
    }


}
