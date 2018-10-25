import org.junit.Test;

import static org.mockito.Mockito.*;

public class PhotoAlbumInfoPrinterTest {

    @Test
    public void printInfoForPhotoAlbumId() {
        PhotoAlbumInfoPrinter fakePrinter = spy(PhotoAlbumInfoPrinter.class);
        PhotoAlbumInfoFormatter fakeFormatter = mock(PhotoAlbumInfoFormatter.class);
        doReturn(fakeFormatter).when(fakePrinter).getPhotoAlbumInfoFormatter();
        doReturn("").when(fakeFormatter).getAlbumInfoStringFromJsonString(any());
        NetworkToolbox fakeToolbox = mock(NetworkToolbox.class);
        doReturn(fakeToolbox).when(fakePrinter).getNetworkToolbox();
        doReturn("[]", "test", null).when(fakeToolbox).makeNetworkCallForString(any(), any());
        fakePrinter.printInfoForPhotoAlbumId("1");
        fakePrinter.printInfoForPhotoAlbumId("2");
        fakePrinter.printInfoForPhotoAlbumId("3");
        verify(fakeFormatter, times(1)).getAlbumInfoStringFromJsonString("test");
        verify(fakeFormatter, times(1)).getAlbumInfoStringFromJsonString("[]");
        verify(fakeFormatter, times(0)).getAlbumInfoStringFromJsonString(null);
    }
}