package org.luopan.client.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;

public class Util {

    private static Logger log = Logger.getLogger(Util.class.getName());

    public static byte[] readFileToByte(String file_path) {
        try {
            FileInputStream in = new FileInputStream(file_path);
            return readFileToByte(in);
        } catch (IOException ex) {
            ex.printStackTrace();
            log.error(ex);
            return null;
        }
    }

    public static byte[] readFileToByte(File file) {
        try {
            FileInputStream in = new FileInputStream(file);
            return readFileToByte(in);
        } catch (IOException ex) {
            ex.printStackTrace();
            log.error(ex);
            return null;
        }
    }

    public static Date StrToDate(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        DateFormat format2;
        format2 = new SimpleDateFormat("yyyy-MM-dd");
        if (str.length() == 8 && !str.contains("-")) {
            format2 = new SimpleDateFormat("yyyyMMdd");
        } else if (str.length() == 6 && !str.contains("-")) {
            format2 = new SimpleDateFormat("yyyyMM");
        }
        format2.setLenient(false);
        try {
            return format2.parse(str);
        } catch (ParseException e) {
            log.error(e);
            return null;
        }
    }

    public static byte[] readFileToByte(FileInputStream in) {
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte[] tmpbuf = new byte[1024];
            int count = 0;
            while ((count = in.read(tmpbuf)) != -1) {
                bout.write(tmpbuf, 0, count);
                tmpbuf = new byte[1024];
            }
            in.close();
            return bout.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
            log.error(ex);
            return null;
        }
    }


    public static String readFileToStr(String file_name) {
        try {
            String privateKey = "";
            BufferedReader in = null;
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file_name)));
            String line = in.readLine();
            while (line != null) {
                privateKey = privateKey + line;
                line = in.readLine();
            }
            in.close();
            return privateKey;
        } catch (IOException ex) {
            log.error(ex);
            return null;
        }
    }

    public static InputStream getInputStreamFromWebPath(String path) {
        URL url = null;
        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        URLConnection conn = null;
        try {
            conn = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream instream = null;
        try {
            instream = conn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instream;
    }

    public static void CopyFile(InputStream fis, File out) throws Exception {
        FileOutputStream fos = new FileOutputStream(out);
        byte[] buf = new byte[1024];
        int i = 0;
        while ((i = fis.read(buf)) != -1) {
            fos.write(buf, 0, i);
        }
        fis.close();
        fos.close();
    }

    public static void CopyFile(File in, File out) throws Exception {
        FileInputStream fis = new FileInputStream(in);
        FileOutputStream fos = new FileOutputStream(out);
        byte[] buf = new byte[1024];
        int i = 0;
        while ((i = fis.read(buf)) != -1) {
            fos.write(buf, 0, i);
        }
        fis.close();
        fos.close();
    }

    // ɾ���ļ������������ݣ��������ļ���
    public static void delAll(File f) throws IOException {
        if (!f.exists())// �ļ��в����ڲ�����
        {
            throw new IOException("ָ��Ŀ¼������:" + f.getName());
        }

        boolean rslt = true;// �����м���
        if (!(rslt = f.delete())) {// �ȳ���ֱ��ɾ��
            // ���ļ��зǿա�ö�١��ݹ�ɾ����������
            File subs[] = f.listFiles();
            for (int i = 0; i <= subs.length - 1; i++) {
                if (subs[i].isDirectory()) {
                    delAll(subs[i]);// �ݹ�ɾ�����ļ�������
                }
                rslt = subs[i].delete();// ɾ�����ļ��б���
            }
            rslt = f.delete();// ɾ����ļ��б���
        }

        if (!rslt) {
            throw new IOException("�޷�ɾ��:" + f.getName());
        }
        return;
    }

    //	 ɾ���ļ������������ݣ��������ļ���
    public static void delAll2(File f) throws IOException {
        if (!f.exists())// �ļ��в����ڲ�����
        {
            throw new IOException("ָ��Ŀ¼������:" + f.getName());
        }

        if (f.isDirectory()) {
            File subs[] = f.listFiles();
            for (int i = 0; i <= subs.length - 1; i++) {
                delAll2(subs[i]);// �ݹ�ɾ�����ļ�������
            }
        }
        f.delete();
    }

    public static void deleteDir(String fileName) {
        File f = new File(fileName);
        try {
            delAll2(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
         * if (!f.exists()) return; if (f.isFile()) { f.delete(); } else if
         * (f.isDirectory()) { if (f.list().length == 0) { f.delete(); } else {
         * for (int i = 0; i < f.list().length; i++) { deleteDir(f.getParent() +
         * "/" + f.list()[i]); } f.delete(); //deleteDir(fileName); } }
         */
    }

    public static void deleteFile(String fileName) {
        File f = new File(fileName);
        if (!f.exists()) {
            return;
        }
        if (f.isFile()) {
            f.delete();
            deleteFile(f.getParent());
        } else if (f.isDirectory() && f.list().length == 0) {
            // && (!f.getName().endsWith(utilPathName))) {
            f.delete();
            deleteFile(f.getParent());
        }
    }
    public static boolean isNum(String code) {
        try {
            Long.parseLong(code);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static Date getYesterday(String nowDate) {
        if (nowDate == null || "".equals(nowDate)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(nowDate));
        } catch (ParseException ex) {

        }
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }
}
