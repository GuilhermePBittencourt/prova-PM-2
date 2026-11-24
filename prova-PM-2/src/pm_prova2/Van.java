package src.pm_prova2;

import java.time.LocalDate;

public class Van extends Veiculo implements Avaliavel, Inspecionavel {

    public static final int NOTA_MINIMA = 1;
    public static final int NOTA_MAXIMA = 5;
    private static final double KM_MAXIMA_APROVACAO = 100000;

    private int maximoPassageiros;
    private int somaNotas;
    private int quantidadeAvaliacoes;
    private LocalDate dataUltimaInspecao;

    public Van(String placa, String modelo, int anoFabricacao, double valorDiaria,
            double quilometragem, StatusVeiculo status, int maximoPassageiros) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem, status);
        this.maximoPassageiros = maximoPassageiros;
    }

    public int getMaximoPassageiros() {
        return maximoPassageiros;
    }

    public void setMaximoPassageiros(int maximoPassageiros) {
        this.maximoPassageiros = maximoPassageiros;
    }

    public int getQuantidadeAvaliacoes() {
        return quantidadeAvaliacoes;
    }

    public double getMediaAvaliacoes() {
        return quantidadeAvaliacoes == 0 ? 0 : (double) somaNotas / quantidadeAvaliacoes;
    }

    public LocalDate getDataUltimaInspecao() {
        return dataUltimaInspecao;
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

    @throws IllegalStateException
    @Override
    public void realizarInspecao() {
        if (getStatus() == StatusVeiculo.ALUGADO) {
            throw new IllegalStateException("Veículo alugado não pode ser inspecionado.");
        }
        dataUltimaInspecao = LocalDate.now();
        if (getQuilometragem() > KM_MAXIMA_APROVACAO) {
            setStatus(StatusVeiculo.MANUTENCAO);
        } else {
            setStatus(StatusVeiculo.DISPONIVEL);
        }
    }

    @Override
    public boolean possuiSeguroEspecial() {
        return true;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("          VAN");
        System.out.println(dadosBasicos());
        System.out.println("Máximo de passageiros: " + maximoPassageiros);
        System.out.printf("Avaliação: %.1f estrela(s) (%d avaliação(ões))%n",
                getMediaAvaliacoes(), quantidadeAvaliacoes);
        System.out.println("Última inspeção: " + (dataUltimaInspecao == null ? "nunca" : dataUltimaInspecao));
    }
}
