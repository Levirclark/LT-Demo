import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class PhotoAlbumInfoPrinterTest {

    @Test
    public void printInfoForPhotoAlbumId() {
        PhotoAlbumInfoPrinter fakePrinter = spy(PhotoAlbumInfoPrinter.class);

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

    @Test
    public void testRejectsNegativeIntegerString(){
        PhotoAlbumInfoPrinter fakePrinter = spy(PhotoAlbumInfoPrinter.class);
        fakePrinter.printInfoForPhotoAlbumId("-1");
        verify(fakePrinter, times(0)).getAlbumInfoStringFromJsonString(any());
    }

    @Test
    public void testRejectsNullString(){
        PhotoAlbumInfoPrinter fakePrinter = spy(PhotoAlbumInfoPrinter.class);
        fakePrinter.printInfoForPhotoAlbumId(null);
        verify(fakePrinter, times(0)).getAlbumInfoStringFromJsonString(any());
    }

    @Test
    public void testRejectsNonIntegerString(){
        PhotoAlbumInfoPrinter fakePrinter = spy(PhotoAlbumInfoPrinter.class);
        fakePrinter.printInfoForPhotoAlbumId("test");
        verify(fakePrinter, times(0)).getAlbumInfoStringFromJsonString(any());
    }

    @Test
    public void testRejectEmptyString(){
        PhotoAlbumInfoPrinter fakePrinter = spy(PhotoAlbumInfoPrinter.class);
        fakePrinter.printInfoForPhotoAlbumId("");
        verify(fakePrinter, times(0)).getAlbumInfoStringFromJsonString(any());
    }
}