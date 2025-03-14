package com.example.exercicio01;

import com.example.exercicio01.dao.VinhosDAO;
import com.example.exercicio01.model.Vinho;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

public class HelloController {
    @FXML
    private TextField tf_nome;
    @FXML
    private TextField tf_safra;
    @FXML
    private TextField tf_preco;
    @FXML
    private ComboBox<Vinho.TipoVinho> combo_tipo;

    @FXML
    public void initialize() {
        combo_tipo.getItems().addAll(Vinho.TipoVinho.values());
    }

    @FXML
    void LimparBtn(ActionEvent event) {
        limparCampos();
    }

    void limparCampos(){
        tf_nome.setText("");
        tf_safra.setText("");
        tf_preco.setText("");
        combo_tipo.setValue(null);
    }

    @FXML
    void SalvarBtn(ActionEvent event){
        String nome = tf_nome.getText();
        String safraInput = tf_safra.getText();
        String precoInput = tf_preco.getText();
        Vinho.TipoVinho tipo = combo_tipo.getValue();

        VinhosDAO vinhoDAO = new VinhosDAO();
        Vinho vinho = new Vinho();

        if (nome.isEmpty() || safraInput.isEmpty() || precoInput.isEmpty() || tipo == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Campos vazios ou inválidos");
            alert.show();
            System.err.println("Os campos estão vazios ou inválidos!");
        } else {
            try {
                int safra = Integer.parseInt(safraInput);
                BigDecimal preco = new BigDecimal(precoInput);

                vinho.setNomeDoVinho(nome);
                vinho.setSafra(safra);
                vinho.setPreco(preco);
                vinho.setTipo(tipo);

                vinhoDAO.create(vinho);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText("Dados Cadastrados");
                alert.show();

                limparCampos();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Valores inválidos para Safra ou Preço");
                alert.show();
                System.err.println("Valores inválidos para Safra ou Preço: " + e.getMessage());
            }
        }
    }
    @FXML
    void SairBtn(ActionEvent event) {
        Platform.exit();
    }
}