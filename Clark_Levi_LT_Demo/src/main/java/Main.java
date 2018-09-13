import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        PhotoAlbumInfoPrinter photoAlbumInfoPrinter = new PhotoAlbumInfoPrinter();
        Scanner userInputScanner = new Scanner(System.in);
        String albumId = "";
        while (!albumId.toLowerCase().equals("done")) {
            System.out.println("Enter the photo album id you'd like to know about: (Enter 'done' to finish)");
            albumId = userInputScanner.next();
            if(albumId.toLowerCase().equals("done")){
                break;
            }
            else{
                photoAlbumInfoPrinter.printInfoForPhotoAlbumId(albumId);
            }
        }
        userInputScanner.close();
    }
}
