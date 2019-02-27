<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="description" content="Manage Data Module">
        <meta name="keywords" content=" Uploading Price master csv files,Upload invoice csv files,upload ndc define csv files,Upload dispense quantity,Assing CDM To NDC(s),Maintain Formids, Maintain Despense Factors and some other modules">
        <meta name="author" content="Info-ways">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Upload Dispense Quantity | Drugordering System</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>"/><!-- using bootstrap framework -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/><!--Main Css file for Drugordering system -->
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
        <link href="<c:url value="/resources/css/jquery-ui.css"/>" rel="stylesheet" type="text/css">
        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>
    <body onload="sideMenu()">
        <header><!-- main page header section start here -->
            <!--banner-->
            <%@include  file="../../common/header.jsp" %>
        </header><!--End of  main page header start here -->
        <div id="wrapper" class="toggled">
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
                    <span style="display: none" id="manage-menubtn-angle">MANAGE DATA</span>
                </button><!--End of Button for clicking hide and show -->
                <div class="col-lg-12"><!-- right side main content div start here -->
                    <div class="container_baground">
                        <div class=""><!-- row hasbeen removed for adjusting content -->
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="right-contentmenu">
                                <div id="gap_30"></div><!-- gap for maintaining spacing between divs -->
                                <div class="row">
                                    <div class="col-sm-8 col-sm-8 col-md-8">
                                        <label class="heading_font">Upload Dispense Quantity</label>
                                    </div>
                                </div>
                                <div id="gap_30"></div>
                                <div class="row">
                                    <div class="container" id="secondsearch_row">

                                        <div id="gap_30"></div>
                                        <div class="row col-lg-offset-2"><div class="col-lg-4"><span class='label label-info' id="upload-file-info"></span></div></div>

                                        <div class="col-lg-2 topclass padding-less" ><label>Choose Dispense Quantity File</label></div>
                                        <form:form  method="post" enctype="multipart/form-data" id="fileUploadForm">
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <input type="file" accept=".csv"  name="dispensequantity" id="dispensequantity" class="file">
                                                    <div class="input-group col-xs-12">
                                                        <input type="text" class="form-control" id="csvfile-invoice" disabled>
                                                        <span class="input-group-btn">
                                                            <button class="browse btn uploadbrowsebutton " type="button">Browse File</button>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-lg-2"><input type="submit" class="btn uploadbutton"  id="btnSubmit" value="Upload File"></div>
                                            </form:form>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-12 text-center" style="display: none;text-align: left" id="exceptionprint">
                                <h3 class="text-center"> <label class="text-danger">Dispense Quantity CSV File contains the following Exceptions</label></h3>
                                <div id="error-div"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer start here-->
        <div class="row text-center ">
            <div class="col-lg-12"><label class="footer"> &COPY; 2017 Occular Healthcare All Rights Reserved</label></div>
        </div>
        <!-- End of Footer-->
        <script src="<c:url value="/resources/js/jQuery-2.2.0.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/leftmenu.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/dynamicsidemenu.js"/>" type="text/javascript"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js" type="text/javascript"></script>
        <script>
        $(function() {
            $("#Manage-Data").addClass("active");
            if ($("#Manage-Data").hasClass("active")) {
                $("#liManage-Data .sel-lr").show();
                $("#liManage-Data .sel-br").show();
            }
            $("#dialog_upload").dialog({
                title: "Alert",
                modal: true,
                draggable: false,
                resizable: false,
                autoOpen: false,
                width: 400,
                show: "blind",
                hide: "explode",
                buttons: {
                    "Ok": function() {
                        $(this).dialog("close");
                        $(this).remove();
                        location.reload();
                    }
                }
            });
            $(document).on('click', '.browse', function() {
                var file = $(this).parent().parent().parent().find('.file');
                file.trigger('click');
            });
            $(document).on('change', '.file', function() {
                $(this).parent().find('.form-control').val($(this).val().replace(/C:\\fakepath\\/i, ''));
            });
            $("#btnSubmit").click(function(event) {
                var filelen = document.getElementById("dispensequantity").files.length;
//                alert(filelen)
                if (filelen == 0) {
                    $("#upload-file-info").html("please select file");
                    $("#upload-file-info").css("color", "red");
                    $("#upload-file-info").css("background-color", "transparent");
                    $("#upload-file-info").css("font-size", "12px");

                    event.preventDefault();

                } else {
                    //stop submit the form, we will post it manually.
                    event.preventDefault();
                    $("#pageloaddiv").show();
                    $("#loading-content").show();
                    // Get form
                    var form = $('#fileUploadForm')[0];

                    // Create an FormData object
                    var data = new FormData(form);

                    // If you want to add an extra field for the FormData
                    data.append("CustomField", "This is some extra data, testing");

                    // disabled the submit button
                    $("#btnSubmit").prop("disabled", true);

                    $.ajax({
                        type: "POST",
                        enctype: 'multipart/form-data',
                        url: "uploaddispenseqtycsv",//Controller Name is **DatamaintainenceController.java** inside (com.occularpharma.core.datamaintanence.controller)package
                        data: data,
                        processData: false,
                        contentType: false,
                        cache: false,
                        success: function(data) {
//                            alert(data)
                            $("#upload-file-info").hide();
                            var countrows = data.split("@");
                            var resulttext = "";
                            var errormsg = countrows[2].split("^");
                            if (errormsg.length > 0) {
                                for (var i = 0; i < errormsg.length - 1; i++) {
                                    var expcount = i + 1;
                                    var addexp = "<label class='text-warning text-left' ><span class='text-info'>" + expcount + ". </span> " + errormsg[i] + "</label>";
                                    $("#error-div").append(addexp);
                                    $("#exceptionprint").show();

                                }
                            }
                            if (parseInt(countrows[0]) > 0 && countrows[1] > 0) {
                                var colortext = "<span style='color:red'>" + countrows[1] + " records have an Exception </span>";
                                resulttext = countrows[0] + " records Inserted successfully " + colortext;
                                $("#dialog_upload").dialog("open");
                                $("#dialog_upload").html(resulttext);
                            } else if (parseInt(countrows[0]) > 0 && parseInt(countrows[1]) === 0 && parseInt(countrows[2]) === 0) {

                                resulttext = countrows[0] + " records Inserted successfully";
                                $("#dialog_upload").dialog("open");
                                $("#dialog_upload").html(resulttext);
                            } else if (parseInt(countrows[0]) < 0 && countrows[1] > 0 && countrows[2] > 0) {
                                resulttext = countrows[1] + " records Not Inserted";
                                $("#dialog_upload").dialog("open");
                                $("#dialog_upload").html(resulttext);

                            } else if (parseInt(countrows[0]) == 0 && parseInt(countrows[1]) === 0 && parseInt(countrows[2]) === 0) {
                                resulttext = countrows[0] + " records Inserted successfully";
                                $("#dialog_upload").dialog("open");
                                $("#dialog_upload").html(resulttext);
                            }
                            $("#pageloaddiv").hide();
                            $("#loading-content").hide();

                            $("div[role=dialog] button:contains('Ok')").css("background-color", "#5cb85c").focus();
                            $("div[role=dialog] button:contains('Ok')").css("color", "#fff");
                            console.log("SUCCESS : ", data);

                        },
                        error: function(e) {
                            alert("error" + e);
                            $("#result").text(e.responseText);
                            console.log("ERROR : ", e);
                            $("#btnSubmit").prop("disabled", false);
                            $("#pageloaddiv").hide();
                            $("#loading-content").hide();
                        }
                    });
                }
            });
        });
        document.onkeydown = function(evt) {
            var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
            if (keyCode == 13)
            {
                $("#btnSubmit").trigger('click');
            }
        }
        </script>
        <div id="loading-content" style="display: none"><p> Uploading..Please Wait...</p></div> 
        <div id="pageloaddiv" style="display: none"></div>
        <div id="dialog_upload" style="display: none">Uploaded successfully</div>
    </body>
</html>
