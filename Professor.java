public class Professor extends Usuario {
    private String nomeDepartamento;

    public Professor(int id, String nome, String login, boolean elegivel, String nomeDepartamento) {
        super(id, nome, login, elegivel);
        this.nomeDepartamento = nomeDepartamento;
    }

    // Getters e setters
    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    @Override
    public String toString() {
        return super.toString() + "," + nomeDepartamento;
    }
}