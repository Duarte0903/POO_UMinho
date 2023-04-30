package controlador;

import modulos.gestores.Gestor;
import modulos.artigos.*;
import modulos.Utilizador;
import modulos.Transportadora;
import modulos.Tratador;
import modulos.Encomenda;
import java.time.LocalDate;


public class ControladorRegistos{

    public static void insertRegistoUtilizador(Gestor gestor, String[] tokens, int flag, int logged){

        switch (flag){

            case Controlador.INSERT_ARTIGO_MALA:
                gestor.insertArtigo(
                    new Mala(
                        logged,
                        tokens[1],
                        tokens[2],
                        tokens[3],
                        tokens[4],
                        Double.valueOf(tokens[5]),
                        Double.valueOf(tokens[6]),
                        tokens[7],
                        Integer.valueOf(tokens[8]),
                        Integer.valueOf(tokens[9]),
                        Boolean.parseBoolean(tokens[10]),
                        Integer.valueOf(tokens[11]),
                        tokens[12],
                        LocalDate.parse(tokens[13])));
                break;

            case Controlador.INSERT_ARTIGO_TSHIRT:
                gestor.insertArtigo(
                    new Tshirt(
                        logged,
                        tokens[1],
                        tokens[2],
                        tokens[3],
                        tokens[4],
                        Double.valueOf(tokens[5]),
                        Double.valueOf(tokens[6]),
                        tokens[7],
                        Integer.valueOf(tokens[8]),
                        Integer.valueOf(tokens[9]),
                        Boolean.parseBoolean(tokens[10]),
                        tokens[11],
                        tokens[12]));
                break;

            case Controlador.INSERT_ARTIGO_SAPATILHA:
                gestor.insertArtigo(
                    new Sapatilha(
                        logged,
                        tokens[1],
                        tokens[2],
                        tokens[3],
                        tokens[4],
                        Double.valueOf(tokens[5]),
                        Double.valueOf(tokens[6]),
                        tokens[7],
                        Integer.valueOf(tokens[8]),
                        Integer.valueOf(tokens[9]),
                        Boolean.parseBoolean(tokens[10]),
                        tokens[11],
                        Integer.valueOf(tokens[12]),
                        Boolean.parseBoolean(tokens[13]),
                        LocalDate.parse(tokens[14])));
                break;

            case Controlador.INSERT_ENCOMENDA:
                gestor.insertEncomenda(new Encomenda(Integer.valueOf(logged)));
                break;
        }
    }

    public static void insertRegistoVintage(Gestor gestor, String[] tokens, int flag, int logged){

        if (logged == -1){

            switch (flag){

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
            }
        }

        else Tratador.trataException(new Exception("Conta sem permissões"));
    }

    public static void alterarRegistoUtilizador(Gestor gestor, String[] tokens, int flag, int logged){

        switch (flag){

            case Controlador.INSERT_ARTIGO_ENCOMENDA:
                gestor.insertArtigoEncomenda(Integer.valueOf(tokens[1]),tokens[2],logged);
                break;

            case Controlador.REMOVE_ARTIGO_ENCOMENDA:
                gestor.removeArtigoEncomenda(Integer.valueOf(tokens[1]),tokens[2],logged);
                break;

            case Controlador.CHECK_OUT_ENCOMENDA:
                gestor.finalizarEncomenda(Integer.valueOf(tokens[1]),logged);
                break;

            case Controlador.REFUND_ENCOMENDA:
                gestor.devolverEncomenda(Integer.valueOf(tokens[1]),logged);
                break;

            case Controlador.CHANGE_PRECO_ARTIGO:
                gestor.alterarPrecoArtigo(tokens[1],Double.valueOf(tokens[2]),logged);
                break;
        }
    }

    public static void alterarRegistoVintage(Gestor gestor, String[] tokens, int flag, int logged){

        if (logged == -1){

            switch (flag){

                case Controlador.CHANGE_DATA:
                    gestor.updateData(LocalDate.parse(tokens[1]));
                    break;

                case Controlador.CHANGE_VINTAGE_COMISSAO:
                    Gestor.setComissao(Double.valueOf(tokens[1]));
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

        else Tratador.trataException(new Exception("Conta sem permissões"));
    }
}