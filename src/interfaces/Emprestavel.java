package interfaces;

import exception.LivroJaEmprestadoException;
import exception.LivroNaoEmprestadoException;

/**
 * Interface que define o contrato de empréstimo para itens da biblioteca.
 */
public interface Emprestavel {

    void emprestar() throws LivroJaEmprestadoException;

    void devolver() throws LivroNaoEmprestadoException;
}
