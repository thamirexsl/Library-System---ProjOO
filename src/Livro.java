class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String categoria;
    private int quantidade;

    public Livro(int _id, String _titulo, String _autor, String _categoria, int _quantidade) {
        this.id = _id;
        this.titulo = _titulo;
        this.autor = _autor;
        this.categoria = _categoria;
        this.quantidade = _quantidade;
    }

    @Override
    public String toString() {
        return this.titulo + " | " + this.autor + " |\n   " + this.categoria + "\n   Quantidade: " + this.quantidade;
    }

    public String getTitulo(){
        return this.titulo.toLowerCase();
    }

    public String getAutor(){
        return this.autor.toLowerCase();
    }

    public String getCategoria(){
        return this.categoria.toLowerCase();
    }

    public int getquantidade(){
        return this.quantidade;
    }
    
    public void emprestarLivro(){
        if (this.quantidade > 0){
            this.quantidade = this.quantidade - 1;
        } else {
            System.out.println("Os exemplares do titulo: " + this.titulo + " acabaram.");
        }
    }
}