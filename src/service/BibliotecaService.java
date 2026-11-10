package service;

import exception.LivroJaEmprestadoException;
import exception.LivroNaoEmprestadoException;
import exception.LivroNaoEncontradoException;
import model.Livro;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {

    /** Coleção que armazena os livros (ArrayList + Generics). */
    private final ArrayList<Livro> livros = new ArrayList<>();

    public void cadastrar(Livro livro) {
        livros.add(livro);
    }

    public List<Livro> listar() {
        return new ArrayList<>(livros); // cópia defensiva
    }

    public Livro buscar(String codigo) throws LivroNaoEncontradoException {
        for (Livro livro : livros) {
            if (livro.getCodigo().equalsIgnoreCase(codigo)) {
                return livro;
            }
        }
        throw new LivroNaoEncontradoException(
            "Livro com código '" + codigo + "' não encontrado.");
    }

    public void emprestar(String codigo)
            throws LivroNaoEncontradoException, LivroJaEmprestadoException {
        Livro livro = buscar(codigo);
        livro.emprestar();
    }

    public void devolver(String codigo)
            throws LivroNaoEncontradoException, LivroNaoEmprestadoException {
        Livro livro = buscar(codigo);
        livro.devolver();
    }
}
