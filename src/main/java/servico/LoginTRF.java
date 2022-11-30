package servico;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;


public class LoginTRF {


    String urlpesquisa = null;
    String  urlLogin = null;

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static String TRF;

    public void setVariaveis(String trf) {

        TRF = trf;
        if(trf.equals("TRF1")){
            urlpesquisa = "https://pje1g.trf1.jus.br/pje/Processo/ConsultaProcesso/listView.seam";
            urlLogin = "https://pje1g.trf1.jus.br/";
        }else{
            urlpesquisa = "https://pje1g.trf6.jus.br/pje/Processo/ConsultaProcesso/listView.seam";
            urlLogin = "https://pje1g.trf6.jus.br/";
        }
    }

    private long time = 15;




    public void login(String CPF, String senha) throws InterruptedException, AWTException {
        String url = urlLogin;
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


    }





}
