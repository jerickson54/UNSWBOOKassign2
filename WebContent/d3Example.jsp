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
    .attr("stroke-width", function(d) { return Math.sqrt(d.value); });

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

  
  
    node.append("circle")
    .attr("r", 75)
    .attr("fill", function(d)
    		{ if(d.gender == "Female")
    			return "red"
    			else if(d.gender == "Male")
    				return "blue"
    				else
    					return "green"
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
	
	node.append("text")
    .attr("class","system")
    .attr("fill","white")
    .attr("dx", -20)
    .attr("dy",40)
    .text(function(d) {
      return d.id;
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