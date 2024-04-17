package csee.ce291_team02.FormUtils;

import csee.ce291_team02.AppConstants;
import csee.ce291_team02.Forms.FormUtils.FlagImageResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class FlagImageResolverTests {

    @Test
    public void testGetKnownFlag(){
        //Assemble
        String iso3Code = "ZWE";
        String countryName = "Zimbabwe";

        //Act
        ImageIcon result = FlagImageResolver.getFlag(iso3Code,countryName);

        //Assert
        Assertions.assertEquals(countryName,result.getDescription());
    }

    @Test
    public void testGetUnknownFlag(){
        String iso3Code = AppConstants.Properties.UNKNOWN_FLAG_ISO_CODE;
        String countryName = AppConstants.Properties.UNKNOWN_FLAG_DESCRIPTION;

        //Act
        ImageIcon result = FlagImageResolver.getFlag(iso3Code,countryName);

        //Assert
        Assertions.assertEquals(countryName,result.getDescription());
    }
}
