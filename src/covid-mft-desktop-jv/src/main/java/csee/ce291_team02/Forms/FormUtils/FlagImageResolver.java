package csee.ce291_team02.Forms.FormUtils;

import csee.ce291_team02.AppConstants;

import javax.swing.ImageIcon;
import java.net.URL;

/*
This is a resolver class for the country flag images, it gets the imagee URL from the resource folder
and presents the image of the flag next to the country name.
*/
public class FlagImageResolver {
    private static final String IMAGE_ROOT_PATH = "/Flags/%s.png";

    public static ImageIcon getFlag(String iso3Code, String countryName) {
        URL imgURL = getURL(iso3Code);
        if (imgURL != null) {
            return new ImageIcon(imgURL, countryName);
        } else {
            imgURL = getURL(AppConstants.Properties.UNKNOWN_FLAG_ISO_CODE);
            return new ImageIcon(imgURL, AppConstants.Properties.UNKNOWN_FLAG_DESCRIPTION);
        }
    }

    private static URL getURL(String iso3Code){
        return FlagImageResolver.class.getResource(String.format(IMAGE_ROOT_PATH, iso3Code));
    }
}
