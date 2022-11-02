import com.example.pole_digital_academy.Entities.Participant;
import com.example.pole_digital_academy.Entities.Responsible;
import com.example.pole_digital_academy.Entities.ResponsibleType;
import com.example.pole_digital_academy.Entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class main {
    public static void main(String[] args) {

        insertres();
        selectres();


    }
        public static void insertres(){

            System.out.println("yaes");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
            EntityManager entityManager = emf.createEntityManager();
            System.out.println("no");


            Participant participant = new Participant();
            participant.setFirstName("abde");
            participant.setLastName("elboukri");
            participant.setPhone("06233385331");
            participant.setEmail("abde2553@gmail.com");
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
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
            EntityManager entityManager = emf.createEntityManager();
            System.out.println("no");


            Query query = entityManager.createQuery("SELECT r FROM User r  where r.firstName = :firstname",User.class);
            query.setParameter("firstname" , "abde");
            List<Participant> resultres = query.getResultList();
            System.out.println("========"+resultres.size());
            resultres.forEach(System.out::println);
            entityManager.close();
        }

}
