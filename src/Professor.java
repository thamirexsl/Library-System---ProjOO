public class Professor extends Usuario {
    private String nomeDepartamento;

    public Professor(String id, String nome, String login, boolean elegivel, String nomeDepartamento) {
        super(id, nome, login, elegivel, "Professor");
        this.nomeDepartamento = nomeDepartamento;
    }

    @Override
    public String toString() {
        return super.toString() + "," + nomeDepartamento;
    }
}