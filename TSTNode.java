
public class TSTNode {
	char data;
	boolean isFinalNode;

	TSTNode left;
	TSTNode middle;
	TSTNode right;

	public TSTNode(char data){
        this.data = data;
        this.isFinalNode = false;
        this.left = null;
        this.middle = null;
        this.right = null;
    }
}
