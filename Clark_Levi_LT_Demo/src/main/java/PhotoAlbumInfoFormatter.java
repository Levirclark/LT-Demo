import javax.json.Json;
import javax.json.JsonArray;
import java.io.StringReader;

public class PhotoAlbumInfoFormatter {
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
}
