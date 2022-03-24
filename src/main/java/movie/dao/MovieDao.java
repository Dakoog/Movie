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
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MovieDao {



    public void saveAll(String title, int productionYear, int durationVideo_min) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Movie> movies = new ArrayList<>(List.of(
                new Movie(title, productionYear, durationVideo_min)));
        for (Movie movie : movies
        ) {
            session.persist(movie);

        }
        transaction.commit();
        session.close();
    }

    public void selectAll() {
        SessionFactory sessionFactory = movie.movieRents.HibernateUtils.getSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cr = cb.createQuery(Movie.class);
        Root<Movie> root = cr.from(Movie.class);

        cr.select(root).orderBy(cb.asc(root.get("title")));

        Query<Movie> newQuery = session.createQuery(cr);
        List<Movie> resultForQuery = newQuery.getResultList();

        System.out.println("\n   * * * * * * * * * * * *  M O V I E * * * * * * * * * * * *\n");
        System.out.println(resultForQuery);
        System.out.println();
        transaction.commit();
        session.close();
    }

    public void queryBetween(String string, int int1, int int2) {
        SessionFactory sessionFactory = movie.movieRents.HibernateUtils.getSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cr = cb.createQuery(Movie.class);
        Root<Movie> root = cr.from(Movie.class);

        cr.select(root).where(cb.between(root.get(string), int1, int2)).orderBy(cb.asc(root.get(string)));

        Query<Movie> newQuery = session.createQuery(cr);
        List<Movie> resultForQuery = newQuery.getResultList();

        System.out.println("\n * * * * " + string.toUpperCase() + " pomiędzy " + int1 + " a " + int2 + " * * * *");
        System.out.println(resultForQuery);
        System.out.println();

    }

    public void queryGreaterThan(String string, int integer) {
        SessionFactory sessionFactory = movie.movieRents.HibernateUtils.getSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cr = cb.createQuery(Movie.class);
        Root<Movie> root = cr.from(Movie.class);

        cr.select(root).where(cb.gt(root.get(string), integer)).orderBy(cb.asc(root.get(string)));

        Query<Movie> newQuery = session.createQuery(cr);
        List<Movie> resultForQuery = newQuery.getResultList();

        System.out.println("\n * * * * " + string.toUpperCase() + " większy niż " + integer + " * * * *");


        System.out.println(resultForQuery);
        System.out.println();
        transaction.commit();
        session.close();
    }

    public void queryLessThan(String string, int integer) {
        SessionFactory sessionFactory = movie.movieRents.HibernateUtils.getSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cr = cb.createQuery(Movie.class);
        Root<Movie> root = cr.from(Movie.class);

        cr.select(root).where(cb.lt(root.get(string), integer)).orderBy(cb.desc(root.get(string)));

        Query<Movie> newQuery = session.createQuery(cr);
        List<Movie> resultForQuery = newQuery.getResultList();

        System.out.println("\n * * * * " + string.toUpperCase() + " mniejszy niż " + integer + " * * * *");
        System.out.println(resultForQuery);
        System.out.println();

        transaction.commit();
        session.close();

    }

    public List<Movie> containingPhrase(String column, String value) {
        SessionFactory sessionFactory = movie.movieRents.HibernateUtils.getSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cr = cb.createQuery(Movie.class);
        Root<Movie> root = cr.from(Movie.class);

        cr.select(root).where(cb.like(cb.lower(root.get(column)), "%"+value.toLowerCase()+"%")).orderBy(cb.asc(root.get(column)));

        Query<Movie> newQuery = session.createQuery(cr);
        List<Movie> resultForQuery = newQuery.getResultList();

        transaction.commit();
        session.close();
        return resultForQuery;
    }

    public void isNull(String string) {
        SessionFactory sessionFactory = movie.movieRents.HibernateUtils.getSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cr = cb.createQuery(Movie.class);
        Root<Movie> root = cr.from(Movie.class);

        cr.select(root).where(cb.isNull(root.get(string))).orderBy(cb.asc(root.get(string)));

        Query<Movie> newQuery = session.createQuery(cr);
        List<Movie> resultForQuery = newQuery.getResultList();

        System.out.println("\n * * * * " + string.toUpperCase() + " * * * *");
        System.out.println(resultForQuery);
        System.out.println();

        transaction.commit();
        session.close();

    }

    public void findName(String column, String value) {
        SessionFactory sessionFactory = movie.movieRents.HibernateUtils.getSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cr = cb.createQuery(Movie.class);
        Root<Movie> root = cr.from(Movie.class);

        cr.select(root).where(cb.lower(root.get(column)).in(value.toLowerCase()));

        Query<Movie> newQuery = session.createQuery(cr);
        List<Movie> resultForQuery = newQuery.getResultList();

        System.out.println("\n * * * * " + column.toUpperCase() + ": " + value.toLowerCase() + " * * * *");
        if (resultForQuery.isEmpty()) {
            System.out.println("Niestety, nie ma filmu o podanym '" + value + "' tytule.");
        }
        System.out.println(resultForQuery);
        System.out.println();
        transaction.commit();
        session.close();
    }


}
