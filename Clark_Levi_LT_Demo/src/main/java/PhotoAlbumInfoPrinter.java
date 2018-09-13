import org.apache.commons.lang3.StringUtils;

import javax.json.Json;
import javax.json.JsonArray;
import java.io.StringReader;
import java.util.HashMap;

class PhotoAlbumInfoPrinter {
    void printInfoForPhotoAlbumId(String albumId) {
        if (!isLegalAlbumId(albumId)) {
            System.out.println("The album id should be a positive number");
            return;
        }
        NetworkToolbox networkToolbox = getNetworkToolbox();
        String url = "https://jsonplaceholder.typicode.com/photos";
        HashMap<String, String> parameterValues = new HashMap<>();
        parameterValues.put("albumId", albumId);
        String returnedValue = networkToolbox.makeNetworkCallForString(url, parameterValues);
        if (returnedValue != null) {
            System.out.println(getAlbumInfoStringFromJsonString(returnedValue));
        }
    }

    private boolean isLegalAlbumId(String albumId) {
        //Asserts the album id is numeric. Can be changed to allow or disallow specific types of albums ids.
        return StringUtils.isNumeric(albumId);
    }

    String getAlbumInfoStringFromJsonString(String albumJsonString) {
        StringBuilder returnValue = new StringBuilder();
        JsonArray albumInfo;
        try {
            albumInfo = Json.createReader(new StringReader(albumJsonString)).readArray();
        } catch (javax.json.JsonException e) {
            albumInfo = null;
        }
        if (albumInfo == null) {
            System.out.println("Data returned from the web request was malformed, or had an unexpected format");
            return null;
        }
        if (albumInfo.size() == 0) {
            System.out.println("No photo album with the given id was found");
            return null;
        }
        for (int i = 0; i < albumInfo.size(); i++) {
            PhotoModel photoModel = new PhotoModel(albumInfo.getJsonObject(i));
            returnValue.append(photoModel.toString()).append("\n");
        }
        return returnValue.toString();
    }

    NetworkToolbox getNetworkToolbox(){
        return new NetworkToolbox();
    }
}
