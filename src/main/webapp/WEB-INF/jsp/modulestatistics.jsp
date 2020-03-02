<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%--<!DOCTYPE html>--%>
<%--<meta charset="utf-8"><html>--%>
<head>
    <title>Springfield Student Management System</title>
    <link rel="stylesheet" type="text/css" href="/home.css" />
</head>
<body>
<div id="page">
    <div id="header">
        <p class='title'>Springfield Student Management System</p>
        <p class="current_user">You are currently logged in as : <c:out value="${sessionScope.username}"/></p>
    </div>
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/modules">Modules</a></li>
        <li><a href="index.html">Fees</a></li>
        <li><a href="index.html">Contact us</a></li>
        <li><a href="/logout">Logout</a></li>
    </ul>
    <div id="pageContent">
        <p class="title">Statistics for Module (ID: ${moduleID} Name: ${moduleName})</p>
        <br/>
        <div id="my_dataviz"></div>
        <!-- Load d3.js -->
        <script src="https://d3js.org/d3.v4.js"></script>
        <script>
            d3.select('h2').attr('align',"center");
            d3.select('h3').style('color', 'darkblue');
            d3.select('h3').style('font-size', '24px');
            d3.select('h3').attr('align',"center");
            d3.select("#my_dataviz").attr("align","center");

            // set the dimensions and margins of the graph
            var width = 450
            height = 450
            margin = 40

            // The radius of the pieplot is half the width or half the height (smallest one). I subtract a bit of margin.
            var radius = Math.min(width, height) / 2 - margin

            // append the svg object to the div called 'my_dataviz'
            var svg = d3.select("#my_dataviz")
                .append("svg")
                .attr("width", width)
                .attr("height", height)
                .append("g")
                .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

            <!-- Create a div where the graph will take place -->



            // set the color scale
            var color = d3.scaleOrdinal()
                .domain(${data})
                .range(["#FF69B4", "#1E90FF"])

            // Compute the position of each group on the pie:
            var pie = d3.pie()
                .value(function(d) {return d.value; })
            var data_ready = pie(d3.entries(${data}))

            // Build the pie chart: Basically, each part of the pie is a path that we build using the arc function.
            svg
                .selectAll('whatever')
                .data(data_ready)
                .enter()
                .append('path')
                .attr('d', d3.arc()
                    .innerRadius(0)
                    .outerRadius(radius)
                )
                .attr('fill', function(d){ return(color(d.data.key)) })
                .attr("stroke", "black")
                .style("stroke-width", "2px")
                .style("opacity", 0.7)

            // shape helper to build arcs:
            var arcGenerator = d3.arc()
                .innerRadius(0)
                .outerRadius(radius)

            // Now add the annotation. Use the centroid method to get the best coordinates
            svg
                .selectAll('mySlices')
                .data(data_ready)
                .enter()
                .append('text')
                .text(function(d){  return d.data.key+ ": "+d.data.value})
                .attr("transform", function(d) { return "translate(" + arcGenerator.centroid(d) + ")";  })
                .style("text-anchor", "middle")
                .style("font-size", 17)

        </script>
    </div>
</div>
</body>