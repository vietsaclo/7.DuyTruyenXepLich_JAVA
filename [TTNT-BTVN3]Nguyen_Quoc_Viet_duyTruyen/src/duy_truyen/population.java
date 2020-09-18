package duy_truyen;

import java.util.Comparator;
import java.util.LinkedList;

import modul._modul;

public class population {

    private LinkedList<rangBuoc_lich> popu;

    public LinkedList<rangBuoc_lich> getPopu() {
        return popu;
    }

    public void setPopu(LinkedList<rangBuoc_lich> popu) {
        this.popu = popu;
    }

    public population(int soLuong, data data) {
        popu = new LinkedList<>();
        for (int i = 0; i < soLuong; i++) {
            rangBuoc_lich temp = new rangBuoc_lich(data);
            temp.getLich().createNew_lich_withRamdom();
            temp.init_getToTal_Cung_Mem();
            addVaoQuanThe(temp);
        }
        printPopulation_KhoiTao();
    }

    public population() {
        popu = new LinkedList<>();
    }

    public void printPopulation_KhoiTao() {
        System.out.println("\n######################## Cá cá thể khởi tạo ban đầu Gồm: " + popu.size() + " #########################\n");
        for (rangBuoc_lich i : popu) {
            i.getLich().xuatLich();
            System.out.println("Rang buoc Cung: " + i.getToTal_cung());
            System.out.println("Rang buoc Mem: " + i.getToTal_mem());
        }
    }

    public void addVaoQuanThe(rangBuoc_lich rbT) {
        if (popu.size() == 0) {
            popu.addFirst(rbT);
        } else {
            if (rbT.getToTal_cung() < popu.getFirst().getToTal_cung()) {
                popu.addFirst(rbT);
            } else {
                if (rbT.getToTal_mem() == popu.getFirst().getToTal_cung()) {
                    if (rbT.getToTal_mem() <= popu.getFirst().getToTal_mem()) {
                        popu.addFirst(rbT);
                    } else {
                        popu.add(1, rbT);
                    }
                } else {
                    popu.add(1, rbT);
                }
            }
        }
    }

    public rangBuoc_lich chonLocTuNhien() {
        int id1 = _modul.ranDomZeroTo_Num(popu.size());
        int id2 = _modul.ranDomZeroTo_Num(popu.size());
        rangBuoc_lich ct1 = popu.get(id1);
        rangBuoc_lich ct2 = popu.get(id2);
        if (ct1.totHon(ct2)) {
            return ct1;
        }
        return ct2;
    }

    public void daoThaiNhungCaTheXau(int maxCaThe) {
        if (popu.size() < maxCaThe) {
            return;
        }
        if (popu.getFirst().getToTal_cung() != 0) {
            sortTheRangBuocCung();
        } else {
            sortTheoRangBuocMem();
        }
        int size = popu.size();
        int id = size - maxCaThe;
        while (id > 0) {
            popu.removeLast();
            id--;
        }
    }

    public void sortTheRangBuocCung() {
        popu.sort(new Comparator<rangBuoc_lich>() {
            @Override
            public int compare(rangBuoc_lich o0, rangBuoc_lich o1) {
                if (o0.getToTal_cung() > o1.getToTal_cung()) {
                    return 1;
                }
                if (o0.getToTal_cung() == o1.getToTal_cung()) {
                    return 0;
                }
                return -1;
            }
        });
    }

    public void sortTheoRangBuocMem() {
        rangBuoc_lich pop = popu.getFirst();
        popu.sort(new Comparator<rangBuoc_lich>() {
            @Override
            public int compare(rangBuoc_lich o0, rangBuoc_lich o1) {
                if (o0.getToTal_mem() > o1.getToTal_mem()) {
                    return 1;
                }
                if (o0.getToTal_mem() == o1.getToTal_mem()) {
                    return 0;
                }
                return -1;
            }
        });
        addVaoQuanThe(pop);
        popu.removeLast();
    }
}
