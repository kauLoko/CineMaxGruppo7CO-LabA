package CineMax;

public class Utenti {
    //Campi
    private String nome;
    private String cognome;
    private String username;
    private int password;
    private String nascista;
    private String domicilio;
    private char role;
    //Construtore
    public Utenti(String na,String cg,String user,int ps, String nsct,String dom,char ro){
        nome = na;
        cognome = cg;
        username = user;
        password = ps;
        nascista = nsct;
        domicilio = dom;
        role = ro;
    }
    //Metodi
    public String toString(){
        return "\nIl tuo  è Utenti:" + nome + " " + cognome +"\nUsername: "+ username + "\nPassword: " + String.valueOf(password)
                + "\nNascita: " + nascista + "\nDomicilio: " + domicilio + "\nRole: " + role;
    }

}
