import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        PhotoAlbumInfoPrinter photoAlbumInfoPrinter = new PhotoAlbumInfoPrinter();
        Scanner userInputScanner = new Scanner(System.in);
        String albumId = "";
        if (args.length > 0) {
            albumId = args[0];
        }
        while (!albumId.toLowerCase().equals("done")) {
            System.out.println("Enter the photo album id you'd like to know about: (Enter 'done' to finish)");
            albumId = userInputScanner.next();
            if (albumId.toLowerCase().equals("done")) {
                break;
            } else {
                if (!isLegalAlbumId(albumId)) {
                    System.out.println("The album id should be a positive number");
                } else {
                    photoAlbumInfoPrinter.printInfoForPhotoAlbumId(albumId);
                }
            }
        }
        userInputScanner.close();
    }

    private static boolean isLegalAlbumId(String albumId) {
        //Asserts the album id is numeric. Can be changed to allow or disallow specific types of albums ids.
        return StringUtils.isNumeric(albumId);
    }
}
