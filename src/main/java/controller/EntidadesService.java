package controller;
import modelo.*;
import repository.SeleniumService;
import DAO.DAOprocessos;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EntidadesService {
    private SeleniumService alerquina = new SeleniumService();
    private BancoController coringa = new BancoController();
    private DAOprocessos proc = new DAOprocessos();

    public String data() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime hoje = LocalDateTime.now();
        String dataAtual = hoje.format(formatter);
        return dataAtual;
    }



    public void seletorDeGrupos(String CPF, String senha) throws InterruptedException, AWTException {
        alerquina.login(CPF, senha);
            Vector processo;
            String janelapadrao = alerquina.automatização();
            Set<EntidadeMapeada> listaDeBusca = coringa.EntidadesMapeadas();
                           listaDeBusca.stream().forEach(EntidadeMapeada->alerquina.irnserirParmetros(
                           EntidadeMapeada.getNome(),
                           EntidadeMapeada.getAssunto(),
                           EntidadeMapeada.getClasseJudicial(),
                           EntidadeMapeada.getGrupo(),
                           janelapadrao));
                  alerquina.driver.close();
    }


}
