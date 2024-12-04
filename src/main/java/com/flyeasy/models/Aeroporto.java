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
        this.longitude = longitude;  // Inicializando longitude
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

    public void setLatitude(double d) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double d) {
        this.longitude = longitude;
    }

        public double calcularDistanciaKm(Aeroporto outroAeroporto) {
        double x1 = this.latitude;
        double y1 = this.longitude;
        double x2 = outroAeroporto.latitude;
        double y2 = outroAeroporto.longitude;

        return 110.57 * Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
