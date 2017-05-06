package GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import javax.swing.SwingWorker;
import core.LoadImage2;

public class BackgroundWorker extends SwingWorker<Integer, String>
{
	private File file;
	private CombinationGui gui;
	private int type;
	private Path path;
	private LoadImage2 compressor;
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
    	
    	switch(type)
    	{
    		case 0:	//create Huffman
    			compressor = new LoadImage2(file);
    			compressor.writeHuffmanToFile();
    			break;
    		case 1:	//update Huffman
    			compressor = new LoadImage2(file);
    	    	compressor.updateHuffmanFile(path);
    			break;
    		case 2: // compress Image
    			compressor = new LoadImage2(file);
    			compressor.compress(externalFile);
    			break;
    		case 3: // decompress/ open compress image
    			compressor = new LoadImage2();
    			g = compressor.deCompress(file, externalFile);
    			break;
    	}
    	
    	
        return 1;
    }
    

    @Override
    protected void done()
    {
    	
    
    	gui.loadImage(file.getAbsolutePath());
   		gui.console.addText(messages[type]);
   		gui.enableAll();
   		if(type == 3)
   		{
   			gui.initialize(g);
			gui.timer.start();
   		}
    	
    	gui.gui.revalidate();
    	gui.gui.repaint();
    			
    	
    }
}