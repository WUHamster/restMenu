package menu;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

/**
 * Created by WUHamster on 13.06.2016.
 */
public class MenuManipulator {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATest");
    static EntityManager em = emf.createEntityManager();
    static Scanner sc = new Scanner(System.in);

    public static void createBaseMenu(){
        em.getTransaction().begin();
        try {
            em.persist(new Dishes("Steak", 45, 400));
            em.persist(new Dishes("Soup", 15, 300));
            em.persist(new Dishes("Salad", 17, 350));
            em.persist(new Dishes("Ice-cream", 10, 150));
            Dishes d1 = new Dishes("Pizza", 30, 500);
            d1.setDiscount(true);
            Dishes d2 = new Dishes("Spaghetti", 25, 450);
            d2.setDiscount(true);
            em.persist(d1);
            em.persist(d2);
            em.getTransaction().commit();
        }
        catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static void viewMenu(){
        Query query = em.createQuery("SELECT c FROM Dishes c", Dishes.class);
        List<Dishes> dishes = (List<Dishes>) query.getResultList();
        for (Dishes dish : dishes) {
            System.out.println(dish);
        }
    }

    public static void addDish(){
        em.getTransaction().begin();
        try {
            System.out.println("Please enter dish's name");
            String name = sc.nextLine();
            System.out.println("Please enter dish's price");
            int price = Integer.parseInt(sc.nextLine());
            System.out.println("Please enter dish's weight");
            int weight = Integer.parseInt(sc.nextLine());
            Dishes dish = new Dishes(name, price, weight);
            System.out.println("Is this dish on discount? y/n");
            String discount = sc.nextLine();
            if (discount.equals("y")) { dish.setDiscount(true);}
            em.persist(dish);
            em.getTransaction().commit();
        }
        catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static void choosePrice() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory factory = configuration.buildSessionFactory(builder.build());
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(Dishes.class).add(Restrictions.between("Price", 300, 400));

        List<Dishes> dishes = (List<Dishes>) criteria.list();
        for (Dishes dish : dishes) {
            System.out.println(dish);
        }
    }
}
