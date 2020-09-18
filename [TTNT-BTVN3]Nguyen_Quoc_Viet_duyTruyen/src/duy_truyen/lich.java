package duy_truyen;

import java.util.ArrayList;
import java.util.Comparator;

import modul._modul;

public class lich {

    private data data;
    private int[][] maTran;
    private ArrayList<Node>[] bienTroGiup;

    public ArrayList<Node>[] getBienTroGiup() {
        return bienTroGiup;
    }

    public void setBienTroGiup(ArrayList<Node>[] bienTroGiup) {
        this.bienTroGiup = bienTroGiup;
    }

    public data getData() {
        return data;
    }

    public void setData(data data) {
        this.data = data;
    }

    public int[][] getMaTran() {
        return maTran;
    }

    public void setMaTran(int[][] maTran) {
        this.maTran = maTran;
    }

    @SuppressWarnings("unchecked")
    public lich(data dt) {
        data = dt;
        maTran = new int[data.getTapNganhDay().length][data.getTapThoiGian().length];
        bienTroGiup = new ArrayList[data.getTapCanBo().length];
    }

    public void createNew_lich_withRamdom() {
        for (int i = 0; i < maTran.length; i++) {
            for (int j = 0; j < maTran[i].length; j++) {
                maTran[i][j] = _modul.ranDomZeroTo_Num(10);
            }
        }
    }

    public void xuatLich() {
        System.out.println(_modul.madeString(537));
        System.out.print(_modul.getVipString("LichDay", 25));
        for (int i = 0; i < data.getTapThoiGian().length; i++) {
            System.out.print(_modul.getVipString(data.getTapThoiGian()[i].toString(), 15));
        }
        System.out.println("\n");
        for (int i = 0; i < maTran.length; i++) {
            System.out.print(_modul.getVipString(data.getTapNganhDay()[i].toString(), 25));
            for (int j = 0; j < maTran[i].length; j++) {
                System.out.print(_modul.getVipStringNguoc(data.getTapCanBo()[maTran[i][j]], 9, 15));
            }
            System.out.println("\n");
        }
    }

    public ArrayList<Integer> layCanBoBan_taiThoiDiemT(int T) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < data.getQuanHe_canBo_thoiGian().length; i++) {
            int active = data.getQuanHe_canBo_thoiGian()[i][T];
            if (active == 1) {
                result.add(i);
            }
        }
        return result;
    }

    public ArrayList<Integer> layCanBoKhongDayDuoc_nganhX(int X) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < data.getTapCanBo().length; i++) {
            int active = data.getQuanHe_canBo_nganhDay()[i][X];
            if (active == 0) {
                arr.add(i);
            }
        }
        return arr;
    }

    public ArrayList<Integer> layCanBoTruc_thoiDiemT(int T) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < data.getTapNganhDay().length; i++) {
            list.add(maTran[i][T]);
        }
        return list;
    }

    public int RB1soCanBoViPham_thoiDiemT(int T) {
        int result = 0;
        ArrayList<Integer> arr = layCanBoTruc_thoiDiemT(T);
        if (arr.size() == 1) {
            return result;
        }
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(i) == arr.get(j)) {
                    result++;
                    arr.remove(j);
                    j--;
                }
            }
            if (arr.size() <= 1) {
                return result;
            }
            arr.remove(i);
            i = -1;
        }
        return result;
    }

    public void xuatCanBoBan_taiThoiDiemT(int T) {
        ArrayList<Integer> list = layCanBoBan_taiThoiDiemT(T);
        for (Integer i : list) {
            System.out.println(data.getTapCanBo()[i]);
        }
    }

    public void xuatCanBoKhongDayDuoc_nganhX(int X) {
        ArrayList<Integer> list = layCanBoKhongDayDuoc_nganhX(X);
        for (Integer i : list) {
            System.out.println(data.getTapCanBo()[i]);
        }
    }

    public boolean canBoX_trongTap_canBoBan_thoiDiemT(int canBoX, ArrayList<Integer> tapCanBoBan_thoiDiemT) {
        for (Integer canBo : tapCanBoBan_thoiDiemT) {
            if (canBo == canBoX) {
                return true;
            }
        }
        return false;
    }

    public boolean canBoX_trongTap_canBoKhongDayDuoc_nganh(int canBoX, ArrayList<Integer> tapCanBoKhongDayDuoc_nganhX) {
        for (Integer canBo : tapCanBoKhongDayDuoc_nganhX) {
            if (canBo == canBoX) {
                return true;
            }
        }
        return false;
    }

    public void initBienTroGiup() {
        int id = 0, ngayNghi = 0;
        for (int tg = 0; tg < bienTroGiup.length; tg++) {
            bienTroGiup[tg] = new ArrayList<Node>();
            for (int i = 0; i < data.getTapNganhDay().length; i++) {
                for (int j = 0; j < data.getTapThoiGian().length; j++) {
                    if (maTran[i][j] == tg) {
                        bienTroGiup[tg].add(new Node(id, i, j, ngayNghi));
                    }
                }
            }
            bienTroGiup[tg].sort(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return data.getTapThoiGian()[o1.getThoiGian()].compareTo(data.getTapThoiGian()[o2.getThoiGian()]);
                }
            });
            for (int i = 0; i < bienTroGiup[tg].size(); i++) {
                bienTroGiup[tg].get(i).setLanDayThu(i + 1);
            }
            setSoNgayNghi(bienTroGiup[tg]);
        }
    }

    public void xuatBienTroGiup() {
        for (int i = 0; i < bienTroGiup.length; i++) {
            System.out.print(data.getTapCanBo()[i]);
            for (int j = 0; j < bienTroGiup[i].size(); j++) {
                Node n = bienTroGiup[i].get(j);
                n.printNode(n.getLanDayThu(), data.getTapNganhDay()[n.getNganhDay()], data.getTapThoiGian()[n.getThoiGian()]);
            }
            System.out.println();
        }
    }

    private void setSoNgayNghi(ArrayList<Node> list) {
        if (list.size() <= 1) {
            return;
        }
        for (int i = 1; i < list.size(); i++) {
            String ngay1 = data.getTapThoiGian()[list.get(i - 1).getThoiGian()];
            String ngay2 = data.getTapThoiGian()[list.get(i).getThoiGian()];
            list.get(i).setSoNgayDaNghi(_modul.khoanCanh2Ngay(ngay1, ngay2));
        }
    }

    public float getSoNgayNghiTrungBinh_canBoID(int id) {
        float result = 0;
        if (bienTroGiup[id].size() != 0) {
            for (Node i : bienTroGiup[id]) {
                result += i.getSoNgayDaNghi();
            }
            result = result / bienTroGiup[id].size();
        }
        return result;
    }

    public int getTrungBinhMoiNguoiDay() {
        int sum = 0;
        for (int i = 0; i < bienTroGiup.length; i++) {
            sum += bienTroGiup[i].size();
        }
        return sum / bienTroGiup.length;
    }
}
