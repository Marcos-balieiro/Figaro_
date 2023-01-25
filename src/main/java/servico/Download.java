package servico;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Set;

@Component
public class Download extends LoginTRF {

       public int z = 1;



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

                    Thread.sleep(1000);
                    driver.close();

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

    }

}
