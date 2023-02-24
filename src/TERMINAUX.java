public enum TERMINAUX {
    PROGRAMME, CONST, VAR, DEBUT, FIN, LIRE, ECRIRE, IDENT, ENT, VIRG(","), PTVIRG(";"), POINT("."), EG("="),
    AFF(":="), PAROUV("("), PARFER(")"), PLUS("+"), MOINS("-"), MULT("*"), DIVI("/")
    ;

    private String value;
    private TERMINAUX(String s) {
        this.value = s;
    }

    private TERMINAUX() {}

    public String toString(){
        return this.value;
    }
}
