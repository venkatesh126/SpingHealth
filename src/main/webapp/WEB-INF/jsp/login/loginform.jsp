<%@page import="javassist.bytecode.stackmap.BasicBlock.Catch"%>
<%@page import="com.occularpharma.core.login.controller.TrippleDes"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%   String decryptemail = "";
    try {

        String convertuname = request.getParameter("username");

        //  System.out.println(convertuname.length());
        if (convertuname.length() > 5) {
            TrippleDes td = new TrippleDes();
            decryptemail = td.decrypt(convertuname);
//            System.out.println(decryptemail + "decrypt mail is");
        }
    } catch (NullPointerException e) {
//        e.printStackTrace();
    }
%>    
<html>
    <head>
        <meta name="description" content="Login Module">
        <meta name="keywords" content="Drugordering system login page">
        <meta name="author" content="Info-ways">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta charset="UTF-8">
        <title>Login | Drug ordering system</title>
        <link href="<c:url value="resources/css/font-awesome.min.css"/>" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>"/><!-- using bootstrap framework -->
        <link href="<c:url value="resources/css/login.css"/>" rel="stylesheet">
        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>
        <style>

            label{
                display: inline !important;
            }
           .form input[type="checkbox"] {
                float: left;
                margin-left: -15px;
                    margin-top: 3px;
            }
            .checkbox{
                padding-left: 0px;
            }
        </style>
    </head>
    <body >
        <div id="login-page">
            <div class="form"><!-- Login Form Div -->
                <p class="heading_mess"><img src="resources/images/OCCular Healthcare Logo-01.png" alt="logo"/></p>
                    <form:form action="loginform"  commandName="loginForm">
                    <div class="login-form myform" >
                        <div id="main-textname"><label>Username</label></div>
                        <p id="logrestext" class="usernotexist" style="color:red;font-size:14px "><form:errors path="userName" /></p>
                        <label id="username-label">
                            <form:input path="userName" id="userName" class="userName" placeholder="Please Enter User Name"/>
                        </label>
                        <div  id="main-textname">Password</div>
                        <p id="logrestext"  style="color:red;font-size:14px"><form:errors path="password" /></p>
                        <label id="password-label">
                            <form:password path="password" id="password" placeholder="Please Enter password"/>
                        </label>

                        <div class="col-lg-4 col-sm-4 col-md-4 col-xs-4">
                            <label class="checkbox" id="main-textname"> <input type="checkbox" value="remember-me" id="remember_me">
                                Remember me</label>
                        </div>
                        <div class="col-lg-8 col-sm-8 col-md-8 col-xs-8">
                            <button id="login" style="opacity:  0;" disabled>Login</button>
                        </div> 
                        <!--<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">-->
                        <div><button id="login" >Login</button> 
                            <p class="trouble-heading"><a>Have Troubled logging in ?</a></p></div>
                        <!--</div>-->
                    </div>
                </div>

            </form:form>
        </div><!-- /Login Form Div -->
    </div>
    <script src="<c:url value="/resources/js/jQuery-2.2.0.min.js"/>" type="text/javascript"></script>
    <script>

        $(function() {
            if (localStorage.chkbx && localStorage.chkbx != '') {
                $('#remember_me').attr('checked', 'checked');
                $('#userName').val(localStorage.usrname);
                $('#password').val(localStorage.pass);
            } else {
                $('#remember_me').removeAttr('checked');
                $('#userName').val('');
                $('#password').val('');
            }
            $('#remember_me').click(function() {
                if ($('#remember_me').is(':checked')) {
                    localStorage.usrname = $('#userName').val();
                    localStorage.pass = $('#password').val();
                    localStorage.chkbx = $('#remember_me').val();
                } else {
                    localStorage.usrname = '';
                    localStorage.pass = '';
                    localStorage.chkbx = '';
                }
            });
        });
    </script>          
</body>
</html>