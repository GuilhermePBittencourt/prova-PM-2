package src.pm_prova2;

import java.time.LocalDate;

public class Moto extends Veiculo implements Inspecionavel {

    private static final double KM_MAXIMA_APROVACAO = 40000;

    private int cilindrada;
    private LocalDate dataUltimaInspecao;

    public Moto(String placa, String modelo, int anoFabricacao, double valorDiaria,
            double quilometragem, StatusVeiculo status, int cilindrada) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem, status);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public LocalDate getDataUltimaInspecao() {
        return dataUltimaInspecao;
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
        return cilindrada >= 500;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("           MOTO");
        System.out.println(dadosBasicos());
        System.out.println("Cilindrada: " + cilindrada + " cc");
        System.out.println("Última inspeção: " + (dataUltimaInspecao == null ? "nunca" : dataUltimaInspecao));
    }
}
