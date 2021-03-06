package core;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.io.FileWriter;
import java.io.FileReader;
import javax.imageio.ImageIO;
import java.io.BufferedReader;

public class LoadImage
{
	private BufferedImage img = null;
	private int temporary[];
	private int rgb[];
	private int freq[][];
	private Tree first;
	private int count = 0;
	private int RGB[];
	private String bit[];
	private int ctr = 0;
	private int counter = 0;
	private int charCounter = 0;
	private Tree last;
	private Node lastNode = null;
	private String lastBin = "";
	private int imageWidth;
	private int imageHeight;
	private File file;
	
	public LoadImage(File file)
	{
		this.file = file;
		first = null;
	}
	
	public LoadImage()
	{
		first = null;
	}

	
	public void getImage(){
		try 
		{
		    img = ImageIO.read(file);
		    imageWidth = img.getWidth();
		    imageHeight = img.getHeight();
		} catch (IOException e)
		{
			System.err.println("Error");
		}
	}

	public void extractPixelInformation()
	{
		try
		{
			rgb = new int[img.getHeight()*img.getWidth()];
			temporary= new int[img.getHeight()*img.getWidth()];
			int temp;
			for(int a = 0; a < img.getHeight(); a++)
			{
				for(int b = 0; b < img.getWidth(); b++)
				{
					temp = img.getRGB(b,a);
					temporary[counter] = temp;
					rgb[counter++] = temp;
				}
			}
		}catch(Exception e){}
	}

	public void getFrequencies()
	{
		freq = new int[img.getHeight()*img.getWidth()][2];
		Arrays.sort(rgb);
		freq[0][0] = rgb[0];
		freq[0][1] = 1;
		for(int a = 1; a < img.getHeight()*img.getWidth(); a++)
		{
			if(rgb[a] == freq[count][0])
			{
				freq[count][1]++;
			}else
			{
				freq[++count][0] = rgb[a];
				freq[count][1] = 1;
			}

		}
		count++;
		
		freq = Arrays.copyOfRange(freq, 0, count);
		Comparator<int[]> arrayComparator = new Comparator<int[]>()
		{
	        @Override
	        public int compare(int[] o1, int[] o2) 
	        {
	            return o1[1]-o2[1];
	        }
	    };
		Arrays.sort(freq, arrayComparator);
	}
	
	public void makeNodes()
	{
		for(int a = 0;a < count; a++)
		{
			Node node = new Node(freq[a][0], freq[a][1]);
			node.isLeaf = true;
			Tree tree = new Tree(node);
			insert(tree);
		}
	}

	public void insert(Tree tree)
	{
		if(first == null)
		{
			first = tree;
			last = first;
		}else
		{
			Tree current = first;
			while(current != null)
			{
				if(tree.root.getFrequency() >= current.root.getFrequency())
				{
					if(current == first)
					{
						first = tree;
					}else
					{
						current.previous.next = tree;
					}
					tree.previous = current.previous;
					tree.next = current;
					current.previous = tree;
					break;
				}
				if(current.next != null)
				{
					current = current.next;
				}else
				{
					last = tree;
					current.next = tree;
					tree.previous = current;
					break;
				}
			}
		}
	}

	public Tree dequeue()
	{
		Tree temp = last;
		if(last!= first)
		{
			last = last.previous;
			last.next = null;
		}else
		{
			first = null;
			last = null;
		}
		return temp;
	}

	public void generateTree()
	{
		Tree temp;
		Tree temp2;

		while(true)
		{
			temp = dequeue();
			temp2 = dequeue();
			if(temp != null && temp2 != null)
			{
				Node node = new Node('\0', temp.root.getFrequency() + temp2.root.getFrequency());
				node.setLeft(temp.root);
				node.setRight(temp2.root);
				Tree tree = new Tree(node);
				insert(tree);
			}else
			{
				if(first == null)
				{
					first = temp;
				}
				break;
			}
		}
		RGB = new int[count];
		bit = new String[count];
	}

	public void traverse(Node node)
	{
		if(node != null)
		{
			if(node.left != null)
			{
				node.left.setBit(node.getBit() + "0");
				traverse(node.left);
			}
			if(node.right != null)
			{
				node.right.setBit(node.getBit() + "1");
				traverse(node.right);
			}
			if(node.left == null && node.right == null)
			{
				RGB[ctr] = node.getRGBValue();
				bit[ctr] = node.getBit();
				ctr++;
			}
		}
	}
	
	public void writeHuffmanToFile()
	{
		
		getImage();
		extractPixelInformation();
		getFrequencies();
		String fPath = file.getAbsolutePath();
		File f = new File(fPath.substring(0, fPath.lastIndexOf('.')) + ".HUFF");
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			FileWriter fw = new FileWriter(f, false);
			for(int i = 0; i < freq.length; i++)
			{
				fw.write( freq[i][0]+"_"+ freq[i][1] + "\r");
				fw.flush();
			}
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void readHuffmanFile(File fileToRead)
	{
		FileReader fr;
		try
		{
			fr = new FileReader(fileToRead.getAbsolutePath());
			
			BufferedReader bufferedReader = new BufferedReader(fr);
			String line;
			try 
			{
				while ((line = bufferedReader.readLine()) != null)
				{
					count++;
					String[] lineValues = line.split("_");
					int[] values = new int[2];
					values[0] = Integer.parseInt(lineValues[0]);
					values[1] = Integer.parseInt(lineValues[1]);
					
					Node node = new Node(values[0], values[1]);
					node.isLeaf = true;
					Tree tree = new Tree(node);
					insert(tree);
					
				}
				
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateHuffmanFile(Path fileToBeUpdated)
	{
		getImage();
		extractPixelInformation();
		getFrequencies();
		Path filePath = fileToBeUpdated;
		ArrayList<String> fileContent;
		try 
		{
			fileContent = new ArrayList<String>(Files.readAllLines(filePath, StandardCharsets.US_ASCII));
			boolean seen = false;
			for (int i = 0; i < freq.length; i++) 
			{
			   for(int j = 0; j < fileContent.size(); j++)
			   {
				   String[] oldValues = fileContent.get(j).split("_");
				   if(freq[i][0] == Integer.parseInt(oldValues[0]))
				   {
					   int k  = Integer.parseInt(oldValues[1]);
					   k += freq[i][1];
					   fileContent.set(j, oldValues[0]+"_"+k);
					   seen = true;
					   break;
				   }
				   
				   
			   }
			   
			   if(!seen)
			   {
				   fileContent.add(freq[i][0] + "_" + freq[i][1]);
			   }
			   
			   seen = false;
			}
			
			
			Comparator<? super String> myComparator = new Comparator<String>() {

		        public int compare(String name1, String name2) {
		            int num1 = Integer.parseInt(name1.split("_")[1]);
		            int num2 = Integer.parseInt(name2.split("_")[1]);
		            // > : Increasing order 
		            // < : Decreasing order
		            if(num1 > num2)
		                    return 1;
		            else
		                    return -1;
		        }

		      };
		      Collections.sort(fileContent, myComparator);
			
			Files.write(filePath, fileContent, StandardCharsets.US_ASCII);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}

	}

	

	public void compress(File huffmanFile)
	{
		getImage();
		extractPixelInformation();
		readHuffmanFile(huffmanFile);		
		generateTree();
		traverse(first.root);
		try
		{
			String fPath = file.getAbsolutePath();
			fPath = fPath.substring(0, fPath.lastIndexOf('.')) + ".San";
			FileWriter writer = new FileWriter(fPath);
			writer.write(img.getWidth()+"x"+img.getHeight()+"\r");
			int size;
			char ch;
			String code;
			String temp = "";			
			for(int a = 0; a <counter; a++)
			{
				for(int b = 0; b < ctr; b++)
				{
					if(temporary[a] == RGB[b])
					{
						code = bit[b];
						while(true)
						{
							size = 7 - temp.length();
							if(size >=  code.length())
							{
								temp = temp + code;
								if(temp.length() == 7)
								{
									ch = (char)(Integer.parseInt(temp, 2));
									writer.write(ch);
									charCounter++;
									temp = "";
								}
								if(a == counter - 1)
								{
									ch = (char)(Integer.parseInt(temp, 2));
									writer.write(ch);
									charCounter++;
									lastBin = temp;
								}
								break;
							}else
							{
								temp = temp + code.substring(0, size);
								code = code.substring(size);
								ch = (char)(Integer.parseInt(temp, 2));
								writer.write(ch);
								charCounter++;
								temp = "";
							}
						}
					}
				}
			}
		writer.close();

		}catch(Exception e)
		{
			System.err.println("File not found");
		}
}


	public BufferedImage deCompress(File toBeDecompressed, File huffmanFile)
	{
		
		readHuffmanFile(huffmanFile);
		generateTree();
		traverse(first.root);
		int a = 0;
		int b = 0;
		BufferedImage sheeet = null;
		try{
			int c = 0;
			String temp = "";
			File file = toBeDecompressed;
			FileReader reader = new FileReader(file);
			/**************************************************************************************/
			@SuppressWarnings("resource")
			BufferedReader r = new BufferedReader(reader);
			String sizeLine = r.readLine();
			String[] imgSize = sizeLine.split("x");
			imageWidth = Integer.parseInt(imgSize[0]);
			imageHeight = Integer.parseInt(imgSize[1]);
			while(r.read() != -1)
			{
				charCounter++;
			}
			charCounter++;
			r.close();
			reader = new FileReader(file);
			r = new BufferedReader(reader);
			r.readLine();
			
			//replaces reader with r bufferedReader
			/**************************************************************************************/
			
			sheeet = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
			lastNode = first.root;
			for(int k = 0; k < charCounter; k++)
			{
				c = r.read();
				temp = Integer.toBinaryString(c);
				if(temp.length() < 7 && k != charCounter - 1)
				{
					int size = 7 - temp.length();
					for(int j = 0; j < size; j++)
					{
						temp = "0" + temp;
					}
				}
				if(charCounter-1 == k)
				{
					temp = lastBin;
				}
				for(int i = 0; i < temp.length(); i++)
				{
					traverseToDeCompress(temp.charAt(i));
					if(lastNode.isLeaf)
					{
						sheeet.setRGB(a, b, lastNode.getRGBValue());
						lastNode = first.root;
						a++;
						if(a == imageWidth)
						{
							a = 0;
							b++;
						}
					}
				}
			}


		}catch(Exception e)
		{
			System.err.println(e);
		}
		return sheeet;
	}

	public void traverseToDeCompress(char ch)
	{
		if(ch == '0'){
			lastNode = lastNode.left;
		}else if(ch == '1'){
			lastNode = lastNode.right;
		}
	}
}
