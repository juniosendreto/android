package br.com.inpe.api.model;

/**
 * Created by junio on 04/11/15.
 */
public class Usuario extends BaseModel{

    private static final String COL_LOGIN = "LOGIN";

    private static final String COL_PASSWORD = "PASSWORD";

    private static final String COL_NOME = "NOME";

    private static final String COL_EMAIL = "EMAIL";

    private static final String COL_MUNICIPIO = "MUNICIPIO";

    private static final String COL_ENDERECO = "ENDERECO";

    private static final String COL_TELEFONE = "TELEFONE";

    private static final String COL_CELULAR = "CELULAR";

    private static final String COL_NIVEL = "NIVEL";

    private static final String TABLE_NAME = "USUARIO";

    protected String login;

    protected String password;

    protected String nome;

    protected String email;

    protected String municipio;

    protected String endereco;

    protected String telefone;

    protected String celular;

    protected  String nivel;

    /*
        - Criar Resttrições "Senha", Email, telefone e celular.

    */
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Usuario(Long id, String password, String login, String nome, String email, String municipio,
                   String endereco, String telefone, String celular, String nivel) {
        this.id = id;
        this.password = password;
        this.login = login;
        this.nome = nome;
        this.email = email;
        this.municipio = municipio;
        this.endereco = endereco;
        this.telefone = telefone;
        this.celular = celular;
        this.nivel = nivel;
    }

    public Usuario(){    }



}
