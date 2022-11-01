import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class main {
    public static void main(String[] args) {

        System.out.println("yaes");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        System.out.println("no");

    }
}
