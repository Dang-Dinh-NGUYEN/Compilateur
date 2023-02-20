public class Constante extends T_ENREG_IDENT{
    private int typc;
    private int val;

    public Constante(String nom, int typc, int val){
        super(nom);
        this.typc = typc;
        this.val = val;
    }
}
