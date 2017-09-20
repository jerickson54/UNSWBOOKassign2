<%--
  Created by IntelliJ IDEA.
  User: jason
  Date: 16/9/17
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="servletAndBeans.*"%>
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
            background-color: #303031;
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
            background-color: #303031;
            margin-top: 10px;
            width: 100%;
            height: 10%;
            font-size: 2em;
            color: white;
            text-align: center;

        }

        #otherDetails {
            background-color: #303031;
            margin-top: 10px;
            width: 100%;
            height: 20%;
            margin-left: 20px;
            color: white;
            font-size: 1em;

        }

        #wall {
            width: 83%;
            height: 100%;
            margin-left: 17%;
            background-color: white;
            position: absolute;
            overflow: auto;
        }


        #messageBanner{
            color: white;
            width: 100%;
            height: 30%;
            background-color: #303031;
            position: relative;
            overflow: auto;

        }

        .speech-bubble {
            width: 95%;
            height: 70%;
            margin-left: 2%;
            margin-top: 1.5%;
            position: relative;
            background: #C63D0F;
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
            border-right-color: #C63D0F;
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

        #messageBox{
            color: white;
            resize: none;
            margin-left: 0.5%;
            margin-top: 0.5%;
            width: 98%;
            height: 90%;
            background-color: rgba(0, 0, 0, 0);
            border-color: rgba(0, 0, 0, 0)
        }

        textarea::-webkit-input-placeholder {
            color: white !important;
        }

        #messageSubmit {
            margin: 0.75% 5% 0 0;
            float: right;
            background-color: #C63D0F;
            color: white;
            border-radius: 18px;
            -moz-border-radius: 5px;
            border-color: #C63D0F;
        }

        .singleMessageBox {
            margin-top: 2%;
            border-radius: 25px;
            border: 2px solid #C63D0F;
            padding: 20px;
            width: 80%;
            height: 150px;
            box-shadow: 0 4px 2px -2px #C63D0F;
            position: relative
        }

        .likes{
            position: absolute;
            bottom: 0;
            margin-bottom: 5px;
            width: 100%;
            height: 10px;
            background-color: #C63D0F;
        }

    </style>
</head>
<body>

<jsp:include page = "/header.jsp"/><div id="userWall">
    <div id="bannerDiv">
        <div id="banner">
            <img id="profileImage" src="http://cdn2-www.dogtime.com/assets/uploads/gallery/pug-dog-breed-pictures/2-face.jpg">
            <div id="name">
                ${wall.name}
            </div><div id="otherDetails">
                <div>zID:  ${wall.userID}</div>
                <div>DOB:  </div>
                <div>Email:  </div>

            </div>
        </div>
    </div>
    <div id="wall">
        <div id="messageBanner">
            <form>
            <div class="speech-bubble">
                <textarea id="messageBox" placeholder="What are your thoughts today?"></textarea>
            </div>
                <input type="submit" value="Submit" id="messageSubmit">
            </form>
        </div>
        <div id="userMessagesBox">
            <p id="messagesHeading">Your Thoughts So Far</p>
            <div id="messages">
                <div class="singleMessageBox">

                    <div class="likes"> noob pls </div>
                </div>

                <div class="singleMessageBox">

                </div>

                <div class="singleMessageBox">

                </div>

                <div class="singleMessageBox">

                </div>


            </div>
        </div>

    </div>

</div>

</body>
</html>