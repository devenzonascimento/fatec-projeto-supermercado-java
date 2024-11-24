package Interface;

import Entidade.HistoricoDeCompras;

import java.util.ArrayList;

public interface IHistoricoDeCompras {

    ArrayList<HistoricoDeCompras> gerar();

    ArrayList<HistoricoDeCompras> pesquisarPorNome(String nome);
}
