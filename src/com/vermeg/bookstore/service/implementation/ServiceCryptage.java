package com.vermeg.bookstore.service.implementation;
        import java.security.MessageDigest;
        import java.security.NoSuchAlgorithmException;
        import java.util.logging.Level;
        import java.util.logging.Logger;


public class ServiceCryptage {

    public  String getHashCodeFromString(String str) throws NoSuchAlgorithmException {
        md = MessageDigest.getInstance("SHA-512");
        md.update(str.getBytes());
        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer hashCodeBuffer = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            hashCodeBuffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return hashCodeBuffer.toString();
    }

    private static MessageDigest md;

    public  String cryptWithMD5(String pass){
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ServiceCryptage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;


    }

}

