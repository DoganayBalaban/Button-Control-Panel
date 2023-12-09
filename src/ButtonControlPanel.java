import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonControlPanel extends JFrame {
    // Butonlar ve durumlarını tutmak için iki boyutlu dizi
    private final JButton[][] btns;
    private final boolean[][] btnsStatus;

    // GraphQL şema adresi
    private String graphqlSchemaUrl = "https://example.com/graphql";

    // Butonların renkleri ve simgeleri
    private final Color pasifRenk = Color.GRAY;
    private final Color aktifRenk = Color.GREEN;
    private final Icon pasifSimge = new ImageIcon("assets/icons8-do-not-disturb-48.png");
    private final Icon aktifSimge = new ImageIcon("assets/icons8-ok-48.png");

    public ButtonControlPanel() {
        setTitle("Control Panel");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Butonlar ve durumlarını tutmak için iki boyutlu dizilerin oluşturulması
        btns = new JButton[4][4];
        btnsStatus = new boolean[4][4];

        // GridLayout kullanarak 4x4 bir düzen oluşturulması
        setLayout(new GridLayout(4, 4));

        // Butonların başlatılması
        initializeButtons();

        // Frame'e butonların eklenmesi
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                add(btns[i][j]);
            }
        }
    }

    // Butonların başlatılması
    private void initializeButtons() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                btns[i][j] = createButton(i, j, "Button " + (i * 4 + j + 1));
            }
        }
    }

    // Yeni bir buton oluşturulması
    private JButton createButton(int row, int col, String text) {
        JButton button = new JButton(text);
        button.setBackground(pasifRenk);
        button.setIcon(pasifSimge);

        // Butona tıklandığında gerçekleşecek olayların belirlenmesi
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick(row, col);
            }
        });

        return button;
    }

    // Buton tıklandığında gerçekleşecek olayların yönetilmesi
    private void handleButtonClick(int row, int col) {
        // Tıklanan butonun durumunu güncelle
        updateButtonStatus(row, col);

        // Diğer butonların durumunu pasif yap
        resetOtherButtonStatus(row, col);

        // Butonların görünümünü güncelle
        updateButtonAppearance();

        // GraphQL mutation'ını çalıştır
        runGraphQLMutation(row, col, graphqlSchemaUrl);
    }

    // Buton durumlarını güncelle
    private void updateButtonStatus(int row, int col) {
        btnsStatus[row][col] = !btnsStatus[row][col];
    }

    // Diğer butonları pasif yap
    private void resetOtherButtonStatus(int row, int col) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!(i == row && j == col)) {
                    btnsStatus[i][j] = false;
                }
            }
        }
    }

    // Butonların görünümünü güncelle
    private void updateButtonAppearance() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JButton button = btns[i][j];
                if (btnsStatus[i][j]) {
                    button.setBackground(aktifRenk);
                    button.setIcon(aktifSimge);
                } else {
                    button.setBackground(pasifRenk);
                    button.setIcon(pasifSimge);
                }
            }
        }
    }

    // GraphQL mutation'ını çalıştır
    private void runGraphQLMutation(int row, int col, String graphqlSchemaUrl) {
        System.out.println("GraphQl mutasyonu " + (row + 1) +  ". satır" + " ve " + (col + 1)+". sütunda" + " çalışıyor.");
    }

    // Uygulamanın başlatılması
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ButtonControlPanel().setVisible(true);
            }
        });
    }
}
