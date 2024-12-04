package com.flyeasy.models;

public class Aeroporto {
    private String nome;
    private String sigla;
    private String cidade;
    private String estado;
    private String pais;
    private TipoVoo tipoVoo;
    private double latitude;
    private double longitude;

    public Aeroporto(String nome, String sigla, String cidade, String estado, String pais, TipoVoo tipoVoo, double latitude, double longitude) {
        this.nome = nome;
        this.sigla = sigla;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.tipoVoo = tipoVoo;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Construtor sem parâmetros
    public Aeroporto() {
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public TipoVoo getTipoVoo() {
        return tipoVoo;
    }

    public void setTipoVoo(TipoVoo tipoVoo) {
        this.tipoVoo = tipoVoo;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) { // Corrigido: atribuir valor correto
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) { // Corrigido: atribuir valor correto
        this.longitude = longitude;
    }

    public double calcularDistanciaKm(Aeroporto outroAeroporto) {
        double x1 = Math.toRadians(this.latitude);
        double y1 = Math.toRadians(this.longitude);
        double x2 = Math.toRadians(outroAeroporto.latitude);
        double y2 = Math.toRadians(outroAeroporto.longitude);

        double deltaLat = x2 - x1;
        double deltaLon = y2 - y1;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(x1) * Math.cos(x2)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371 * c; // Retorna a distância em km (raio médio da Terra = 6371 km)
    }
}
