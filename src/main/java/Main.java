
import com.example.pole_digital_academy.Entities.Admin;
import com.example.pole_digital_academy.Entities.Participant;
import com.example.pole_digital_academy.Entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {

         //insertres();
         //selectres();

        addAdmin();
/*
        EntityManager em= EntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        Activity activity=new Activity();
        activity.setActivityType(Activity.ActivityTypeEnum.COURSE);
        activity.setTitle("test activity");
        activity.setStatus(Activity.ActivityStatusEnum.ACTIVE);
        activity.setStartDate(LocalDate.now());
        activity.setEndDate(LocalDate.now().plusDays(12));
        activity.setDescription("some activity description");

        Exercice e1=new Exercice();
        e1.setActivity(activity);
        e1.setYear(2022);
        e1.setStartDate(LocalDate.now().plusDays(10));
        e1.setEndDate(LocalDate.now().plusDays(12));
        e1.setTitle("exercice 1");

        Exercice e2=new Exercice();
        e2.setActivity(activity);
        e2.setYear(2022);
        e2.setStartDate(LocalDate.now().plusDays(10));
        e2.setEndDate(LocalDate.now().plusDays(12));
        e2.setTitle("exercice 2");
        List<Exercice> exercices=new ArrayList<>();

        exercices.add(e1);
        exercices.add(e2);
        activity.setExercices(exercices);
        em.persist(activity);
        em.getTransaction().commit();


    }
    */
    }

    public static void insertres() {

        System.out.println("yaes");
        jakarta.persistence.EntityManagerFactory emf = Persistence.createEntityManagerFactory("pda_academy");
        EntityManager entityManager = emf.createEntityManager();
        System.out.println("no");


        Participant participant = new Participant();
        participant.setFirstName("abde");
        participant.setLastName("elboukri");
        participant.setPhone("463333");
        participant.setEmail("65HI5@gmail.com");
        participant.setRole(User.Role.PARTICIPANT);
        participant.setUserStatus(User.UserStatusEnum.ACTIVE);
        participant.setDomaine("developer");
        //=================
        entityManager.getTransaction().begin();
        entityManager.persist(participant);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void selectres() {
        jakarta.persistence.EntityManagerFactory emf = Persistence.createEntityManagerFactory("pda_academy");
        EntityManager entityManager = emf.createEntityManager();
        System.out.println("no");


        Query query = entityManager.createQuery("SELECT r FROM Participant r  where r.firstName = :firstname", Participant.class);
        query.setParameter("firstname", "abde");
        List<Participant> resultres = query.getResultList();
        System.out.println("========" + resultres.size());
        resultres.forEach(System.out::println);
        entityManager.close();
    }

    public static void addAdmin(){
        System.out.println("yaes");
        jakarta.persistence.EntityManagerFactory emf = Persistence.createEntityManagerFactory("pda_academy");
        EntityManager entityManager = emf.createEntityManager();
        System.out.println("no");


        Admin admin = new Admin();
        admin.setFirstName("MYADMIN");
        admin.setLastName("REGEX");
        admin.setPhone("0653292739");
        admin.setEmail("admin@gmail.com");
        admin.setRole(User.Role.ADMIN);
        admin.setUserStatus(Admin.UserStatusEnum.ACTIVE);
        admin.setPassword("1234");
        //=================
        entityManager.getTransaction().begin();
        entityManager.persist(admin);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
