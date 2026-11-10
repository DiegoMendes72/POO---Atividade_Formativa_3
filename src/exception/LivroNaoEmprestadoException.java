package exception;

public class LivroNaoEmprestadoException extends Exception {

    public LivroNaoEmprestadoException(String mensagem) {
        super(mensagem);
    }
}
