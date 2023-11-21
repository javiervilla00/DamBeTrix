package com.example.dambetrix;

import java.util.ArrayList;
import java.util.List;

public class Porra {
    private List<Participante> participantes;

    public Porra() {
        this.participantes = new ArrayList<>();
    }

    public void agregarParticipante(Participante participante) {
        participantes.add(participante);
    }

    public List<Participante> obtenerParticipantes() {
        return participantes;
    }

    public void eliminarParticipante(Participante participante) {
        participantes.remove(participante);
    }

    public Participante encontrarGanador(double notaReal) {
        Participante ganador = participantes.get(0);
        double menorDiferencia = ganador.calcularDiferencia(notaReal);

        for (Participante p : participantes) {
            double diferencia = p.calcularDiferencia(notaReal);
            if (diferencia < menorDiferencia) {
                ganador = p;
                menorDiferencia = diferencia;
            }
        }

        return ganador;
    }

    public double calcularPromedioNotasOponentes() {
        double sumaNotas = 0.0;
        int totalParticipantes = 0;

        for (Participante p : participantes) {
            List<Double> notas = p.getNotasOponentes();
            for (Double nota : notas) {
                sumaNotas += nota;
                totalParticipantes++;
            }
        }

        return totalParticipantes > 0 ? sumaNotas / totalParticipantes : 0.0;
    }

    public int cantidadParticipantes() {
        return participantes.size();
    }

    // Otros métodos para gestionar la porra, obtener estadísticas, etc.
}