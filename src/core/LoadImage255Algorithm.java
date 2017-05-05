package core;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.io.FileWriter;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class LoadImage255Algorithm extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage img = null;
	//private int temporary[];
	private Color temporary[];
	private Color rgb[];
	private int freq[][];
	private Tree first;
	//private int count = 0;
	//private int RGB[];
	//private String bit[];
	//private int ctr = 0;
	int index = 0;
	int counter = 0;
	private Tree last;
	//private int trial[];
	
	//private Color rgbColor;
	
	
	public LoadImage255Algorithm(){

		first = null;
		getImage();
		extractPixelInformation();
		getFrequencies();
		//makeNodes();
		//generateTree();

		//traverse(first.root);
		//compress();

	}

	public void getImage(){
		try {
		    img = ImageIO.read(new File("pics\\default2.png"));
		} catch (IOException e) {
			System.err.println("Error regarding getImage function");
		}
	}

	public void extractPixelInformation(){
		
		rgb = new Color[img.getHeight()*img.getWidth()];

		for(int a = 0; a < img.getHeight(); a++){
			for(int b = 0; b < img.getWidth(); b++){
				rgb[counter++] = new Color(img.getRGB(b, a));
			}
		}
		temporary = Arrays.copyOf(rgb, rgb.length);
	}

	public void getFrequencies(){
		
		freq = new int[256][2];


		for(int i = 0; i < 256; i++)
		{
			freq[i][0] = i;
		}
		
		for(int i = 0; i < img.getHeight()*img.getWidth(); i++)
		{
			for(int j = 0; j < 256; j++)
			{
				if(rgb[i].getRed() == freq[j][0])
				{
					freq[j][1]++;
					break;
				}
			}
			
			for(int j = 0; j < 256; j++)
			{
				if(rgb[i].getGreen() == freq[j][0])
				{
					freq[j][1]++;
					break;
				}
			}
			
			for(int j = 0; j < 256; j++)
			{
				if(rgb[i].getBlue() == freq[j][0])
				{
					freq[j][1]++;
					break;
				}
			}
				
		}
		
		Comparator<int[]> arrayComparator = new Comparator<int[]>() {
	        @Override
	        public int compare(int[] o1, int[] o2) {
	            return o1[1]-o2[1];
	        }
	    };
		Arrays.sort(freq, arrayComparator);
		
		
		for(int i = 0; i < 256; i++)
		{
			System.out.println("Value of " + freq[i][0] + "is " + freq[i][1]);
		}
		//freq = Arrays.copyOfRange(freq, 0, count);
		
		
		/*
		freq = new int[img.getHeight()*img.getWidth()][2];
		Arrays.sort(rgb);
		freq[0][0] = rgb[0];
		freq[0][1] = 1;
		for(int a = 1; a < img.getHeight()*img.getWidth(); a++){
			if(rgb[a] == freq[count][0]){
				freq[count][1]++;
			}else{
				freq[++count][0] = rgb[a];
				freq[count][1] = 1;
			}
			
		}

		freq = Arrays.copyOfRange(freq, 0, count);
		
		int ggwp = 0;
		for(int i = 0; i < freq.length; i++)
		{
			if(freq[i][1] != 0)
			{
				ggwp++;
			}
		}
		System.out.println("number of Unique rgb's : " + ggwp);
		
		Comparator<int[]> arrayComparator = new Comparator<int[]>() {
	        @Override
	        public int compare(int[] o1, int[] o2) {
	            return o1[1]-o2[1];
	        }
	    };
		Arrays.sort(freq, arrayComparator);*/
	}

	public void makeNodes(){
		
		/*for(int a = 0;a < count; a++){
			Node node = new Node(freq[a][0], freq[a][1]);
			Tree tree = new Tree(node);
			insert(tree);
		}
		//System.out.println(count);
		for(int a = 0;a < 5; a++){
			Node node = new Node(trial[a][0], trial[a][1]);
			Tree tree = new Tree(node);
			insert(tree);
		}*/
	}

	public void insert(Tree tree){
		
		if(first == null){
			first = tree;
			last = first;
		}else{
			Tree current = first;
			while(current != null){
				if(tree.root.getFrequency() >= current.root.getFrequency()){
					if(current == first){
						first = tree;
					}else{
						current.previous.next = tree;
					}
					tree.previous = current.previous;
					tree.next = current;
					current.previous = tree;
					break;
				}
				if(current.next != null){
					current = current.next;
				}else{
					last = tree;
					current.next = tree;
					tree.previous = current;
					break;
				}
			}
		}
	}

	private Tree temp;
	public Tree dequeue(){
		/*
		Tree temp = last;
		if(last!= first){
			last = last.previous;
			last.next = null;
		}else{
			first = null;
			last = null;
		}*/
		return temp;
	}

	public void generateTree(){
		/*
		Tree temp;
		Tree temp2;
		while(true){
			temp = dequeue();
			temp2 = dequeue();
			if(temp != null && temp2 != null){
				Node node = new Node('\0', temp.root.getFrequency() + temp2.root.getFrequency());
				node.setLeft(temp.root);
				node.setRight(temp2.root);
				Tree tree = new Tree(node);
				insert(tree);
			}else{
				if(first == null){
					first = temp;
				}
				break;
			}
		}
		RGB = new int[count];
		bit = new String[count];
		trial = new int[count];*/
	}

	public void traverse(Node node){
		/*
		if(node != null){
			if(node.left != null){
				node.left.setBit(node.getBit() + "0");
				traverse(node.left);
			}
			if(node.right != null){
				node.right.setBit(node.getBit() + "1");
				traverse(node.right);
			}
			if(node.left == null && node.right == null){
				RGB[ctr] = node.getRGBValue();
				bit[ctr] = node.getBit();
				trial[ctr] = node.getFrequency();
				ctr++;
			}
		}*/
	}

	public void compress() //?
	{
		/*
		try
		{
			FileWriter writer = new FileWriter("pics//trial.HUFF", true);
			int length = 0;
			String temp = "";
			int size;
			char ch;
			String code;
			for(int a = 0; a < counter; a++)
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
									length += temp.length();
									ch = (char)(Integer.parseInt(temp, 2));
									writer.write(ch);
									temp = "";
								}
								break;
							}else
							{
								temp = temp + code.substring(0, size);
								code = code.substring(size);
								length += temp.length();
								ch = (char)(Integer.parseInt(temp, 2));
								writer.write(ch);
								temp = "";
							}
						}
						break;
					}
				}
			}
			System.out.println(length);
			writer.close();
		}catch(Exception e)
		{
			System.err.println("File not found on FileWriter");
		}
		*/
	}



	public static void main(String args[]){
		LoadImage255Algorithm image = new LoadImage255Algorithm();
	}
}
