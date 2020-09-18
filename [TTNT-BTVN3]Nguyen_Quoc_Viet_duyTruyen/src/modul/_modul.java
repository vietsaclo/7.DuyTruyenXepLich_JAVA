package modul;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class _modul {

    public static String file_tapCanBo;
    public static String file_tapNganhDay;
    public static String file_tapThoiGian;
    public static String file_quanHeCanBo_nganhDay;
    public static String file_quanHeCanBo_thoiGian;
    public static String quanHeCanBo_thoiGianInfo = "================== quan he can bo va thoi gian ==================\n";
    public static String quanHeCanBo_nganhDayInfo = "================== quan he can bo va nganh day ==================\n";

    public static String[] loadMangString(String fileName) {
        String[] str = null;
        File file = new File(fileName);
        if (file.exists()) {
            try {
                Scanner input = new Scanner(file);
                int size = input.nextInt();
                str = new String[size];
                for (int i = 0; i < size; i++) {
                    input.nextInt();
                    str[i] = input.nextLine().trim();
                }
                input.close();
            } catch (Exception e) {
                System.out.println("Lỗi Đọc File");
            }
        }
        return str;
    }

    public static void xuatMangChuoi(String name, String[] str) {
        System.out.println("================== " + name + " ==================");
        int id = 0;
        for (String i : str) {
            System.out.println(id++ + " - " + i);
        }
        System.out.println();
    }

    public static int ranDomZeroTo_Num(int Num) {
        return new Random().nextInt(Num);
    }

    public static int[][] loadMangInt(String path, boolean canBo_thoiGian) {
        int[][] result = null;
        File file = new File(path);
        if (file.exists()) {
            try {
                Scanner ip = new Scanner(file);
                String line = ip.nextLine();
                while (!line.equals("===ngat===")) {
                    if (canBo_thoiGian) {
                        quanHeCanBo_thoiGianInfo += line + "\n";
                    } else {
                        quanHeCanBo_nganhDayInfo += line + "\n";
                    }
                    line = ip.nextLine();
                }
                int rows = ip.nextInt(), col = ip.nextInt();
                result = new int[rows][col];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < col; j++) {
                        result[i][j] = ip.nextInt();
                    }
                }
                ip.close();
            } catch (Exception e) {
                System.out.println("Loi Doc File!");
            }
        }
        return result;
    }

    public static void xuatMaTranInt(String name, int[][] maTran) {
        System.out.println("================== " + name + " ==================");
        for (int i = 0; i < maTran.length; i++) {
            for (int j = 0; j < maTran[i].length; j++) {
                System.out.print(maTran[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static String getBan_ranh(int i) {
        if (i == 1) {
            return "Bận";
        }
        return "Ranh";
    }

    public static String getDuoc_Khong(int i) {
        if (i == 1) {
            return "Được";
        } else {
            return "Không";
        }
    }

    public static String getVipString(String str, int space) {
        String result = "";
        int mid_str = str.length() / 2;
        int mid_space = space / 2;
        int khoanTrang = mid_space - mid_str;
        result += " |";
        for (int i = 0; i < khoanTrang; i++) {
            result += " ";
        }
        result += str;
        while (result.length() <= space) {
            result += " ";
        }
        result += "|";
        return result;
    }

    public static String getVipStringNguoc(String str, int get, int space) {
        String result = "";
        int id = str.length() - get;
        for (; id < str.length(); id++) {
            result += str.charAt(id);
        }
        result = getVipString(result, space);
        return result;
    }

    public static String madeString(int lengh) {
        String str = "";
        for (int i = 0; i < lengh; i++) {
            str += "=";
        }
        return str;
    }

    public static int khoanCanh2Ngay(String ngay1, String ngay2) {
        int result = 0;
        int n1 = Integer.parseInt(ngay1.split("/")[0]);
        int n2 = Integer.parseInt(ngay2.split("/")[0]);
        result = n2 - n1 - 1;
        if (result == -1) {
            return 0;
        }
        return result;
    }

    public static boolean thoaDoLech(int conSo, int doLech, int dich) {
        for (int i = 0; i <= doLech; i++) {
            if (conSo + i == dich) {
                return true;
            }
            if (conSo - i == dich) {
                return true;
            }
        }
        return false;
    }

    protected static ArrayList<String> conf;

    @SuppressWarnings("unchecked")
    public static boolean initConf() {
        conf = new ArrayList<>();
        File file = new File("src/file/sysconf.vsl");
        if (!file.exists()) {
            return false;
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream oip = new ObjectInputStream(fis);
            conf = (ArrayList<String>) oip.readObject();
            oip.close();
            fis.close();
            file_tapCanBo = conf.get(0);
            file_tapNganhDay = conf.get(1);
            file_tapThoiGian = conf.get(2);
            file_quanHeCanBo_nganhDay = conf.get(3);
            file_quanHeCanBo_thoiGian = conf.get(4);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }
}
