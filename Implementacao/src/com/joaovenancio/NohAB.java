package com.joaovenancio;

public class NohAB <E extends IUnificavel> {
    //Atributos:
    private E dado;
    private NohAB filhoEsquerdo;
    private NohAB filhoDireito;
    private NohAB pai;
    private int nivelAVL;

    //Construtor:
    public NohAB(E dado) {
        this.dado = dado;
        this.filhoEsquerdo = null;
        this.filhoDireito = null;
        this.pai = null;
        this.nivelAVL = 0;
    }

    public NohAB(E dado, NohAB filhoEsquerdo, NohAB filhoDireito) {
        this.dado = dado;
        this.filhoEsquerdo = filhoEsquerdo;
        this.filhoDireito = filhoDireito;
        this.pai = null;
        this.nivelAVL = 0;
    }

    public NohAB(E dado, NohAB pai) {
        this.dado = dado;
        this.pai = pai;
        this.filhoEsquerdo = null;
        this.filhoDireito = null;
        this.nivelAVL = 0;
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

    public NohAB getPai() {
        return pai;
    }

    public void setPai(NohAB pai) {
        this.pai = pai;
    }

    public int getNivelAVL() {
        return nivelAVL;
    }

    public void setNivelAVL(int nivelAVL) {
        this.nivelAVL = nivelAVL;
    }
}
