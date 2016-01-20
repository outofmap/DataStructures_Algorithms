
package DataStructure;

public class binaryTree {
	public static class treeNode{
		int data;
		treeNode left;
		treeNode right;
		treeNode(int data){
			this.data = data;
		}
	}
	
	public static int countInternalNodes(treeNode node){
		int count = 0;
		if(node == null){
			System.out.println("잘못된 입력 값입니다.");
			return 0;
		}
		
		if(node.left == null && node.right == null){
			return 0;
		}
		else
			return count += countInternalNodes(node.left) +countInternalNodes(node.right);
		
		
	}
	public static void main(String[] args) {
		binaryTree bTree = new binaryTree();
		treeNode rootNode = createBinaryTree();
		System.out.println("number of internal nodes :"+ countInternalNodes(rootNode));
	}
	
	public static treeNode createBinaryTree()  
	 {  
	    
	  treeNode rootNode =new treeNode(40);  
	  treeNode node20=new treeNode(20);  
	  treeNode node10=new treeNode(10);  
	  treeNode node30=new treeNode(30);  
	  treeNode node60=new treeNode(60);  
	  treeNode node50=new treeNode(50);  
	  treeNode node70=new treeNode(70);  
	    
	  rootNode.left=node20;  
	  rootNode.right=node60;  
	    
	  node20.left=node10;  
	  node20.right=node30;  
	    
	  node60.left=node50;  
	  node60.right=node70;  
	    
	  return rootNode;  
	 }  

}
