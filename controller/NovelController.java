package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.Novel;

public class NovelController {

    @FXML
    private TextField tfJudul, tfPenulis;

    @FXML
    private DatePicker dpTanggalTerbit;

    @FXML
    private TableView<Novel> tableNovel;

    @FXML
    private TableColumn<Novel, String> colJudul, colPenulis, colTanggal;

    @FXML
    private Button btnTambah, btnEdit, btnHapus;

    private final ObservableList<Novel> daftarNovel = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colJudul.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getJudul()));
        colPenulis.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPenulis()));
        colTanggal.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTanggalTerbit()));

        tableNovel.setItems(daftarNovel);

        tableNovel.setOnMouseClicked((MouseEvent e) -> {
            Novel novel = tableNovel.getSelectionModel().getSelectedItem();
            if (novel != null) {
                tfJudul.setText(novel.getJudul());
                tfPenulis.setText(novel.getPenulis());
                dpTanggalTerbit.setValue(java.time.LocalDate.parse(novel.getTanggalTerbit()));
            }
        });
    }

    @FXML
    private void tambahData() {
        String judul = tfJudul.getText();
        String penulis = tfPenulis.getText();
        String tanggal = (dpTanggalTerbit.getValue() != null) ? dpTanggalTerbit.getValue().toString() : "";

        if (!judul.isEmpty() && !penulis.isEmpty() && !tanggal.isEmpty()) {
            daftarNovel.add(new Novel(judul, penulis, tanggal));
            clearForm();
        } else {
            showAlert("Harap isi semua kolom!");
        }
    }

    @FXML
    private void editData() {
        Novel selected = tableNovel.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setJudul(tfJudul.getText());
            selected.setPenulis(tfPenulis.getText());
            if (dpTanggalTerbit.getValue() != null) {
                selected.setTanggalTerbit(dpTanggalTerbit.getValue().toString());
            }
            tableNovel.refresh();
            clearForm();
        } else {
            showAlert("Pilih data yang ingin diedit!");
        }
    }

    @FXML
    private void hapusData() {
        Novel selected = tableNovel.getSelectionModel().getSelectedItem();
        if (selected != null) {
            daftarNovel.remove(selected);
            clearForm();
        } else {
            showAlert("Pilih data yang ingin dihapus!");
        }
    }

    private void clearForm() {
        tfJudul.clear();
        tfPenulis.clear();
        dpTanggalTerbit.setValue(null);
    }

    private void showAlert(String pesan) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }
}
