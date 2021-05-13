package Aula02.classes;

public class Conta {
    //atributos
    private String nome;
    private String cpf;
    private double saldo;
    private int limiteCartao;
    //constante
    public static final int MAX_SAQUE = 3000;

    //Construtor
    public Conta(String nome,String cpf,double saldo, int limiteCartao){
        this.nome=nome;
        this.cpf=cpf;
        this.saldo = saldo;
        this.limiteCartao=limiteCartao;
    }
    public double getSaldo(){
        return this.saldo;
    }
    //método saca()
    public void saca(double valor){
        if(valor> MAX_SAQUE){
            System.out.println("Valor máximo para saque: R$ "+MAX_SAQUE);
            return;
        }
        if(valor > this.saldo){
            System.out.println("Não há saldo disponível para este valor");
            return;
        }
        this.saldo -= valor;
    }
    public void deposita(double valor){
        if(valor < 0){
          System.out.println("Valor inválido para depósito!");
          return;
        }
        this.saldo+=valor;
    }

    public void compraCartao(double valor){
        if(valor < this.limiteCartao){
            System.out.println("Não há saldo para esta compra!");
            return;
        }
        this.limiteCartao -=valor;
    }
    public void transferePara(Conta contaDestino, double valor){
        if(valor < 0){
            System.out.println("Valor inválido!");
            return;
        }
        if(valor > this.saldo){
            System.out.println("Saldo Insuficiente!");
            return;
        }
        //realiza saque
        this.saca(valor);
        //deposito na conta destino
        contaDestino.deposita(valor);
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\n"+
                "CPF: " + this.cpf + "\n" +
                "Saldo: "+ this.saldo + "\n"+
                "limiteCartao: "+ this.limiteCartao + "\n";
    }
}