package duy_truyen;

import modul.*;

public class data {

    private String[] tapCanBo;
    private String[] tapNganhDay;
    private String[] tapThoiGian;
    private int[][] quanHe_canBo_nganhDay;
    private int[][] quanHe_canBo_thoiGian;

    public String[] getTapCanBo() {
        return tapCanBo;
    }

    public void setTapCanBo(String[] tapCanBo) {
        this.tapCanBo = tapCanBo;
    }

    public String[] getTapNganhDay() {
        return tapNganhDay;
    }

    public void setTapNganhDay(String[] tapNganhDay) {
        this.tapNganhDay = tapNganhDay;
    }

    public String[] getTapThoiGian() {
        return tapThoiGian;
    }

    public void setTapThoiGian(String[] tapThoiGian) {
        this.tapThoiGian = tapThoiGian;
    }

    public int[][] getQuanHe_canBo_nganhDay() {
        return quanHe_canBo_nganhDay;
    }

    public void setQuanHe_canBo_nganhDay(int[][] quanHe_canBo_nganhDay) {
        this.quanHe_canBo_nganhDay = quanHe_canBo_nganhDay;
    }

    public int[][] getQuanHe_canBo_thoiGian() {
        return quanHe_canBo_thoiGian;
    }

    public void setQuanHe_canBo_thoiGian(int[][] quanHe_canBo_thoiGian) {
        this.quanHe_canBo_thoiGian = quanHe_canBo_thoiGian;
    }

    public data() {

    }

    public boolean load() {
        boolean vsl = _modul.initConf();
        if (vsl) {
            try {
                tapCanBo = _modul.loadMangString(_modul.file_tapCanBo);
                tapNganhDay = _modul.loadMangString(_modul.file_tapNganhDay);
                tapThoiGian = _modul.loadMangString(_modul.file_tapThoiGian);
                quanHe_canBo_thoiGian = _modul.loadMangInt(_modul.file_quanHeCanBo_thoiGian, true);
                quanHe_canBo_nganhDay = _modul.loadMangInt(_modul.file_quanHeCanBo_nganhDay, false);
                printInFoAfterLoad();
                if (tapCanBo == null || tapNganhDay == null || tapThoiGian == null
                        || quanHe_canBo_nganhDay == null || quanHe_canBo_thoiGian == null) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                // TODO: handle exception
                return false;
            }
        }
        return false;
    }

    private void xuatTapCanBo() {
        if (getTapCanBo() != null) {
            _modul.xuatMangChuoi("Tap Can Bo", getTapCanBo());
        } else {
            System.out.println("tap can bo null");
        }
    }

    private void xuatTapNghanhDay() {
        if (getTapNganhDay() != null) {
            _modul.xuatMangChuoi("Tap Nganh Day", getTapNganhDay());
        } else {
            System.out.println("tap Nganh Day null");
        }
    }

    private void xuatTapThoiGian() {
        if (getTapThoiGian() != null) {
            _modul.xuatMangChuoi("Tap Thoi Gian", getTapThoiGian());
        } else {
            System.out.println("Tap Thoi Gian null");
        }
    }

    private void xuatQuanHeCanBo_thoiGian() {
        System.out.println(_modul.madeString(537));
        System.out.print(_modul.quanHeCanBo_thoiGianInfo);
        if (getQuanHe_canBo_thoiGian() != null) {
            _modul.xuatMaTranInt("Ma Trận đã mã hóa", getQuanHe_canBo_thoiGian());
            System.out.println("****>>>> Ma Trận đã được giải mã.\n");
            System.out.print(_modul.getVipString("QH_canBo_ThoiGian", 25));
            for (int i = 0; i < tapThoiGian.length; i++) {
                System.out.print(_modul.getVipString(tapThoiGian[i], 15));
            }
            System.out.println("\n");
            for (int i = 0; i < tapCanBo.length; i++) {
                System.out.print(_modul.getVipString(tapCanBo[i], 25));
                for (int j = 0; j < tapThoiGian.length; j++) {
                    System.out.print(_modul.getVipString(_modul.getBan_ranh(quanHe_canBo_thoiGian[i][j]), 15));
                }
                System.out.println("\n");
            }
        } else {
            System.out.println("Quan he can bo va thoi gian NULL");
        }
    }

    private void xuatQuanHeCanBo_nganhDay() {
        System.out.println(_modul.madeString(537));
        System.out.print(_modul.quanHeCanBo_nganhDayInfo);
        if (getQuanHe_canBo_thoiGian() != null) {
            _modul.xuatMaTranInt("Ma Trận đã mã hóa", getQuanHe_canBo_nganhDay());
            System.out.println("****>>>> Ma Trận đã được giải mã.\n");
            System.out.print(_modul.getVipString("QH_canBo_nganhDay", 25));
            for (int i = 0; i < tapNganhDay.length; i++) {
                System.out.print(_modul.getVipString(tapNganhDay[i], 25));
            }
            System.out.println("\n");
            for (int i = 0; i < tapCanBo.length; i++) {
                System.out.print(_modul.getVipString(tapCanBo[i], 25));
                for (int j = 0; j < tapNganhDay.length; j++) {
                    System.out.print(_modul.getVipString(_modul.getDuoc_Khong(quanHe_canBo_nganhDay[i][j]), 25));
                }
                System.out.println("\n");
            }
        } else {
            System.out.println("Quan he can bo va thoi gian NULL");
        }
    }

    public void printInFoAfterLoad() {
        xuatTapCanBo();
        xuatTapNghanhDay();
        xuatTapThoiGian();
        xuatQuanHeCanBo_nganhDay();
        xuatQuanHeCanBo_thoiGian();
    }
}
