package com.example.dambetrix;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Participante {
    private String nombre;
    private double notaApostada;
    private String premio;
    private BufferedImage imagen;
    private List<Double> notasOponentes;

    public Participante(String nombre, double notaApostada, String premio, BufferedImage imagen) {
        this.nombre = nombre;
        this.notaApostada = notaApostada;
        this.premio = premio;
        this.imagen = imagen;
        this.notasOponentes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getNotaApostada() {
        return notaApostada;
    }

    public void setNotaApostada(double notaApostada) {
        this.notaApostada = notaApostada;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public List<Double> getNotasOponentes() {
        return notasOponentes;
    }

    public void agregarNotaOponente(double nota) {
        notasOponentes.add(nota);
    }

    public double calcularDiferencia(double notaReal) {
        return Math.abs(notaReal - notaApostada);
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Nota apostada: " + notaApostada);
        System.out.println("Premio: " + premio);
        System.out.println("Notas de oponentes: " + notasOponentes);
    }
}
