package utils;

import org.jboss.security.auth.spi.Util;

public class Hash {

    public static String hashMD5(String password){
        return Util.createPasswordHash("MD5", Util.BASE64_ENCODING, null, null, password);
    }
}
