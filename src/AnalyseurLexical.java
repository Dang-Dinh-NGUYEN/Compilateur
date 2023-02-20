import java.io.*;

public class AnalyseurLexical {

    public AnalyseurLexical() throws FileNotFoundException {
    }

    /*
    enum T_UNILEX {
        motcle, ident, ent, ch, virg, ptvirg, point, deuxspts, parouv, parfer, inf, sup, eg,
        plus, moins, mult, divi, infe, supe, diff, aff
    }

     */

    static final int LONG_MAX_IDENT = 20;
    static final int LONG_MAX_CHAINE = 50;
    static final int NB_MOTS_RESERVES = 7;
    static final int MAXINT = 32767;

    String SOURCE;
    Character CARLU;
    int NOMBRE;
    String CHAINE;
    int NUM_LIGNE = 0;
    String[] TABLE_MOTS_RESERVES = new String[NB_MOTS_RESERVES];

    BufferedReader bf ;

    public  void ERREUR(int n) {
        switch (n) {
            case 1:
                System.out.println("fin de fichier atteinte _ ligne n°" + NUM_LIGNE);
                System.exit(0);
            case 2:
                System.out.println("dépassé la valeur maximale _ ligne n°" + NUM_LIGNE);
                System.exit(0);
            case 3:
                System.out.println("dépassé la longueur maximale _ ligne n°" + NUM_LIGNE);
                System.exit(0);
            case 4:
                System.out.println("invalide identificateur _ ligne n°" + NUM_LIGNE);
                System.exit(0);
        }
    }

    public  void LIRE_CAR() throws IOException {
        int c = bf.read();
        if (c == -1)
            ERREUR(1);
        CARLU = (char) c;
        if (CARLU == '\n')
            NUM_LIGNE++;
    }

    public  void SAUTER_SEPARATEURS() throws Exception {
        while(CARLU == ' ' || CARLU == '\t' || CARLU == '\n' || CARLU == '\r') {
            LIRE_CAR();
        }

        if (CARLU == '{') {
            //commentaires imbriqués
            int count = 1;
            while (count > 0) {
                LIRE_CAR();
                if (CARLU == '{')
                    count++;
                if (CARLU == '}')
                    count--;
            }
            LIRE_CAR();
            SAUTER_SEPARATEURS();
        }
    }


    public T_UNILEX RECO_ENTIER() throws IOException {
        int n = 0;
        while (Character.isDigit(CARLU)) {
            n = n * 10 + (CARLU - '0');
            if (n > MAXINT) {
                ERREUR(2);
            }
            LIRE_CAR();
        }
        if(Character.isAlphabetic(CARLU))
             ERREUR(4);
        NOMBRE = n;
        System.out.print(NOMBRE + " >> ");

        return T_UNILEX.ent;
    }

    public T_UNILEX RECO_CHAINE() throws IOException {
        //chaines imbriquées
        LIRE_CAR();
        while (CARLU != '\'') {
            CHAINE += CARLU;
            LIRE_CAR();
        }
        LIRE_CAR();
        if(CARLU == '\'') {
            CHAINE += CARLU;
            return RECO_CHAINE();
        }

        if(CHAINE.length() > LONG_MAX_CHAINE)
            ERREUR(3);
        System.out.print(CHAINE + " >> ");
        return T_UNILEX.ch;
    }


    public T_UNILEX RECO_IDENT_OU_MOT_RESERVE() throws IOException {
        CHAINE = "";
        while (Character.isLetterOrDigit(CARLU) || CARLU == '_') {
            if (CHAINE.length() < LONG_MAX_IDENT) {
                CHAINE += CARLU;
            }
            LIRE_CAR();
        }
        CHAINE = CHAINE.toUpperCase();
        System.out.print(CHAINE + " >> ");
        return EST_UN_MOT_RESERVE(CHAINE)? T_UNILEX.motcle : T_UNILEX.ident;
    }


    public boolean EST_UN_MOT_RESERVE(String chaine) {
        for (String motCle : TABLE_MOTS_RESERVES) {
            if (motCle.equals(chaine))
                return true;
        }
        return false;
    }

    /*
    static boolean EST_UN_MOT_RESERVE(String chaine) {
        int i = 0;
        int j = NB_MOTS_RESERVES - 1;
        while (i <= j) {
            int k = (i + j) / 2;
            int compareResult = chaine.compareTo(TABLE_MOTS_RESERVES[k]);
            if (compareResult == 0) {
                return true;
            } else if (compareResult < 0) {
                j = k - 1;
            } else {
                i = k + 1;
            }
        }
        return false;
    }
     */

    public T_UNILEX RECO_SYMB() throws Exception {
        switch (CARLU) {
            case ';':
                System.out.print(CARLU + " >> ");
                LIRE_CAR();
                return T_UNILEX.ptvirg;
            case ',':
                System.out.print(CARLU + " >> ");
                LIRE_CAR();
                return T_UNILEX.virg;
            case '.':
                System.out.print(CARLU + " >> ");
                LIRE_CAR();
                return T_UNILEX.point;
            case '(':
                System.out.print(CARLU + " >> ");
                LIRE_CAR();
                return T_UNILEX.parouv;
            case ')':
                System.out.print(CARLU + " >> ");
                LIRE_CAR();
                return T_UNILEX.parfer;
            case '<':
                System.out.print(CARLU);
                LIRE_CAR();
                if (CARLU == '=') {
                    System.out.print(CARLU + " >> ");
                    LIRE_CAR();
                    return T_UNILEX.infe;
                }
                else if (CARLU == '>'){
                    System.out.print(CARLU + " >> ");
                    LIRE_CAR();
                    return T_UNILEX.diff;
                }
                else {
                    System.out.print(" >> ");
                    return T_UNILEX.inf;
                }
            case '>':
                System.out.print(CARLU);
                LIRE_CAR();
                if (CARLU == '=') {
                    System.out.print(CARLU + " >> ");
                    LIRE_CAR();
                    return T_UNILEX.supe;
                }
                System.out.print(" >> ");
                return T_UNILEX.sup;
            case '=':
                System.out.print(CARLU + " >> ");
                LIRE_CAR();
                return T_UNILEX.eg;
            case '+':
                System.out.print(CARLU + " >> ");
                LIRE_CAR();
                return T_UNILEX.plus;
            case '-':
                System.out.print(CARLU + " >> ");
                LIRE_CAR();
                return T_UNILEX.moins;
            case '*':
                System.out.print(CARLU + " >> ");
                LIRE_CAR();
                return T_UNILEX.mult;
            case '/':
                System.out.print(CARLU + " >> ");
                LIRE_CAR();
                return T_UNILEX.divi;
            case ':':
                System.out.print(CARLU);
                LIRE_CAR();
                if (CARLU == '=') {
                    System.out.print(CARLU + " >> ");
                    LIRE_CAR();
                    return T_UNILEX.aff;
                }
                return T_UNILEX.deuxspts;
        }
        return null;
    }

    public T_UNILEX ANALEX() throws Exception {
        CHAINE = "";
        // saute les séparateurs et commentaires
        SAUTER_SEPARATEURS();

        // essaie de reconnaître une unité lexicale
        if (Character.isDigit(CARLU))
            return RECO_ENTIER();
        if (CARLU == '\'')
            return RECO_CHAINE();
        if (Character.isLetter(CARLU))
            return RECO_IDENT_OU_MOT_RESERVE();
        return RECO_SYMB();
    }

    public  void INITIALISER() {
        NUM_LIGNE = 0;
        SOURCE = "C:\\Users\\Dang Dinh NGUYEN\\Documents\\L3_INFO\\S6\\Compilateur\\src\\Test\\Test2.txt";
        try {
            bf = new BufferedReader(new FileReader(SOURCE));
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier source n'a pas été trouvé");
            System.exit(0);
        }
        TABLE_MOTS_RESERVES = new String[]{"PROGRAMME", "DEBUT", "FIN", "CONST", "VAR", "ECRIRE", "LIRE"};
    }

    public void TERMINER() throws Exception {
            bf.close();
    }
}

