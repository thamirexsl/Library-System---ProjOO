public abstract class Usuario {
    private int id;
    private String nome;
    private String login;
    private boolean elegivel;

    public Usuario(int id, String nome, String login, boolean elegivel) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.elegivel = elegivel;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isElegivel() {
        return elegivel;
    }

    public void setElegivel(boolean elegivel) {
        this.elegivel = elegivel;
    }

    @Override
    public String toString() {
        return id + "," + nome + "," + login + "," + elegivel;
    }
}