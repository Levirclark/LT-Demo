import javax.json.JsonObject;

public class PhotoModel {
    private String id = "";
    private String albumId = "";
    private String title = "";
    private String url = "";
    private String thumbnailUrl = "";

    PhotoModel(JsonObject photoData) {
        if (photoData != null) {
            id = photoData.get("id") != null ? photoData.get("id").toString() : "";
            title = photoData.get("title") != null ? photoData.get("title").toString() : "";
            albumId = photoData.get("albumId") != null ? photoData.get("albumId").toString() : "";
            url = photoData.get("url") != null ? photoData.get("url").toString() : "";
            thumbnailUrl = photoData.get("thumbnailUrl") != null ? photoData.get("thumbnailUrl").toString() : "";
        }
    }

    public String toString() {
        String idString = id;
        String titleString = title;
        if (idString == null || idString.isEmpty()) {
            idString = "No id";
        }
        if (titleString == null || titleString.isEmpty()) {
            titleString = "No title";
        }
        return "[" + idString + "] " + titleString;
    }
}
