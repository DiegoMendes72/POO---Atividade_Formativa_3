package model;

import interfaces.Emprestavel;
import exception.LivroJaEmprestadoException;
import exception.LivroNaoEmprestadoException;

public class Livro implements Emprestavel {

    private String codigo;
    private String titulo;
    private String autor;
    private boolean emprestado;

    public Livro(String codigo, String titulo, String autor) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.emprestado = false;
    }

    // Getters
    public String getCodigo()   { return codigo; }
    public String getTitulo()   { return titulo; }
    public String getAutor()    { return autor;  }
    public boolean isEmprestado() { return emprestado; }

    @Override
    public void emprestar() throws LivroJaEmprestadoException {
        if (emprestado) {
            throw new LivroJaEmprestadoException(
                "O livro '" + titulo + "' já está emprestado!");
        }
        emprestado = true;
    }

    @Override
    public void devolver() throws LivroNaoEmprestadoException {
        if (!emprestado) {
            throw new LivroNaoEmprestadoException(
                "O livro '" + titulo + "' não está emprestado!");
        }
        emprestado = false;
    }

    @Override
    public String toString() {
        return String.format(
            "Código: %-6s | Título: %-30s | Autor: %-25s | Status: %s",
            codigo, titulo, autor,
            emprestado ? "EMPRESTADO" : "DISPONÍVEL"
        );
    }
}
