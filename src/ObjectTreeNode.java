
public class ObjectTreeNode implements ObjectTreeNodeInterface{
    private Object info;
    private ObjectTreeNode left;
    private ObjectTreeNode right;

    public ObjectTreeNode() {
        info = null;
        left = null;
        right = null;
    }

    /**
     * The ObjectTreeNode constructor
     *
     * @param o an Object to become an ObjectTreeNode
     */
    public ObjectTreeNode (Object o) {
        info = o;
        left = null;
        right = null;
    }

    /**
     * Sets the info for an Object
     *
     * @param o an Object that will have its info set
     */
    public void setInfo(Object o) {
        info = o;
    }

    /**
     *Returns the info for an Object
     *
     * @return Object info containing the info needed
     */
    public Object getInfo() {
        return info;
    }

    /**
     * Sets the left node
     *
     * @param p an ObjectTreeNode being set left
     */
    public void setLeft(ObjectTreeNode p) {
        left = p;
    }

    /**
     * Returns the left ObjectTreeNode
     *
     * @return ObjectTreeNode left, the left ObjectTreeNode
     */
    public ObjectTreeNode getLeft() {
        return left;
    }

    /**
     * Sets the right node
     *
     * @param p the ObjectTreeNode being set right
     */
    public void setRight(ObjectTreeNode p) {
        right = p;
    }

    /**
     * Returns the right ObjectTreeNode
     *
     * @return ObjectTreeNode right representing the right node
     */
    public ObjectTreeNode getRight() {
        return right;
    }
}
