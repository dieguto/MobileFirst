package br.senai.sp.agendamobilefirst;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import br.senai.sp.dao.ContatoDao;
import br.senai.sp.modelo.Contato;

import static br.senai.sp.agendamobilefirst.R.menu.menu_cadastro_contato;

public class CadastroContatoActivity extends AppCompatActivity {

    private TextView titulo;


    private CadastroContatoHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);

        titulo = findViewById(R.id.titulo_tela);

        helper = new CadastroContatoHelper(this);
        Intent intent = getIntent();
        Contato contato = (Contato) intent.getSerializableExtra("contato");
        if(contato != null){
            titulo.setText("Contato");
            helper.preencherFormulario(contato);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cadastro_contato, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


            switch (item.getItemId()){
                case R.id.menu_salvar:

                    Contato contato = helper.getContato();
                    ContatoDao dao = new ContatoDao(this);
                    if(helper.validar()) {
                        if (contato.getId() == 0) {
                            dao.salvar(contato);
                            Toast.makeText(CadastroContatoActivity.this, "contato salvo", Toast.LENGTH_LONG).show();
                        } else {
                            dao.atualizar(contato);
                            Toast.makeText(CadastroContatoActivity.this, "contato atualizado com sucesso", Toast.LENGTH_LONG).show();
                        }
                        dao.close();
                        finish();
                    }


                    break;
                case R.id.menu_del:


                    final Contato contato2 = helper.getContato();
                    final ContatoDao dao2 = new ContatoDao(this);

                    if(contato2.getId() == 0){
                        Toast.makeText(CadastroContatoActivity.this, "Ops, esse contato ainda não foi cadastrado", Toast.LENGTH_LONG).show();
                    }else{
                        new AlertDialog.Builder(this)
                                .setTitle("Deletando o contato")
                                .setMessage("Tem certeza que deseja deletar esse contato:" + contato2.getNome()+"?")
                                .setPositiveButton("sim",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                dao2.excluir(contato2);
                                                dao2.close();
                                                Toast.makeText(CadastroContatoActivity.this, "O contato" + contato2.getNome() + "foi excluido com sucesso", Toast.LENGTH_LONG).show();
                                                finish();

                                            }
                                        })
                                .setNegativeButton("não", null).show();

                    }




//                    Toast.makeText(CadastroContatoActivity.this, "Contato excluido com sucesso", Toast.LENGTH_LONG).show();
                    break;

                default:
//                    Toast.makeText(CadastroContatoActivity.this, "voltar", Toast.LENGTH_LONG).show();
            }





        return super.onOptionsItemSelected(item);
    }


}
