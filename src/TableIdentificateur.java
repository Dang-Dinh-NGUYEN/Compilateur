import java.util.ArrayList;
import java.util.HashMap;

class TableIdentificateurs {
    private static final int NB_IDENT_MAX = 100;
    private static HashMap<String, T_ENREG_IDENT> identTab = new HashMap<>(NB_IDENT_MAX);

    public boolean chercher(String nom) {
        return identTab.containsKey(nom);
    }

    public void inserer(String nom, T_ENREG_IDENT gerne) {
        identTab.put(nom,gerne);
    }

    void afficher() {
        System.out.println("Table des identificateurs :");
        System.out.println("Nom | Genre");
        for (String i : identTab.keySet()) {
            System.out.println(i + " | " + identTab.get(i));
        }
    }
}