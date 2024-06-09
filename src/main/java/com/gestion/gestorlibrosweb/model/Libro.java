package com.gestion.gestorlibrosweb.model;

public class Libro {

    private int ISBN;
    private String titulo;
    private String autor;
    private String editorial;
    private int ano;
    private TipoLibro tipoLibro;
    public Categoria categoria;

    public Libro(String ISBN, String titulo, String autor, Categoria editorial, int ano, TipoLibro tipoLibro) {
        this.ISBN = Integer.parseInt(ISBN);
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = String.valueOf(editorial);
        this.ano = ano;
        this.tipoLibro = tipoLibro;
        this.categoria = categoria;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public TipoLibro getTipoLibro() {
        return tipoLibro;
    }

    public void setTipoLibro(TipoLibro tipoLibro) {
        this.tipoLibro = tipoLibro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {

        this.categoria = categoria;
    }
}
