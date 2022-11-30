package repositorio;

import DAO.DAOEntidade;
import modelo.Entidade;

public class EntidadeRepositorioFrame {
    DAOEntidade daoEntidade = new DAOEntidade();

    public boolean salvarEntidadeController(Entidade entidade){

        return this.daoEntidade.salvarEntidadeDAO(entidade);

    }

    public boolean excluirEntidadeController() {
        return this.daoEntidade.excluirEntidade();
    }
}
