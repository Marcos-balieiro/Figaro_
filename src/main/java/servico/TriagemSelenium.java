package servico;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.awt.*;
import java.util.List;
import java.util.*;


public class TriagemSelenium extends LoginTRF {


    private String urlpesquisaCorresp = null;

    public void setEndereco() {

        if(TRF.equals("TRF1")){
            urlpesquisaCorresp = "https://pje1g.trf1.jus.br/pje/Processo/ConsultaProcesso/listView.seam";
        }else{
            urlpesquisaCorresp = "https://pje1g.trf6.jus.br/pje/Processo/ConsultaProcesso/listView.seam";
        }
    }


    public ColetorDeProcessos coletor = new ColetorDeProcessos();



    public void pesquisa(String janelaPadrao, String nome, String grupo, String assunto, String classe) throws InterruptedException, AWTException {

        int paginação = 5;

        boolean testeDePagicacao = true;

        while(testeDePagicacao) {
            String displayNone = "";
            while (!displayNone.equals("display: none;")) {
                displayNone = driver.findElement(By.id("_viewRoot:status.start")).getAttribute("style");
            }


            WebElement TabelaTref = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody"));
            List<WebElement> listaMovimentacao = new ArrayList<>(TabelaTref.findElements(By.cssSelector("tr")));

            WebElement TabelaPag = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tfoot/tr/td/div/div[1]/div/table/tbody/tr/td["+paginação+"]"));;

            for (int j = listaMovimentacao.size(); j > 0; j--) {

                Boolean constaProcesso = driver.findElements(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]")).size() > 0;

                if (constaProcesso) {
                    switch (grupo) {
                        case ("1A"):
                        case ("1D"):
                        case ("2D"):
                            coletor.selecionaProcessos(janelaPadrao, j, nome);
                            break;
                        case ("1B"):
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[5]")));
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[5]")));
                            String verifica = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[5]")).getText().toUpperCase();
                            Set<String> excecoes = new HashSet<String>(Arrays.asList(
                                    "EXECUÇÃO FISCAL", "EMBARGOS À EXECUÇÃO FISCAL", "CARTA PRECATÓRIA", "CUMPRIMENTO DE SENTENÇA", "PROCEDIMENTO DE JUIZADO ESPECIAL"
                            ));
                            if (!excecoes.contains(verifica)) {
                                coletor.selecionaProcessos(janelaPadrao, j, nome);
                            }
                            break;

                        case ("1C"):
                            if (classe.equals("") && assunto.equals("")) {
                                String[] JURIDICAS1 = new String[5];
                                JURIDICAS1[0] = "MUNICIPIO";
                                JURIDICAS1[1] = "ESTADO";
                                JURIDICAS1[2] = "SINDICATO";
                                JURIDICAS1[3] = "ASSOCIAÇÃO";
                                JURIDICAS1[4] = "FEDERAÇÃO";
                                String verifica2 = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[6]")).getText().toUpperCase();
                                for (int caso = 0; caso < 4; caso++) {
                                    if (verifica2.contains(JURIDICAS1[caso])) {
                                        coletor.selecionaProcessos(janelaPadrao, j, nome);
                                    }
                                }
                            } else {
                                coletor.selecionaProcessos(janelaPadrao, j, nome);
                            }
                            break;


                        case ("2B"):

                            if (classe.equals("")) {
                                String[] JURIDICAS1 = new String[5];
                                JURIDICAS1[0] = "MUNICIPIO";
                                JURIDICAS1[1] = "ESTADO";
                                JURIDICAS1[2] = "SINDICATO";
                                JURIDICAS1[3] = "ASSOCIAÇÃO";
                                JURIDICAS1[4] = "FEDERAÇÃO";
                                String verifica2 = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[6]")).getText().toUpperCase();
                                for (int caso = 0; caso < 4; caso++) {
                                    if (verifica2.contains(JURIDICAS1[caso])) {
                                        coletor.selecionaProcessos(janelaPadrao, j, nome);
                                    }
                                }
                            } else if (nome.equals("FUNDO NACIONAL DESENVOLVIMENTO DA EDUCACAO")) {
                                String[] JURIDICAS1 = new String[5];
                                JURIDICAS1[0] = "MUNICIPIO";
                                JURIDICAS1[1] = "ESTADO";
                                JURIDICAS1[2] = "SINDICATO";
                                JURIDICAS1[3] = "ASSOCIAÇÃO";
                                JURIDICAS1[4] = "FEDERAÇÃO";
                                String verifica2 = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[6]")).getText().toUpperCase();
                                for (int caso = 0; caso < 4; caso++) {
                                    if (verifica2.contains(JURIDICAS1[caso])) {
                                        coletor.selecionaProcessos(janelaPadrao, j, nome);
                                    }
                                }
                            } else {
                                coletor.selecionaProcessos(janelaPadrao, j, nome);
                            }
                            break;

                        case ("2A"):

                            if (!classe.equals("")) {
                                String[] JURIDICAS = new String[4];
                                JURIDICAS[0] = "LTDA";
                                JURIDICAS[1] = "EIRELI";
                                JURIDICAS[2] = "S A";
                                JURIDICAS[3] = "S/A";
                                String verifica1 = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[6]")).getText().toUpperCase();
                                for (int caso = 0; caso < 4; caso++) {
                                    if (verifica1.contains(JURIDICAS[caso])) {
                                        coletor.selecionaProcessos(janelaPadrao, j, nome);
                                    }
                                }
                            } else {
                                coletor.selecionaProcessos(janelaPadrao, j, nome);
                            }
                            break;

                        case ("2C"):

                            if (nome.equals("DEPARTAMENTO NACIONAL DE INFRAESTRUTURA DE TRANSPORTE")) {
                                String[] JURIDICAS = new String[4];
                                JURIDICAS[0] = "LTDA";
                                JURIDICAS[1] = "EIRELI";
                                JURIDICAS[2] = "S A";
                                JURIDICAS[3] = "S/A";
                                String verifica1 = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[6]")).getText().toUpperCase();
                                for (int caso = 0; caso < 4; caso++) {
                                    if (verifica1.contains(JURIDICAS[caso])) {
                                        coletor.selecionaProcessos(janelaPadrao, j, nome);
                                    }
                                }
                            } else {
                                coletor.selecionaProcessos(janelaPadrao, j, nome);
                            }

                            break;
                    }
                } else {
                    driver.get(urlpesquisaCorresp);
                }
            }

          String paginacaoPath = TabelaPag.getText();

          if(paginacaoPath.matches("^\\d+$")){
                 TabelaPag.click();
                 paginação ++;
           }else{
           testeDePagicacao = false;
           }

        }



        }

}
