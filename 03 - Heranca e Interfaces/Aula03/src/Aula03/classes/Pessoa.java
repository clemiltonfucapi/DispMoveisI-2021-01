package Aula03.classes;

public class Pessoa {
    protected String nome;
    protected String cpf;
    protected Endereco endereco;
   
    public Pessoa(String nome) {
        this.nome = nome;
     }
   
   public Pessoa(String nome, String cpf) {
        this(nome);
        this.cpf = cpf;
    }

    public Pessoa(String nome,String cpf, Endereco endereco){
        this(nome,cpf);
        this.endereco=endereco;
    }
   
   public String getNome() {
        return nome;
    }
 
    public void setNome(String nome) {
        this.nome = nome;
    }
 
    public String getCpf() {
        return cpf;
    }
 
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Nome: "+this.nome + "\n" + 
                "CPF: "+this.cpf + "\n" +
                "Endereco: "+this.endereco;
    }
 
}