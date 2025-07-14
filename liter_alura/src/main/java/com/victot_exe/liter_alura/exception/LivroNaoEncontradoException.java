package com.victot_exe.liter_alura.exception;

public class LivroNaoEncontradoException extends RuntimeException{
    public LivroNaoEncontradoException(String message){
        super(message);
    }

    public LivroNaoEncontradoException(){}
}
