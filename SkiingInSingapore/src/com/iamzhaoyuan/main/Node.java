package com.iamzhaoyuan.main;

/**
 * This class stores information for each node
 * 
 * @author Yuan
 *
 */
public class Node {

	// Value of node
	private int value;
	// Visited or not
	private boolean isVisited;
	// this node's value - last node's value
	private int drop;
	// number of node
	private int length;
	// Nodes around this
	private Node upNode, downNode, leftNode, rightNode;

	public Node(int value) {
		this.value = value;
		this.isVisited = false;
		this.drop = 0;
		this.length = 1;
	}

	/**
	 * Set neighbor nodes
	 * 
	 * @param upNode
	 *            index y-1
	 * @param downNode
	 *            index y+1
	 * @param leftNode
	 *            index x-1
	 * @param rightNode
	 *            index x+1
	 */
	public void setNeighbor(Node upNode, Node downNode, Node leftNode,
			Node rightNode) {
		this.upNode = upNode;
		this.downNode = downNode;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}

	/**
	 * Getter and setter
	 */
	/**
	 * Get value
	 * 
	 * @return value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Set value
	 * 
	 * @param value
	 *            value of node
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Get isVisited
	 * 
	 * @return isVisited
	 */
	public boolean isVisited() {
		return isVisited;
	}

	/**
	 * Set isVisited
	 * 
	 * @param isVisited
	 *            visit node or not
	 */
	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	/**
	 * Get drop value
	 * 
	 * @return drop value
	 */
	public int getDrop() {
		return drop;
	}

	/**
	 * Set drop value
	 * 
	 * @param drop
	 *            drop value
	 */
	public void setDrop(int drop) {
		this.drop = drop;
	}

	/**
	 * Get length
	 * 
	 * @return length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Set length
	 * 
	 * @param length
	 *            length
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * Set up node
	 * 
	 * @return up node
	 */
	public Node getUpNode() {
		return upNode;
	}

	/**
	 * Set down node
	 * 
	 * @return down node
	 */
	public Node getDownNode() {
		return downNode;
	}

	/**
	 * Set left node
	 * 
	 * @return left node
	 */
	public Node getLeftNode() {
		return leftNode;
	}

	/**
	 * Set right node
	 * 
	 * @return right node
	 */
	public Node getRightNode() {
		return rightNode;
	}

	/**
	 * Calculate longest length
	 * 
	 * @return longest length
	 */
	public int calculateLength() {
		
		if (!this.isVisited) {
			this.isVisited = true;
			int tmpLength = 1;

			// Up
			if (this.upNode != null && this.upNode.getValue() < this.value) {
				if (this.upNode.isVisited()) {
					tmpLength = this.upNode.getLength();
				} else {
					tmpLength = this.upNode.calculateLength();
				}
				if (this.length <= tmpLength || 
						(this.length == tmpLength + 1 && 
						(this.drop < this.upNode.getDrop() + this.value - this.upNode.getValue()))) {
					this.length = tmpLength + 1;
					this.drop = this.upNode.getDrop() + this.value - this.upNode.getValue();
				}
			}
			// Down
			if (this.downNode != null && this.downNode.getValue() < this.value) {
				if (this.downNode.isVisited()) {
					tmpLength = this.downNode.getLength();
				} else {
					tmpLength = this.downNode.calculateLength();
				}
				if (this.length <= tmpLength || 
						(this.length == tmpLength + 1 && 
						(this.drop < this.downNode.getDrop() + this.value - this.downNode.getValue()))) {
					this.length = tmpLength + 1;
					this.drop = this.downNode.getDrop() + this.value - this.downNode.getValue();
				}
			}
			// Left
			if (this.leftNode != null && this.leftNode.getValue() < this.value) {
				if (this.leftNode.isVisited()) {
					tmpLength = this.leftNode.getLength();
				} else {
					tmpLength = this.leftNode.calculateLength();
				}
				if (this.length <= tmpLength || 
						(this.length == tmpLength + 1 && 
						(this.drop < this.leftNode.getDrop() + this.value - this.leftNode.getValue()))) {
					this.length = tmpLength + 1;
					this.drop = this.leftNode.getDrop() + this.value - this.leftNode.getValue();
				}
			}
			// Right
			if (this.rightNode != null && this.rightNode.getValue() < this.value) {
				if (this.rightNode.isVisited()) {
					tmpLength = this.rightNode.getLength();
				} else {
					tmpLength = this.rightNode.calculateLength();
				}
				if (this.length <= tmpLength || 
						(this.length == tmpLength + 1 && 
						(this.drop < this.rightNode.getDrop() + this.value - this.rightNode.getValue()))) {
					this.length = tmpLength + 1;
					this.drop = this.rightNode.getDrop() + this.value - this.rightNode.getValue();
				}
			}
		}

		return this.length;
	}

}
