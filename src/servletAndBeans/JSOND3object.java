package servletAndBeans;

public class JSOND3object {
	
	private nodeD3[] nodes;
	private linkD3[] links;
	
	public JSOND3object(nodeD3[] nodes, linkD3[] links) {
		super();
		this.nodes = nodes;
		this.links = links;
	}

	public nodeD3[] getNodes() {
		return nodes;
	}

	public void setNodes(nodeD3[] nodes) {
		this.nodes = nodes;
	}

	public linkD3[] getLinks() {
		return links;
	}

	public void setLinks(linkD3[] links) {
		this.links = links;
	}
	
	
	
	
	

}
