package encomendas;

import artigos.Artigo;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;


public class Encomenda{

    private LocalDate data_criacao;
    private List<Artigo> artigos;
    private String embalagem;
    private String estado;
}
