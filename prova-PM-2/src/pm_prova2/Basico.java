package src.pm_prova2;

import Aluguel;

public class Basico extends Aluguel {

    private static final int KM_POR_DIA = 100;

    public Basico(String nomeCliente, int quantidadeDias, double valorTotal, StatusAluguel status) {
        super(nomeCliente, quantidadeDias, valorTotal, status);
    }

    public int getKmPorDia() {
        return KM_POR_DIA;
    }

    public int getKmPermitidosNoPeriodo() {
        return KM_POR_DIA * getQuantidadeDias();
    }

    @Override
    public boolean possuiSeguroIncluso() {
        return false;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("          ALUGUEL BÁSICO");
        System.out.println(dadosBasicos());
        System.out.println("Limite: " + KM_POR_DIA + " km/dia (" + getKmPermitidosNoPeriodo() + " km no período)");
    }
}
