package com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;

public class ComparePic extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel northPanel = null;
	private JPanel centerPanel = null;
	private JPanel showPicPanl1 = null;
	private JPanel showPicPanl2 = null;
	private DropLabel showPicLab1 = null;
	private DropLabel showPicLab2 = null;
	private JPanel southPanel = null;
	private JMenuBar menu = null;
	private String pic1Path = "";
	private String pic2Path = "";
	private JLabel pixCutSizeLab = null;
	private JTextField pixCutSizeTxt = null;
	private JLabel pixCompareTimeLab = null;
	private JTextField pixCompareTimeTxt = null;
	private JLabel difChunkLab = null;
	private JTextField difChunkTxt = null;
	private JLabel compareReultLab = null;
	private JButton startCompare = null;
	private String lastPicFileDir = null;
	private JScrollPane scroolPicLeft = null;

	public void initUI() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException
	{
		initGlobalFont();
		UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		UIManager.put("swing.boldMetal", false);
		setTitle("图片差异对比工具");
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JMenu menuFile = new JMenu("文件");
		JMenuItem itmPic1 = new JMenuItem("添加对比图");
		JMenuItem itmPic2 = new JMenuItem("添加标准图");
		JMenuItem itmComPare = new JMenuItem("开始对比");
		JMenuItem itmExit = new JMenuItem("退出");
		menuFile.add(itmPic1);
		menuFile.add(itmPic2);
		menuFile.add(itmComPare);
		menuFile.add(itmExit);
		addLoadPicEvent(itmPic1);
		addLoadPicEvent(itmPic2);

		JMenu menuHelp = new JMenu("帮助");
		JMenuItem itmAbout = new JMenuItem("关于");
		menuHelp.add(itmAbout);

		menu = new JMenuBar();
		menu.add(menuFile);
		menu.add(menuHelp);

		setJMenuBar(menu);

		startCompare = new JButton("开始对比");
		pixCutSizeLab = new JLabel("对比粒度");
		pixCutSizeTxt = new JTextField(5);
		pixCutSizeTxt.setText("10");
		pixCompareTimeLab = new JLabel("对比用时");
		pixCompareTimeTxt = new JTextField(5);
		pixCompareTimeTxt.setEditable(false);
		difChunkLab = new JLabel("差异块个数");
		difChunkTxt = new JTextField(5);
		difChunkTxt.setEditable(false);
		compareReultLab = new JLabel("");

		addCompareEvent(itmComPare, startCompare);

		northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		northPanel.add(startCompare);
		northPanel.add(pixCutSizeLab);
		northPanel.add(pixCutSizeTxt);
		northPanel.add(pixCompareTimeLab);
		northPanel.add(pixCompareTimeTxt);
		northPanel.add(difChunkLab);
		northPanel.add(difChunkTxt);
		northPanel.add(compareReultLab);

		centerPanel = new JPanel(new GridLayout(1, 2));
		centerPanel.setBorder(BorderFactory.createTitledBorder("图片展示区"));
		showPicPanl1 = new JPanel(new BorderLayout());
		showPicPanl1.setBorder(BorderFactory.createTitledBorder("对比图"));
		showPicLab1 = new DropLabel();
		scroolPicLeft = new JScrollPane(showPicLab1);
		showPicPanl1.add(scroolPicLeft);
		showPicPanl2 = new JPanel(new BorderLayout());
		showPicPanl2.setBorder(BorderFactory.createTitledBorder("标准图"));
		showPicLab2 = new DropLabel();
		JScrollPane scroolPic2 = new JScrollPane(showPicLab2);
		showPicPanl2.add(scroolPic2);
		centerPanel.add(showPicPanl1);
		centerPanel.add(showPicPanl2);
		southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		validate();
		setVisible(true);
	}

	private static void initGlobalFont()
	{
		FontUIResource fontUIResource = new FontUIResource(new Font("宋体", Font.PLAIN, 14));
		for (Enumeration<?> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();)
		{
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource)
			{
				UIManager.put(key, fontUIResource);
			}
		}
	}

	private void addLoadPicEvent(JMenuItem itmPic)
	{
		itmPic.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jfc.setFileFilter(new FileNameExtensionFilter("图片", "bmp", "jpg", "jpeg", "gif", "png", "ico"));
				jfc.setApproveButtonText("确定");
				jfc.setDialogTitle("图片选择对话框");
				if (lastPicFileDir != null)
				{
					jfc.setCurrentDirectory(new File(lastPicFileDir));
				}
				jfc.showOpenDialog(ComparePic.this);

				File f = jfc.getSelectedFile();
				if (f.isDirectory())
				{
					lastPicFileDir = f.getAbsolutePath();
				} else if (f.isFile())
				{
					System.out.println("文件:" + f.getAbsolutePath());
					String picFilePath = f.getAbsolutePath();
					File file = new File(picFilePath);
					Image image = null;
					try
					{
						image = ImageIO.read(file);
					} catch (IOException e1)
					{
						e1.printStackTrace();
					}
					if (e.getSource() == menu.getMenu(0).getMenuComponent(0))
					{
						ImageIcon icon = new ImageIcon(image.getScaledInstance(showPicLab1.getWidth(),
								showPicLab1.getHeight(), Image.SCALE_SMOOTH));
						showPicLab1.setIcon(icon);
						showPicLab1.setFilePath(picFilePath);
						pic1Path = picFilePath;
						addLabList(showPicLab1,  new ImageIcon(image), icon);
					} else if (e.getSource() == menu.getMenu(0).getMenuComponent(1))
					{
						ImageIcon icon = new ImageIcon(image.getScaledInstance(showPicLab2.getWidth(),
								showPicLab2.getHeight(), Image.SCALE_SMOOTH));
						showPicLab2.setIcon(icon);
						showPicLab2.setFilePath(picFilePath);
						pic2Path = picFilePath;
						//addLabList(showPicLab2,  new ImageIcon(image), icon);
					}
					lastPicFileDir = f.getParent();
				}

			}
		});
	}

	private void startComppare()
	{
		pic1Path = showPicLab1.getFilePath();
		pic2Path = showPicLab2.getFilePath();
		if (pic1Path==null || pic2Path==null )
		{
			JOptionPane.showMessageDialog(this, "请先添加标准图片和对比图片后再对比", "错误", JOptionPane.WARNING_MESSAGE);
			return;
		}
		File f1 = new File(pic1Path);
		File f2 = new File(pic2Path);
		if (!f1.isFile())
		{
			JOptionPane.showMessageDialog(this, "请先添加对比图片后再对比", "错误", JOptionPane.WARNING_MESSAGE);
			return;
		} else if (!f2.isFile())
		{
			JOptionPane.showMessageDialog(this, "先添加标准图片后再对比", "错误", JOptionPane.WARNING_MESSAGE);
			return;
		}
		// Image image1=null;
		Image image2 = null;
		BufferedImage b1 = null;
		BufferedImage b2 = null;
		try
		{
			// image1 = ImageIO.read(f1);
			image2 = ImageIO.read(f2);
			b1 = ImageIO.read(f1);
			b2 = ImageIO.read(f2);
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
		
		
		Graphics2D g2d = (Graphics2D) image2.getGraphics();
		g2d.setColor(Color.RED);

		// g2d.drawRect(20, 20,10, 10);

		int cutWigh = Integer.parseInt(pixCutSizeTxt.getText());
		int cutHigh = Integer.parseInt(pixCutSizeTxt.getText());
		List<int[]> difRectList = comparePic(b1, b2, cutHigh, cutWigh);
		if (difRectList.size() > 0)
		{
			difChunkTxt.setText(difRectList.size() + "");
			compareReultLab.setText("对比结论:不同");
		} else
		{
			difChunkTxt.setText("0");
			compareReultLab.setText("对比结论:相同");
		}

		drawRectByPoint(g2d, difRectList, cutHigh, cutWigh);
		
		Image tmpImage=image2.getScaledInstance(showPicLab2.getWidth(), showPicLab2.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iconMix = new ImageIcon(tmpImage);
		ImageIcon iconMax = new ImageIcon(image2);
		showPicLab2.setIcon(iconMix);
		addLabList(showPicLab2, iconMax, iconMix);
	}

	private void addLabList(final DropLabel lab,final ImageIcon maxIame,final ImageIcon normalImage)
	{
		lab.addMouseListener(new MouseListener()
		{
			
			@Override
			public void mouseReleased(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (lab.max)
				{
					lab.setIcon(normalImage);
					lab.max=false;
				}
				else
				{
					lab.setIcon(maxIame);
					lab.max=true;
				}
				
			}
		});
		
	}
	private void addCompareEvent(JMenuItem itmPic, JButton startCompare)
	{
		itmPic.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				startComppare();
			}
		});
		startCompare.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				startComppare();
			}
		});
	}

	public void init()
	{
		this.setTitle("图片差异对比");
		setLayout(new GridLayout(1, 2));
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		File file1 = new File("./sshot-1.bmp");
		File file2 = new File("./sshot-2.bmp");
		// file=new File("./1.jpg");
		Image image1 = null;
		Image image2 = null;
		BufferedImage b1 = null;
		BufferedImage b2 = null;
		try
		{
			image1 = ImageIO.read(file1);
			image2 = ImageIO.read(file2);
			b1 = ImageIO.read(file1);
			b2 = ImageIO.read(file2);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ImageIcon icon1 = new ImageIcon(image1);
		ImageIcon icon2 = new ImageIcon(image2);
		Graphics2D g2d = (Graphics2D) image2.getGraphics();
		g2d.setColor(Color.RED);

		// g2d.drawRect(20, 20,10, 10);

		int cutWigh = 10;
		int cutHigh = 10;
		JLabel label = new JLabel(icon1);
		JScrollPane scroll1 = new JScrollPane(label);
		JLabel labe2 = new JLabel(icon2);
		JScrollPane scroll2 = new JScrollPane(labe2);
		add(scroll1);
		add(scroll2);

		List<int[]> difRectList = comparePic(b1, b2, cutHigh, cutWigh);
		drawRectByPoint(g2d, difRectList, cutHigh, cutWigh);

		validate();
		setVisible(true);
	}

	public List<int[]> comparePic(BufferedImage b1, BufferedImage b2, int cutHigh, int cutWight)
	{
		long startTime = System.currentTimeMillis();
		List<ArrayList<BufferedImage>> imageList1 = getImageList(b1, cutHigh, cutWight);
		List<ArrayList<BufferedImage>> imageList2 = getImageList(b2, cutHigh, cutWight);
		List<int[]> difPointList = new ArrayList<int[]>();
		int size1 = imageList1.size();
		int size2 = imageList2.size();
		for (int row = 0; row < size2; row++)
		{
			ArrayList<BufferedImage> colList = imageList2.get(row);
			int rowColSize1 = colList.size();
			for (int col = 0; col < rowColSize1; col++)
			{
				int x = col * cutWight;
				int y = row * cutHigh;
				if (row < size1 && col < imageList1.get(row).size())
				{
					BufferedImage cut1Image = imageList1.get(row).get(col);
					BufferedImage cut2Image = imageList2.get(row).get(col);
					// System.out.println("x="+row*cutWight+",y="+col*cutHigh+":");;
					boolean isEqual = comPareRGB(getImageGRB(cut1Image), getImageGRB(cut2Image));
					if (!isEqual)
					{
						difPointList.add(new int[]
						{ x, y });
					}
				} else
				{
					difPointList.add(new int[]
					{ x, y });
				}
			}
		}
		/*
		 * for(int col=0;col<size1;col++) { ArrayList<BufferedImage>
		 * colList=imageList1.get(col); int rowSize1=colList.size(); for(int
		 * row=0;row<rowSize1;row++) { int x=row*cutWight; int y=col*cutHigh;
		 * if(col<=size2 && imageList2.get(col).size()<=rowSize1) {
		 * BufferedImage cut1Image=imageList1.get(col).get(row); BufferedImage
		 * cut2Image=imageList2.get(col).get(row);
		 * //System.out.println("x="+row*cutWight+",y="+col*cutHigh+":");;
		 * boolean
		 * isEqual=comPareRGB(getImageGRB(cut1Image),getImageGRB(cut2Image));
		 * if(!isEqual) { difPointList.add(new int[]{x,y}); } } else {
		 * difPointList.add(new int[]{x,y}); } } }
		 */
		long endTime = System.currentTimeMillis();
		if (pixCompareTimeTxt != null)
		{
			pixCompareTimeTxt.setText((endTime - startTime) + "ms");
		}

		System.out.println("不同的块数目：" + difPointList.size());
		return difPointList;
	}

	public void drawRectByPoint(Graphics2D g2d, List<int[]> difPointList, int cutHigh, int cutWight)
	{
		for (int[] is : difPointList)
		{
			g2d.drawRect(is[0], is[1], cutWight, cutHigh);
			System.out.println("draw diff:x=" + is[0] + ",y=" + is[1]);
		}
	}

	public List<ArrayList<BufferedImage>> getImageList(BufferedImage b, int cutHigh, int cutWight)
	{
		int imageHeigh = b.getHeight();
		int imageWight = b.getWidth();
		List<ArrayList<BufferedImage>> imageList = new ArrayList<ArrayList<BufferedImage>>();
		for (int y = 0; y < imageHeigh; y += cutHigh)
		{
			ArrayList<BufferedImage> rowImageList = new ArrayList<BufferedImage>();
			for (int x = 0; x < imageWight; x += cutWight)
			{
				// System.out.println(imageHeigh+","+imageWight+","+x+","+y);
				int tmpCutWight = cutWight;
				int tmpCutHigh = cutHigh;
				if (imageWight - x < tmpCutWight)
				{
					tmpCutWight = imageWight - x;
				}
				if (imageHeigh - y < tmpCutHigh)
				{
					tmpCutHigh = imageHeigh - y;
				}
				rowImageList.add(b.getSubimage(x, y, tmpCutWight, tmpCutHigh));
			}
			imageList.add(rowImageList);
		}
		System.out.println("图像矩阵");
		for (int row = 0; row < imageList.size(); row++)
		{
			int colNum = imageList.get(row).size();
			for (int col = 0; col < colNum; col++)
			{
				System.out.print("# ");
			}
			System.out.println();
			// System.out.println("第"+(row+1)+"行有"+colNum+"块");
		}
		System.out.println("----------------------------------------------------------");
		return imageList;
	}

	public String arratToString(int[] num)
	{
		String s = "";
		for (int i : num)
		{
			s += i;
		}
		return s;
	}

	public boolean comPareRGB(int[][] rgb1, int[][] rgb2)
	{
		int totalRow1 = rgb1.length;
		int totalRow2 = rgb2.length;
		if (totalRow1 != totalRow2)
		{
			return false;
		}
		for (int row = 0; row < totalRow1; row++)
		{

			String rgb1_ = arratToString(rgb1[row]);
			String rgb2_ = arratToString(rgb2[row]);
			if (!rgb1_.equals(rgb2_))
			{
				return false;
			}
			/*
			 * if(rgb2.length!=totalRow) { return false; } int
			 * rowTotalCol=rgb1[row].length; for(int
			 * rowCol=0;rowCol<rowTotalCol;rowCol++) {
			 * if(rgb2[row].length!=rowCol) { return false; } else
			 * if(rgb2[row][rowCol]!=rgb1[row][rowCol]) { return false; } }
			 */
		}

		return true;
	}

	public int[][] getImageGRB(BufferedImage bfImage)
	{
		int width = bfImage.getWidth();
		int height = bfImage.getHeight();
		int[][] result = new int[height][width];
		for (int h = 0; h < height; h++)
		{
			for (int w = 0; w < width; w++)
			{
				// 使用getRGB(w,
				// h)获取该点的颜色值是ARGB，而在实际应用中使用的是RGB，所以需要将ARGB转化成RGB，即bufImg.getRGB(w,
				// h) & 0xFFFFFF。
				result[h][w] = bfImage.getRGB(w, h) & 0xFFFFFF;
			}
		}
		return result;
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException
	{
		// TODO Auto-generated method stub
		// new ComparePic().init();
		new ComparePic().initUI();
	}
}

class DropLabel extends JLabel implements DropTargetListener
{
	private static final long serialVersionUID = 1L;
	private String filePath = null;
	private String tmpFilePath = null;
	public boolean max=false;

	public DropLabel()
	{
		new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, this, true);
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	@Override
	public void drop(DropTargetDropEvent dtde)
	{
		if (tmpFilePath != null)
		{
			Image image = null;
			try
			{
				image = ImageIO.read(new File(tmpFilePath));
				ImageIcon icon = new ImageIcon(image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));
				setIcon(icon);
				setFilePath(tmpFilePath);
			} catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}
		tmpFilePath = null;
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde)
	{
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde)
	{
		DataFlavor[] dataFlavors = dtde.getCurrentDataFlavors();
		if (dataFlavors[0].match(DataFlavor.javaFileListFlavor))
		{
			try
			{
				Transferable tr = dtde.getTransferable();
				Object obj = tr.getTransferData(DataFlavor.javaFileListFlavor);
				@SuppressWarnings("unchecked")
				List<File> files = (List<File>) obj;
				int size = files.size();
				for (int i = 0; i < size;)
				{
					String path = files.get(i).getAbsolutePath();
					tmpFilePath = path;
					break;
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde)
	{
	}

	@Override
	public void dragExit(DropTargetEvent dte)
	{

	}
}