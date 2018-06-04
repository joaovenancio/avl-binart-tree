package com.joaovenancio;

public class NohAB <E extends IUnificavel> {
    //Atributos:
    private E dado;
    private NohAB filhoEsquerdo;
    private NohAB filhoDireito;

    //Construtor:
    public NohAB(E dado) {
        this.dado = dado;
        this.filhoEsquerdo = null;
        this.filhoDireito = null;
    }

    public NohAB(E dado, NohAB filhoEsquerdo, NohAB filhoDireito) {
        this.dado = dado;
        this.filhoEsquerdo = filhoEsquerdo;
        this.filhoDireito = filhoDireito;
    }

    //Metodos:
    public NohAB getFilhoEsquerdo() {
        return filhoEsquerdo;
    }

    public void setFilhoEsquerdo(NohAB filhoEsquerdo) {
        this.filhoEsquerdo = filhoEsquerdo;
    }

    public NohAB getFilhoDireito() {
        return filhoDireito;
    }

    public void setFilhoDireito(NohAB filhoDireito) {
        this.filhoDireito = filhoDireito;
    }

    public E getDado() {
        return dado;
    }

    public void setDado(E dado) {
        this.dado = dado;
    }
}
