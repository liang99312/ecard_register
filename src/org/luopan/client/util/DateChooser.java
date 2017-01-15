/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luopan.client.util;

import java.util.Date;
import java.util.Calendar;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Administrator
 */
public class DateChooser extends JPanel implements ActionListener,
        ChangeListener {

    int startYear = 1980; // Ĭ�ϡ���С����ʾ���
    int lastYear = 2050; // Ĭ�ϡ������ʾ���
    int width = 200; // ������
    int height = 200; // ����߶�
    Color backGroundColor = Color.gray; // ��ɫ
    // ��������ɫ----------------//
    Color palletTableColor = Color.white; // ������ɫ
    Color todayBackColor = Color.orange; // ���챳��ɫ
    Color weekFontColor = Color.blue; // ��������ɫ
    Color dateFontColor = Color.black; // ��������ɫ
    Color weekendFontColor = Color.red; // ��ĩ����ɫ

    // ��������ɫ------------------//
    Color controlLineColor = Color.pink; // ��������ɫ
    Color controlTextColor = Color.white; // ��������ǩ����ɫ
    Color rbFontColor = Color.white; // RoundBox����ɫ
    Color rbBorderColor = Color.red; // RoundBox�߿�ɫ
    Color rbButtonColor = Color.pink; // RoundBox��ťɫ
    Color rbBtFontColor = Color.red; // RoundBox��ť����ɫ
    JDialog dialog;
    private boolean picked = false;

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }

    public void setDialog(JDialog dialog) {
        this.dialog = dialog;
    }
    JSpinner yearSpin;
    JSpinner monthSpin;
    JSpinner hourSpin;
    JButton[][] daysButton = new JButton[6][7];
    private Date date = null;

    public Date getDate() {
        if (date == null)
            date = new Date();
        return date;
    }

    public void setDate2(Date date) {
        this.date = date;
        Calendar c = getCalendar();
        yearSpin.setValue(c.get(Calendar.YEAR));
        monthSpin.setValue(c.get(Calendar.MONTH) + 1);
        flushWeekAndDay();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DateChooser() {
        setLayout(new BorderLayout());
        //setBorder(new LineBorder(backGroundColor, 2));
        //setBackground(backGroundColor);

        JPanel topYearAndMonth = createYearAndMonthPanal();
        add(topYearAndMonth, BorderLayout.NORTH);
        JPanel centerWeekAndDay = createWeekAndDayPanal();
        add(centerWeekAndDay, BorderLayout.CENTER);

    }

    private JPanel createYearAndMonthPanal() {
        Calendar c = getCalendar();
        int currentYear = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        int currentHour = c.get(Calendar.HOUR_OF_DAY);

        JPanel result = new JPanel();
        result.setLayout(new FlowLayout());
        result.setBackground(controlLineColor);

        yearSpin = new JSpinner(new SpinnerNumberModel(currentYear,
                startYear, lastYear, 1));
        yearSpin.setPreferredSize(new Dimension(48, 20));
        yearSpin.setName("Year");
        yearSpin.setEditor(new JSpinner.NumberEditor(yearSpin, "####"));
        yearSpin.addChangeListener(this);
        result.add(yearSpin);

        JLabel yearLabel = new JLabel("��");
        yearLabel.setForeground(controlTextColor);
        result.add(yearLabel);

        monthSpin = new JSpinner(new SpinnerNumberModel(currentMonth, 1,
                12, 1));
        monthSpin.setPreferredSize(new Dimension(35, 20));
        monthSpin.setName("Month");
        monthSpin.addChangeListener(this);
        result.add(monthSpin);

        JLabel monthLabel = new JLabel("��");
        monthLabel.setForeground(controlTextColor);
        result.add(monthLabel);

        hourSpin = new JSpinner(new SpinnerNumberModel(currentHour, 0, 23,
                1));
        hourSpin.setPreferredSize(new Dimension(35, 20));
        hourSpin.setName("Hour");
        hourSpin.addChangeListener(this);
        result.add(hourSpin);

        JLabel hourLabel = new JLabel("ʱ");
        hourLabel.setForeground(controlTextColor);
        result.add(hourLabel);

        return result;
    }

    private JPanel createWeekAndDayPanal() {
        String colname[] = {"��", "һ", "��", "��", "��", "��", "��"};
        JPanel result = new JPanel();
        // ���ù̶����壬������û����ı�Ӱ���������
        result.setFont(new Font("����", Font.PLAIN, 12));
        result.setLayout(new GridLayout(7, 7));
        result.setBackground(Color.white);
        JLabel cell;

        for (int i = 0; i < 7; i++) {
            cell = new JLabel(colname[i]);
            cell.setHorizontalAlignment(JLabel.RIGHT);
            if (i == 0 || i == 6) {
                cell.setForeground(weekendFontColor);
            } else {
                cell.setForeground(weekFontColor);
            }
            result.add(cell);
        }

        int actionCommandId = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                JButton numberButton = new JButton();
                numberButton.setBorder(null);
                numberButton.setHorizontalAlignment(SwingConstants.RIGHT);
                numberButton.setActionCommand(String.valueOf(actionCommandId));
                numberButton.addActionListener(this);
                numberButton.setBackground(palletTableColor);
                numberButton.setForeground(dateFontColor);
                if (j == 0 || j == 6) {
                    numberButton.setForeground(weekendFontColor);
                } else {
                    numberButton.setForeground(dateFontColor);
                }
                daysButton[i][j] = numberButton;
                result.add(numberButton);
                actionCommandId++;
            }
        }

        return result;
    }

    Point getAppropriateLocation(Frame owner, Point position) {
        Point result = new Point(position);
        Point p = owner.getLocation();
        int offsetX = (position.x + width) - (p.x + owner.getWidth());
        int offsetY = (position.y + height) - (p.y + owner.getHeight());

        if (offsetX > 0) {
            result.x -= offsetX;
        }

        if (offsetY > 0) {
            result.y -= offsetY;
        }

        return result;

    }

    private Calendar getCalendar() {
        Calendar result = Calendar.getInstance();
        result.setTime(getDate());
        return result;
    }

    private int getSelectedYear() {
        return ((Integer) yearSpin.getValue()).intValue();
    }

    private int getSelectedMonth() {
        return ((Integer) monthSpin.getValue()).intValue();
    }

    private int getSelectedHour() {
        return ((Integer) hourSpin.getValue()).intValue();
    }

    private void dayColorUpdate(boolean isOldDay) {
        Calendar c = getCalendar();
        int day = c.get(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, 1);
        int actionCommandId = day - 2 + c.get(Calendar.DAY_OF_WEEK);
        int i = actionCommandId / 7;
        int j = actionCommandId % 7;
        if (isOldDay) {
            //daysButton[i][j].setForeground(dateFontColor);
        } else {
            //daysButton[i][j].setForeground(todayBackColor);
        }
    }

    private void flushWeekAndDay() {
        Calendar c = getCalendar();
        String day = c.get(Calendar.DAY_OF_MONTH)+"";
        Calendar ca = c;
        ca.set(Calendar.DAY_OF_MONTH, 1);
        int maxDayNo = ca.getActualMaximum(Calendar.DAY_OF_MONTH);
        int dayNo = 2 - ca.get(Calendar.DAY_OF_WEEK);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                String s = "";
                if (dayNo >= 1 && dayNo <= maxDayNo) {
                    s = String.valueOf(dayNo);
                }
                daysButton[i][j].setText(s);
                if (daysButton[i][j].getText().equals(day)){
                    daysButton[i][j].setBackground(Color.BLUE);
                    daysButton[i][j].setForeground(Color.white);
                } else {
                    daysButton[i][j].setBackground(Color.white);
                    daysButton[i][j].setForeground(Color.BLACK);
                }
                dayNo++;
            }
        }
        dayColorUpdate(false);
    }

    public void stateChanged(ChangeEvent e) {
        JSpinner source = (JSpinner) e.getSource();
        Calendar c = getCalendar();
        if (source.getName().equals("Hour")) {
            c.set(Calendar.HOUR_OF_DAY, getSelectedHour());
            setDate(c.getTime());
            return;
        }

        dayColorUpdate(true);

        if (source.getName().equals("Year")) {
            c.set(Calendar.YEAR, getSelectedYear());
        } else // (source.getName().equals("Month"))
        {
            c.set(Calendar.MONTH, getSelectedMonth() - 1);
        }
        setDate(c.getTime());
        flushWeekAndDay();
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source.getText().length() == 0) {
            return;
        }
        dayColorUpdate(true);
        source.setForeground(todayBackColor);
        int newDay = Integer.parseInt(source.getText());
        Calendar c = getCalendar();
        c.set(Calendar.DAY_OF_MONTH, newDay);
        setDate(c.getTime());
        picked = true;
        dialog.dispose();
    }
}