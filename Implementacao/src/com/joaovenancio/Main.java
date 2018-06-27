package com.joaovenancio;

public class Main {

    public static void main(String[] args) {
	    ABBADAVL<Pessoa> arvore = new ABBADAVL<Pessoa>();

	    Pessoa p1 = new Pessoa("Debora");
        p1.setiD(10);
        Pessoa p2 = new Pessoa("Debora");
        p2.setiD(20);
        Pessoa p3 = new Pessoa("Debora");
        p3.setiD(30);
        Pessoa p4 = new Pessoa("Debora");
        p4.setiD(40);
        Pessoa p5 = new Pessoa("Debora");
        p5.setiD(50);
        Pessoa p6 = new Pessoa("Debora");
        p6.setiD(25);


        System.out.println("Inserir:");
        arvore.inserir(p1);
        System.out.println("Inserindo "+p1.getID()+".");
        arvore.inserir(p2);
        System.out.println("Inserindo "+p2.getID()+".");
        arvore.inserir(p3);
        System.out.println("Inserindo "+p3.getID()+".");
        arvore.inserir(p4);
        System.out.println("Inserindo "+p4.getID()+".");
        arvore.inserir(p5);
        System.out.println("Inserindo "+p5.getID()+".");
        arvore.inserir(p6);
        System.out.println("Inserindo "+p6.getID()+".");

        System.out.println();
        System.out.println("Buscando:");



//        Pessoa p1 = new Pessoa("Debora");
//        p1.setiD(10);
//        Pessoa p2 = new Pessoa("Debora");
//        p2.setiD(20);
//        Pessoa p3 = new Pessoa("Debora");
//        p3.setiD(30);
//
//        arvore.inserir(p1);
//        arvore.inserir(p2);
//        arvore.inserir(p3);
//
        //arvore.preOredem(arvore.getRaiz());

        try {
            System.out.println(arvore.buscar(20).getID());
            System.out.println(arvore.buscar(25).getID());
            System.out.println(arvore.buscar(50).getID());
            System.out.println(arvore.buscar(100).getID());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Excuir:");
        System.out.println("50");
        arvore.remover(50);
        System.out.println("20");
        arvore.remover(20);

        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("Buscando:");
        try {
            System.out.println(arvore.buscar(10).getID());
            System.out.println(arvore.buscar(20).getID());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("Imprimir a arvore:");
        arvore.emOrdem();
    }
}
