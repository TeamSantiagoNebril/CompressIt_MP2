package core;


public class Node {
	private int rgb;
	private int frequency;
	public Node left;
	public Node right;
	private String bit = "";
	public Boolean isLeaf = false;
	public Node(int rgb, int frequency){
		this.rgb = rgb;
		this.frequency = frequency;
	}

	public int getRGBValue(){
		return rgb;
	}

	public int getFrequency(){
		return frequency;
	}

	public void setLeft(Node left){
		this.left = left;
	}

	public void setRight(Node right){
		this.right = right;
	}

	public Node getLeft(){
		return left;
	}

	public Node getRight(){
		return right;
	}

	public void setBit(String bit){
	    this.bit = bit;
	  }

	  public String getBit(){
	    return bit;
	  }
}
