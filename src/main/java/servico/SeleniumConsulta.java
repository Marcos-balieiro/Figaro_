package servico;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class SeleniumConsulta extends LoginTRF {

    private String urlpesquisaCorresp = null;
    private String EnderocoClasse = null;
    private String EnderecoAssunto = null;
    private String EnderecoPesquisa  = null;


    public void setEnderecos() {

         if(TRF.equals("TRF1")){
            urlpesquisaCorresp = "https://pje1g.trf1.jus.br/pje/Processo/ConsultaProcesso/listView.seam";
            EnderocoClasse = "fPP:j_id257:classeJudicial";
            EnderecoAssunto = "fPP:j_id248:assunto";
            EnderecoPesquisa  = "fPP:searchProcessos";

        }else{
            urlpesquisaCorresp = "https://pje1g.trf6.jus.br/pje/Processo/ConsultaProcesso/listView.seam";
            EnderocoClasse = "fPP:j_id258:classeJudicial";
            EnderecoAssunto = "fPP:j_id249:assunto";
            EnderecoPesquisa  = "fPP:searchProcessos";

        }
    }



   TriagemSelenium filtro = new TriagemSelenium();

    public String data() {
        LocalDateTime hoje = LocalDateTime.now();
        String diadasemana = String.valueOf(hoje.getDayOfWeek());
        if (diadasemana.equals("MONDAY")) {
            LocalDateTime sexta = hoje.minusDays(3);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return sexta.format(formatter);
        } else {
            LocalDateTime ontem = hoje.minusDays(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return ontem.format(formatter);

        }

    }

    public String automatização() throws InterruptedException, AWTException {
        setEnderecos();
        String urlpesquisa = urlpesquisaCorresp;
        driver.navigate().to(urlpesquisa);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        String janelapadrao = driver.getWindowHandle();

        return janelapadrao;
    }


    public void irnserirParmetros(String nome, String assunto, String classe, String grupo, String janelaPadrao, String data_fin, String dataInicio) {

        LocalDateTime hoje = LocalDateTime.now();

        if ((dataInicio == null)) {


            String iddatahoje = "fPP:dataAutuacaoDecoration:dataAutuacaoFimInputDate";
            String iddatasexta = "fPP:dataAutuacaoDecoration:dataAutuacaoInicioInputDate";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String hojeformatado = hoje.format(formatter);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(iddatasexta)));
            driver.findElement(By.id(iddatasexta)).sendKeys(data());
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(iddatahoje)));
            driver.findElement(By.id(iddatahoje)).sendKeys(hojeformatado);
        } else {
            String hoje1 = (dataInicio);
            String ontem = (data_fin);
            String iddatahoje = "fPP:dataAutuacaoDecoration:dataAutuacaoFimInputDate";
            String iddatasexta = "fPP:dataAutuacaoDecoration:dataAutuacaoInicioInputDate";
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(iddatasexta)));
            driver.findElement(By.id(iddatasexta)).sendKeys(hoje1);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(iddatahoje)));
            driver.findElement(By.id(iddatahoje)).sendKeys(ontem);
        }

        System.out.println("1");
        String idnomeparte = "fPP:j_id150:nomeParte";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(idnomeparte)));
        driver.findElement(By.id(idnomeparte)).clear();
        driver.findElement(By.id(idnomeparte)).sendKeys(nome);


        String polopassivo = "/html/body/div[6]/div/div/div/div[2]/form/div[1]/div/div/div[5]/div[2]/table/tbody/tr/td[2]/label";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(polopassivo)));
        driver.findElement(By.xpath(polopassivo)).click();


        String idclasse_judicial = EnderocoClasse;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(idclasse_judicial)));
        driver.findElement(By.id(idclasse_judicial)).clear();
        driver.findElement(By.id(idclasse_judicial)).sendKeys(classe);

        String assuntoid = EnderecoAssunto;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(assuntoid)));
        driver.findElement(By.id(assuntoid)).clear();
        driver.findElement(By.id(assuntoid)).sendKeys(assunto);


        String pesquisar = EnderecoPesquisa;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(pesquisar)));
        driver.findElement(By.id(pesquisar)).click();


        try {

            filtro.pesquisa(janelaPadrao, nome, grupo, assunto, classe);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }


    }














}
