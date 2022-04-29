package view;

import Connection.SessionFactorySingleton;
import org.hibernate.SessionFactory;

public class main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    }
}
