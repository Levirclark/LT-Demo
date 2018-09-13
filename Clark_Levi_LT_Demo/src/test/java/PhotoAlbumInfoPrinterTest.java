import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class PhotoAlbumInfoPrinterTest {

    @Test
    public void printInfoForPhotoAlbumId() {
        //Test non-allowed album ids
        PhotoAlbumInfoPrinter fakePrinter = spy(PhotoAlbumInfoPrinter.class);
        fakePrinter.printInfoForPhotoAlbumId("asdf");
        fakePrinter.printInfoForPhotoAlbumId(null);
        fakePrinter.printInfoForPhotoAlbumId("-1");
        fakePrinter.printInfoForPhotoAlbumId("");
        verify(fakePrinter, times(0)).getAlbumInfoStringFromJsonString(any());

        //Test allowed album ids
        doReturn("").when(fakePrinter).getAlbumInfoStringFromJsonString(any());
        NetworkToolbox fakeToolbox = mock(NetworkToolbox.class);
        doReturn(fakeToolbox).when(fakePrinter).getNetworkToolbox();
        doReturn("[]", "test", null).when(fakeToolbox).makeNetworkCallForString(any(), any());
        fakePrinter.printInfoForPhotoAlbumId("1");
        fakePrinter.printInfoForPhotoAlbumId("2");
        fakePrinter.printInfoForPhotoAlbumId("3");
        verify(fakePrinter, times(1)).getAlbumInfoStringFromJsonString("test");
        verify(fakePrinter, times(1)).getAlbumInfoStringFromJsonString("[]");
        verify(fakePrinter, times(0)).getAlbumInfoStringFromJsonString(null);
    }

    @Test
    public void getAlbumInfoStringFromJsonString() {
        String returned = new PhotoAlbumInfoPrinter().getAlbumInfoStringFromJsonString("[{\"id\": \"testId\", \"title\": \"test_Title\"}, {\"id\": 7, \"title\":\"\"}, {\"id\": null}]");
        Assert.assertTrue(returned.contains("[\"testId\"] \"test_Title\""));
        Assert.assertTrue(returned.contains("[7] \"\""));
        Assert.assertTrue(returned.contains("[null] No title"));
        returned = new PhotoAlbumInfoPrinter().getAlbumInfoStringFromJsonString("[]");
        Assert.assertNull(returned);
    }
}