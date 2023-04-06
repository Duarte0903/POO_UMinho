package entidades;

import entidades.artigo.Artigo;

import java.util.Date;
import java.util.ArrayList;

public class Encomenda
{
    private Double preco_total;

    private Date data_criacao;

    private ArrayList<Artigo> artigos;

    private String embalagem; // grande, media, pequena

    private String estado; // pendente, finalizada, expedida
}
