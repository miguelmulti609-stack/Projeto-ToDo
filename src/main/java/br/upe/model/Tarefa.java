package br.upe.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Tarefa {

    private String descricao;
    private LocalDate Data;
    private LocalTime Hora;
    private Integer DuracaoTarefa;
    private LocalDate dataCriacao;
    private LocalDate dataFinalizacao;
    private LocalDate dataVencimento;
    private Boolean finalizada;
    private int ordem;

    public Tarefa(String texto, int ordem, LocalDate data, LocalTime hora, Integer duracao) {
        this.Data = data;
        this.Hora = hora;
        this.DuracaoTarefa = duracao;
        this.dataCriacao = LocalDate.now();
        this.descricao = texto;
        this.ordem = ordem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDate dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(Boolean finalizada) {
        this.finalizada = finalizada;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public LocalDate getData(){return this.Data;}

    public void setData(LocalDate data) {
        this.Data = data;
    }

    public LocalTime getHora(){return this.Hora;}

    public void setHora(LocalTime hora) {
        this.Hora = hora;
    }
    public Integer getDuracaoTarefa(){return this.DuracaoTarefa;}
}

