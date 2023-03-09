public class AnalyseurSyntaxique {
    static T_UNILEX UNILEX;

    public static boolean PROG() throws Exception {
        if(UNILEX == T_UNILEX.motcle && AnalyseurLexical.CHAINE.equals("PROGRAMME")){
            UNILEX = AnalyseurLexical.ANALEX();
            if(UNILEX == T_UNILEX.ident){
                UNILEX = AnalyseurLexical.ANALEX();
                if(UNILEX == T_UNILEX.ptvirg){
                    UNILEX = AnalyseurLexical.ANALEX();
                    if(DECL_CONST())
                        UNILEX = AnalyseurLexical.ANALEX();
                    if(DECL_VAR())
                        UNILEX = AnalyseurLexical.ANALEX();
                    if(BLOC()){
                        UNILEX = AnalyseurLexical.ANALEX();
                        if(UNILEX == T_UNILEX.point) return true;
                        System.out.println("erreur syntaxique dans une instruction de déclaration d'une programme: '.' attendu");
                        return false;
                    }
                    System.out.println("erreur syntaxique dans une instruction de déclaration d'une programme: BLOC attendu");
                    return false;
                }else{
                    System.out.println("erreur syntaxique dans une instruction de déclaration d'une programme: ';' attendu");
                    return false;
                }
            }else{
                System.out.println("erreur syntaxique dans une instruction de déclaration d'une programme: identificateur attendu");
                return false;
            }
        }
        System.out.println("erreur syntaxique dans une instruction de déclaration d'une programme: mot-clé 'PROGRAMME' attendu");
        return false;
    }

    public static boolean DECL_CONST() throws Exception {
        boolean fin;
        boolean erreur;
        if(UNILEX == T_UNILEX.motcle && AnalyseurLexical.CHAINE.equals("CONST")){
            UNILEX = AnalyseurLexical.ANALEX();
            if(UNILEX == T_UNILEX.ident){
                UNILEX = AnalyseurLexical.ANALEX();
                if(UNILEX == T_UNILEX.eg){
                    UNILEX = AnalyseurLexical.ANALEX();
                    if(UNILEX == T_UNILEX.ch || UNILEX == T_UNILEX.ent){
                        UNILEX = AnalyseurLexical.ANALEX();
                        fin = false;
                        erreur = false;
                        while(!fin){
                            if(UNILEX == T_UNILEX.virg){
                                UNILEX = AnalyseurLexical.ANALEX();
                                if(UNILEX == T_UNILEX.ident){
                                    UNILEX = AnalyseurLexical.ANALEX();
                                    if(UNILEX == T_UNILEX.eg){
                                        UNILEX = AnalyseurLexical.ANALEX();
                                        if(UNILEX == T_UNILEX.ch || UNILEX == T_UNILEX.ent){
                                            UNILEX = AnalyseurLexical.ANALEX();
                                        }else{
                                            fin = true;
                                            erreur = true;
                                        }
                                    }else{
                                        fin = true;
                                        erreur = true;
                                    }
                                }else{
                                    return false; //erreur syntaxique dans une instruction de déclaration d'une constante: identificateur attendu
                                }
                            }else{
                                fin = true;
                            }
                        }
                        if(erreur) return false; //erreur syntaxique dans une instruction de déclaration d'une constante: type de constante attendu
                        if(UNILEX == T_UNILEX.ptvirg){
                            return true;
                        }else{
                            return false; //erreur syntaxique dans une instruction de déclaration d'une constante: ';' attendu
                        }
                    }else{
                        return false; //erreur syntaxique dans une instruction de déclaration d'une constante: type de constante attendu
                    }
                }else{
                    return false; //erreur syntaxique dans une instruction de déclaration d'une constante: '=' attendu
                }
            }else{
                return false; //erreur syntaxique dans une instruction de déclaration d'une constante: identificateur attendu
            }
        }
        return false; //erreur syntaxique dans une instruction de déclaration d'une constante: mot-clé 'CONST' attendu
    }

    public static boolean DECL_VAR() throws Exception {
        boolean fin;
        boolean erreur;
        if(UNILEX == T_UNILEX.motcle && AnalyseurLexical.CHAINE.equals("VAR")){
            UNILEX = AnalyseurLexical.ANALEX();
            if(UNILEX == T_UNILEX.ident){
                UNILEX = AnalyseurLexical.ANALEX();
                fin = false;
                erreur = false;
                while (!fin){
                    if(UNILEX == T_UNILEX.virg){
                        UNILEX = AnalyseurLexical.ANALEX();
                        if(UNILEX == T_UNILEX.ident){
                            UNILEX = AnalyseurLexical.ANALEX();
                        }else{
                            fin = true;
                            erreur = true;
                        }
                    }else{
                        fin = true;
                    }
                }
                if(erreur){
                    return false; //erreur syntaxique dans une instruction de bloc: identificateur attendu
                }
                if(UNILEX == T_UNILEX.ptvirg){
                    return true;
                }else{
                    return false; //erreur syntaxique dans une instruction de bloc: ';' attendu
                }
            }else{
                return false; //erreur syntaxique dans une instruction de bloc: identificateur attendu
            }
        }
        return false; //erreur syntaxique dans une instruction de bloc: mot-clé 'VAR' attendu
    }

    public static boolean BLOC() throws Exception {
        boolean fin;
        boolean erreur;
        if(UNILEX == T_UNILEX.motcle && AnalyseurLexical.CHAINE.equals("DEBUT")){
            UNILEX = AnalyseurLexical.ANALEX();
            if(INSTRUCTION()){
                fin = false;
                erreur = false;
                while (!fin){
                    if(UNILEX == T_UNILEX.ptvirg){
                        UNILEX = AnalyseurLexical.ANALEX();
                        if(!INSTRUCTION()){
                            fin = true;
                            erreur = true;
                        }
                    }else{
                        fin = true;
                    }
                }
                if(erreur){
                    System.out.println("erreur syntaxique dans une instruction de bloc: instruction attendu");
                    return false;
                }
                if(UNILEX == T_UNILEX.motcle && AnalyseurLexical.CHAINE.equals("FIN")){
                    UNILEX = AnalyseurLexical.ANALEX();
                    return true;
                }else{
                    System.out.println("erreur syntaxique dans une instruction de bloc: mot-clé 'FIN' attendu");
                    return false;
                }
            }else{
                System.out.println("erreur syntaxique dans une instruction de bloc: instruction attendu");
                return false;
            }
        }
        //System.out.println("erreur syntaxique dans une instruction de bloc: mot-clé 'DEBUT' attendu ");
        return false;
    }

    public static boolean INSTRUCTION() throws Exception{
        return AFFECTATION() || LECTURE() || ECRITURE() || BLOC();
    }

    /*A verifier*/
    public static boolean AFFECTATION() throws Exception {
        if(UNILEX == T_UNILEX.ident){
            UNILEX = AnalyseurLexical.ANALEX();
            if(UNILEX == T_UNILEX.aff){
                UNILEX = AnalyseurLexical.ANALEX();
                return EXP();
            }else{return false;} //erreur syntaxique dans une instruction d'affectation::= attendu
        }
        return false; //erreur syntaxique dans une instruction d'affectation: identificateur attendu
    }

    public static boolean LECTURE() throws Exception{
        boolean fin;
        boolean erreur;
        if(UNILEX == T_UNILEX.motcle && AnalyseurLexical.CHAINE.equals("LIRE")){
            UNILEX = AnalyseurLexical.ANALEX();
            if(UNILEX == T_UNILEX.parouv){
                UNILEX = AnalyseurLexical.ANALEX();
                if(UNILEX == T_UNILEX.ident){
                    UNILEX = AnalyseurLexical.ANALEX();
                    fin = false;
                    erreur = false;
                    while(!fin){
                        if(UNILEX == T_UNILEX.virg){
                            UNILEX = AnalyseurLexical.ANALEX();
                            if(UNILEX == T_UNILEX.ident){
                                UNILEX = AnalyseurLexical.ANALEX();
                            }else{
                                fin = true;
                                erreur = true;
                            }
                        }
                        else{fin = true;}
                    }
                    if(erreur){
                        return false; //erreur syntaxique dans instruction de lecture: identificateur attendu
                    }else if(UNILEX == T_UNILEX.parfer){
                        UNILEX = AnalyseurLexical.ANALEX();
                        return true;
                    }else{return false;} //erreur syntaxique dans instruction de lecture: ')' attendu
                }
                return false; //erreur syntaxique dans instruction de lecture: identificateur attendu
            }
            return false; //erreur syntaxique dans instruction de lecture: '(' attendu
        }
        return false; //erreur syntaxique dans instruction de lecture: mot_clé 'LIRE' attendu
    }

    public static boolean ECRITURE() throws Exception{
        boolean fin;
        boolean erreur;
        if(UNILEX == T_UNILEX.motcle && AnalyseurLexical.CHAINE.equals("ECRIRE")){
            UNILEX = AnalyseurLexical.ANALEX();
            if(UNILEX == T_UNILEX.parouv){
                UNILEX = AnalyseurLexical.ANALEX();
                erreur = false;
                if(ECR_EXP()){
                    UNILEX = AnalyseurLexical.ANALEX();
                    fin = false;
                    while(!fin){
                        if(UNILEX == T_UNILEX.virg){
                            UNILEX = AnalyseurLexical.ANALEX();
                            erreur = !(ECR_EXP());
                            if(erreur) fin = true;
                        }else{
                            fin = true;
                        }
                    }
                }
                if(erreur){
                    //System.out.println("erreur syntaxique dans instruction d'écriture: expression incorrecte");
                    return false;
                }
                if(UNILEX == T_UNILEX.parfer){
                    UNILEX = AnalyseurLexical.ANALEX();
                    return true;
                }
                //System.out.println("erreur syntaxique dans instruction d'écriture: ')' attendu " + UNILEX );
                return false;
            }else{
                //System.out.println("erreur syntaxique dans instruction d'écriture: '(' attendu");
                return false;
            }
        }
        //System.out.println("erreur syntaxique dans instruction d'écriture: mot-clé ECRIRE attendu");
        return false;
    }

    public static boolean ECR_EXP() throws Exception {
        if(EXP() || UNILEX == T_UNILEX.ch){
            return true;
        }
        return false;
    }

    public static boolean EXP() throws Exception {
        if(TERME()){
            UNILEX = AnalyseurLexical.ANALEX();
            return SUITE_TERME();
        }
        return false; //erreur syntaxique dans instruction d'EXP: l'expression attendu
    }

    public static boolean SUITE_TERME() throws Exception {
        if(OP_BIN()){
            UNILEX = AnalyseurLexical.ANALEX();
            return EXP();
        }
        UNILEX = AnalyseurLexical.ANALEX();
        return true; //cas vide
    }

    public static boolean TERME() throws Exception {
        if(UNILEX == T_UNILEX.ident || UNILEX == T_UNILEX.ent){
            UNILEX = AnalyseurLexical.ANALEX();
            return true;
        }
        if(UNILEX == T_UNILEX.parouv){
            UNILEX = AnalyseurLexical.ANALEX();
            if(!EXP()){
                System.out.println("erreur syntaxique dans instruction de TERME: EXP attendu");
                return false;
            }
            UNILEX = AnalyseurLexical.ANALEX();
            if(UNILEX == T_UNILEX.parfer){
                UNILEX = AnalyseurLexical.ANALEX();
                return true;
            }
            System.out.println("erreur syntaxique dans instruction de TERME: ')' attendu");
            return false;
        }
        if(UNILEX == T_UNILEX.moins){
            UNILEX = AnalyseurLexical.ANALEX();
            return TERME();
        }
        //System.out.println("erreur syntaxique dans instruction de TERME: TERME attendu");
        return false;
    }

    public static boolean OP_BIN() throws Exception {
        if(UNILEX == T_UNILEX.plus || UNILEX == T_UNILEX.moins || UNILEX == T_UNILEX.mult || UNILEX == T_UNILEX.divi){
            UNILEX = AnalyseurLexical.ANALEX();
            return true;
        }
        //erreur syntaxique dans instruction de OP_BIN: OP_BIN attendu
        return false;
    }

    public static void ANASYNT() throws Exception {
        UNILEX = AnalyseurLexical.ANALEX();
        if(PROG()) System.out.println("Le programme source est syntaxiquement correcte");
        AnalyseurLexical.ERREUR(5);
    }

}
