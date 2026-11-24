package src.pm_prova2;

@author glbra
public abstract class Veiculo {
    
    private String placa;
    private String modelo;
    private int anoFabricacao;
    private double valorDiaria;
    private double quilometragem;
    private StatusVeiculo status;

    public Veiculo(String placa, String modelo, int anoFabricacao, double valorDiaria,double quilometragem, StatusVeiculo status) {

        this.placa = placa;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.valorDiaria = valorDiaria;
        this.quilometragem = quilometragem;
        this.status = status;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(double quilometragem) {
        this.quilometragem = quilometragem;
    }

    public StatusVeiculo getStatus() {
        return status;
    }

    public void setStatus(StatusVeiculo status) {
        this.status = status;
    }
    
    protected String dadosBasicos() {
        return String.format("Placa: %s%nModelo: %s%nAno de fabricação: %d%n"
                + "Valor da diária: R$ %.2f%nQuilometragem: %.1f km%nStatus: %s%nSeguro especial: %s",
                placa, modelo, anoFabricacao, valorDiaria, quilometragem, status,
                possuiSeguroEspecial() ? "Sim" : "Não");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + modelo + " [" + placa + "] - " + status;
    }

    public abstract void exibirDetalhes();

    public abstract boolean possuiSeguroEspecial();
    
}

