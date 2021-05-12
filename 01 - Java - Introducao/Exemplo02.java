package Aula01;
import java.util.Scanner;

class Exemplo02{
    public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      int num1, num2, op;
      System.out.println("Digite a operação:");
      System.out.println("1 - Somar");
      System.out.println("2 - Dividir");
      System.out.println("3 - Multiplicar");
      System.out.println("4 - Dividir");
      System.out.println("5 - Resto");
      op = scan.nextInt();
      System.out.println("Digite o primeiro numero:");
      num1 = scan.nextInt();
  
      System.out.println("Digite o segundo numero:");
      num2 = scan.nextInt();
  
      if(op==1){
        System.out.println("A soma é: "+(num1+num2));
      }else if(op==2){
        System.out.println("A subtração é: "+(num1-num2));
      }else if(op==3){
        System.out.println("A multiplicao é: "+(num1*num2));
      }else if(op==4){
        System.out.println("A divisão é: "+((float)num1/(float)num2));
      }else if(op==5){
        System.out.println("O resto é: "+(num1%num2));
      }
    }
  }
  