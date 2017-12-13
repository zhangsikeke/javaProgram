package com;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Mem extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int windWidth = 1000;
	private int windHight = 600;
	
	
    private JPanel drawPanel=new DrawPanel(600,300);
	public void init()
	{
		this.setSize(windWidth, windHight);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		//this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane scPanel=new JScrollPane(drawPanel);
		scPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scPanel.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		
		
		this.add(scPanel);
		this.validate();
	}

	class DrawPanel extends JPanel
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		//画图数据仓库
		private  int[][] points;
		private Point startPoint=new Point(40,20);
		private int dw=600;
		private int dh=300;
		private int w=6;
		private int pos=0;
		private int xNum=0;
		public DrawPanel(int wdith,int heigh)
		{
			dw=wdith;
			dh=heigh;
			setBackground(Color.BLACK);
			xNum=(int)(dw/w);
			points=new int[xNum][2];
			// 使用一个线程画图
	        new Thread(new Runnable() 
	        {
	            @Override
	            public void run() {
	                try {
	                	
	                    while (true) 
	                    {
	            		  int y=dh+startPoint.y-((int)(Math.random()*20)+10)*w;
	            		  addValue(y);
	                      repaint();
	                      Thread.sleep(100);
	                    }
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	        }).start();
			
		}
		private void drawGrid(Graphics g)
		{
			g.setColor(new Color(69,169,177));
			
			
			//画竖线
			int lineNumY=(int)(dw/w);
			for (int i = 0; i <lineNumY; i++)
			{
				g.drawLine(startPoint.x+i*w, startPoint.y, startPoint.x+i*w, startPoint.y+dh);
			}
			//画横线
			int lineNumX=(int)(dh/w);
			for (int i = 0; i <=lineNumX; i++)
			{
				g.drawLine(startPoint.x, startPoint.y+i*w, startPoint.x+dw-w, startPoint.y+i*w);
			}
			
			//画刻度
			g.drawLine(10, startPoint.y,10, startPoint.y+dh);
			for (int i = 0; i <=lineNumX; i++)
			{
				g.drawLine(10, startPoint.y+i*w,10+w,startPoint.y+i*w);
				if(i%5==0)
					g.drawString((lineNumX-i)+"", 10+w+2,startPoint.y+(int)(w/2)+1+i*w);
			}
		}
		private void drawMonitorLine(Graphics g)
		{
			g.setColor(Color.GREEN);
			for (int i = 0; i < pos-1; ++i) 
			{
				g.drawLine(points[i][0],points[i][1],points[i+1][0],points[i+1][1]);	
	        }
			for (int i = 0; i < pos; ++i) 
			{
				//System.out.print(points[i][0]+","+points[i][1]+"|");	
	        }
			//System.out.println();
		}
		private synchronized void addValue(int y)
		{
			if(pos>xNum-1)
			{
				int size=points.length;
				for (int i = 0; i <size-1; i++)
				{
					points[i][1]=points[i+1][1];
				}
				points[size-1][1]=y;
				pos--;
			}
			else
			{
				points[pos][0]=startPoint.x+w*pos;
				points[pos][1]=y;
			}
			pos++;
		}
		@Override
		public void paintComponent(Graphics g)
		{
			// 调用父类函数完成初始化（这句话不能少,现在似乎可以不加，最好加上）
			super.paintComponent(g);
			drawGrid(g);
			drawMonitorLine(g);
		}
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Mem m=new Mem();
		m.init();
	}

}
