import org.junit.Assert;
import org.junit.Test;

public class PhotoAlbumInfoFormatterTest {

    @Test
    public void getAlbumInfoStringFromJsonString() {
        String returned = new PhotoAlbumInfoFormatter().getAlbumInfoStringFromJsonString("[{\"id\": \"testId\", \"title\": \"test_Title\"}, {\"id\": 7, \"title\":\"\"}, {\"id\": null}]");
        Assert.assertTrue(returned.contains("[\"testId\"] \"test_Title\""));
        Assert.assertTrue(returned.contains("[7] \"\""));
        Assert.assertTrue(returned.contains("[null] No title"));
    }

    @Test
    public void getAlbumInfoStringFromEmptyString() {
        String returned = new PhotoAlbumInfoFormatter().getAlbumInfoStringFromJsonString("[]");
        Assert.assertNull(returned);
    }
}