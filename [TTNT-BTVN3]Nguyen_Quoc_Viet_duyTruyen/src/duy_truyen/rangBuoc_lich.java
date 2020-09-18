package duy_truyen;

import java.util.ArrayList;
import modul._modul;

public class rangBuoc_lich {

    private lich lich;
    private int toTal_cung;
    private float toTal_mem;

    public lich getLich() {
        return lich;
    }

    public void setLich(lich lich) {
        this.lich = lich;
    }

    public int getToTal_cung() {
        return toTal_cung;
    }

    public void setToTal_cung(int toTal_cung) {
        this.toTal_cung = toTal_cung;
    }

    public float getToTal_mem() {
        return toTal_mem;
    }

    public void setToTal_mem(float toTal_mem) {
        this.toTal_mem = toTal_mem;
    }

    public rangBuoc_lich(data data) {
        lich = new lich(data);
    }

    // Ràng buộc cứng thứ nhất (chi tiết tại src/file/readMe.txt)
    private int getRangBuocCung1() {
        int sum = 0;
        for (int i = 0; i < lich.getData().getTapThoiGian().length; i++) {
            sum += lich.RB1soCanBoViPham_thoiDiemT(i);
        }
        return sum;
    }

    // Ràng buộc cứng thứ hai (chi tiết tại src/file/readMe.txt)
    private int getRangBuocCung2() {
        int sum = 0;
        for (int i = 0; i < lich.getMaTran().length; i++) {
            for (int j = 0; j < lich.getMaTran()[i].length; j++) {
                if (lich.getMaTran()[i][j] < 0 || lich.getMaTran()[i][j] >= lich.getData().getTapCanBo().length) {
                    sum++;
                }
            }
        }
        return sum;
    }

    // Ràng buộc cứng thứ ba (chi tiết tại src/file/readMe.txt)
    private int getRangBuocCung3() {
        int sum = 0;
        for (int i = 0; i < lich.getData().getTapNganhDay().length; i++) {
            ArrayList<Integer> canBoKhongDayDuoc_nganhX = lich.layCanBoKhongDayDuoc_nganhX(i);
            for (int j = 0; j < lich.getData().getTapThoiGian().length; j++) {
                if (lich.canBoX_trongTap_canBoKhongDayDuoc_nganh(lich.getMaTran()[i][j], canBoKhongDayDuoc_nganhX)) {
                    sum++;
                }
            }
        }
        return sum;
    }

    // Ràng buộc cứng thứ tư (chi tiết tại src/file/readMe.txt)
    private int getRangBuocCung4() {
        int sum = 0;
        for (int i = 0; i < lich.getData().getTapThoiGian().length; i++) {
            ArrayList<Integer> tapCanBoBan = lich.layCanBoBan_taiThoiDiemT(i);
            for (int j = 0; j < lich.getData().getTapNganhDay().length; j++) {
                if (lich.canBoX_trongTap_canBoBan_thoiDiemT(lich.getMaTran()[j][i], tapCanBoBan)) {
                    sum++;
                }
            }
        }
        return sum;
    }

    // Ràng buộc mềm thứ nhất (chi tiết tại readMe.txt)
    private float getRangBuocMem1() {
        float trungBinh = 0, temp = 0;
        for (int i = 0; i < lich.getBienTroGiup().length; i++) {
            temp = lich.getSoNgayNghiTrungBinh_canBoID(i);
            temp = 10 - temp;
            trungBinh += temp;
        }
        trungBinh /= lich.getBienTroGiup().length;
        return trungBinh;
    }

    // Ràng buộc mềm thứ hai (chi tiết tại readMe.txt)
    private int getRangBuocMem2(int gioiHangNgayLech) {
        int result = 0;
        int TB_day = lich.getTrungBinhMoiNguoiDay();
        for (int i = 0; i < lich.getBienTroGiup().length; i++) {
            int size = lich.getBienTroGiup()[i].size();
            if (size == 0) {
                continue;
            }
            if (!_modul.thoaDoLech(lich.getBienTroGiup()[i].get(size - 1).getLanDayThu(), gioiHangNgayLech, TB_day)) {
                result++;
            }
        }
        return result;
    }

    private void init_getToTal_cung() {
        int BC1 = getRangBuocCung1();
        int BC2 = getRangBuocCung2();
        int BC3 = getRangBuocCung3();
        int BC4 = getRangBuocCung4();
        setToTal_cung(BC1 + BC2 + BC3 + BC4);
    }

    private void init_getToTal_mem() {
        float BM1 = getRangBuocMem1();
        int BM2 = getRangBuocMem2(3);// truyền vào số ngày lệch mong muốn.
        setToTal_mem(BM1 + BM2);
    }

    public void init_getToTal_Cung_Mem() {
        lich.initBienTroGiup();
        init_getToTal_cung();
        init_getToTal_mem();
    }

    public rangBuoc_lich laiGhepVoi(rangBuoc_lich other, data data) {
        rangBuoc_lich result = new rangBuoc_lich(data);
        int id, indexMax = lich.getData().getTapThoiGian().length;
        for (int i = 0; i < lich.getMaTran().length; i++) {
            id = _modul.ranDomZeroTo_Num(indexMax);
            int[] temp = ghepNhiemSacThe(this.getLich().getMaTran()[i], other.getLich().getMaTran()[i], id);
            result.getLich().getMaTran()[i] = temp;
        }
        result.getLich().initBienTroGiup();
        result.init_getToTal_Cung_Mem();
        return result;
    }

    private int[] ghepNhiemSacThe(int[] nstA, int[] nstB, int id) {
        int[] result = new int[nstA.length];
        for (int i = id; i < nstA.length; i++) {
            nstA[i] = nstB[i];
        }
        for (int i = 0; i < nstA.length; i++) {
            result[i] = nstA[i];
        }
        return result;
    }

    public void dotBienNhiemSacThe() {
        int nstThu = _modul.ranDomZeroTo_Num(lich.getMaTran().length);
        for (int i = 0; i < lich.getMaTran()[nstThu].length; i++) {
            lich.getMaTran()[nstThu][i] = _modul.ranDomZeroTo_Num(lich.getData().getTapCanBo().length);
        }
        lich.initBienTroGiup();
        init_getToTal_Cung_Mem();
    }

    public boolean totHon(rangBuoc_lich other) {
        if (getToTal_cung() <= other.getToTal_cung()) {
            if (getToTal_cung() == other.getToTal_cung()) {
                if (getToTal_mem() <= other.getToTal_mem()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    public void xuatRangBuoc_lich() {
        lich.xuatLich();
        System.out.println("Tong Rang Buoc Cung: " + getToTal_cung());
        System.out.println("Tong Rang Buoc Mem: " + getToTal_mem());
    }
}
