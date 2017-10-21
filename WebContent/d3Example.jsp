<!DOCTYPE html>
<meta charset="utf-8">
<script src="https://d3js.org/d3.v4.min.js"></script>

<style>
.links line {
  stroke: #000000;
  stroke-opacity: 0.99;
}
.node text {
stroke:#ffffff;
cursos:pointer;
}
.nodes circle {
  stroke: #fff;
  stroke-width: 1.5px;
}
	
</style>
<body>

<table>
	<tr>
		<td>
		<label>Node Key</label>
		</td>
		<td>
		<p> Person: <div style = "background-color:#00ccff;width:150px;height:15px;"></div></p> 
		</td>
		<td>
		<p> Message: <div style = "background-color:#006666;width:150px;height:15px;"></div> </p> 
		</td>

		<td><p> Border color In Query: <div style = "background-color:#0dfc00;width:150px;height:15px;"></div></p> 
		</td>
		<td><p> Border color Not In Query: <div style = "background-color:black;width:150px;height:15px;"></div></p> 
		</td>
</tr>

<tr>
<td>
<label> Link Key</label>
</td>
<td>
<p>Posted: <div style = "background-color:#898b89;width:150px;height:15px;"></div> </p>
</td>
<td>
<p>Friends: <div style = "background-color:#5B0265;width:150px;height:15px;"></div> </p>
</td>
<td>
<p>Liked: <div style = "background-color:#f900fc;width:150px;height:15px;"></div> </p>
</td>
</tr>
</table>


<jsp:include page = "/header.jsp"/>
<script>


var width = 2000,
    height = 2000
var svg = d3.select("body").append("svg")
    .attr("width", width)
    .attr("height", height);
    
    
var toString = JSON.stringify(${json});
 console.log(toString);
var data = JSON.parse(toString);

	var simulation = d3.forceSimulation()
		.force("link", d3.forceLink().id(function(d) { return d.id; }).distance(300))
		.force("charge", d3.forceManyBody().strength(-50))
		.force("center", d3.forceCenter(1000, 500));
	
	var link = svg.append("g")
    .attr("class", "links")
  .selectAll("line")
  .data(data.links)
  .enter().append("line")
  .style("stroke", function(d) {
	  if(d.edge == "posted")
		  return "#898b89"
		if(d.edge == "friends")
			  return "#5B0265"
		if(d.edge == "liked")
				return "#f900fc"
		  
  })
    .attr("stroke-width", 5)
    .attr("id",function(d,i) {return 'link	'+i});
    //.enter().append("path");
	


	
var node = svg.append("g")
    .attr("class", "nodes")
  .selectAll("circle")
  .data(data.nodes)
  .enter().append("g")
  .attr("transform", function(d, i) {
             return "translate(" + width/12 + "," + height/12 + ")";
           })
  .call(d3.drag()
        .on("start", dragstarted)
        .on("drag", dragged)
        .on("end", dragended));
        



var labelText = svg.append("g")
.data(data.links)
.append("textPath")
.text(function(d) { return d.edge;});
  
  
    node.append("circle")
    .attr("r", 75)
    .style("stroke-width", 3)
    .style("stroke", function(d){
    	//is in query
    	if(d.queried)
    		return "0dfc00"
    	//is not in query
    	else
    		return "black"
    })
    .attr("fill", function(d){ 
    		//is a person
    		if(d.name != null)
    			return "#00ccff";
    		//is a message
    		else
    			return "#006666";
    					});
    
    		
  
  	simulation
  		.nodes(data.nodes)
  		.on("tick", ticked);
	simulation.force("link")
  		.links(data.links);
	
	
	node.append("text")
    .attr("class","system")
    .attr("fill","white")
    .attr("text-anchor", "middle")
    .text(function(d) {
      return d.name;
     });
	
	node.append("text")
    .attr("class","system")
    .attr("fill","white")
    .attr("dx", -15)
    .attr("dy",20)
    .text(function(d) {
      return d.gender;
     });
	
	//only append id if a person
	node.append("text")
    .attr("class","system")
    .attr("fill","white")
    .attr("dx", -20)
    .attr("dy",40)
    .text(function(d) {
    	if(d.name != null)
      return d.id;
     });
	
	//Add message if is message type
	node.append("text")
    .attr("class","system")
    .attr("fill","white")
    .attr("dx", -50)
    .attr("dy", -20)
    .text(function(d) {
    	if(d.name == null)
      return d.message.substring(0,15);
     });
	
	//Add message if is message type
	node.append("text")
    .attr("class","system")
    .attr("fill","white")
    .attr("dx", -50)
    .attr("dy", -5)
    .text(function(d) {
    	if(d.name == null)
      return d.message.substring(15,30);
     });
	
	//Add message if is message type
	node.append("text")
    .attr("class","system")
    .attr("fill","white")
    .attr("dx", -50)
    .attr("dy", 10)
    .text(function(d) {
    	if(d.name == null)
      return d.message.substring(30,45);
     });
	
	//Add message if is message type
	node.append("text")
    .attr("class","system")
    .attr("fill","white")
    .attr("dx", -50)
    .attr("dy", 25)
    .text(function(d) {
    	if(d.name == null)
      return d.message.substring(45,60) + "...";
     });
    
    
function ticked() {
link
    .attr("x1", function(d) { return d.source.x; })
    .attr("y1", function(d) { return d.source.y; })
    .attr("x2", function(d) { return d.target.x; })
    .attr("y2", function(d) { return d.target.y; });
node
	.attr("transform",function(d){return "translate(" + d.x + "," + d.y + ")"});
	

    
}
  function dragstarted(d) {
    if (!d3.event.active) simulation.alphaTarget(0.3).restart();
    d.fx = d.x;
    d.fy = d.y;
  }
  function dragged(d) {
    d.fx = d3.event.x;
    d.fy = d3.event.y;
  }
  function dragended(d) {
    if (!d3.event.active) simulation.alphaTarget(0);
    d.fx = null;
    d.fy = null;
  }
</script>
</body>