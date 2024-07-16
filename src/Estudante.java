public class Estudante extends Usuario {
    private String numeroMatricula;

    public Estudante(String id, String nome, String login, boolean elegivel, String numeroMatricula) {
        super(id, nome, login, elegivel, "Estudante");
        this.numeroMatricula = numeroMatricula;
    }

    @Override
    public String toString() {
        return super.toString() + "," + numeroMatricula;
    }
}
