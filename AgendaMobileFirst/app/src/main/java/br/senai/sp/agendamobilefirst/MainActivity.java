package br.senai.sp.agendamobilefirst;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

import br.senai.sp.dao.ContatoDao;
import br.senai.sp.modelo.Contato;

public class MainActivity extends AppCompatActivity {

    private ListView listaContatos;
    private ImageButton btNovoContato;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContatos = findViewById(R.id.list_contatos);

        btNovoContato = findViewById(R.id.bt_novo_contato);
        btNovoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirCadastro = new Intent(MainActivity.this, CadastroContatoActivity.class);
                startActivity(abrirCadastro);


            }
        });

        registerForContextMenu(listaContatos);
        listaContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contato contato = (Contato) listaContatos.getItemAtPosition(position);

                Intent cadastro = new Intent(MainActivity.this, CadastroContatoActivity.class);
                cadastro.putExtra("contato", contato);


                startActivity(cadastro);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

    }

    @Override
    protected void onResume() {
//        Toast.makeText(MainActivity.this, "R E S U M E", Toast.LENGTH_LONG).show();
        carregarLista();
        super.onResume();
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_context_lista_contatos, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }



    private void carregarLista(){
        ContatoDao dao = new ContatoDao(this);
        List<Contato> contatos = dao.getContatos();
        dao.close();

        ArrayAdapter<Contato> adapterContatos = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatos);

        listaContatos.setAdapter(adapterContatos);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        final ContatoDao dao = new ContatoDao(this);

        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Contato contato = (Contato) listaContatos.getItemAtPosition(info.position);
        new AlertDialog.Builder(this)
                .setTitle("Deletanto o contato")
                .setMessage("Tem certeza que deseja deletar esse contato: "+ contato.getNome()+ "?")
                .setPositiveButton("sim",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                dao.excluir(contato);
                                dao.close();
                                carregarLista();
                            }
                        })
                .setNegativeButton("não", null).show();

        return super.onContextItemSelected(item);
    }
}
