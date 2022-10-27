package repository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;

import java.awt.event.KeyEvent;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



import java.time.Duration;

import java.util.*;
import java.util.List;


public class SeleniumTRF1<usuario> {
    String urlpesquisa = "https://pje1g.trf1.jus.br/pje/Processo/ConsultaProcesso/listView.seam";
    public WebDriver driver;
    public int z = 1;

    private WebDriverWait wait;

    private long time = 15;

    SeleniumTRF6 alerquina = new SeleniumTRF6();

    public int login(String CPF, String senha) throws InterruptedException, AWTException {
        if (trf6 == "trf6") {
            alerquina.login(CPF, senha);
        } else {
            String url = "https://pje1g.trf1.jus.br/";
            System.setProperty("webdriver.gecko.driver", "GeckoDriver.exe");
            ProfilesIni profile = new ProfilesIni();
            FirefoxProfile fxProfile = profile.getProfile("default");
            fxProfile.setPreference("browser.download.dir", "C:\\Figaro\\Downloads");
            fxProfile.setPreference("browser.download.folderList", 2);
            fxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            fxProfile.setPreference("browser.download.manager.showWhenStarting", false);
            fxProfile.setPreference("browser.helperApps.neverAsk.openFile", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            fxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
            fxProfile.setPreference("browser.download.manager.useWindow", false);
            fxProfile.setPreference("browser.download.manager.focusWhenStarting", false);
            fxProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
            fxProfile.setPreference("browser.download.manager.closeWhenDone", true);
            FirefoxOptions opt = new FirefoxOptions();
            opt.setProfile(fxProfile);
            driver = new FirefoxDriver(opt);

            driver.get(url);
            this.time = 15000;
            wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            String a = "/html/body/div[4]/div[2]/div[2]/div/div/div[2]/form/div[1]/input";
            String b = CPF;
            driver.manage().window().maximize();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(a)));
            driver.findElement(By.xpath(a)).sendKeys(b);
            String c = "/html/body/div[4]/div[2]/div[2]/div/div/div[2]/form/div[2]/input";
            String d = senha;
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(a)));
            driver.findElement(By.xpath(c)).sendKeys(d);
            Thread.sleep(2000);
            String pesquisar = "btnEntrar";
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(pesquisar)));
            driver.findElement(By.id(pesquisar)).click();


            Thread.sleep(2000);


            automatização();
            return 1;
        }
        return 0;
    }

    public String automatização() throws InterruptedException, AWTException {
        if (trf6 == "trf6") {
            String janelapadrao = alerquina.automatização();
            return janelapadrao;
        } else {
            String urlpesquisa = "https://pje1g.trf1.jus.br/pje/Processo/ConsultaProcesso/listView.seam";
            driver.navigate().to(urlpesquisa);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            String janelapadrao = driver.getWindowHandle();

            return janelapadrao;
        }
    }
    public void entradadados(String filtropesquisa, String nome, int test, String data_fin, String dataInicio) {
        if (trf6 == "trf6") {
            alerquina.entradadados(filtropesquisa, nome, test, data_fin, dataInicio);
        } else {
            LocalDateTime hoje = LocalDateTime.now();
            driver.get(urlpesquisa);
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
            String idnomeparte = "fPP:j_id151:nomeParte";
            String nomeparte = nome;
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(idnomeparte)));
            driver.findElement(By.id(idnomeparte)).sendKeys(nomeparte);
            String polopassivo = "/html/body/div[6]/div/div/div/div[2]/form/div[1]/div/div/div[5]/div[2]/table/tbody/tr/td[2]/label";
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(polopassivo)));
            driver.findElement(By.xpath(polopassivo)).click();
            if (test == 0) {
                String idclasse_judicial = "fPP:j_id258:classeJudicial";
                String classe_judicial = filtropesquisa;
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id(idclasse_judicial)));
                driver.findElement(By.id(idclasse_judicial)).sendKeys(classe_judicial);
            } else {
                String assuntoid = "fPP:j_id249:assunto";
                String assunto = filtropesquisa;
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id(assuntoid)));
                driver.findElement(By.id(assuntoid)).sendKeys(assunto);
            }
            String pesquisar = "fPP:searchProcessos";
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(pesquisar)));
            driver.findElement(By.id(pesquisar)).click();

        }
    }

    public Vector<String> pesquisa(String janelapadrao, String nome) throws InterruptedException, AWTException {
        if(trf6=="trf6")
        {
            Vector<String> processos = alerquina.pesquisa(janelapadrao, nome);
            return processos;
        }else {
            String displayNone = "";
            System.out.println("Display none? " + displayNone);
            while (!displayNone.equals("display: none;")) {
                displayNone = driver.findElement(By.id("_viewRoot:status.start")).getAttribute("style");
            }
            WebElement TabelaTref = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody"));
            List<WebElement> listaMovimentacao = new ArrayList<>(TabelaTref.findElements(By.cssSelector("tr")));
            Vector<String> processos = new Vector<String>();
            for (int j = listaMovimentacao.size(); j > 0; j--) {
                Boolean isPresent = driver.findElements(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]")).size() > 0;
                System.out.println(isPresent);

                if (isPresent) {

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]/a")));
                    processos.add(driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]/a")).getText());
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]")));
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]")));
                    driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]")).click();
                    Thread.sleep(1500);
                    System.setProperty("java.awt.headless", "false");
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyPress(KeyEvent.VK_ENTER);
                    Thread.sleep(2000);
                    janeladownload(janelapadrao);
                    salvararquivo(processos, nome);

                    driver.switchTo().window(janelapadrao);

                } else {
                    driver.get(urlpesquisa);

                }

            }
            return processos;
        }

    }

    public Vector pesquisaExceçoes(String janelapadrao, String nome) throws InterruptedException, AWTException {
        if (trf6 == "trf6") {
            Vector<String> processos = alerquina.pesquisaExceçoes(janelapadrao, nome);
            return processos;
        } else {
            String displayNone = "";
            System.out.println("Display none? " + displayNone);
            while (!displayNone.equals("display: none;")) {
                displayNone = driver.findElement(By.id("_viewRoot:status.start")).getAttribute("style");
            }
            WebElement TabelaTref = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody"));
            List<WebElement> listaMovimentacao = new ArrayList<>(TabelaTref.findElements(By.cssSelector("tr")));
            Vector<String> processos = new Vector<String>();
            for (int j = listaMovimentacao.size(); j > 0; j--) {
                Boolean isPresent = driver.findElements(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]")).size() > 0;


                if (isPresent) {
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[5]")));
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[5]")));
                    String verifica = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[5]")).getText().toUpperCase();
                    Set<String> exceões = new HashSet<String>(Arrays.asList(
                            "EXECUÇÃO FISCAL", "EMBARGOS À EXECUÇÃO FISCAL", "CARTA PRECATÓRIA", "CUMPRIMENTO DE SENTENÇA", "PROCEDIMENTO DE JUIZADO ESPECIAL", "PROCEDIMENTO DO JUIZADO ESPECIAL CÍVEL"
                    ));
                    if (exceões.contains(verifica)) {
                        System.out.println("triste");
                    } else {
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]/a")));
                        processos.add(driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]/a")).getText());
                        driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]")).click();
                        Thread.sleep(1500);
                        System.setProperty("java.awt.headless", "false");
                        Robot robot = new Robot();
                        robot.keyPress(KeyEvent.VK_ENTER);
                        robot.keyPress(KeyEvent.VK_ENTER);
                        robot.keyPress(KeyEvent.VK_ENTER);
                        robot.keyPress(KeyEvent.VK_ENTER);
                        Thread.sleep(2000);
                        janeladownload(janelapadrao);
                        salvararquivo(processos, nome);
                        driver.switchTo().window(janelapadrao);

                    }
                } else {
                    driver.get(urlpesquisa);
                }
            }
            return processos;
        }
    }
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

    public void janeladownload(String janelapadrao) throws InterruptedException, AWTException {
        Thread.sleep(1000);
        Set<String> janela = driver.getWindowHandles();
        for (String handle : janela) {
            try {
                if (!handle.equals(janelapadrao)) {
                    driver.switchTo().window(handle);

                    String download12 = "/html/body/div[2]/div[1]/div/form[1]/span/ul[2]/li[6]/a";
                    boolean certeza = driver.findElements(By.xpath(download12)).size() > 0;
                    if (certeza) {
                        driver.findElement(By.xpath(download12)).click();
                    } else {
                        download12 = "/html/body/div[2]/div[1]/div/form[1]/span/ul[2]/li[7]/a";
                        driver.findElement(By.xpath(download12)).click();
                    }
                    Thread.sleep(500);
                    String download = "navbar:cbTipoDocumento";
                    wait.until(ExpectedConditions.elementToBeClickable(By.id(download)));
                    Thread.sleep(1000);
                    WebElement teste = driver.findElement(By.id(download));
                    driver.findElement(By.id(download)).click();
                    Select sel = new Select(teste);
                    try {
                        try {
                            sel.selectByVisibleText("Inicial");
                            String button = "navbar:downloadProcesso";
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(button)));
                            wait.until(ExpectedConditions.elementToBeClickable(By.id(button)));
                            Thread.sleep(200);
                            driver.findElement(By.id(button)).click();
                            System.setProperty("java.awt.headless", "false");
                            Robot robot = new Robot();
                            robot.keyPress(KeyEvent.VK_ENTER);
                            Thread.sleep(5000);

                            driver.close();
                        } catch (Exception e) {
                            sel.selectByVisibleText("Petição inicial");
                            String button = "navbar:downloadProcesso";
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(button)));
                            wait.until(ExpectedConditions.elementToBeClickable(By.id(button)));
                            Thread.sleep(200);
                            driver.findElement(By.id(button)).click();
                            System.setProperty("java.awt.headless", "false");
                            Robot robot = new Robot();
                            robot.keyPress(KeyEvent.VK_ENTER);
                            Thread.sleep(5000);
                        }
                    } catch (Exception ea) {

                        //versao antiga de donwload{
                        String spam = "detalheDocumento:primeiroDocumento";
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(spam)));
                        driver.findElement(By.id(spam)).click();

                        Thread.sleep(500);
                        String downloadantig = "detalheDocumento:download";
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(downloadantig)));
                        wait.until(ExpectedConditions.elementToBeClickable(By.id(downloadantig)));
                        Thread.sleep(1000);
                        driver.findElement(By.id(download)).click();
                        Thread.sleep(3000);
                        System.setProperty("java.awt.headless", "false");
                        Robot robot = new Robot();
                        robot.keyPress(KeyEvent.VK_A + z);
                        robot.keyPress(KeyEvent.VK_A + z);
                        z = z + 1;
                        robot.keyPress(KeyEvent.VK_ENTER);
                        robot.keyPress(KeyEvent.VK_ENTER);

                        Thread.sleep(2000);

                        driver.close();
                    }

                }
            }catch (Exception e){
                System.out.println("n deu");
                driver.close();
            }
        }


            Set<String> janela1 = driver.getWindowHandles();
            for (String handle1 : janela1) {
                if (!handle1.equals(janelapadrao)) {
                    try {
                        driver.switchTo().window(handle1);
                    /*String download1 = "download";
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id(download1)));
                    wait.until(ExpectedConditions.elementToBeClickable(By.id(download1)));
                    Thread.sleep(3000);
                    driver.findElement(By.id(download1)).click();

                    System.setProperty("java.awt.headless", "false");
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyPress(KeyEvent.VK_ENTER);

                    Thread.sleep(2000);
                    */
                        Thread.sleep(1000);
                        driver.close();

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }

        }


    public Vector exceções2(String janelapadrao, String nome) throws InterruptedException, AWTException {
        if (trf6 == "trf6") {
            Vector<String> processos = alerquina.exceções2(janelapadrao, nome);
            return processos;
        } else {
            String displayNone = "";
            System.out.println("Display none? " + displayNone);
            while (!displayNone.equals("display: none;")) {
                displayNone = driver.findElement(By.id("_viewRoot:status.start")).getAttribute("style");
            }
            WebElement TabelaTref = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody"));
            List<WebElement> listaMovimentacao = new ArrayList<>(TabelaTref.findElements(By.cssSelector("tr")));
            Vector<String> processos = new Vector<String>();
            for (int j = listaMovimentacao.size(); j > 0; j--) {
                Boolean isPresent = driver.findElements(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]")).size() > 0;
                System.out.println(isPresent);

                if (isPresent) {

                    String[] JURIDICAS = new String[4];
                    JURIDICAS[0] = "LTDA";
                    JURIDICAS[1] = "EIRELI";
                    JURIDICAS[2] = "S A";
                    JURIDICAS[3] = "S/A";

                    String verifica = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[6]")).getText().toUpperCase();
                    for (int xablau = 0; xablau < 4; xablau++) {
                        if (verifica.contains(JURIDICAS[xablau])) {
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]/a")));
                            processos.add(driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]/a")).getText());
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]")));
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]")));
                            driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]")).click();
                            Thread.sleep(1500);
                            System.setProperty("java.awt.headless", "false");
                            Robot robot = new Robot();
                            robot.keyPress(KeyEvent.VK_ENTER);
                            robot.keyPress(KeyEvent.VK_ENTER);
                            Thread.sleep(2000);
                            janeladownload(janelapadrao);
                            salvararquivo(processos, nome);
                            driver.switchTo().window(janelapadrao);
                        }
                    }
                }
            }
            return processos;
        }
    }
    public Vector<String> pesquisa2(String janelapadrao, String nome) throws InterruptedException, AWTException {
        if (trf6 == "trf6") {
            Vector<String> processos = alerquina.pesquisa2(janelapadrao, nome);
            return processos;
        } else {
            String displayNone = "";
            System.out.println("Display none? " + displayNone);
            while (!displayNone.equals("display: none;")) {
                displayNone = driver.findElement(By.id("_viewRoot:status.start")).getAttribute("style");
            }
            WebElement TabelaTref = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody"));
            List<WebElement> listaMovimentacao = new ArrayList<>(TabelaTref.findElements(By.cssSelector("tr")));
            Vector<String> processos = new Vector<String>();
            for (int j = listaMovimentacao.size(); j > 0; j--) {
                Boolean isPresent = driver.findElements(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]")).size() > 0;
                System.out.println(isPresent);

                if (isPresent) {
                    String[] JURIDICAS = new String[4];
                    JURIDICAS[0] = "LTDA";
                    JURIDICAS[1] = "EIRELI";
                    JURIDICAS[2] = "S A";
                    JURIDICAS[3] = "S/A";
                    String verifica = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[6]")).getText().toUpperCase();
                    for (int xablau = 0; xablau < 4; xablau++) {
                        if (verifica.contains(JURIDICAS[xablau])) {
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]/a")));
                            processos.add(driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]/a")).getText());
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]")));
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]")));
                            driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]")).click();
                            Thread.sleep(1500);
                            System.setProperty("java.awt.headless", "false");
                            Robot robot = new Robot();
                            robot.keyPress(KeyEvent.VK_ENTER);
                            robot.keyPress(KeyEvent.VK_ENTER);
                            Thread.sleep(2000);
                            janeladownload(janelapadrao);
                            salvararquivo(processos, nome);
                            driver.switchTo().window(janelapadrao);
                        }
                    }
                } else {
                    driver.get(urlpesquisa);

                }

            }
            return processos;
        }
    }
    public Vector<String> pesquisaMunicipio(String janelapadrao, String nome) throws InterruptedException, AWTException {
        if (trf6 == "trf6") {
            Vector<String> processos = alerquina.pesquisaMunicipio(janelapadrao, nome);
            return processos;
        } else {
            String displayNone = "";
            System.out.println("Display none? " + displayNone);
            while (!displayNone.equals("display: none;")) {
                displayNone = driver.findElement(By.id("_viewRoot:status.start")).getAttribute("style");
            }
            WebElement TabelaTref = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody"));
            List<WebElement> listaMovimentacao = new ArrayList<>(TabelaTref.findElements(By.cssSelector("tr")));
            Vector<String> processos = new Vector<String>();
            for (int j = listaMovimentacao.size(); j > 0; j--) {
                Boolean isPresent = driver.findElements(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]")).size() > 0;
                System.out.println(isPresent);

                if (isPresent) {
                    String[] JURIDICAS = new String[5];
                    JURIDICAS[0] = "MUNICIPIO";
                    JURIDICAS[1] = "ESTADO";
                    JURIDICAS[2] = "SINDICATO";
                    JURIDICAS[3] = "ASSOCIAÇÃO";
                    JURIDICAS[4] = "FEDERAÇÃO";
                    String verifica = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[6]")).getText().toUpperCase();
                    for (int xablau = 0; xablau < 4; xablau++) {
                        if (verifica.contains(JURIDICAS[xablau])) {
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]/a")));
                            processos.add(driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]/a")).getText());
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]")));
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]")));
                            driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]")).click();
                            Thread.sleep(1500);
                            System.setProperty("java.awt.headless", "false");
                            Robot robot = new Robot();
                            robot.keyPress(KeyEvent.VK_ENTER);
                            robot.keyPress(KeyEvent.VK_ENTER);
                            Thread.sleep(2000);
                            janeladownload(janelapadrao);
                            salvararquivo(processos, nome);
                            driver.switchTo().window(janelapadrao);
                        }
                    }
                } else {
                    driver.get(urlpesquisa);

                }

            }
            return processos;
        }
    }
    public void salvararquivo(Vector<String> processos, String nome) {
        File diretorio = new File("C:\\Figaro\\Downloads");
        File[] arquivos = diretorio.listFiles();
        for (File arquivo : arquivos) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM");
            LocalDateTime hoje = LocalDateTime.now();
            String hojeformatado = hoje.format(formatter);
            File novoDiretorio = new File("C:\\Figaro\\Processos\\" + hojeformatado + "\\" + nome);
            novoDiretorio.mkdirs();
            File arquivoNovo = new File(novoDiretorio + "\\" + processos.lastElement() + ".pdf");
            if (arquivoNovo.exists()) {
                arquivoNovo.delete();

                arquivo.renameTo(arquivoNovo);
            } else {
                arquivo.renameTo(arquivoNovo);
            }
        }
    }
}
