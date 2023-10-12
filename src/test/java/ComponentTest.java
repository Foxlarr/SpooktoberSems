import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ComponentTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testLeafOperation() {
        Component leaf = new Leaf();
        leaf.operation();
        String expected = "Leaf operation\n";
        String actual = outputStream.toString().replaceAll("\\r\\n", "\n"); // Нормализация строк
        assertEquals(expected, actual);
    }

    @Test
    void testCompositeOperation() {
        Composite composite = new Composite();
        Component leaf1 = new Leaf();
        Component leaf2 = new Leaf();
        composite.addChild(leaf1);
        composite.addChild(leaf2);

        composite.operation();

        String expected = "Composite operation\nLeaf operation\nLeaf operation\n";
        String actual = outputStream.toString().replaceAll("\\r\\n", "\n"); // Нормализация строк
        assertEquals(expected, actual);
    }

    @Test
    void testCompositeWithNestedComposites() {
        Composite rootComposite = new Composite();
        Composite composite1 = new Composite();
        Composite composite2 = new Composite();
        Component leaf1 = new Leaf();
        Component leaf2 = new Leaf();

        rootComposite.addChild(composite1);
        rootComposite.addChild(leaf1);
        composite1.addChild(leaf2);
        composite1.addChild(composite2);

        rootComposite.operation();

        assertTrue(outputStream.toString().contains("Composite operation"));
        assertTrue(outputStream.toString().contains("Leaf operation"));
    }

}
