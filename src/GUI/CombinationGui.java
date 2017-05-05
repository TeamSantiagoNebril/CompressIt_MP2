package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import core.LoadImage;
import thread.LoadingThread;

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
	private LoadImage compressor;

	private JTextField openFileTextField;
	private JButton openFileBrowse;
	private JButton createNew;
	private JButton updateHuffman;
	private JButton compressImage;
	private JButton openCompressImage;
	private ImageIcon image;
	private JLabel imageLabel;
    private JScrollPane scroller;
    private JPanel imagePane;
    private File selectedFile;
    	
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
	    selectedFile = new File("pics/default2.png");
	    loadImage(selectedFile.getAbsolutePath());
	    /***************************ImagePanel Configuration***********************/
	    
	    /***************************console Configuration***********************/
	    
	    console.addText("Console");
	    console.addText("Aerol D. Nebril");
	    console.addText("Bea Santiago");
	    console.addText("");
	    /***************************console Configuration***********************/
	    
	    /*********Adding Listeners****************/
	    openFileTextField.addActionListener(this);
	    openFileBrowse.addActionListener(this);
	    compressImage.addActionListener(this);
	    openCompressImage.addActionListener(this);
	    createNew.addActionListener(this);
	    updateHuffman.addActionListener(this);
	    
	    MouseAdapter mouseAdapter = new MouseAdapter(){
	    	public void mouseEntered(MouseEvent e)
	    	{
	    		if(e.getSource() == openFileBrowse)
	    		{
	    			openFileBrowse.setBackground(new Color(232, 17, 35));
	    		}
	    		else if(e.getSource() == compressImage)
	    		{
	    			compressImage.setBackground(new Color(232, 17, 35));
	    		}
	    		else if(e.getSource() == openCompressImage)
	    		{
	    			openCompressImage.setBackground(new Color(232, 17, 35));
	    		}
	    		else if(e.getSource() == createNew)
	    		{
	    			createNew.setBackground(new Color(232, 17, 35));
	    		}
	    		else if(e.getSource() == updateHuffman)
	    		{
	    			updateHuffman.setBackground(new Color(232, 17, 35));
	    		}
	    	}
	    	
	    	public void mouseExited(MouseEvent e)
	    	{
	    		if(e.getSource() == openFileBrowse)
	    		{
	    			openFileBrowse.setBackground(new Color(0.1f, 0.1f, 0.1f, 1f));
	    		}
	    		else if(e.getSource() == compressImage)
	    		{
	    			compressImage.setBackground(new Color(0.1f, 0.1f, 0.1f, 1f));
	    		}
	    		else if(e.getSource() == openCompressImage)
	    		{
	    			openCompressImage.setBackground(new Color(0.1f, 0.1f, 0.1f, 1f));
	    		}
	    		else if(e.getSource() == createNew)
	    		{
	    			createNew.setBackground(new Color(0.1f, 0.1f, 0.1f, 1f));
	    		}
	    		else if(e.getSource() == updateHuffman)
	    		{
	    			updateHuffman.setBackground(new Color(0.1f, 0.1f, 0.1f, 1f));
	    		}
	    	}
	    };
	    
	    openFileTextField.addMouseListener(mouseAdapter);
	    openFileBrowse.addMouseListener(mouseAdapter);
	    compressImage.addMouseListener(mouseAdapter);
	    openCompressImage.addMouseListener(mouseAdapter);
	    createNew.addMouseListener(mouseAdapter);
	    updateHuffman.addMouseListener(mouseAdapter);
	    
	    /*********Adding Listeners****************/
	    
	    try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException e) {}
	    
	    
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

	
	private LoadingThread thread;
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == openFileBrowse)
		{
			try
			{
				selectedFile = browse("PNG Images","png");
				console.addText("Loading Image");
				gui.repaint();
				loadImage(selectedFile.getAbsolutePath());
				console.addText("Image successfully loaded");
				gui.repaint();

			}
			catch(NullPointerException e)
			{
				JOptionPane.showConfirmDialog(null, "Opening Image Cancelled", "Open Image", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			}
			//thread = new LoadingThread(console, gui, "Loading");
			//thread.start();
			
		}
		else if(event.getSource() == openFileTextField)
		{
			selectedFile = new File(openFileTextField.getText());
			if(selectedFile.exists() && selectedFile.getAbsolutePath().contains(".png"))
			{
				loadImage(selectedFile.getAbsolutePath());
				console.addText("Image Successfuly Loaded");
				gui.repaint();
			}
			else
			{
				JOptionPane.showMessageDialog(gui
						, "File on Path: '" + selectedFile.getAbsolutePath() + "' not found!"
						, "Error"
						, JOptionPane.ERROR_MESSAGE);
			}
			//thread.deActivate(true);
		}
		else if(event.getSource() == createNew)
		{
			try
			{
				compressor = new LoadImage(selectedFile);
				console.addText("Writing Huffman File");
				gui.repaint();
				thread = new LoadingThread(console, gui, "Writing Huffman File"); // thread
				thread.start();
				compressor.writeHuffmanToFile();
				thread.deActivate(true);
				console.addText("Successfully wrote Huffman Distribution to " + selectedFile.getAbsolutePath().substring(0, selectedFile.getAbsolutePath().lastIndexOf('.')) + ".HUFF");
				gui.repaint();
			}
			catch(NullPointerException e)
			{
				JOptionPane.showConfirmDialog(null, "Huffman Creation Cancelled", "Huffman Creation", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			}
			
		}
		else if(event.getSource() == updateHuffman)
		{
			try
			{
				compressor = new LoadImage(selectedFile);
				//System.out.println(selectedFile + "gg");
				JOptionPane.showConfirmDialog(null, "Please select the Huffman File to be Updated", "Update", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
				File huffmanFile = browse("Huffman File", "HUFF");
				Path filePath = Paths.get(huffmanFile.getAbsolutePath());
				thread = new LoadingThread(console, gui, "Updating Huffman File"); // thread
				thread.start();
				compressor.updtateHuffmanFile(filePath);
				thread.deActivate(true);
				console.addText("Successfully updated Huffman File");
				gui.repaint();
			}
			catch(NullPointerException e)
			{
				JOptionPane.showConfirmDialog(null, "Huffman Update Cancelled", "Huffman Update", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			}
			
		}
		else if(event.getSource() == compressImage)
		{
			try
			{
				compressor = new LoadImage(selectedFile);
				File huffmanFile = browse("Huffman File", "HUFF");
				console.addText("Compressing Currently Rendered Image");
				gui.repaint();
				thread = new LoadingThread(console, gui, "Compressing Image"); // thread
				thread.start();
				compressor.compress(huffmanFile);
				thread.deActivate(true);
				console.addText("Image successfully Compressed!");
				gui.repaint();
			}
			catch(NullPointerException e)
			{
				JOptionPane.showConfirmDialog(null, "Huffman Compression Cancelled", "Huffman Compression", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			}
			
		}
		else if(event.getSource() == openCompressImage)
		{
			try
			{
				JOptionPane.showConfirmDialog(null, "Please select the file to be decompressed", "Decompression", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
				File tobeDecompressed = browse("Compressed Image File", "San");
				JOptionPane.showConfirmDialog(null, "Please select the Huffman File to be used", "Decompression", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
				File huffman = browse("Huffman File", "Huff");
				compressor = new LoadImage();
				thread = new LoadingThread(console, gui, "Decompressing Compressed Image"); // thread
				thread.start();
				BufferedImage g = compressor.deCompress(tobeDecompressed, huffman);
				thread.deActivate(true);
				console.addText("Image successfully Decompressed!");
				gui.repaint();
				initialize(g);
				timer.start();
				gui.repaint();
			}
			catch(NullPointerException e)
			{
				JOptionPane.showConfirmDialog(null, "Opening Image Cancelled", "Opening Image", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			}
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
	
	public void initialize(BufferedImage img)
	{
		origImage = img;
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
				    	
		    		}
		    		//this loop is the fastest image display Bee
		    		
			        gui.repaint();
			        gui.revalidate();
			        timer.stop();
			        
		    	}
		    	
		    }
		});
	}
	
	
	public File browse(String type, String extension)
	{
		File selected = null;
		JFileChooser fileChooser = null;
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter( type,extension);
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		int result = fileChooser.showOpenDialog(gui);
		if (result == JFileChooser.APPROVE_OPTION)
		{
		    selected = fileChooser.getSelectedFile();
		}
		return selected;
	}
	
	public String browseDirectory()
	{
		String directory = null;
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
		chooser.setDialogTitle("Select Folder");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		int result = chooser.showOpenDialog(gui);
		if (result == JFileChooser.APPROVE_OPTION)
		{
		    directory = chooser.getCurrentDirectory().getAbsolutePath();
		}
		return directory;
	}
}