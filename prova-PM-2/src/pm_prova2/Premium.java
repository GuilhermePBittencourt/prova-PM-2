package src.pm_prova2;

import Aluguel;

public class Premium extends Aluguel {

    public Premium(String nomeCliente, int quantidadeDias, double valorTotal, StatusAluguel status) {
        super(nomeCliente, quantidadeDias, valorTotal, status);
    }

    public boolean isQuilometragemIlimitada() {
        return true;
    }

    @Override
    public boolean possuiSeguroIncluso() {
        return true;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("           ALUGUEL PREMIUM");
        System.out.println(dadosBasicos());
        System.out.println("Quilometragem: ilimitada");
    }
}
