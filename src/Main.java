import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        AnalyseurLexical analyseurLexical = new AnalyseurLexical();
        analyseurLexical.INITIALISER();
        AnalyseurLexical.LIRE_CAR();

        /*
         //TableIdentificateurs tableIdentificateurs = new TableIdentificateurs();

        analyseurLexical.LIRE_CAR();

        while (analyseurLexical.CARLU != -1)
        {
            T_UNILEX token = analyseurLexical.ANALEX();
            System.out.println(token + " ");
        }

         */
        AnalyseurSyntaxique.ANASYNT();
        analyseurLexical.TERMINER();
    }
}
