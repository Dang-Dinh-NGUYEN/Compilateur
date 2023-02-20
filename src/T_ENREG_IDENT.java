abstract class T_ENREG_IDENT {
    private char[] nom = new char[AnalyseurLexical.LONG_MAX_IDENT];

    public T_ENREG_IDENT(String nom){
        this.nom = nom.toCharArray();
    }
}