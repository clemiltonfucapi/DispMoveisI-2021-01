# RecyclerView - Listagem de Itens:
- Crie um novo projeto chamado Lista de tarefas.
- Criar um novo pacote chamado ``model`` e inserir criar uma classe ``Tarefa``
    - ![Images](imgs/img02.png)
    ```java
    package com.example.listadetarefas2.model;

    public class Tarefa {
        private String tarefa;

        public Tarefa(String tarefa) {
            this.tarefa = tarefa;
        }

        public String getTarefa() {
            return tarefa;
        }

        public void setTarefa(String tarefa) {
            this.tarefa = tarefa;
        }
    }
    ```
- No codigo a seguir estamos preenchendo um ``ArrayList``
    ```java
    public class MainActivity extends AppCompatActivity {
        private ArrayList<Tarefa> listaTarefas = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            preencherLista();
        }

        public void preencherLista(){
            /* 1º - Estático, depois B.D */
            listaTarefas.add(new Tarefa("Corrida"));
            listaTarefas.add(new Tarefa("Supermercado"));
            listaTarefas.add(new Tarefa("Fucapi"));
        }
    }
    ```
- Agora vamos adicionar uma RecyclerView em ``activity_main.xml``:
    - ![Images](imgs/img01.png)
- Coloque as constraints e margens na RecyclerView:
    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

    <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/recyclerTarefas"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_marginStart="8dp"
            android:layout_marginLeft="8dp"
            android:layout_marginTop="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginRight="8dp"
            android:layout_marginBottom="8dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />
    </androidx.constraintlayout.widget.ConstraintLayout>
    ```
    - 
- Agora vamos criar um layout de um elemento: ``layout/item_tarefa.xml``:
    - ![Images](imgs/img03.png)
    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="5dp">
        <TextView
            android:id="@+id/nomeTarefa"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Tarefa"
            android:textStyle="bold"
            />
    </LinearLayout>
    ```
    - Resutado do Layout: 
    - ![Images](imgs/img04.png)
- Crie um atributo para a RecyclerView:
```java
package com.example.listadetarefas2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.listadetarefas2.model.Tarefa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Tarefa> listaTarefas = new ArrayList<>();
    //adicionar recycler
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerTarefas);
        preencherLista();


    }
    ...
}

```
- Agora vamos criar o adaptador para a RecyclerView:
    - ![Images](imgs/img05.png)
    ```java
    public class TarefaAdapter {

        /* Classe ViewHolder -> Representa um elemento da recycler*/
        public class TarefaVH extends RecyclerView.ViewHolder{
            TextView tarefa;
            public TarefaVH(@NonNull View itemView) {
                super(itemView);
                tarefa = itemView.findViewById(R.id.nomeTarefa);
            }
        }
    }
    ```
    - Criamos uma classe interna chamada ``TarefaVH``. Esta classe fará o papel da ViewHolder, ou seja, representa um elemento da *RecyclerView*.
    - O parâmetro ``itemView``  representa o layout ``item_tarefa.xml`` convertido p/ View
    - Para criar uma classe ``ViewHolder`` devemos herdar a classe ``RecyclerView.ViewHolder``
- Agora vamos implementar o Adapter:
    - Implemente os métodos abstratos da classe Adapter ![Images](imgs/img06.png)
    ```java
    public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaVH> {
        //atributos
        private Context context;
        private ArrayList<Tarefa> listaTarefas;
        
        //construtor
        public TarefaAdapter(Context context, ArrayList<Tarefa> listaTarefas){
            this.context = context;
            this.listaTarefas = listaTarefas;
        }

        @NonNull
        @Override
        public TarefaVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull TarefaVH holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
        .....

    ```
    - Criamos dois atributos, ``context`` e ``listaTarefas``. O atributo ``context`` guarda um objeto do tipo ``Context`` para ser utilizado fora da activity. O atributo ``listaTarefas`` guarda os dados das tarefas.
    - O construtor inicializa os atriubutos
- Agora vamos implementar os métodos do ``TarefaAdapter``
- ``getItemCount()``
    ```java
    @Override
    public int getItemCount() {
        return listaTarefas.size();
    }
    ```
    - Este método deve retorna o número de elementos que a Recycler deve ter. Neste caso é o mesmo número de elementos do ArrayList

- ``onCreateViewHolder()``
    ```java
    public TarefaVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //criando um inflater: objeto que converte XML em VIEW
        LayoutInflater l = LayoutInflater.from(context);
        //convertendo XML em View
        View itemLista = l.inflate(R.layout.item_tarefa,parent,false);
        //criando ViewHolder
        return new TarefaVH(itemLista);
    }
    ```
    - Este método tem como objetivo instanciar um objeto do tipo ``TarefaVH``. 
- ``onBindViewHolder()``
    ```java
    @Override
    public void onBindViewHolder(@NonNull TarefaVH holder, int position) {
        Tarefa tarefa = listaTarefas.get(position);
        holder.textTarefa.setText(tarefa.getTarefa());
    }
    ```
    - Este método deve juntar os dados(ArrayList) com as Views.
- Agora vamos reconfigurar o código do onCreate
```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerTarefas);
        preencherLista();
        //setando o gerenciador de layouts
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //setando adapter
        recyclerView.setAdapter(new TarefaAdapter(getApplicationContext(),listaTarefas));
        //criando um separador de itens
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

    }
```
