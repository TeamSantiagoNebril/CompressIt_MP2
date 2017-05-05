package GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.List;

import javax.swing.SwingWorker;

import core.LoadImage;

public class BackgroundWorker extends SwingWorker<Integer, String>
{
	private File file;
	private CombinationGui gui;
	private int type;
	private Path path;
	private LoadImage compressor;
	private String[] messages = {"Huffman Creation Successful!", "Huffman Update Successful!", "Image Compression Successful!", "Image Decompression Successful!"};
	private File externalFile;
	public BufferedImage g;
	
	public BackgroundWorker(File file, CombinationGui gui, int type, Path path)
	{
		this.file = file;
		this.gui = gui;
		this.type = type;
		this.path = path;
	}
	
	public BackgroundWorker(File file, CombinationGui gui, int type, File externalFile)
	{
		this.file = file;
		this.gui = gui;
		this.type = type;
		this.externalFile = externalFile;
	}
	
    protected Integer doInBackground() throws Exception
    {
    	publish("pics\\loader.gif");
    	switch(type)
    	{
    		case 0:	//create Huffman
    			compressor = new LoadImage(file);
    			compressor.writeHuffmanToFile();
    			break;
    		case 1:	//update Huffman
    			compressor = new LoadImage(file);
    	    	compressor.updateHuffmanFile(path);
    	    //	System.out.println("natapos update");
    			break;
    		case 2: // compress Image
    			compressor = new LoadImage(file);
    			compressor.compress(externalFile);
    		//	System.out.println("natapos compress");
    			break;
    		case 3: // decompress/ open compress image
    			compressor = new LoadImage();
    			g = compressor.deCompress(file, externalFile);
    			//System.out.println("natapos decompress");
    			break;
    	}
    	
    	publish(file.getAbsolutePath());
        return 1;
    }
    
    private String add = "";
    private String temp = "";
    @Override
    protected void process(List< String> chunks){
    	
    	for(String string : chunks)
    	{
    		if(!temp.contains(string))
    		{
    			temp += string;
    			add += string;
    		}
    	}
    	
    	if(add.contains("pics\\loader.gif"))
    	{
    		gui.loadImage("pics\\loader.gif");
			gui.gui.revalidate();
       		gui.gui.repaint();
       		add = "";
    	}
    	else if(add.contains(file.getAbsolutePath()))
    	{
    		gui.loadImage(file.getAbsolutePath());
   			gui.console.addText(messages[type]);
   			gui.enableAll();
   			add = "";
   			if(type == 3)
   			{
   				gui.initialize(g);
				gui.timer.start();
   			}
    	}
    	
    	gui.gui.revalidate();
    	gui.gui.repaint();
    			
    	
      // Messages received from the doInBackground() (when invoking the publish() method)
    }
}