<%--
  Created by IntelliJ IDEA.
  User: jason
  Date: 16/9/17
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css' rel='stylesheet' />
    <title>Users Wall</title>
    <style>
        #userWall {
            margin-top: -20px;
            height: 100%;
        }
        #bannerDiv {
            position: absolute;
            height: 100%;
            width:  17%;
            overflow:auto;
        }
        #banner {
            background-color: red;
            width: 100%;
            height: 100%;
            overflow: visible;
            white-space: nowrap;
        }

        #profileImage {
            width: 200px;
            height: 200px;
            border-radius: 50%;
            margin-left: 10%;
            margin-top: 10%;
            border:8px solid white;
        }

        #name {
            background-color: green;
            margin-top: 10px;
            width: 100%;
            height: 10%;

        }

        #otherDetails {
            background-color: blue;
            margin-top: 10px;
            width: 100%;
            height: 20%;

        }

        #wall {
            width: 83%;
            height: 100%;
            margin-left: 17%;
            background-color: purple;
            position: absolute;
            overflow: auto;
        }


        #messageBanner{
            width: 100%;
            height: 30%;
            background-color: black;
            position: relative;
            overflow: auto;

        }

        .speech-bubble {
            position: relative;
            height: 70%;
            width: 95%;
            margin-left: 2%;
            margin-top: 2%;
            background: #30baba;
            border-radius: .4em;
        }

        .speech-bubble:after {
            content: '';
            position: absolute;
            left: 0;
            top: 50%;
            width: 0;
            height: 0;
            border: 27px solid transparent;
            border-right-color: #30baba;
            border-left: 0;
            border-top: 0;
            margin-top: -13.5px;
            margin-left: -27px;
        }



    </style>
</head>
<body>
<nav class="navbar navbar-inverse" style="border-radius:0;">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">UNSWBook</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="search.jsp">Search</a></li>
            <li><a href="#">Site Map</a></li>
            <li><a href="#">Log Out -> </a></li>
        </ul>
    </div>
</nav>
<div id="userWall">
    <div id="bannerDiv">
        <div id="banner">
            <img id="profileImage" src="http://cdn2-www.dogtime.com/assets/uploads/gallery/pug-dog-breed-pictures/2-face.jpg">
            <div id="name">
                USERS NAME GOES HERE
            </div><div id="otherDetails">
            Other Details Go here
        </div>

        </div>
    </div>
    <div id="wall">
        <div id="messageBanner">
            <p class="speech-bubble">What are your thoughts today?!</p>

        </div>

    </div>

</div>

</body>
</html>

