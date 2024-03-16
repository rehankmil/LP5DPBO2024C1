public class Mahasiswa
{
    private String nim;
    private String nama;
    private String jenisKelamin;

    private String jurusan;

    private String kelas;

    public Mahasiswa(String nim, String nama, String jenisKelamin, String jurusan, String kelas)
    {
        this.nim = nim;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.jurusan = jurusan;
        this.kelas = kelas;
    }

    public void setNim(String nim)
    {
        this.nim = nim;
    }

    public void setNama(String nama)
    {
        this.nama = nama;
    }

    public void setJenisKelamin(String jenisKelamin)
    {
        this.jenisKelamin = jenisKelamin;
    }

    public void setJurusan(String jurusan)
    {
        this.jurusan = jurusan;
    }

    public void setKelas(String kelas)
    {
        this.kelas = kelas;
    }

    public String getNim()
    {
        return this.nim;
    }

    public String getNama()
    {
        return this.nama;
    }

    public String getJenisKelamin()
    {
        return this.jenisKelamin;
    }

    public String getJurusan()
    {
        return this.jurusan;
    }

    public String getKelas()
    {
        return this.kelas;
    }
}
