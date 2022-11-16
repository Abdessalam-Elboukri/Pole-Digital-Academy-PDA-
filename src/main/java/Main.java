
import com.example.pole_digital_academy.Dao.DaoFactory;
import com.example.pole_digital_academy.Dao.Responsible.IResponsibleDao;
import com.example.pole_digital_academy.Dao.ResponsibleType.IResponsibleTypeDao;
import com.example.pole_digital_academy.Dao.activity.IActivityDao;
import com.example.pole_digital_academy.Entities.*;
import com.example.pole_digital_academy.utils.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        //System.out.println(DaoFactory.getActivityDao().findById(5).getExercices().size());
         //insertres();
         //selectres();

        //addAdmin();

        //addResType();
      /*  IActivityDao ad=DaoFactory.getActivityDao();
        IResponsibleDao responsibleDao=DaoFactory.getResponsibleDao();
        IResponsibleTypeDao responsibleTypeDao=DaoFactory.getResponsibleTypeDao();

        //created responsible type
        ResponsibleType responsibleType= new ResponsibleType() ;
        responsibleType.setName("teacher");
        responsibleTypeDao.insert(responsibleType);
        //created responsible
        Responsible responsible = new Responsible();
        responsible.setFirstName("omar");
        responsible.setLastName("kazoum");
        responsible.setPhone("0667673755");
        responsible.setEmail("Okazoum@gmail.com");
        responsible.setRole(User.Role.PARTICIPANT);
        responsible.setUserStatus(User.UserStatusEnum.ACTIVE);
        responsible.setRes_type(responsibleTypeDao.getAll().stream().findAny().get());
        responsible.setDomaine("coach");
        responsibleDao.insert(responsible);
        //created activity
        Activity a1=new Activity();
        a1.setTitle("a1");
        a1.setStatus(Activity.ActivityStatusEnum.ACTIVE);
        a1.setActivityType(Activity.ActivityTypeEnum.EVENT);
        a1.setStartDate(LocalDate.now());
        a1.setEndDate(LocalDate.now());
        a1.setResponsible(responsible);
        responsible.setActivityList(Arrays.asList(a1));
        responsibleTypeDao.insert(responsibleType);
        ad.insert(a1);*/
       // System.out.println(ad.search(LocalDate.now().minusDays(100),LocalDate.now(), Activity.ActivityTypeEnum.EVENT).size()==1);

    }

    public static void insertres() {

        System.out.println("yaes");
        jakarta.persistence.EntityManagerFactory emf = Persistence.createEntityManagerFactory("pda_academy");
        EntityManager entityManager = emf.createEntityManager();
        System.out.println("no");

        ResponsibleType rs= new ResponsibleType() ;
        Responsible participant = new Responsible();
        participant.setFirstName("omar");
        participant.setLastName("kazoum");
        participant.setPhone("0667673755");
        participant.setEmail("Okazoum@gmail.com");
        participant.setRole(User.Role.PARTICIPANT);
        participant.setUserStatus(User.UserStatusEnum.ACTIVE);
        participant.setRes_type(rs);
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

    public static  void addResType(){
        jakarta.persistence.EntityManagerFactory emf = Persistence.createEntityManagerFactory("pda_academy");
        EntityManager entityManager = emf.createEntityManager();

        ResponsibleType restype = new ResponsibleType();
        ResponsibleType restype1 = new ResponsibleType();

        restype.setName("formateur");
        restype1.setName("intervenant");

        entityManager.getTransaction().begin();
        entityManager.persist(restype);
        entityManager.persist(restype1);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
