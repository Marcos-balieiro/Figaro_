package servico;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

@Component
public class ColetorDeProcessos extends LoginTRF {

    public Download download = new Download();

    public Vector<String> selecionaProcessos  (String janelaPadrao, int j, String nome)  {



        Vector<String> processos = new Vector<String>();
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]/a")));
            processos.add(driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]/a")).getText());
           // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]")));
           // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]")));
            driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/div/table/tbody/tr[" + j + "]/td[1]")).click();
            Thread.sleep(1500);
            System.setProperty("java.awt.headless", "false");
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
            download.janeladownload(janelaPadrao);
            salvararquivo(processos, nome);
            driver.switchTo().window(janelaPadrao);
        }catch (Exception e){
            System.out.println(e);
        }
        return processos;
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
