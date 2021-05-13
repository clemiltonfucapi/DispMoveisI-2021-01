package Aula02;
import Aula02.classes.Conta;

public class Exemplo02 {
    public static void main(String[] args) {
        Conta c1 = new Conta("Andre","22222222",3235.57,10000);
        Conta c2 = new Conta("Beatriz","323232",4500,10000);
        
        c1.transferePara(c2, 500);
        System.out.println(c1);
        System.out.println(c2); 
    }
}
