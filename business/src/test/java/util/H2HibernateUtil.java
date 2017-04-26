
package util;

import org.apache.log4j.Logger;
import org.h2.tools.RunScript;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

public class H2HibernateUtil {
    private static H2HibernateUtil util = null;
    private static Logger log = Logger.getLogger(H2HibernateUtil.class);
    private static SessionFactory sessionFactory = null;
    private static final ThreadLocal threadLocal = new ThreadLocal();
    private static Transaction transaction = null;
    protected static Session session;

    public H2HibernateUtil() {
        try {
            String hibernatePropsFilePath = "/Users/dzianismitrakhovich/Documents/Java IDEA Projects/restaurant_Hibernate/DAO/src/test/resources/h2_hibernate.cfg.xml";
            File hibernatePropsFile = new File(hibernatePropsFilePath);

            Configuration configuration = new Configuration();
            configuration.configure(hibernatePropsFile);

            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            log.info("H2 factory created");
        } catch (Throwable ex) {
            log.error("Initial H2 SessionFactory creation failed." + ex);
            ex.printStackTrace();

            System.exit(0);
        }
    }

    public Session getSession () {
        session = (Session) threadLocal.get();
        if (session == null) {
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }
        log.info("Hibernate Session opened");
        return session;
    }

    public static void closeSession () {
        session = (Session) threadLocal.get();
        threadLocal.set(null);
        if (session != null) {
            session.close();
        }
        log.info("The session has been successfully closed");
    }

    @BeforeClass
    public static synchronized void getH2HibernateUtil(){
        if (util == null){
            util = new H2HibernateUtil();
        }
    }
    @Before
    public void initializeDatabase(){
        session = util.getSession();
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                try {
                    File script = new File("/Users/dzianismitrakhovich/Documents/Java IDEA Projects/restaurant_Hibernate/DAO/src/test/resources/pizza_test_data.sql");
                    RunScript.execute(connection, new FileReader(script));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    throw new RuntimeException("could not initialize with script ");

                }
            }
        });
    }

    @AfterClass
    public static void tearDown(){
        closeSession();
        sessionFactory.close();
    }

}
