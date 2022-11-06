
import com.example.pole_digital_academy.Entities.Activity;
import com.example.pole_digital_academy.Entities.Admin;
import com.example.pole_digital_academy.Entities.Participant;
import com.example.pole_digital_academy.Entities.User;
import com.example.pole_digital_academy.utils.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

         //insertres();
         //selectres();

        //addAdmin();


    }

    public static void insertres() {

        System.out.println("yaes");
        jakarta.persistence.EntityManagerFactory emf = Persistence.createEntityManagerFactory("pda_academy");
        EntityManager entityManager = emf.createEntityManager();
        System.out.println("no");


        Participant participant = new Participant();
        participant.setFirstName("omar");
        participant.setLastName("kazoum");
        participant.setPhone("0667673755");
        participant.setEmail("Okazoum@gmail.com");
        participant.setRole(User.Role.PARTICIPANT);
        participant.setUserStatus(User.UserStatusEnum.ACTIVE);
        participant.setDomaine("coach");
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
