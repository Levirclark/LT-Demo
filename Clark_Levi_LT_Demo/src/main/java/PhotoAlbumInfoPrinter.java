import java.util.HashMap;

class PhotoAlbumInfoPrinter {
    void printInfoForPhotoAlbumId(String albumId) {
        NetworkToolbox networkToolbox = getNetworkToolbox();
        String url = "https://jsonplaceholder.typicode.com/photos";
        HashMap<String, String> parameterValues = new HashMap<>();
        parameterValues.put("albumId", albumId);
        String returnedValue = networkToolbox.makeNetworkCallForString(url, parameterValues);
        if (returnedValue != null) {
            System.out.println(getPhotoAlbumInfoFormatter().getAlbumInfoStringFromJsonString(returnedValue));
        }
    }

    NetworkToolbox getNetworkToolbox() {
        return new NetworkToolbox();
    }

    PhotoAlbumInfoFormatter getPhotoAlbumInfoFormatter() {
        return new PhotoAlbumInfoFormatter();
    }
}
