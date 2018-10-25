import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTest {

    @Test
    public void main() {
        ByteArrayOutputStream outStreamInards = new ByteArrayOutputStream();
        PrintStream outStream = new PrintStream(outStreamInards);
        System.setOut(outStream);
        ByteArrayInputStream stream = new ByteArrayInputStream("done".getBytes());

        System.setIn(stream);
        Main.main(new String[]{});
        Assert.assertTrue(outStreamInards.toString().contains("Enter the photo album id you'd like to know about: (Enter 'done' to finish)"));
    }

    @Test
    public void mainOnlyAllowsPositiveNumbers() {
        ByteArrayOutputStream outStreamInards = new ByteArrayOutputStream();
        PrintStream outStream = new PrintStream(outStreamInards);
        System.setOut(outStream);

        ByteArrayInputStream stream = new ByteArrayInputStream("-1\ndone".getBytes());
        System.setIn(stream);
        Main.main(new String[]{});
        Assert.assertTrue(outStreamInards.toString().contains("The album id should be a positive number"));

        stream = new ByteArrayInputStream("asdf\ndone".getBytes());
        System.setIn(stream);
        Main.main(new String[]{});
        Assert.assertTrue(outStreamInards.toString().contains("The album id should be a positive number"));
    }
}