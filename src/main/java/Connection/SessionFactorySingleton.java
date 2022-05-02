package Connection;

import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class SessionFactorySingleton {

    private SessionFactorySingleton() {}

    private static class LazyHolder {
        static SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(Admin.class)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Expert.class)
                    .addAnnotatedClass(Offer.class)
                    .addAnnotatedClass(Order.class)
                    .addAnnotatedClass(Wallet.class)
                    .addAnnotatedClass(SysService.class)
                    .addAnnotatedClass(SysTransaction.class)
                    .addAnnotatedClass(Comment.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }

    public static SessionFactory getInstance() {
        return LazyHolder.INSTANCE;
    }
}
