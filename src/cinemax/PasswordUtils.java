package cinemax;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {
    public static String cifrapassword(String passwordChiara) 
    {
        try 
        {
            //Prende la password inserita, la converte e restituisce una stringa di lunghezza fissa
            //La convert in byte ottenendo una stringa di numeri per farla comprendere al computer
            //Converto in stringa con stringbuilder per poterla salvare con caratteri leggibili e comprensibili nel CSV

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(passwordChiara.getBytes());
            StringBuilder hexString = new StringBuilder();
            for(byte b: hash) 
            {
                String hex = Integer.toHexString(0xff & b); // 0xff & b è l'AND bit a bit, serve per togliere eventuali segni negativi dei byte
                if(hex.length() == 1) 
                {
                    hexString.append('0'); //Se viene prodotto un solo carattere viene aggiunto '0' prima per formattazione
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(NoSuchAlgorithmException e) 
        {
            throw new RuntimeException("Errore: algoritmo di cifratura non trovato", e);
        }
    }
}