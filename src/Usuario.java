abstract class Usuario {
    protected int id;
    protected String nome;
    protected String login;
    protected boolean elegivel;
    protected String tipo; // Adicionando o campo tipoz

    public Usuario(int id, String nome, String login, boolean elegivel, String tipo) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.elegivel = elegivel;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }
     public String getLogin() {
        return login;
    }

    public String getTipo() {
        return tipo;
    }

    public String getId() {
        return Integer.toString(id);
    }

    @Override
    public String toString() {
        return id + "," + nome + "," + login + "," + elegivel + "," + tipo;
    }
}