import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;

public class AnalyseurLexical {

    public AnalyseurLexical() throws FileNotFoundException {
    }

    enum T_UNILEX {
        motcle, ident, ent,  ch, virg, ptvirg, point, deuxspts, parouv, parfer, inf, sup, eg, plus, moins, mult, divi, infe, supe, diff, aff
    }

    final int LONG_MAX_IDENT = 20;
    final int LONG_MAX_CHAINE = 50;
    final int NB_MOTS_RESERVES = 7;
    final int LONG_MAXINT = 32767;

    String SOURCE;
    Character CARLU;
    double NOMBRE;
    String CHAINE;
    int NUM_LIGNE = 0;
    String[] TABLE_MOTS_RESERVES = new String[NB_MOTS_RESERVES];

    File file = new File(SOURCE);
    FileReader fr = new FileReader(file);
    BufferedReader bf = new BufferedReader(fr);

    public void ERREUR(int n){
       switch (n){
           case 1:
               System.out.println("fin de fichier atteinte _ ligne n°" + NUM_LIGNE);
               System.exit(0);
           case 2:
               System.out.println("dépassé la valeur maximale");
               System.exit(0);
       }
    }

    public void LIRE_CAR() throws IOException {
            char c = (char) bf.read();
            CARLU = c;
            if(c== '\n') {
              NUM_LIGNE++;
            }else{ERREUR(1);}
    }

    public void SAUTER_SEPARATEURS() throws IOException {
        while(CARLU.equals(" ") || CARLU.equals("\t") || CARLU.equals("\n")){
                LIRE_CAR();
            }
        if(CARLU.equals("{")){
            do {
                LIRE_CAR();
            } while (!CARLU.equals("}"));
        }
    }

    public T_UNILEX RECO_ENTIER() throws IOException {
        String e = CARLU.toString();
        while(Character.isDigit(CARLU)){
            LIRE_CAR();
            e += CARLU.toString();
        }
        int entier = Integer.parseInt(e);
        if(entier > LONG_MAXINT)
            ERREUR(2);
        return T_UNILEX.ent;
    }

    public T_UNILEX RECO_CHAINE() throws IOException {


    }

    public void RECO_IDENT_OU_MOT_RESERVE(){}

    public T_UNILEX RECO_SYMB(){}

    public void ANALEX(){}

    public void INITIALISER(){}

    public void TERMINER(){}
}

