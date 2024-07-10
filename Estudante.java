public class Estudante extends Usuario {
    private String numeroMatricula;

    public Estudante(int id, String nome, String login, boolean elegivel, String numeroMatricula) {
        super(id, nome, login, elegivel);
        this.numeroMatricula = numeroMatricula;
    }

    // Getters e setters
    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    @Override
    public String toString() {
        return super.toString() + "," + numeroMatricula;
    }
}
