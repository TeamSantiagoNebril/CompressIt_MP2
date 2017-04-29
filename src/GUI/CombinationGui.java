package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CombinationGui implements ActionListener
{
	private Gui gui;
	private JPanel centerPanel;
	private JPanel imagePanel;
	private JPanel visualPanel;
	private JPanel functionPanel;
	private ConsoleJPanel console;
	private JSplitPane vertSplitPane;
	private JSplitPane horizontalSplitPane;
	

	private JTextField openFileTextField;
	private JButton openFileBrowse;
	private JButton createNew;
	private JButton updateHuffman;
	private JButton compressImage;
	private JButton openCompressImage;
	private boolean ImageLoaded = false;
	private ImageIcon image;
	private JLabel imageLabel;
    private JScrollPane scroller;
    private JPanel imagePane;
	
	public CombinationGui() throws IOException
	{
		gui = new Gui();
	  
	    
	    centerPanel = new JPanel();
	    centerPanel.setOpaque(false);
	    gui.mainPanel.add(centerPanel, BorderLayout.CENTER);
	    
	    /*Panel fix for of imagePanel and Console*/
	    centerPanel.setLayout(new GridLayout());
		visualPanel = new JPanel();
		visualPanel.setOpaque(false);
	    functionPanel = new JPanel();
	    functionPanel.setBackground(new Color(0f,0f,0f,0.6f));
	    
	    vertSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, visualPanel, functionPanel);
	    vertSplitPane.setVisible(true);
	    vertSplitPane.setOpaque(false);
	    vertSplitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener() {
	    	@Override
	    	public void propertyChange(PropertyChangeEvent pce) {
	    		gui.repaint();
	    	}
	    	});
	    visualPanel.setMinimumSize(new Dimension(1100, 705));
	    functionPanel.setMinimumSize(new Dimension(0,0));
        vertSplitPane.setContinuousLayout(true);
	    vertSplitPane.setResizeWeight(0.5);
	    centerPanel.add(vertSplitPane);
	  
	   
	    visualPanel.setLayout(new GridLayout());
	    console = new ConsoleJPanel(4);
	    imagePanel = new JPanel();
	    horizontalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, imagePanel, console);
	    horizontalSplitPane.setVisible(true);    
	    horizontalSplitPane.setOpaque(false);
	    horizontalSplitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener() {
	    	@Override
	    	public void propertyChange(PropertyChangeEvent pce) {
	    		gui.repaint();
	    	}
	    	});
	    imagePanel.setMinimumSize(new Dimension(1100, 600));
	    console.setMinimumSize(new Dimension(1100,0));
	    horizontalSplitPane.setContinuousLayout(true);
	    horizontalSplitPane.setResizeWeight(0.5);
	    visualPanel.add(horizontalSplitPane);
	 
	    
	    /***************************FunctionPanel adding Components****************/
	    functionPanel.setLayout(new GridLayout(4, 1));
	    JPanel[] buttonPanels = new JPanel[4];
	    for(int i = 0; i < 4; i++)
	    {
	    	buttonPanels[i] = new JPanel();
	    	buttonPanels[i].setOpaque(false);
	    	functionPanel.add(buttonPanels[i]);
	    }
	    
	    buttonPanels[0].setLayout(new GridLayout(5, 1));
	    JLabel menuLabel = new JLabel("Menu");
	    menuLabel.setForeground(Color.ORANGE);
	    menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    menuLabel.setFont(new Font("Arial", Font.BOLD, 27));
	    buttonPanels[0].add(new JLabel(""));
	    buttonPanels[0].add(menuLabel);
	    
	    JLabel openLabel = new JLabel("Open File(.png): ");
	    openLabel.setFont(new Font("Arial", Font.BOLD, 15));
	    openLabel.setForeground(Color.ORANGE);
	    buttonPanels[0].add(openLabel);
	    
	    JPanel inputAt3 = new JPanel();
	    inputAt3.setLayout(new BorderLayout());
	    openFileTextField = new JTextField("C:\\");
	    inputAt3.add(openFileTextField, BorderLayout.CENTER);
	    openFileBrowse = new JButton("Browse");
	    openFileBrowse.setFont(new Font("Arial", Font.BOLD, 13));
	    openFileBrowse.setForeground(Color.WHITE);
	    openFileBrowse.setFocusPainted(false);
	    openFileBrowse.setBackground(new Color(0.1f ,0.1f ,0.1f ,1f));
	    openFileBrowse.setBorderPainted(false);
	    inputAt3.add(openFileBrowse, BorderLayout.EAST);
	    buttonPanels[0].add(inputAt3);
	    
	    
	    buttonPanels[1].setLayout(new GridLayout(5, 1, 0 ,2));
	    JLabel trainHuffmanTreeLabel = new JLabel("Huffman File");
	    trainHuffmanTreeLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    trainHuffmanTreeLabel.setFont(new Font("Arial", Font.BOLD, 17));
	    trainHuffmanTreeLabel.setForeground(Color.ORANGE);
	    buttonPanels[1].add(trainHuffmanTreeLabel);
	    
	    createNew = new JButton("Create");
	    createNew.setFont(new Font("Arial", Font.BOLD, 13));
	    createNew.setHorizontalAlignment(SwingConstants.CENTER);
	    createNew.setForeground(Color.WHITE);
	    createNew.setFocusPainted(false);
	    createNew.setBackground(new Color(0.1f, 0.1f, 0.1f, 1f));
	    createNew.setBorderPainted(false);
	    updateHuffman = new JButton("Update");
	    updateHuffman.setFont(new Font("Arial", Font.BOLD, 13));
	    updateHuffman.setHorizontalAlignment(SwingConstants.CENTER);
	    updateHuffman.setForeground(Color.WHITE);
	    updateHuffman.setFocusPainted(false);
	    updateHuffman.setBackground(new Color(0.1f, 0.1f, 0.1f, 1f));
	    updateHuffman.setBorderPainted(false);
	    JPanel temp1 = new JPanel();
	    temp1.setLayout(new GridLayout(1, 3));
	    temp1.setOpaque(false);
	    temp1.add(new JLabel(""));
	    temp1.add(createNew);
	    temp1.add(new JLabel(""));
	    buttonPanels[1].add(temp1);
	    
	    temp1 = new JPanel();
	    temp1.setLayout(new GridLayout(1, 3));
	    temp1.add(new JLabel(""));
	    temp1.setOpaque(false);
	    temp1.add(updateHuffman);
	    temp1.add(new JLabel(""));
	    buttonPanels[1].add(temp1);
	    buttonPanels[1].add(new JLabel(""));
	    
	    
	    JLabel compressLabel = new JLabel("Image");
	    compressLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    compressLabel.setFont(new Font("Arial", Font.BOLD, 17));
	    compressLabel.setForeground(Color.ORANGE);
	    buttonPanels[1].add(compressLabel);
	    
	    buttonPanels[2].setLayout(new GridLayout(5, 1, 0, 2));
	    
	    
	    compressImage = new JButton("Compress Image");
	    compressImage.setFont(new Font("Arial", Font.BOLD, 14));
	    compressImage.setHorizontalAlignment(SwingConstants.CENTER);
	    compressImage.setForeground(Color.WHITE);
	    compressImage.setFocusPainted(false);
	    compressImage.setBackground(new Color(0.1f, 0.1f, 0.1f, 1f));
	    compressImage.setBorderPainted(false);
	    buttonPanels[2].add(compressImage);
	    
	    openCompressImage = new JButton("Open Compressed Image");
	    openCompressImage.setFont(new Font("Arial", Font.BOLD, 14));
	    openCompressImage.setHorizontalAlignment(SwingConstants.CENTER);
	    openCompressImage.setForeground(Color.WHITE);
	    openCompressImage.setFocusPainted(false);
	    openCompressImage.setBackground(new Color(0.1f, 0.1f, 0.1f, 1f));
	    openCompressImage.setBorderPainted(false);
	    buttonPanels[2].add(openCompressImage);
	    /***************************FunctionPanel adding Components****************/
	    
	    /***************************ImagePanel Configuration***********************/
	    imagePanel.setLayout(new GridLayout());
	    //imagePanel.add(new ImagePanel("pics/default1.png"));
	    loadImage("pics/default2.png");
	    /***************************ImagePanel Configuration***********************/
	    
	    /***************************console Configuration***********************/
	    
	    console.addText("Hello");
	    console.addText("Aerol D. Nebril");
	    console.addText("Bea Santiago");
	    /***************************console Configuration***********************/
	    
	    /*********Adding Listeners****************/
	    openFileTextField.addActionListener(this);
	    openFileBrowse.addActionListener(this);
	    compressImage.addActionListener(this);
	    openCompressImage.addActionListener(this);
	    /*********Adding Listeners****************/
	    
	    gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
		gui.setUndecorated(true);
		gui.setLocationRelativeTo(null);
	    gui.setBackground(new Color(0, 0, 0, 0));
	    gui.setVisible(true);
	    restoreDefault();
	}
	
	public void restoreDefault()
	{
		SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                vertSplitPane.setDividerLocation(1100);
                horizontalSplitPane.setDividerLocation(600);
            }
        });
	}

	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == openFileBrowse)
		{
			File selectedFile;
			JFileChooser fileChooser = null;
			LookAndFeel previousLF = UIManager.getLookAndFeel();
			try
			{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			    fileChooser = new JFileChooser();
			    UIManager.setLookAndFeel(previousLF);
			}
			catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException e) {}
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
			FileNameExtensionFilter filter = new FileNameExtensionFilter( "PNG Images","png");
			fileChooser.setFileFilter(filter);
			fileChooser.setAcceptAllFileFilterUsed(false);
			int result = fileChooser.showOpenDialog(gui);
			if (result == JFileChooser.APPROVE_OPTION)
			{
			    selectedFile = fileChooser.getSelectedFile();
			    loadImage(selectedFile.getAbsolutePath());
				//imagePanel.add(new ImagePanel(selectedFile.getAbsolutePath()));
				console.addText("Browse Button Successful");
			}
		}
		else if(event.getSource() == openFileTextField)
		{
			File f = new File(openFileTextField.getText());
			if(f.exists() && f.getAbsolutePath().contains(".png"))
			{
				loadImage(f.getAbsolutePath());
				 //imagePanel.add(new ImagePanel(f.getAbsolutePath()));
				console.addText("Successful");
			}
			else
			{
				JOptionPane.showMessageDialog(gui
						, "File on Path: '" + f.getAbsolutePath() + "' not found!"
						, "Error"
						, JOptionPane.ERROR_MESSAGE);
			}
			//System.out.println("hello!");
		}
		else if(event.getSource() == createNew)
		{
			
		}
		else if(event.getSource() == updateHuffman)
		{
			
		}
		else if(event.getSource() == compressImage)
		{
			
		}
		else if(event.getSource() == openCompressImage)
		{
			initialize();
			timer.start();
		}
	}
	
	public void loadImage(String filePath)
	{
		imagePanel.removeAll();
		image = new ImageIcon(filePath);
		imageLabel = new JLabel(image);
		imagePane = new JPanel();
		imagePane.add(imageLabel);
		imagePane.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
		LookAndFeel previousLF = UIManager.getLookAndFeel();
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			scroller = new JScrollPane(imagePane);
		    UIManager.setLookAndFeel(previousLF);
		}
		catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException e) {}
		scroller.setAutoscrolls(true);
		imagePanel.add(scroller);
		gui.repaint();
	}
	
	
	private BufferedImage origImage, drawnImage;
	private int column = 0;
	private int row = 0;
	private Timer timer;
	
	public void initialize()
	{
		//drawnImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		origImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		try {
			origImage = ImageIO.read(new File("C:\\Users\\User\\Documents\\cmsc130mp2\\CompressIt_MP2\\pics\\avalancheEffectSha1.png"));
			row = origImage.getWidth();
			column = origImage.getHeight();
			
			drawnImage = new BufferedImage(row, column, BufferedImage.TYPE_INT_ARGB);
			image = new ImageIcon(drawnImage);
			imagePanel.removeAll();
			imageLabel = new JLabel(image);
			imagePane = new JPanel();
			imagePane.add(imageLabel);
			imagePane.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
			LookAndFeel previousLF = UIManager.getLookAndFeel();
			try
			{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				scroller = new JScrollPane(imagePane);
			    UIManager.setLookAndFeel(previousLF);
			}
			catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException e) {}
			scroller.setAutoscrolls(true);
			imagePanel.add(scroller);
			gui.repaint();
			//imagePanel.repaint();
			//imageLabel.repaint();
			 

		} catch (IOException e) {
			System.exit(-1);
			System.out.println("GG");
			e.printStackTrace();
		}
		
		row = 0;
		column = 0;
		this.timer = new Timer(10, new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	for(row = 0; row < origImage.getWidth(); row++)
		    	{
		    		for(column = 0; column < origImage.getHeight(); column++)
		    		{
		    			drawnImage.setRGB(row, column, origImage.getRGB(row, column));
				    	
					       // gui.repaint();

					       /* column++;

					        if (column >= origImage.getWidth())
					        {
					            row++;
					            column = 0;
					        }

					        
					        if (row >= origImage.getHeight())
					        {
					        	//if(row == origImage.getHeight())
					        	//{
					        		
					        		//break;
					        	//}
					        	//else
					        	//{
					        		Timer timer = (Timer)e.getSource();	
					        		timer.stop();
					        		gui.repaint();
							        gui.revalidate();
					        		
					        	//}
					            
					            
					        }*/
		    		}
		    		//this loop is the fastest image display Bee
		    		
			        gui.repaint();
			        gui.revalidate();
			        timer.stop();
	        		//gui.repaint();
			        //gui.revalidate();
			        
		    	}
		    	
		    }
		});
	}
}