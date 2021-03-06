<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Springfield Student Management System</title>
    <link rel="stylesheet" type="text/css" href="home.css" />
</head>
<body>
<div id="page">
    <div id="header">
        <p class='title'>Springfield Student Management System</p>
        <p class="current_user">You are currently logged in as : ${pageContext.request.userPrincipal.name}</p>
        <a href="${contextPath}/dropout">Cancel Registration</a>
    </div>
    <ul>
        <li><a href="${contextPath}/">Home</a></li>
        <li><a href="${contextPath}/modules">Modules</a></li>
        <li><a href="${contextPath}/fees">Fees</a></li>
        <li>
            <form method="post" action="logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input id="logout" type="submit" value="Logout">
            </form>
        </li>
    </ul>
    <div id="pageContent">
        <p class="title">Welcome</p>
        <br/>
        <!-- <div class="sideMenu">
            Links: <br/>
            <a href=""></a><br/>
            <a href=""></a><br/>
            <a href=""></a><br/>
            <a href=""></a><br/>
            <a href=""></a><br/>
            <a href=""></a><br/>
        </div> -->
        <p>
            Welcome to Springfields Student Management System.
        </p>
        <p>
            You are now able to view School population statistic on our home page. If you would like to change your enrolment or add aditional modulee please select 'Registration'. PLEASE NOTE: To enrol in new modules, you must not have any outstanding fees on your account. To view you outstanding fees please select 'fees'.
        </p>
        <br/>
        <p class="title">Statistics for School Population</p>
        <br/>
        <div id="sex_dataviz"></div>
        <div id="nationality_dataviz"></div>
        <!-- Load d3.js -->
        <script src="https://d3js.org/d3.v4.js"></script>
        <script>
            d3.select("#sex_dataviz").attr("align","center");

            // set the dimensions and margins of the graph
            var width = 450
            height = 450
            margin = 40

            // The radius of the pieplot is half the width or half the height (smallest one). I subtract a bit of margin.
            var radius = Math.min(width, height) / 2 - margin

            // append the svg object to the div called 'sex_dataviz'
            var svg = d3.select("#sex_dataviz")
                .append("svg")
                .attr("width", width)
                .attr("height", height)
                .append("g")
                .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

            <!-- Create a div where the graph will take place -->
            // set the color scale
            var color = d3.scaleOrdinal()
                .domain(${sexData})
                .range(["#FF69B4", "#1E90FF"])


            // Compute the position of each group on the pie:
            var pie = d3.pie()
                .value(function(d) {return d.value; })
            var data_ready = pie(d3.entries(${sexData}))

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
                .style("font-size", 13)

        </script>
        <script>
            d3.select("#nationality_dataviz").attr("align","center");

            // set the dimensions and margins of the graph
            var width = 450
            height = 450
            margin = 40

            // The radius of the pieplot is half the width or half the height (smallest one). I subtract a bit of margin.
            var radius = Math.min(width, height) / 2 - margin

            // append the svg object to the div called 'sex_dataviz'
            var svg = d3.select("#nationality_dataviz")
                .append("svg")
                .attr("width", width)
                .attr("height", height)
                .append("g")
                .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

            <!-- Create a div where the graph will take place -->
            // set the color scale
            var color = d3.scaleOrdinal()
                .domain(${nationalityData})
                .range(["#800080", "#ff00ff", "#000080", "#ffff00", "#800000", "#ff0000", "#ffffff", "#00ffffff"])


            // Compute the position of each group on the pie:
            var pie = d3.pie()
                .value(function(d) {return d.value; })
            var data_ready = pie(d3.entries(${nationalityData}))

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

                .filter(function(d) { return d.endAngle - d.startAngle > .2; })
                .append('text')
                .text(function(d){  return d.data.key+ ": "+d.data.value})
                .attr("transform", function(d) { return "translate(" + arcGenerator.centroid(d) + ")rotate(" + angle(d) + ")"; })
                .style("text-anchor", "middle")
                .style("font-size", 13)

            // Computes the angle of an arc, converting from radians to degrees.
            function angle(d) {
                var a = (d.startAngle + d.endAngle) * 90 / Math.PI - 90;
                return a > 90 ? a - 180 : a;
            }

        </script>
    </div>
</div>
</body>
</html>
