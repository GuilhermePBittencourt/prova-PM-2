package src.pm_prova2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import Aluguel;

public class Locadora {

    private final List<Veiculo> veiculos = new ArrayList<>();
    private final List<Aluguel> alugueis = new ArrayList<>();

    public void cadastrarVeiculo(Veiculo veiculo) {
        if (buscarVeiculoPorPlaca(veiculo.getPlaca()) != null) {
            throw new IllegalArgumentException("Já existe um veículo com a placa " + veiculo.getPlaca() + ".");
        }
        veiculos.add(veiculo);
    }

    public void cadastrarAluguel(Aluguel aluguel) {
        alugueis.add(aluguel);
    }

    public List<Veiculo> getVeiculos() {
        return Collections.unmodifiableList(veiculos);
    }

    public List<Aluguel> getAlugueis() {
        return Collections.unmodifiableList(alugueis);
    }

    public Veiculo buscarVeiculoPorPlaca(String placa) {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }

    public void associarVeiculo(Aluguel aluguel, String placa) {
        Veiculo veiculo = buscarVeiculoPorPlaca(placa);
        if (veiculo == null) {
            throw new IllegalArgumentException("Nenhum veículo encontrado com a placa " + placa + ".");
        }
        aluguel.associarVeiculo(veiculo);
    }

    public List<Veiculo> getVeiculosDisponiveis() {
        List<Veiculo> disponiveis = new ArrayList<>();
        for (Veiculo v : veiculos) {
            if (v.getStatus() == StatusVeiculo.DISPONIVEL) {
                disponiveis.add(v);
            }
        }
        return disponiveis;
    }

    public List<Avaliavel> getAvaliaveis() {
        List<Avaliavel> avaliaveis = new ArrayList<>();
        avaliaveis.addAll(filtrarPorTipo(veiculos, Avaliavel.class));
        avaliaveis.addAll(filtrarPorTipo(alugueis, Avaliavel.class));
        return avaliaveis;
    }

    public List<Inspecionavel> getInspecionaveis() {
        return filtrarPorTipo(veiculos, Inspecionavel.class);
    }

    @return
    public List<Veiculo> removerVeiculosEmManutencao() {
        List<Veiculo> removidos = new ArrayList<>();
        Iterator<Veiculo> it = veiculos.iterator();
        while (it.hasNext()) {
            Veiculo v = it.next();
            if (v.getStatus() == StatusVeiculo.MANUTENCAO) {
                for (Aluguel a : alugueis) {
                    a.desassociarVeiculo(v);
                }
                it.remove();
                removidos.add(v);
            }
        }
        return removidos;
    }

    private <T> List<T> filtrarPorTipo(List<?> origem, Class<T> tipo) {
        List<T> resultado = new ArrayList<>();
        for (Object obj : origem) {
            if (tipo.isInstance(obj)) {
                resultado.add(tipo.cast(obj));
            }
        }
        return resultado;
    }
}
