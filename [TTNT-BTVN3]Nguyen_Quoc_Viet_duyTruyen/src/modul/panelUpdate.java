package modul;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import duy_truyen.population;

public class panelUpdate extends JFrame {

    public static final String text1 = "Số lượng cá thể trong quần thể-> lớn hơn: ";
    public static final String text2 = "Ràng buộc CỨNG tốt nhất hiện tại-> ";
    public static final String text3 = "Ràng buộc MỀM tốt nhất hiện tại-> ";
    public static final String text4 = "Sau Khi Đào Thải Còn-> ";
    public static final String text5 = "Trải Qua Đời thứ (repeat util)-> ";
    /**
     *
     */
    boolean _go = true;
    private static final long serialVersionUID = 1L;

    public panelUpdate() {
        _start();
        update_time();
    }

    private void _start() {
        setTitle("Nguyen Quoc Viet");
        setSize(500, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Container ctn = this.getContentPane();
        BorderLayout border_ctn = new BorderLayout(0, 0);
        ctn.setLayout(border_ctn);

        // <news>
        panel_tren = new JPanel();
        panel_giua = new JPanel();
        lable_showLoad = new JLabel();
        panel_duoi = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        lable1 = new JLabel();
        lable2 = new JLabel();
        lable3 = new JLabel();
        lable4 = new JLabel();
        lable5 = new JLabel();
        lable_time = new JLabel();
        // </news>

        // <North>
        panel_tren.setLayout(new BoxLayout(panel_tren, BoxLayout.Y_AXIS));
        panel_tren.setBackground(new Color(183, 218, 234));
        lable_showLoad.setFont(new Font("Times New Roman", 1, 15));
        lable_showLoad.setHorizontalAlignment(JLabel.RIGHT);
        lable_showLoad.setSize(new Dimension(ctn.getMaximumSize().width, 30));
        lable_showLoad.setBackground(new Color(198, 183, 234));
        lable_showLoad.setForeground(Color.RED);
        lable_showLoad.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        panel_tren.add(lable_showLoad);
        // </North>

        // <center>
        panel_giua.setBackground(Color.PINK);
        panel_giua.setLayout(new BoxLayout(panel_giua, BoxLayout.Y_AXIS));
        panel1.setBackground(Color.WHITE);
        panel2.setBackground(new Color(230, 220, 218));
        panel3.setBackground(Color.WHITE);
        panel4.setBackground(new Color(230, 220, 218));
        panel5.setBackground(Color.WHITE);

        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        lable1.setSize(new Dimension(panel1.getMaximumSize().width, panel1.getMaximumSize().height));
        lable1.setFont(new Font("Times New Roman", 1, 20));
        lable1.setForeground(new Color(138, 177, 125));
        lable2.setSize(new Dimension(panel1.getMaximumSize().width, panel1.getMaximumSize().height));
        lable2.setFont(new Font("Times New Roman", 1, 20));
        lable2.setForeground(new Color(138, 177, 125));
        lable3.setSize(new Dimension(panel1.getMaximumSize().width, panel1.getMaximumSize().height));
        lable3.setFont(new Font("Times New Roman", 1, 20));
        lable3.setForeground(new Color(138, 177, 125));
        lable4.setSize(new Dimension(panel1.getMaximumSize().width, panel1.getMaximumSize().height));
        lable4.setFont(new Font("Times New Roman", 1, 20));
        lable4.setForeground(new Color(138, 177, 125));
        lable5.setSize(new Dimension(panel1.getMaximumSize().width, panel1.getMaximumSize().height));
        lable5.setFont(new Font("Times New Roman", 1, 20));
        lable5.setForeground(new Color(138, 177, 125));

        panel1.add(lable1);
        panel2.add(lable2);
        panel3.add(lable3);
        panel4.add(lable4);
        panel5.add(lable5);

        panel_giua.add(panel1);
        panel_giua.add(panel2);
        panel_giua.add(panel3);
        panel_giua.add(panel4);
        panel_giua.add(panel5);
        // </center>

        // <south>
        panel_duoi.setBackground(Color.cyan);
        panel_duoi.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel_duoi.add(lable_time);
        // </south>

        ctn.add(panel_tren, BorderLayout.NORTH);
        ctn.add(panel_giua, BorderLayout.CENTER);
        ctn.add(panel_duoi, BorderLayout.SOUTH);
    }

    public void setLable(int current, int fanal, population popu) {
        int strLength = (int) ((float) current / (float) fanal * 100);
        int midString = strLength / 2;
        String text = _modul.madeString(midString);
        lable_showLoad.setText(text + strLength + "%");
        lable1.setText(text1 + popu.getPopu().size());
        lable2.setText(text2 + popu.getPopu().getFirst().getToTal_cung());
        lable3.setText(text3 + popu.getPopu().getFirst().getToTal_mem());
        lable4.setText(text4 + popu.getPopu().size());
        lable5.setText(text5 + current + " / " + fanal);

    }

    public void update_time() {
        Thread up_time = new Thread(new Runnable() {
            int gio, phut, giay;

            @Override
            public void run() {
                while (_go) {
                    giay++;
                    if (giay == 61) {
                        phut++;
                        giay = 0;
                    }
                    if (phut == 61) {
                        gio++;
                        phut = 0;
                    }
                    lable_time.setText("ĐÃ QUA:    " + gio + " : " + phut + " : " + giay + " s");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        up_time.start();
    }

    public void _finish() {
        _go = false;
        JOptionPane.showMessageDialog(null, "ĐÃ THỰC HIỆN XONG GIẢI THUẬT. XEM KẾT QUẢ DƯỚI CONSLE", "FINISH", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    // <place Variabel>
    private JPanel panel_tren;
    private JPanel panel_giua;
    private JPanel panel_duoi;
    private JLabel lable_showLoad;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JLabel lable1;
    private JLabel lable2;
    private JLabel lable3;
    private JLabel lable4;
    private JLabel lable5;
    private JLabel lable_time;
    // </place Variable>
}
