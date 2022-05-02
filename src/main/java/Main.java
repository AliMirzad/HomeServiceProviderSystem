import Connection.SessionFactorySingleton;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    }
}
