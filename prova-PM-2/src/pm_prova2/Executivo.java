package src.pm_prova2;

import Aluguel;

public class Executivo extends Aluguel implements Avaliavel {

    public static final int NOTA_MINIMA = 1;
    public static final int NOTA_MAXIMA = 5;

    private String motoristaParticular;
    private int somaNotas;
    private int quantidadeAvaliacoes;

    public Executivo(String nomeCliente, int quantidadeDias, double valorTotal,
            StatusAluguel status, String motoristaParticular) {
        super(nomeCliente, quantidadeDias, valorTotal, status);
        this.motoristaParticular = motoristaParticular;
    }

    public String getMotoristaParticular() {
        return motoristaParticular;
    }

    public void setMotoristaParticular(String motoristaParticular) {
        this.motoristaParticular = motoristaParticular;
    }

    public boolean isQuilometragemIlimitada() {
        return true;
    }

    public int getQuantidadeAvaliacoes() {
        return quantidadeAvaliacoes;
    }

    public double getMediaAvaliacoes() {
        return quantidadeAvaliacoes == 0 ? 0 : (double) somaNotas / quantidadeAvaliacoes;
    }


    @throws IllegalArgumentException
    
    @Override
    public void avaliar(int nota) {
        if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
            throw new IllegalArgumentException("A nota deve estar entre " + NOTA_MINIMA + " e " + NOTA_MAXIMA + ".");
        }
        somaNotas += nota;
        quantidadeAvaliacoes++;
    }

    @Override
    public boolean possuiSeguroIncluso() {
        return false;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("---------- ALUGUEL EXECUTIVO ----------");
        System.out.println(dadosBasicos());
        System.out.println("Quilometragem: ilimitada");
        System.out.println("Motorista particular: " + motoristaParticular);
        System.out.printf("Avaliação: %.1f estrela(s) (%d avaliação(ões))%n",
                getMediaAvaliacoes(), quantidadeAvaliacoes);
    }
}
