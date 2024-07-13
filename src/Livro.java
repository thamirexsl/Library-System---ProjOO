class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String descricao;

    public Livro(int _id, String _titulo, String _autor, String _descricao) {
        this.id = _id;
        this.titulo = _titulo;
        this.autor = _autor;
        this.descricao = _descricao;
    }

    @Override
    public String toString() {
        return this.id + "," + this.titulo + "," + this.autor + "," + this.descricao;
    }

    public String getTitulo(){
        return this.titulo.toLowerCase();
    }

    public String getAutor(){
        return this.autor.toLowerCase();
    }

    public String getDescricao(){
        return this.descricao.toLowerCase();
    }
}