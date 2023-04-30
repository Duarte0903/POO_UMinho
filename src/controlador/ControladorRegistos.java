package controlador;

import modulos.gestores.Gestor;
import modulos.artigos.*;
import modulos.Utilizador;
import modulos.Transportadora;
import modulos.Encomenda;
import java.time.LocalDate;


public class ControladorRegistos{

    public static void insertRegisto(Gestor gestor, String[] tokens, int flag){

        switch (flag){

            case Controlador.INSERT_ARTIGO_MALA:
                gestor.insertArtigo(
                    new Mala(
                        Integer.valueOf(tokens[1]),
                        tokens[2],
                        tokens[3],
                        tokens[4],
                        tokens[5],
                        Double.valueOf(tokens[6]),
                        Double.valueOf(tokens[7]),
                        tokens[8],
                        Integer.valueOf(tokens[9]),
                        Integer.valueOf(tokens[10]),
                        Boolean.parseBoolean(tokens[11]),
                        Integer.valueOf(tokens[12]),
                        tokens[13],
                        LocalDate.parse(tokens[14])));
                break;

            case Controlador.INSERT_ARTIGO_TSHIRT:
                gestor.insertArtigo(
                    new Tshirt(
                        Integer.valueOf(tokens[1]),
                        tokens[2],
                        tokens[3],
                        tokens[4],
                        tokens[5],
                        Double.valueOf(tokens[6]),
                        Double.valueOf(tokens[7]),
                        tokens[8],
                        Integer.valueOf(tokens[9]),
                        Integer.valueOf(tokens[10]),
                        Boolean.parseBoolean(tokens[11]),
                        tokens[12],
                        tokens[13]));
                break;

            case Controlador.INSERT_ARTIGO_SAPATILHA:
                gestor.insertArtigo(
                    new Sapatilha(
                        Integer.valueOf(tokens[1]),
                        tokens[2],
                        tokens[3],
                        tokens[4],
                        tokens[5],
                        Double.valueOf(tokens[6]),
                        Double.valueOf(tokens[7]),
                        tokens[8],
                        Integer.valueOf(tokens[9]),
                        Integer.valueOf(tokens[10]),
                        Boolean.parseBoolean(tokens[11]),
                        tokens[12],
                        Integer.valueOf(tokens[13]),
                        Boolean.parseBoolean(tokens[14]),
                        LocalDate.parse(tokens[15])));
                break;


            case Controlador.INSERT_UTILIZADOR:
                gestor.insertUtilizador(
                    new Utilizador(
                        tokens[1],
                        tokens[2],
                        tokens[3],
                        Integer.valueOf(tokens[4]),
                        tokens[5]));
                break;

            case Controlador.INSERT_TRANPORTADORA:
                gestor.insertTransportadora(
                    new Transportadora(
                        tokens[1],
                        Double.valueOf(tokens[2]),
                        Double.valueOf(tokens[3]),
                        Double.valueOf(tokens[4]),
                        Double.valueOf(tokens[5]),
                        Boolean.parseBoolean(tokens[6])));
                break;

            case Controlador.INSERT_ENCOMENDA:
                gestor.insertEncomenda(new Encomenda(Integer.valueOf(tokens[1])));
                break;
        }
    }

    public static void alterarRegisto(Gestor gestor, String[] tokens, int flag){

        switch (flag){

            case Controlador.CHANGE_DATA:
                gestor.updateData(LocalDate.parse(tokens[1]));
                break;

            case Controlador.CHANGE_VINTAGE_COMISSAO:
                Gestor.setComissao(Double.valueOf(tokens[1]));
                break;

            case Controlador.INSERT_ARTIGO_ENCOMENDA:
                gestor.insertArtigoEncomenda(Integer.valueOf(tokens[1]),tokens[2]);
                break;

            case Controlador.REMOVE_ARTIGO_ENCOMENDA:
                gestor.removeArtigoEncomenda(Integer.valueOf(tokens[1]),tokens[2]);
                break;

            case Controlador.CHECK_OUT_ENCOMENDA:
                gestor.finalizarEncomenda(Integer.valueOf(tokens[1]));
                break;

            case Controlador.REFUND_ENCOMENDA:
                gestor.devolverEncomenda(Integer.valueOf(tokens[1]));
                break;

            case Controlador.CHANGE_PRECO_ARTIGO:
                gestor.alterarPrecoArtigo(tokens[1],Double.valueOf(tokens[2]));
                break;

            case Controlador.CHANGE_PRECOS_TRANSPORTADORA:
                gestor.alterarPrecosTransportadora(
                    tokens[1],
                    Double.valueOf(tokens[2]),
                    Double.valueOf(tokens[3]),
                    Double.valueOf(tokens[4]),
                    Double.valueOf(tokens[5]));
                break;
        }
    }
}