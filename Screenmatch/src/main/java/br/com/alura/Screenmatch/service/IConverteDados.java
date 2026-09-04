package br.com.alura.Screenmatch.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
