import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Menu extends JFrame
{
    public static void main(String[] args)
    {
        // buat object window
        Menu window = new Menu();

        // atur ukuran window
        window.setSize(480, 560);

        // letakkan window di tengah layar
        window.setLocationRelativeTo(null);

        // isi window
        window.setContentPane(window.mainPanel);

        // ubah warna background
        window.getContentPane().setBackground(Color.white);

        // tampilkan window
        window.setVisible(true);

        // agar program ikut berhenti saat window diclose
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua mahasiswa
    private ArrayList<Mahasiswa> listMahasiswa;

    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JComboBox jurusanComboBox;
    private JLabel Jurusan;
    private JComboBox kelasComboBox;
    private JLabel Kelas;

    // constructor
    public Menu() {
        // inisialisasi listMahasiswa
        listMahasiswa = new ArrayList<>();

        // isi listMahasiswa
        populateList();

        // isi tabel mahasiswa
        mahasiswaTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] jenisKelaminData = {"", "Laki-Laki", "Perempuan"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel(jenisKelaminData));
        String[] jurusanData = {"", "Ilmu Komputer", "Pendidikan Ilmu Komputer"};
        jurusanComboBox.setModel(new DefaultComboBoxModel(jurusanData));
        String[] kelasData = {"", "C1", "C2", "A", "B"};
        kelasComboBox.setModel(new DefaultComboBoxModel(kelasData));

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(selectedIndex == -1)
                {
                    insertData();
                }
                else
                {
                    updateData();
                }

            }
        });

        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(selectedIndex >= 0)
                {
                    deleteData();
                }
            }
        });
        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // saat tombol
                clearForm();
            }
        });

        // saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.getSelectedRow();

                // simpan value textfield dan combo box
                String selectedNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();
                String selectedNama = mahasiswaTable.getModel().getValueAt(selectedIndex, 2).toString();
                String selectedJenisKelamin = mahasiswaTable.getModel().getValueAt(selectedIndex, 3).toString();
                String selectedJurusan = mahasiswaTable.getModel().getValueAt(selectedIndex, 4).toString();
                String selectedKelas = mahasiswaTable.getModel().getValueAt(selectedIndex, 5).toString();

                // ubah isi textfield dan combo box
                nimField.setText(selectedNim);
                namaField.setText(selectedNama);
                jenisKelaminComboBox.setSelectedItem(selectedJenisKelamin);
                jurusanComboBox.setSelectedItem(selectedJurusan);
                kelasComboBox.setSelectedItem(selectedKelas);

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");

                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable()
    {
        // tentukan kolom tabel
        Object[] column = {"No", "NIM", "Nama", "Jenis Kelamin", "Jurusan", "Kelas"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel temp = new DefaultTableModel(null, column);

        // isi tabel dengan listMahasiswa
        for(int i = 0; i < listMahasiswa.size(); i++)
        {
            Object[] row = new Object[6];
            row[0] = i + 1;
            row[1] = listMahasiswa.get(i).getNim();
            row[2] = listMahasiswa.get(i).getNama();
            row[3] = listMahasiswa.get(i).getJenisKelamin();
            row[4] = listMahasiswa.get(i).getJurusan();
            row[5] = listMahasiswa.get(i).getKelas();

            temp.addRow(row);
        }

        return temp;
    }

    public void insertData()
    {
        // ambil value dari textfield dan combobox
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String jurusan = jurusanComboBox.getSelectedItem().toString();
        String kelas = kelasComboBox.getSelectedItem().toString();

        // tambahkan data ke dalam list
        listMahasiswa.add(new Mahasiswa(nim, nama, jenisKelamin, jurusan, kelas));

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Insert Berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
    }

    public void updateData()
    {
        // ambil data dari form
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String jurusan = jurusanComboBox.getSelectedItem().toString();
        String kelas = kelasComboBox.getSelectedItem().toString();

        // ubah data mahasiswa di list
        listMahasiswa.get(selectedIndex).setNim(nim);
        listMahasiswa.get(selectedIndex).setJenisKelamin(jenisKelamin);
        listMahasiswa.get(selectedIndex).setJurusan(jurusan);
        listMahasiswa.get(selectedIndex).setKelas(kelas);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Update Berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
    }

    public void deleteData()
    {
        // hapus data dari list
        listMahasiswa.remove(selectedIndex);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Delete Berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
    }

    public void clearForm()
    {
        // kosongkan semua texfield dan combo box
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedItem("");
        jurusanComboBox.setSelectedItem("");
        kelasComboBox.setSelectedItem("");

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }

    private void populateList()
    {
        listMahasiswa.add(new Mahasiswa("2203999", "Amelia Zalfa Julianti", "Perempuan", "Ilmu Komputer", "C1"));
        listMahasiswa.add(new Mahasiswa("2210239", "Muhammad Hanif Abdillah", "Laki-laki", "Ilmu Komputer", "C1"));
        listMahasiswa.add(new Mahasiswa("2202046", "Nurainun", "Perempuan", "Ilmu Komputer", "C1"));
        listMahasiswa.add(new Mahasiswa("2209489", "Rakha Dhifiargo Hariadi", "Laki-laki", "Ilmu Komputer", "C1"));
        listMahasiswa.add(new Mahasiswa("2204509", "Muhammad Fahreza Fauzan", "Laki-laki", "Ilmu Komputer", "C1"));
        listMahasiswa.add(new Mahasiswa("2206600", "Albiana", "Laki-laki", "Pendidikan Ilmu Komputer", "A"));
        listMahasiswa.add(new Mahasiswa("2204756", "Arianti Apriani Sagita", "Perempuan", "Pendidikan Ilmu Komputer", "A"));
        listMahasiswa.add(new Mahasiswa("2204744", "Aurell Nur Jasmine Indrayani", "Perempuan", "Pendidikan Ilmu Komputer", "A"));
        listMahasiswa.add(new Mahasiswa("2205573", "Bintang Wibawa Inha putra", "Laki-laki", "Pendidikan Ilmu Komputer", "A"));
        listMahasiswa.add(new Mahasiswa("2205521", "Citra Ayu Rahmawati", "Perempuan", "Pendidikan Ilmu Komputer", "A"));
        listMahasiswa.add(new Mahasiswa("2202729", "Abdullah Hafidz Furqon", "Laki-laki", "Ilmu Komputer", "C2"));
        listMahasiswa.add(new Mahasiswa("2205361", "Adri Sapta F", "Laki-laki", "Ilmu Komputer", "C2"));
        listMahasiswa.add(new Mahasiswa("2200598", "Jasmine Noor Fawzia", "Perempuan", "Ilmu Komputer", "C2"));
        listMahasiswa.add(new Mahasiswa("2205324", "Fahmi Rasyid Aflah", "Laki-laki", "Ilmu Komputer", "C2"));
        listMahasiswa.add(new Mahasiswa("2202869", "Revana Faliha Salma", "Perempuan", "Ilmu Komputer", "C2"));
        listMahasiswa.add(new Mahasiswa("2209841", "Nadila Az-Zahra", "Perempuan", "Pendidikan Ilmu Komputer", "B"));
        listMahasiswa.add(new Mahasiswa("2202843", "Aldi Prasetyo Widodo", "Laki-laki", "Pendidikan Ilmu Komputer", "B"));
        listMahasiswa.add(new Mahasiswa("2202638", "Alissa Isni Silviani Sutadi", "Perempuan", "Pendidikan Ilmu Komputer", "B"));
        listMahasiswa.add(new Mahasiswa("2204083", "Dzulfikri Najmul Falah ", "Laki-laki", "Pendidikan Ilmu Komputer", "B"));
        listMahasiswa.add(new Mahasiswa("2204340", "Khrisna Wahyu Wibisono", "Laki-laki", "Pendidikan Ilmu Komputer", "B"));
    }
}
