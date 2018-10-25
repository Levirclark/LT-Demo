import org.junit.Assert;
import org.junit.Test;

import javax.json.Json;

public class PhotoModelTest {

    @Test
    public void nullConstructionTest() {
        PhotoModel model = new PhotoModel(null);
        Assert.assertEquals("[No id] No title", model.toString());
    }

    @Test
    public void emptyContructorTest() {
        PhotoModel model = new PhotoModel(Json.createObjectBuilder().build());
        Assert.assertEquals("[No id] No title", model.toString());
    }

}