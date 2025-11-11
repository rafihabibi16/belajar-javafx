package model;

public class Novel {
    private String judul;
    private String penulis;
    private String tanggalTerbit;

    public Novel(String judul, String penulis, String tanggalTerbit) {
        this.judul = judul;
        this.penulis = penulis;
        this.tanggalTerbit = tanggalTerbit;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getTanggalTerbit() {
        return tanggalTerbit;
    }

    public void setTanggalTerbit(String tanggalTerbit) {
        this.tanggalTerbit = tanggalTerbit;
    }
}
