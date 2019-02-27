<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="utf-8">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta name="description" content="Manage Data Module">
        <meta name="keywords" content=" Assing CDM To NDC(s), Maintain Despense Factors and some other modules">
        <meta name="author" content="Info-ways">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inventory Status || Drugordering System</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>"/><!-- using bootstrap framework -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/><!--Main Css file for Drugordering system -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/scroll.css"/>"/><!-- scroll.css for scroll moving -->
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>
    </head>
    <body onload="sideMenu();">
        <header><!-- main page header section start here -->
            <%@include  file="../common/header.jsp" %>
        </header><!--End of  main page header start here -->
        <div id="wrapper" class="toggled"><!-- main page Content body start here -->
            <div class="container-flued">
                <div class="col-lg-2">
                    <div class="nav-side-menu"><!-- Left sidemenu collapsed  nav bar -->
                        <div class="menu-list"><!-- Left sidemenu item menu list -->
                            <nav class="navbar  navbar-fixed-top" id="sidebar-wrapper" role="navigation" title="Left Navigation Menu">
                                <ul id="menu-content" class="menu-content nav sidebar-nav"> 

                                </ul>
                            </nav>
                        </div><!-- Left sidemenu item menu list -->
                    </div><!-- End of Left sidemenu collapsed  nav bar -->
                </div>

                <button type="button" title="click to hide/show" class="hamburger .is-open is-open" data-toggle="offcanvas" id="show-hidehamburger"><!-- Button for clicking hide and show -->
                    <i id="left-menu-angle" class="fa fa-2x fa-angle-right"></i>
                    <span style="display: none" id="manage-menubtn-angle">REPORT</span>
                </button><!--End of Button for clicking hide and show -->
                <div class="col-lg-12">
                    <div class="container_baground"><!-- Main page content start here -->
                        <div id="gap_30"></div>
                        <div class="col-sm-8 col-lg-10 col-md-9 col-xs-12">
                            <label class="heading_font">Select an option from the Generate Report menu</label>
                        </div>
                    </div><!-- End of div container Main page content -->
                </div><!-- End of right side main content div  -->
            </div><!-- End of wrapper div-->
        </div><!-- End of wrapper div-->
        <!-- Footer start here-->
        <div class="row text-center ">
            <div class="col-lg-12"><label class="footer"> &COPY; 2017 Occular Healthcare All Rights Reserved</label></div>
        </div>
        <!-- End of Footer-->

        <script src="<c:url value="/resources/js/jQuery-2.2.0.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/leftmenu.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/dynamicreportsidemenu.js"/>" type="text/javascript"></script>

        <script>
        $(function() {
            $("#Generate-Report").addClass("active");
            if ($("#Generate-Report").hasClass("active")) {
                $("#liGenerate-Report .sel-lr").show();
                $("#liGenerate-Report .sel-br").show();

            }
        })
        </script>
    </body>
</html>
