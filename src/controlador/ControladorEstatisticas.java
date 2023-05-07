package controlador;

import modulos.gestores.Gestor;
import modulos.Fatura;
import modulos.Fatura.TIPO;
import java.util.function.Predicate;
import java.time.LocalDate;


public class ControladorEstatisticas{

    public static void getEstatistica(Gestor gestor, String[] tokens, int flag){

        switch (flag){

            case Controlador.VINTAGE_LUCRO:
                gestor.getLucroVintage();
                break;

            case Controlador.ENCOMENDAS_EMITIDAS_VENDEDOR:
                gestor.getEncomendasEmitidasVendedor(Integer.valueOf(tokens[1]));
                break;

            case Controlador.MELHORES_TRANSPORTADORAS:
                gestor.getMelhoresTransportadoras();
                break;

            case Controlador.MELHORES_VENDEDORES:
            case Controlador.MELHORES_COMPRADORES:

                Predicate<Fatura> filtro = x -> true;

                if (tokens.length == 3){
                    filtro.and(x -> x.getData().isAfter(LocalDate.parse(tokens[1]))
                                && x.getData().isBefore(LocalDate.parse(tokens[2])));
                }

                if (flag == Controlador.MELHORES_VENDEDORES) filtro = filtro.and(x -> x.getTipo() == TIPO.VENDA);
                else filtro = filtro.and(x -> x.getTipo() == TIPO.COMPRA);

                gestor.getMelhoresUtilizadores(filtro);

                break;
        }
    }
}