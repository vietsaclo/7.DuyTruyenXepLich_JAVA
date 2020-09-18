package duy_truyen;

public class Node {

    private int nganhDay, thoiGian, lanDayThu;
    private int soNgayDaNghi;

    public int getSoNgayDaNghi() {
        return soNgayDaNghi;
    }

    public void setSoNgayDaNghi(int soNgayDaNghi) {
        this.soNgayDaNghi = soNgayDaNghi;
    }

    public int getLanDayThu() {
        return lanDayThu;
    }

    public void setLanDayThu(int lanDayThu) {
        this.lanDayThu = lanDayThu;
    }

    public int getNganhDay() {
        return nganhDay;
    }

    public void setNganhDay(int nganhDay) {
        this.nganhDay = nganhDay;
    }

    public int getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(int thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Node() {

    }

    public Node(int lanDay, int nganh, int thoiG, int soNgayNghi) {
        nganhDay = nganh;
        thoiGian = thoiG;
        lanDayThu = lanDay;
        soNgayDaNghi = soNgayNghi;
    }

    public void printNode(int lanDay, String nganhDay, String thoiGian) {
        System.out.print("  [lanDay@" + lanDay + ":" + nganhDay + ":" + thoiGian + ":" + getSoNgayDaNghi() + "]  ");
    }
}
