public class AnalyseurSyntaxique {
    static T_UNILEX UNILEX;

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
                        }else{fin = true;}
                    }
                }
                if(erreur) return false; //erreur syntaxique dans instruction d'écriture: expression incorrecte
                else if(UNILEX == T_UNILEX.parfer){
                    UNILEX = AnalyseurLexical.ANALEX();
                    return true;
                }else{return false; } //erreur syntaxique dans instruction d'écriture: ')' attendu
            }else{return false;} //erreur syntaxique dans instruction d'écriture: '(' attendu
        }
        return false; //erreur syntaxique dans instruction d'écriture: mot-clé ECRIRE attendu
    }


}
