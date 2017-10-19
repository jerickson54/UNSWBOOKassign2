<!DOCTYPE html>
<meta charset="utf-8">
<script src="http://d3js.org/d3.v2.min.js?2.9.3"></script>
<style>

.link {
  stroke: #000000;
}

.node text {
stroke:#ffffff;

}

.node rect{
stroke:#001538;
stroke-width:5px;
fill:#0c24d6;
opacity:0.8;
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

var force = d3.layout.force()
    .gravity(.05)
    .distance(250)
    .charge(-250)
    .size([width, height]);
    
 

d3.json("toDisplay.json", function(json) {
  force
      .nodes(json.nodes)
      .links(json.links)
      .start();

  var link = svg.selectAll(".link")
      .data(json.links)
    .enter().append("line")
      .attr("class", "link")
    .style("stroke-width", function(d) { return Math.sqrt(d.weight); });
  

 
  	
  var node = svg.selectAll(".node")
      .data(json.nodes)
    .enter().append("g")
      .attr("class", "node")
      .call(force.drag);

  node.append("rect")
      .attr("width",135)
  		.attr("height",80);
  		
  		
  node.append("text")
  .attr("dx", 12)
  .attr("dy", 15)
  .text(function(d) {
	  return d.username});
  
  
  node.append("text")
  	.attr("dx", 12)
  	.attr("dy", 35)
  	.text(function(d) {
	  return d.name});
  
  node.append("text")
	.attr("dx", 12)
	.attr("dy", 55)
	.text(function(d) {
	  return d.gender});
  
  node.append("text")
	.attr("dx", 12)
	.attr("dy", 75)
	.text(function(d) {
	  return d.dob});
  
 
  	

  force.on("tick", function() {
    link.attr("x1", function(d) { return d.source.x; })
        .attr("y1", function(d) { return d.source.y; })
        .attr("x2", function(d) { return d.target.x; })
        .attr("y2", function(d) { return d.target.y; })
        .attr("xlink:href",
         function(d) {
            return "#path"+d.source.index+"_"+d.target.index;
         });

    node.attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; });
  });
});

</script>
</body>
