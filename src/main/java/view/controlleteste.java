package view;


import controller.BancoController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import modelo.PesquisaSave;
import DAO.DAOEntidadesp;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class controlleteste implements Initializable {

        @FXML
        private Text error;
        @FXML
        private Button add;
        @FXML
        private Button começar;
        @FXML
        private Button cancelar;

        @FXML
        private ComboBox<String> cmboxze;


        private BancoController batman = new BancoController();

        @FXML
        private TextArea escolhas;
        List<String> list = new ArrayList<>();
        Vector<String> entidades= new Vector<>();

        private String vava = "";
        private String vavaAnterior;
        @FXML
        public void initialize(URL location, ResourceBundle resources) {


                Vector<Integer> idcargo = new Vector<>();
                try {
                        ResultSet rs = batman.listaentidade();

                        cmboxze.getItems().add("TODAS AS ENTIDADES");
                        while (rs.next()) {
                                idcargo.addElement(rs.getInt(1));
                                cmboxze.getItems().add(rs.getString(2));
                        }
                } catch (Exception e) {
                        System.out.println("odeio");
                }
                PesquisaSave save = batman.dadospesquisa();
                escolhas.setText(save.getEntidadesp());



        }
        public void add(javafx.event.ActionEvent actionEvent) {
                try {
                        error.setText("");
                        if (cmboxze.getSelectionModel().getSelectedItem() == null) {
                                error.setText("Escolha uma entidade a ser adicionada");
                                return;
                        }
                        if (cmboxze.getSelectionModel().getSelectedItem() == "TODAS AS ENTIDADES" && vava != "") {
                                        error.setText("Não é possivel escolher TODAS AS ENTIDADES já havendo escolhido uma entidade");
                                        return;
                        }
                        if (entidades.contains("TODAS AS ENTIDADES")) {
                                error.setText("Não é possível escolher uma entidade já havendo escolhido TODAS AS ENTIDADES");
                                return;
                        }

                        if (entidades.contains(cmboxze.getSelectionModel().getSelectedItem()))
                        {
                                error.setText("Não repita entidades!!");
                                return;
                        }

                        entidades.addElement(cmboxze.getSelectionModel().getSelectedItem());
                        vava = escolhas.getText();
                        vavaAnterior = escolhas.getText();
                        // List<String> VALORES = new ArrayList<>(Arrays.asList(vava.split(",")));

                        escolhas.setText( vava+"\n" + entidades.lastElement());
                        System.out.println(entidades);

                } catch(Exception e)
                {
                        System.out.println("vai te fude hein");
                        System.out.println(e);
                }
        }
        public void remove(javafx.event.ActionEvent actionEvent) {
                try {
                        error.setText("");
                        entidades.removeElementAt(entidades.size()-1);
                        Iterator<String> iterate = entidades.iterator();
                        vava = "";
                        while(iterate.hasNext()) {
                                vava = vava + "\n " + iterate.next();
                        }
                        if (entidades.isEmpty()) { vava = ""; }
                        escolhas.setText(vava);
                        System.out.println(entidades);
                }catch (Exception e){
                        System.out.println(e);
                }
        }
        public void clear(javafx.event.ActionEvent actionEvent) {
                try {
                        error.setText("");
                        escolhas.clear();
                        entidades.clear();
                        vava = "";
                        System.out.println(entidades);
                }catch (Exception e){
                        System.out.println(e);
                }
        }
        public void figaro(MouseEvent mouseEvent) throws SQLException {
                try {
                        vava = escolhas.getText();
                        if (vava == ""){
                                error.setText("Adicione uma entidade a ser verificada antes de gravar");
                                return;
                        }
                        String tutu =escolhas.getText();
                        PesquisaSave save = new PesquisaSave();
                        DAOEntidadesp user = new DAOEntidadesp();
                        save.setEntidadesp(tutu);
                        user.salvarentisp(save);
                        Vector idcargo = new Vector(new Vector<>(Arrays.asList(tutu.split("\n"))));
                        idcargo.removeAll(Arrays.asList(""));
                        System.out.println(idcargo.get(0));
                        main.teste12(idcargo);

                        main.changeScreen("main");

                }catch (Exception e) {System.out.println(e);}

        }





}


