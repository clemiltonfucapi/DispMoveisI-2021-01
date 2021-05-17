# Classes e Herança
![Images](imgs/slide01.png)

# Classe Object
![Images](imgs/slide02.png)
- Vamos implementar o seguinte modelo:
<center><img src="imgs/heranca.png" ></center>

# Pessoa.java
```java
package Aula03.classes;

public class Pessoa {
    protected String nome;
    protected String cpf;
   
    public Pessoa(String nome) {
        this.nome = nome;
    }
   
   public Pessoa(String nome, String cpf) {
        this(nome);
        this.cpf = cpf;
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
        return "Nome: "+this.nome + "\n" + "CPF: "+this.cpf;
    }
 
}
```

## Modificadores de Acesso 
### Observe que os atributos da classe estão definidos como `protected`.
```java
public class Pessoa {
    protected String nome;
    protected String cpf;
```
- Isto significa que estes atributos são visíveis em classes filhas de `Pessoa`, ou do mesmo pacote. 
- Observe como se comportam os diferentes modificadores de acesso:

<img src="imgs/modificadores_acesso.png" >

## 1.2 Sobrecarga de construtores
### Observe que foram definidos 2 construtores com assinaturas diferentes:
```java
    ...
    public Pessoa(String nome) {
        this.nome = nome;
     }
   
   public Pessoa(String nome, String cpf) {
        this(nome);
        this.cpf = cpf;
    }
    ...
```
- O primeiro construtor inicializa apenas no atributo `nome`
- O segundo construtor inicializa os atributos `nome` e `cpf`. O comando `this(nome)` chama o primeiro construtor, que inicializa o `nome`.
- Ou seja, a classe Pessoa, pode ser instanciada de duas maneiras.
- Nesta parte, está acontencendo uma **sobrecarga**. 
- SOBRECARGA: ocorre quando 2 métodos com mesmo nome e assinaturas diferentes estão na mesma classe.
## Classe Endereco
- Crie a classe ``classes/Endereco.java``:
    ```java
    package Aula03.classes;

    public class Endereco {
        private String rua;
        private String bairro;
        private String numero;

        public Endereco(String rua,String bairro, String numero){
            this.rua = rua;
            this.bairro = bairro;
            this.numero = numero;
        }
        @Override
        public String toString(){
            return this.rua + ", "+ this.bairro+", "+this.numero;
        }
    }
    ```
- Agora vamos adicionar um atributo ``Endereco`` na classe ``Pessoa``
    ```java
    public class Pessoa {
        .....
        /* Adicionando um atributo endereco */
        protected Endereco endereco;
        .......
        /* Construtor com endereco */
        public Pessoa(String nome,String cpf, Endereco endereco){
            this(nome,cpf);
            this.endereco=endereco;
        }
        .......
        /* Refatorando o classe toString() */
        @Override
        public String toString() {
            return "Nome: "+this.nome + "\n" + 
                    "CPF: "+this.cpf + "\n" +
                    "Endereco: "+this.endereco;
        }
        
    }
    ```
- Agora vamos fazer uma classe teste ``Aula03/Main.java``:
    ```java
    package Aula03;

    import Aula03.classes.Pessoa;
    import Aula03.classes.Endereco;

    public class Main {
        public static void main(String[] args) {
            Pessoa p = new Pessoa(
                "Italo",
                "111235",
                new Endereco("Av. Epaminondas", "Centro", "41"
            ));
            System.out.println(p);
        }  
    }
    ```
# Classe Funcionario
- ``classes/Funcionario.java``
```java
package Aula03.classes;

public class Funcionario extends Pessoa {
    protected double salario;

    public Funcionario(String nome,String cpf, Endereco endereco, double salario){
        super(nome,cpf,endereco);
        this.salario = salario;
    }
    public String toString(){
        String func = super.toString();
        func += "\nSalario: " + this.salario;
        return func;
    }
    public double getSalario(){
        return salario;
    }
    public void setSalario(double salario){
        this.salario = salario;
    }
}
```
## Palavra chave Extends

```java
...
public class Funcionario extends Pessoa {
    protected double salario;
    ...
```
- O trecho de codigo acima faz uma relação de herança entre Pessoa e Funcionário. A palavra chave `extends` realiza a herança;
- Funcionário é subclasse de Pessoa. Logo `Funcionário` tem todos os atributos e métodos da classe `Pessoa`;
- O atributo salario foi adicionado como `protected`, ou seja suas sub-classes também tem acesso a ele;

## Método super()

```java
    public Funcionario(String nome,String cpf, Endereco endereco, double salario){
        super(nome,cpf,endereco);
        this.salario = salario;
    }
```
- Acima temos os construtor da classe `Funcionário`.
- O método super(), faz referência ao construtor da classe pai `Pessoa`.
- Ou seja, os atributos da classe pai são inicializados.
- Após isso, o atributo `salario` também é inicializado


## Método toString()
```java
    public String toString(){
        String func = super.toString();
        func += "\nSalario: " + this.salario;
        return func;
    }
```
- Acima temos a implementação do método toString(), da classe Funcionário.
- Observe que o comando `super.toString()`, chama o método da classe pai (`Pessoa`)

## getter and setter
```java
    public double getSalario(){
        return salario;
    }
    public void setSalario(double salario){
        this.salario = salario;
    }
```
## Refactoring Main.java
```java
package Aula03;

import Aula03.classes.*;

public class Main {
    public static void main(String[] args) {
        Pessoa p = new Pessoa(
            "Italo",
            "111235",
            new Endereco("Av. Epaminondas", "Centro", "41"
        ));
        System.out.println(p);

        Endereco e2 = new Endereco("Av. Noel Nutels","Cidade Nova","1110");
        Funcionario f = new Funcionario("Jamilly", "22222222",e2, 3000);
        System.out.println(f);
    }  
}
```
- Um funcionário foi instanciado e mostrado os seus valores.
- Observe na saída, que os atributos de pessoa também são iniciados.
# Classes abstratas
- As classes abstratas servem como “modelo” para outras classes. Informam como as classes devem se comportar. Definem atributos e métodos.
- Classes abstratas são classes que NÃO PODEM SER INSTACIADAS.
- Classes derivadas DEVEM implementar os métodos abstratos.
- Identificamos uma classe como abstrata pelo modificador `abstract`.
## Exemplo
<center><img src="imgs/heranca.png" ></center>

- Tanto o vendedor, quanto o gerente, são subclasses de funcionários. Porém a forma de calculo do salário de cada um é diferente.
- Neste caso a classe Funcionário pode ser abstrata, e as classes Vendedor e Gerente podem implementar essas diferentes formas de calcular o salário
- Caso haja outros tipos de funcionários, com calculos diferentes, a classe `Funcionario` servirá de base para outras.
- LEMBRE-SE: Classes abstratas NÃO podem ser instanciadas.

# Classe vendedor
```java
package Aula03.classes;

public class Vendedor extends Funcionario {
    private double totalVendas;

    public Vendedor(String nome, String cpf,Endereco endereco, 
                    double salario, double totalVendas){
        super(nome,cpf,endereco,salario);
        this.totalVendas =totalVendas;
    }

    
    @Override
    public String toString(){
        return "Vendedor[ Nome: "+ this.nome + "\n"+
                "Cpf: "+this.cpf + "\n"+
                "Endereco: "+ this.endereco +
                "Salario: "+this.calculaSalario()+"\n"+
                "Vendas: "+this.totalVendas+"]";

    }
    @Override
    public double calculaSalario(){
        return salario + (totalVendas*0.02);
    }

    public double getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(double totalVendas) {
        this.totalVendas = totalVendas;
    }

}

```
- Observe que a classe `Vendedor` estende de `Funcionario`
- Então, somos obrigados a implementar o método calculaSalario()
- Observe que também implementamos o método `toString()`, utilizando o método `calculaSalario()`

# Classe gerente
```java
package Aula03.classes;

public class Gerente extends Funcionario {
    
    public Gerente(String nome, String cpf,Endereco endereco, 
    double salario){
        super(nome,cpf,endereco,salario);
    }

    @Override
    public double calculaSalario() {
        return salario + (salario * 0.2);
    }

    @Override
    public String toString(){
        return "Gerente[\n\tNome: "+ this.nome + "\n"+
                "\tCpf: "+this.cpf + "\n"+
                "\tEndereco: "+ this.endereco + "\n"+
                "\tSalario: "+this.calculaSalario()+"\n]";
    }
}
```
- A classe `Gerente` também estende de funcionário.
- Observe que a implementação do método `calculaSalario()` é diferente da classe `Vendedor`.
# Classe Main
```java
package Aula03;

import Aula03.classes.*;
public class Main {
    public static void main(String[] args) {
        Endereco e1 = new Endereco("Av. Epaminondas", "Centro", "41");
        Pessoa p = new Pessoa(
            "Italo",
            "111235",
            e1
        );
        System.out.println(p);

        Funcionario g = new Gerente("Jamilly", "22222222",e1, 3000);
        Funcionario v = new Vendedor("Mauro","9852",e1,2500,10000);

        System.out.println(g);
        System.out.println(v);
    }  
}
```
- Obtemos como saída: ![Image](imgs/shell01.png)

# Herança Múltipla
![Images](imgs/slide03.png)
# Interfaces
![Images](imgs/slide04.png)
# Exemplo Interfaces
- A Interface é muito utilizada em grandes projetos para obrigar o programador a seguir o padrão do projeto
- Ex: ![Images](imgs/interface.png)
- Temos uma Interface BasicoDAO que dirá aos programadores do nosso projeto o que suas classes DAO devem ter.
- Para realizar o CRUD de Funcionários, ele implementa a interface acima e ainda adiciona métodos a parte.
![Images](imgs/codigoInterface.png)

# Exemplo Interface
- ![Images](imgs/interface-exercicios.png)
- Implemente interface de nome Forma onde são declarados dois métodos abstractos:
    - float calcularArea();
    - float calcularPerimetro();
- A classe ``Retangulo`` deve implementar os métodos *calcularArea()* e *calcularPerimetro()*. Deve ter atributo largura e altura do tipo float.
- A classe ``Circulo`` é uma subclasse de Forma e deve implementar os métodos *calcularArea()* e calcular perímetro. Adicione o raio como atributo.
-  classe ``Quadrado`` é uma subclasse de ``Retangulo``. Não tem nenhum atributo específico, porém a largura e a altura tem o mesmo tamanho.
- Todas as classes devem implementar um construtor.
- Todas as classes deve implementar um método toString(), informando o tipo da Forma, a área e o perímetro.
- Crie um arquivo Exercicio03.java, onde você irá declarar um array de Formas, do tamanho 03.
    - 1º - Círculo(Raio: 10)
    - 2º - Retângulo(Lado: 20, Altura:30);
    - 3º - Quadrado(Lado: 20)
- Faça um laço de repetição que percorra o array e imprima cada elemento

## Forma.java
```java
package Aula03.classes;

public interface Forma {
    float calcularArea();

    float calcularPerimetro();
}
```
## Retangulo.java
```java
package Aula03.classes;

public class Retangulo implements Forma {
    private float largura;
    private float altura;

    public Retangulo(float largura, float altura){
        this.largura = largura;
        this.altura = altura;
    };

    public float calcularArea(){
        return largura*altura;
    }

    public float calcularPerimetro(){
        return 2*(largura+altura);
    }

    @Override
    public String toString() {
        return "RETANGULO [\n"+
                "AREA: "+ this.calcularArea() + "\n"+
                "PERIMETRO: "+ this.calcularPerimetro() + "\n" + 
                "]";
    }
}
```
## Circulo.java
```java
package Aula03.classes;
import java.lang.Math;
public class Circulo implements Forma {
       private float raio;

    public Circulo(float raio){
        this.raio = raio;
    }
    public float calcularArea(){
        return (float)Math.PI*raio*raio;
    }

    public float calcularPerimetro(){
        return 2*(float)Math.PI*raio;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "CIRCULO [\n"+
                "AREA: "+ this.calcularArea() + "\n"+
                "PERIMETRO: "+ this.calcularPerimetro() + "\n" + 
                "]";
    }
    
}
```
## Quadrado.java
```java
package Aula03.classes;

public class Quadrado extends Retangulo{
    private float lado;

    public Quadrado(float lado) {
        super(lado, lado);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "QUADRADO [\n"+
                "AREA: "+ this.calcularArea() + "\n"+
                "PERIMETRO: "+ this.calcularPerimetro() + "\n" + 
                "]";
    }
}
```
## Main2.java
```java
package Aula03;
import Aula03.classes.*;
public class Main2 {
    public static void main(String[] args) {
        Circulo c = new Circulo(10);
        Retangulo r = new Retangulo(20,30);
        Quadrado q = new Quadrado(20);

        Forma[] formas = {c,q,r};

        for(int i = 0 ; i<formas.length ; i++){
            System.out.println(formas[i]);
        }
    }
    
}
```