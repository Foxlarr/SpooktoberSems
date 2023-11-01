import org.example.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.*;

public class ProductTest {
    private static SessionFactory factory;

    @BeforeClass
    public static void setUp() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    @AfterClass
    public static void tearDown() {
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    public void testCreateProduct() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Product product = new Product();
            product.setMenuName("New Product");
            product.setPrice(99.99);
            session.save(product);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void testReadProduct() {
        Session session = factory.openSession();
        Product product = session.get(Product.class, 1); // Assuming product with ID 1 exists
        session.close();

        Assert.assertNotNull(product);
    }

    @Test
    public void testUpdateProduct() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Product product = session.get(Product.class, 1); // Assuming product with ID 1 exists
            product.setMenuName("Updated Product");
            product.setPrice(49.99);
            session.update(product);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void testDeleteProduct() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Product product = session.get(Product.class, 1); // Assuming product with ID 1 exists
            session.delete(product);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
