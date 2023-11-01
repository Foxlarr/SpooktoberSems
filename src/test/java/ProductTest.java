import org.example.Product;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductTest extends AbstractTest {

    private Session session;

    @BeforeEach
    void setUp() {
        session = getSession(); // Получаем сессию Hibernate перед каждым тестом
        session.beginTransaction(); // Начинаем транзакцию для тестов
    }

    @AfterEach
    void tearDown() {
        session.getTransaction().commit(); // Завершаем транзакцию после каждого теста
        session.close(); // Закрываем сессию
    }

    @Test
    void testProductCreation() {
        Product product = new Product();
        product.setMenuName("Test Product");
        product.setPrice(10.99);

        session.save(product);

        // Проверяем, что объект был сохранен в базу данных
        assertNotNull(product.getProductId());

        // Извлекаем объект из базы данных и сравниваем его с оригинальным объектом
        Product retrievedProduct = session.get(Product.class, product.getProductId());
        assertEquals(product, retrievedProduct);
    }

    @Test
    void testProductUpdate() {
        Product product = new Product();
        product.setMenuName("Original Product");
        product.setPrice(15.99);

        session.save(product);

        // Изменяем название и цену продукта
        product.setMenuName("Updated Product");
        product.setPrice(19.99);

        session.update(product);

        // Проверяем, что объект был успешно обновлен
        Product updatedProduct = session.get(Product.class, product.getProductId());
        assertEquals("Updated Product", updatedProduct.getMenuName());
        assertEquals(19.99, updatedProduct.getPrice(), 0.01);
    }

    @Test
    void testProductDeletion() {
        Product product = new Product();
        product.setMenuName("Product to Delete");
        product.setPrice(5.99);

        session.save(product);

        int productId = product.getProductId();

        // Удаляем продукт из базы данных
        session.delete(product);

        // Проверяем, что объект больше не существует в базе данных
        Product deletedProduct = session.get(Product.class, productId);
        assertNull(deletedProduct);
    }
}
