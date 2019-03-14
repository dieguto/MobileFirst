package br.senai.sp.agendamobilefirst;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import br.senai.sp.modelo.Contato;

public class CadastroContatoHelper {

    private EditText txtNome;
    private EditText txtEndereco;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtLinkedin;
    private Contato contato;
    private TextInputLayout layoutNome;
    private  TextInputLayout layoutEndereco;
    private TextInputLayout layoutTelefone;
    private TextInputLayout layoutEmail;
    private TextInputLayout layoutLinkedin;


    public CadastroContatoHelper(CadastroContatoActivity activity){

        txtNome = activity.findViewById(R.id.txt_nome);
        txtEndereco = activity.findViewById(R.id.txt_endereco);
        txtTelefone = activity.findViewById(R.id.txt_telefone);
        txtEmail = activity.findViewById(R.id.txt_email);
        txtLinkedin = activity.findViewById(R.id.txt_linkedin);
        layoutNome = activity.findViewById(R.id.layoutTxtNome);
        layoutEndereco = activity.findViewById(R.id.layoutTxtEndereco);
        layoutTelefone = activity.findViewById(R.id.layoutTxtTelefone);
        layoutEmail = activity.findViewById(R.id.layoutTxtEmail);
        layoutLinkedin = activity.findViewById(R.id.layoutTxtLinkedin);


        contato = new Contato();
    }

    public Contato getContato(){

        contato.setNome(txtNome.getText().toString());
        contato.setEndereco(txtEndereco.getText().toString());
        contato.setTelefone(txtTelefone.getText().toString());
        contato.setEmail(txtEmail.getText().toString());
        contato.setLinkedin(txtLinkedin.getText().toString());

        return contato;

    }

    public void preencherFormulario(Contato contato){
        txtNome.setText(contato.getNome());
        txtEndereco.setText(contato.getEndereco());
        txtTelefone.setText(contato.getTelefone());
        txtEmail.setText(contato.getEmail());
        txtLinkedin.setText(contato.getLinkedin());
        this.contato = contato;
    }

    public boolean validar(){
        boolean validado = true;
        if (txtNome.getText().toString().isEmpty()){
            layoutNome.setErrorEnabled(true);
            layoutNome.setError("Preencha o campo nome");
            validado = false;
        }else{
            layoutNome.setErrorEnabled(false);
        }

        if (txtEndereco.getText().toString().isEmpty()){
            layoutEndereco.setErrorEnabled(true);
            layoutEndereco.setError("Preencha o campo endereço");
            validado = false;
        }else{
            layoutEndereco.setErrorEnabled(false);
        }

        if (txtTelefone.getText().toString().isEmpty()){
            layoutTelefone.setErrorEnabled(true);
            layoutTelefone.setError("Preencha o campo telefone");
            validado = false;
        }else{
            layoutTelefone.setErrorEnabled(false);
        }

        if (txtEmail.getText().toString().isEmpty()){
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError("Preencha o campo endereço");
            validado = false;
        }else{
            layoutEmail.setErrorEnabled(false);
        }

        if (txtLinkedin.getText().toString().isEmpty()){
            layoutLinkedin.setErrorEnabled(true);
            layoutLinkedin.setError("Preencha o campo Linkedin");
            validado = false;
        }else{
            layoutLinkedin.setErrorEnabled(false);
        }


        return validado;

    }

}
