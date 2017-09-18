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
            width: 95%;
            height: 80%;
            margin-left: 2%;
            margin-top: 1%;
            position: relative;
            background: #00aabb;
            border-radius: .4em;
        }

        .speech-bubble:after {
            content: '';
            position: absolute;
            left: 0;
            top: 50%;
            width: 0;
            height: 0;
            border: 24px solid transparent;
            border-right-color: #00aabb;
            border-left: 0;
            border-bottom: 0;
            margin-top: -12px;
            margin-left: -24px;
        }

        #userMessagesBox {
            width: 95%;
            margin-left: 2%;
            margin-top: 1%;

        }

        #messagesHeading{
            font-size: 2em;
        }

        .nav ul li:last-child {
            float: right;
        }

        #searchFriendForm{
            width: 70%
        }

        #searchInput{
            width: 92%;
        }

        #logoutButton {
            margin-top: 1%;
            width: 2.5%;
            height: 4%;
        }

        #searchInputText{
            width:100%;
        }

        #logo{
            margin-left: 25px;
            margin-right: 25px;
        }

        #logout{
            margin-left: 4%;
        }

        #messageBox{
            resize: none;
            margin-left: 0.5%;
            margin-top: 0.5%;
            width: 98%;
            height: 90%;
            background-color: rgba(0, 0, 0, 0);
            border-color: rgba(0, 0, 0, 0)
        }

    </style>
</head>
<body>
<nav class="navbar navbar-inverse" style="border-radius:0;">
    <div class="container-fluid">
        <div class="navbar-header" id="logo">
            <a class="navbar-brand" href="/">UNSWBook</a>
        </div>
        <ul class="nav navbar-nav" id="homeButton">
            <li class="active"><a href="#">Home</a></li>
        </ul>
        <form class="navbar-form navbar-left" id="searchFriendForm">
            <div class="form-group" id="searchInput">
                <input type="text" class="form-control" placeholder="Search" id="searchInputText">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
        <ul class="nav navbar-nav" id="logout">
            <li><a href="#">Logout</a></li>
        </ul>
        <img id="logoutButton" src="http://www.free-icons-download.net/images/logout-button-icon-76344.png">
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
            <div class="speech-bubble"><textarea id="messageBox" placeholder="What are your thoughts today?"></textarea></div>
        </div>
        <div id="userMessagesBox">
            <p id="messagesHeading">Your Thoughts So Far</p>
        </div>

    </div>

</div>

</body>
</html>

