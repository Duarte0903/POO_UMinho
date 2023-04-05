package entidades;

import java.util.Date;
import java.util.ArrayList;

public class Encomenda
{
    private Double preco_total;

    private Date data_criacao;

    private ArrayList<Artigo> artigos;

    private enum Embalagem {
        grande, media, pequena;
    }

    private enum Estado {
        pendente, finalizada, expedida;
}
}
