package servico;

import DAO.DAOprocessos;
import modelo.EntidadeMapeada;
import repositorio.EntidadesRepositorio;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;


public class EntidadesService extends LoginTRF {


    ConsultaSelenium alerquina = new ConsultaSelenium();
    EntidadesRepositorio coringa = new EntidadesRepositorio();

    DAOprocessos proc = new DAOprocessos();



    public String data() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime hoje = LocalDateTime.now();
        String dataAtual = hoje.format(formatter);
        return dataAtual;
    }



    public void seletorDeGrupos(String data_fin, String dataInicio) throws InterruptedException, AWTException {

        String janelapadrao = alerquina.automatização();


            Set<EntidadeMapeada> listaDeBusca = coringa.EntidadesMapeadas();


                           listaDeBusca.stream().forEach(e->alerquina.irnserirParmetros(
                           e.getNome(),
                           e.getAssunto(),
                           e.getClasseJudicial(),
                           e.getGrupo(),
                           janelapadrao,
                           data_fin,
                           dataInicio));

        driver.close();


    }

    public void seletorDeUnidade(String entity, String data_fin, String dataInicio) throws InterruptedException, AWTException {


        String janelapadrao = alerquina.automatização();

        Set<EntidadeMapeada> listaDeBusca = coringa.EntidadeMapeada(entity);
        try {
            listaDeBusca.stream().forEach(e -> alerquina.irnserirParmetros(
                    e.getNome(),
                    e.getAssunto(),
                    e.getClasseJudicial(),
                    e.getGrupo(),
                    janelapadrao,
                    data_fin,
                    dataInicio));
           driver.close();
        }catch (Exception ignored){

        }
    }

    public void SeletorDeUnidades(List<String> entidades, String data_fin, String dataInicio) throws InterruptedException, AWTException{

        String janelapadrao = alerquina.automatização();

        for (int i=0;i<entidades.size();i++) {
            Set<EntidadeMapeada> listaDeBusca = coringa.EntidadeMapeada(entidades.get(i));
            try {
                listaDeBusca.stream().forEach(e -> alerquina.irnserirParmetros(
                        e.getNome(),
                        e.getAssunto(),
                        e.getClasseJudicial(),
                        e.getGrupo(),
                        janelapadrao,
                        data_fin,
                        dataInicio));

            } catch (Exception ignored) {

            }
        }
           driver.close();
    }


}
