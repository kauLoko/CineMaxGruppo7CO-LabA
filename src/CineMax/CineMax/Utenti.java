package CineMax;

public class Utenti {
    //Campi
    private String nome;
    private String cognome;
    private String username;
    private int password;
    private String nascista;
    private String domicilio;
    private String role;
    //Construtore
    public Utenti(String na,String cg,String user,int ps, String nsct,String dom,String ro){
        nome = na;
        cognome = cg;
        username = user;
        password = ps;
        nascista = nsct;
        domicilio = dom;
        role = ro;
    }

}
