package run_here;
import javax.swing.JOptionPane;
import duy_truyen.*;
import modul.showUpdate;

public class run {
    data data;
    population popu;
    showUpdate show;
    boolean toanVen;

    public run() {
        data = new data();
        toanVen = data.load();
        if (toanVen) {
            popu = new population(5, data);
            show = new showUpdate();
        }
    }

    public void giaiThuatDuyTruyenTrenQuanThe(int theHe, double alphalt) {
        population new_popu;
        for (int i = 0; i < theHe; i++) {
            new_popu = new population();
            for (int j = 0; j < popu.getPopu().size(); j++) {
                rangBuoc_lich caTheDuc = popu.chonLocTuNhien();
                rangBuoc_lich caTheCai = popu.chonLocTuNhien();
                rangBuoc_lich child = caTheDuc.laiGhepVoi(caTheCai, data);
                if (Math.random() < alphalt) {
                    child.dotBienNhiemSacThe();
                }
                new_popu.addVaoQuanThe(child);
            }
            popu.getPopu().addFirst(new_popu.getPopu().getFirst());
            new_popu.getPopu().removeLast();
            popu.getPopu().addAll(new_popu.getPopu());
            popu.daoThaiNhungCaTheXau(1000);
            show.updatePanel(i + 1, theHe, popu);
        }
        show.getPanel_update()._finish();
        System.out.println("\n######################## Cá thể tốt nhất trong quần thể #########################\n");
        popu.getPopu().getFirst().xuatRangBuoc_lich();
    }

    public static void main(String[] args) {
        run run = new run();
        if (run.toanVen) {
            run.giaiThuatDuyTruyenTrenQuanThe(150, 0.60);
        } else {
            JOptionPane.showConfirmDialog(null, "Bài Toán Xếp Lịch DỮ LIỆU KHÔNG ĐƯỢC TOÀN VẸN", "Error!", JOptionPane.WARNING_MESSAGE);
            System.out.println("Bài Toán Xếp Lịch DỮ LIỆU KHÔNG ĐƯỢC TOÀN VẸN");
        }
    }
}
