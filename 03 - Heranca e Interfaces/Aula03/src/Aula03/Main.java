package Aula03;


import Aula03.classes.Pessoa;
import Aula03.classes.Endereco;


public class Main {
    public static void main(String[] args) {
        Pessoa p = new Pessoa("Clemilton","111235",new Endereco("Av. Epaminondas", "Centro", "41"));
        
        Endereco e;
        System.out.println(p);
    }
    
}
