package Connection;

import entity.AdminUser;
import entity.CustomerUser;
import entity.ExpertUser;
import entity.Service;
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
                    .addAnnotatedClass(AdminUser.class)
                    .addAnnotatedClass(CustomerUser.class)
                    .addAnnotatedClass(ExpertUser.class)
                    .addAnnotatedClass(Service.class)
                    .buildMetadata()
                    .buildSessionFactory();


        }
    }

    public static SessionFactory getInstance() {
        return LazyHolder.INSTANCE;
    }
}
