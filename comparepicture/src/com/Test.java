package com;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
 
public class Test extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Integer> values;                      // ������յ������ݵ�����.
    private static final int MAX_VALUE = 200;          // ���յ������ݵ����ֵ.
    private static final int MAX_COUNT_OF_VALUES = 50; // ��ౣ�����ݵĸ���.
 
    public Test() {
        values = Collections.synchronizedList(new ArrayList<Integer>());
 
        // ʹ��һ���߳�ģ���������.
        new Thread(new Runnable() {
            @Override
            public void run() {
                Random rand = new Random();
 
                try {
                    while (true) {
                        addValue(rand.nextInt(MAX_VALUE)); // ����һ�����ݣ���ģ����ղ��ŵ�������.
                        repaint();
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
 
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
 
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 
        int w = getWidth();
        int h = getHeight();
        int xDelta = w / MAX_COUNT_OF_VALUES;
        int length = values.size();
        System.out.println(length);
        for (int i = 0; i < length - 1; ++i) {
            g2d.drawLine(xDelta * (MAX_COUNT_OF_VALUES - length + i), normalizeValueForYAxis(values.get(i), h),
                    xDelta * (MAX_COUNT_OF_VALUES - length + i + 1), normalizeValueForYAxis(values.get(i + 1), h));
        }
    }
 
    /**
     * ���յ������ݷ����ڴ�.
     * @param value
     */
    private void addValue(int value) {
        // ѭ����ʹ��һ���������ݵĿռ�.
        // �����ʵ��һ��ѭ�����飬������͵����ʹ��ArrayList.
        if (values.size() > MAX_COUNT_OF_VALUES) {
            values.remove(0);
        }
 
        values.add(value);
    }
 
    /**
     * ��һ��y�᷽���ֵ. ʹ��value��y���ֵΪ[0, height]֮��.
     *
     * @param value
     * @param height
     * @return
     */
    private int normalizeValueForYAxis(int value, int height) {
        return (int) ((double) height / MAX_VALUE * value);
    }
 
    private static void createGuiAndShow() {
        JFrame frame = new JFrame("");
 
        frame.getContentPane().add(new Test());
 
        // Set frame's close operation and location in the screen.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        createGuiAndShow();
    }
}