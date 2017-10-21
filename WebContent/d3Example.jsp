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

<div>
<p style = "display:inline;">Person: <div style = "background-color:#00ccff;width:250px;height:15px;"></div> </p> 
<p>Message: <div style = "background-color:#006666;width:250px;height:15px;"></div> </p> 

<p> Border color for contained in query: </p> <div style = "background-color:#ffff00;width:250px;height:15px;"></div>
<p> Border color for not contained in query: </p> <div style = "background-color:black;width:250px;height:15px;"></div>

</div>
<jsp:include page = "/header.jsp"/>
<script>


var width = 2000,
    height = 1000
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
  .attr("fill", "black")
    .attr("stroke-width", function(d) { return Math.sqrt(d.value); })
    .attr("id",function(d,i) {return 'link	'+i});
	


	
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
        
var path = svg.append("g").selectAll("link")
.data(data.links)
.enter().append("path")
.attr("id",function(d,i) { return "linkid_" + i; });


var labelText = svg.selectAll(".labelText")
.data(data.links)
.enter().append("text")
.attr("class","labelText")
.attr("dx",20)
.attr("dy",0)
.style("fill","red")
.append("textPath")
.attr("xlink:href",function(d,i) { return "#linkid_" + i;})
.text(function(d) { return d.edge;});
  
  
    node.append("circle")
    .attr("r", 75)
    .style("stroke-width", 3)
    .style("stroke", function(d){
    	//is in query
    	if(d.queried)
    		return "ffff00"
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