package com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class FourPersion extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int winWight = 800;
	private static int winHight = 800;
	private static int circlePix = 40;
	private static int maxX=winWight - circlePix * 2;
	private static int maxY=winHight - circlePix * 4;
	private static int minX=circlePix;
	private static int minY=0;
	
	private static Position[] poses = new Position[]
	{
		    new Position(circlePix, 0),
		    new Position(maxX, 0),
		    new Position(maxX, maxY),
		    new Position(circlePix, maxY)
	};
	private static People[] pers = new People[]
	{
			new People(poses[0].x, poses[0].y,0,"1","1"),
			new People(poses[1].x, poses[1].y,1,"2","2"),
			new People(poses[2].x, poses[2].y,2,"3","3"),
			new People(poses[3].x, poses[3].y,3,"4","4"),
	};
	

	public FourPersion()
	{
		super();
		new Thread()
		{

			@Override
			public void run()
			{
				super.run();
				try
				{
					while (true)
					{
						countPersionPosition();
						repaint();
						sleep(500);
					}
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}.start();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.RED);
		for (int i = 0; i < pers.length; i++)
		{
			g.drawOval(pers[i].x, pers[i].y, circlePix, circlePix);
			g.drawString(pers[i].peoplerNum + "("+pers[i].peoplerPosition+")", pers[i].x + circlePix / 2 - 2, pers[i].y+ circlePix / 2 + 5);
			g.drawString(pers[i].peoplerNum+":is run:"+pers[i].isRun+"",350,300+i*20);
		}
	}

	public static void createFrame()
	{
		JFrame f = new JFrame("�ĸ��˵���Ϸ");

		JButton startBtn = new JButton("��ʼ��Ϸ");
		startBtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				

			}
		});
		f.add(startBtn, BorderLayout.NORTH);
		f.add(new FourPersion(), BorderLayout.CENTER);
		f.setSize(winWight, winHight);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.validate();
	}
	public void debug(String msg)
	{
		//System.out.print("[debug]:");
		//System.out.println(msg);
	}
	public void info(String msg)
	{
		System.out.print("[info]:");
		System.out.println(msg);
	}
	public int getPositionpeopleIndex(String index)
	{
		for (int i = 0; i < pers.length; i++)
		{
			if(pers[i].peoplerPosition.equals(index))
			{
				return i;
			}
		}
		return -1;
	}
	public void countPersionPosition()
	{
		boolean hasPersionRun=false;
		for (int i = 0; i < pers.length; i++)
		{
			if(pers[i].isRun)
			{
				hasPersionRun=true;
				break;
			}
		}
		if(!hasPersionRun)
		{
			debug("��1���˿�ʼ��");
			pers[0].isRun=true;
			pers[0].peoplerPosition="";
			poses[0].hasP=false;
		}
		
		int pIndex=getRunPersionIndex();
		debug(pers[pIndex].peoplerNum+" is move x="+pers[pIndex].x+",y="+pers[pIndex].y);
		if(pers[pIndex].x==poses[1].x && pers[pIndex].y==poses[1].y)
		{
			//����ڶ���λ��
			debug(pers[pIndex].peoplerNum+"����2��λ��");
			if(poses[1].hasP)
			{
				int index=getPersionIndexByPos("2");
				pers[index].isRun=true;
				pers[index].peoplerPosition="";
				
				pers[pIndex].isRun=false;
				pers[pIndex].peoplerPosition="2";
				info(pers[pIndex].peoplerNum+"����2��λ��,����"+pers[index].peoplerNum+"һ��,"+pers[index].peoplerNum+"��ʼ��");
			}
			else
			{
				info(pers[pIndex].peoplerNum+"����2��λ��,2��λ��û����,"+pers[pIndex].peoplerNum+"����һ��������");
			}
		}
		else if(pers[pIndex].x==poses[2].x && pers[pIndex].y==poses[2].y)
		{
			debug(pers[pIndex].peoplerNum+"����3��λ��");
			//���������λ��
			if(poses[2].hasP)
			{
				int index=getPersionIndexByPos("3");
				pers[index].isRun=true;
				pers[index].peoplerPosition="";
				
				pers[pIndex].isRun=false;
				pers[pIndex].peoplerPosition="3";
				info(pers[pIndex].peoplerNum+"����3��λ��,����"+pers[index].peoplerNum+"һ��,"+pers[index].peoplerNum+"��ʼ��");
			}
			else
			{
				info(pers[pIndex].peoplerNum+"����3��λ��,3��λ��û����,"+pers[pIndex].peoplerNum+"����һ��������");
			}
		}
		else if(pers[pIndex].x==poses[3].x && pers[pIndex].y==poses[3].y)
		{
			//������ĸ�λ��
			debug(pers[pIndex].peoplerNum+"����4��λ��");
			if(poses[3].hasP)
			{
				int index=getPersionIndexByPos("4");
				pers[index].isRun=true;
				pers[index].peoplerPosition="";
				
				pers[pIndex].isRun=false;
				pers[pIndex].peoplerPosition="4";
				info(pers[pIndex].peoplerNum+"����4��λ��,����"+pers[index].peoplerNum+"һ��,"+pers[index].peoplerNum+"��ʼ��");
			}
			else
			{
				info(pers[pIndex].peoplerNum+"����4��λ��,4��λ��û����,"+pers[pIndex].peoplerNum+"����һ��������");
			}
		}
		else if(pers[pIndex].x==poses[0].x && pers[pIndex].y==poses[0].y)
		{
			//�����һ��λ��
			debug(pers[pIndex].peoplerNum+"����1��λ��");
			if(poses[0].hasP)
			{
				int index=getPersionIndexByPos("1");
				pers[index].isRun=true;
				pers[index].peoplerPosition="";
				pers[pIndex].isRun=false;
				pers[pIndex].peoplerPosition="1";
				info(pers[pIndex].peoplerNum+"����1��λ��,����"+pers[index].peoplerNum+"һ��,"+pers[index].peoplerNum+"��ʼ��");
			}
			else
			{
				info(pers[pIndex].peoplerNum+"����1��λ��,1��λ��û����,"+pers[pIndex].peoplerNum+"����һ��������");
			}
		}
		int  runPersionIndex=getRunPersionIndex();
		if(pers[runPersionIndex].x==poses[1].x && pers[runPersionIndex].y==poses[1].y)
		{
			//����ڶ���λ��
			pers[runPersionIndex].y+=circlePix;
			
		}
		else if(pers[runPersionIndex].x==poses[2].x && pers[runPersionIndex].y==poses[2].y)
		{
			//���������λ��
			pers[runPersionIndex].x-=circlePix;
			
		}
		else if(pers[runPersionIndex].x==poses[3].x && pers[runPersionIndex].y==poses[3].y)
		{
			//������ĸ�λ��
			pers[runPersionIndex].y-=circlePix;
		}
		else if(pers[runPersionIndex].x==poses[0].x && pers[runPersionIndex].y==poses[0].y)
		{
			//�����һ��λ��
			pers[runPersionIndex].x+=circlePix;
		}
		else if(pers[runPersionIndex].x<maxX && pers[runPersionIndex].y==minY)
		{
			//�˶���λ���Ϸ�
			pers[runPersionIndex].x+=circlePix;
		}
		else if(pers[runPersionIndex].x==maxX && pers[runPersionIndex].y<maxY)
		{
			//�˶���λ���ҷ�
			pers[runPersionIndex].y+=circlePix;
		}
		else if(pers[runPersionIndex].x<maxX && pers[runPersionIndex].y==maxY)
		{
			//�˶���λ���·�
			pers[runPersionIndex].x-=circlePix;
		}
		else if(pers[runPersionIndex].x==minX && pers[runPersionIndex].y<maxY)
		{
			//�˶���λ����
			pers[runPersionIndex].y-=circlePix;
		}
		
		
	}
	
	public int getPersionIndexByPos(String pos)
	{
		for (int i = 0; i < pers.length; i++)
		{
			if(pers[i].peoplerPosition.equals(pos))
			{
				return i;
			}
		}
		debug("λ��"+pos+"������");
		return -1;
	}
	
	public int getRunPersionIndex()
	{
		for (int i = 0; i < pers.length; i++)
		{
			if(pers[i].isRun)
			{
				return i;
			}
		}
		debug("û�����ƶ�");
		return -1;
	}
	
	public static void main(String[] args)
	{
		createFrame();
	}

}

class Position
{
	public int x;
	public int y;
	public boolean hasP=true;//��ǰλ���Ƿ�����
	public Position(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
	}

};

class People
{
	public int x;
	public int y;
	public int index;
	public String peoplerNum;//���
	public String peoplerPosition;//λ��
	public boolean isRun = false;//�Ƿ��߶���Ĭ�ϲ���
	public People(int x, int y, int index_,String peoplerNum, String peoplerPosition)
	{
		super();
		this.x = x;
		this.y = y;
		this.index=index_;
		this.peoplerNum = peoplerNum;
		this.peoplerPosition = peoplerPosition;
	}
};
