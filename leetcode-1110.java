import java.util.List;
//Leetcode: 1110. Delete Nodes And Return Forest (Correct)
//Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
     TreeNode(int x) { val = x; }
 }
 
 class Solution {
    
    public enum Node {
    LEFT,RIGHT,ROOT;
}

List<TreeNode> nodes=new ArrayList<>();

public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
    createForest(root,to_delete, null, Node.ROOT.ordinal());
    return nodes;
}

public void createForest(TreeNode curr, int[] to_delete, TreeNode copy, int direction){
    if(curr==null){
        return;
    }
    if(arrayContains(to_delete,curr.val)){
        createForest(curr.left, to_delete, null, Node.ROOT.ordinal());
        createForest(curr.right, to_delete, null, Node.ROOT.ordinal());
        return;
    } 
    
    if(direction==Node.ROOT.ordinal()){
        copy=new TreeNode(curr.val);
        nodes.add(copy);
    }else if(direction==Node.LEFT.ordinal()){
        copy.left=new TreeNode(curr.val);
        copy=copy.left;
    }else {
        copy.right=new TreeNode(curr.val);
        copy=copy.right;
    }

    createForest(curr.left, to_delete, copy, Node.LEFT.ordinal());
    createForest(curr.right, to_delete, copy, Node.RIGHT.ordinal());
}

public boolean arrayContains(int[] arr,int val){
    for(int i:arr){
        if(i==val)
            return true;
    }
    return false;
}
}