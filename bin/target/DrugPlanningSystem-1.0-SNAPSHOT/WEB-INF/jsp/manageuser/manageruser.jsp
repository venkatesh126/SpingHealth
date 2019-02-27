<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="utf-8">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta name="description" content="Manage Data Module">
        <meta name="keywords" content=" Assing CDM To NDC(s), Maintain Despense Factors and some other modules">
        <meta name="author" content="Info-ways">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Manage User || Drugordering System</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>"/><!-- using bootstrap framework -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/><!--Main Css file for Drugordering system -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/scroll.css"/>"/><!-- scroll.css for scroll moving -->
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/dataTable.bootstrap.min.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/dataTable.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/hummingbird-treeview.css"/>" />
        <link href="https://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>
        <style>
            li input[type="checkbox"],input[type="checkbox"]{
                vertical-align: bottom;
                margin: 0px 0px -5px;
            }

            li{
                list-style: none;
            }
            .modal-dialog {
                width:50%;
                overflow-y: hidden
            }
            #resetfooter{
                text-align: center;
            }
            #successdiv{
                color: green;

                /* font-size: 14px; */
                font-weight: 600;
                text-transform: capitalize;

            }
            .stylish-input-group .input-group-addon{
                background: white !important;
            }
            .stylish-input-group .form-control{
                //border-right:0;
                box-shadow:0 0 0;
                border-color:#ccc;
            }
            .stylish-input-group button{
                border:0;
                background:transparent;
            }

            .h-scroll {
                background-color: #fcfdfd;
                height: 260px;
                overflow-y: scroll;
            }
        </style>
    </head>
    <body onload="getAllusers();
            regkeys();">
        <header><!-- main page header section start here -->
            <!--banner-->
            <%@include  file="../common/header.jsp" %>
        </header><!--End of  main page header start here -->
        <div id="wrapper"><!-- main page Content body start here -->
            <div class="container-flued">
                <nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-right" id="filter-nav">
                    <div class=""><!-- row hasbeen removed for adjusting content -->
                        <div>
                            <div id="gap-5"></div>
                            <div class="col-lg-10"><h4 id="add_deleteuser-heading">Add User</h4></div>
                            <div class="col-lg-2 ">
                                <i class="fa fa-times" onclick="fliterclose();"  id="close-filter" style="font-size: 20px;cursor: pointer"></i></div>
                            <div class="col-lg-12">  
                                <small>First Name</small>
                                <input type="text" id="firstname"/>
                            </div>
                            <div id="gap-5"></div>
                            <div class="col-lg-12">
                                <small>Last Name</small>
                                <input type="text" id="lastname"/>
                            </div>
                            <div id="gap-5"></div>
                            <div class="col-lg-12">
                                <small>Email</small>
                                <input type="text" id="email" />
                            </div>
                            <div id="gap-5"></div>

                            <div class="col-lg-12" id="reg-keydiv">
                                <small>Registration Key</small>
                                <input type="text" disabled="" id="reg-key" value="1235sdfhjksdf746749"/>
                            </div>
                            <div class="col-lg-12">
                                <small>Grant Access</small>
                            </div>
                            <!--                            <div class="col-sm-12" id="treeview-checkbox">
                            
                                                            <ul id="dynamicul">
                            
                                                            </ul> 
                            
                                                        </div>  -->
                            <div id="treeview_container" class="hummingbird-treeview well h-scroll-large">

                                <ul id="treeview" class="hummingbird-base">
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="col-lg-5 col-lg-offset-1 col-md-5 col-sm-5 col-xs-6">
                                <button class="btn primarybutton pull-right " id="adduserbtn" onclick="adduser();">Add User</button>
                                <button class="btn primarybutton pull-right " id="updateuserbtn" style="display: none" onclick="updateuser();">Update</button>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                <button class="btn secondarybutton" id="cancel_user">Cancel</button>
                            </div>
                        </div>
                    </div>
                </nav>
                <div class="col-lg-12">
                    <div class="container_baground"><!-- Main page content start here -->
                        <div class="">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="right-contentmenu">


                                <div class=""><!-- heading content start here -->
                                    <div id="gap_30"></div><!-- gap for maintaining spacing between divs -->
                                    <div class="col-sm-8 col-lg-9 col-md-8 padding-less" >
                                        <label class="heading_font">Manage Users</label>
                                    </div>
                                    <div class="col-sm-2 col-lg-2 col-md-2 padding-less">

                                    </div>

                                    <div class="col-sm-2 col-lg-1 col-md-2 padding-less">
                                        <button class="btn btn-primary search-button" id="showRight" type="button"><i class="fa fa-plus" style="color:#FFFFFF;"></i> Add user</button>
                                    </div>

                                </div><!-- End of First page Main heading -->   
                                <div id="gap_30"></div>
                                <div    id="successdiv"></div>
                                <div class="row">
                                    <div class='table-responsive'>
                                        <table class="table tab-content tab-pane table-striped display" cellspacing='0' cellpadding='0' id="manageruser_table">
                                            <thead>
                                                <tr>
                                                    <th class="">First Name</th>
                                                    <th class="">Email</th>
                                                    <th class=" text-center">Registered?</th>
                                                    <th class=" text-center">Enabled?</th>
                                                    <th class=" text-center">Edit Details</th>
                                                    <th class=" text-center">Reset Password</th>
                                                    <th class=" text-center">Delete</th>
                                                </tr>
                                            </thead>
                                        </table>
                                    </div>                    
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <div class="modal fade" id="myModal" role="dialog"  data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h5 class="modal-title text-center"><label style="color:#1B6C8C">Reset Password</label></h5>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="col-lg-4">
                                        <small>Old Password:</small>

                                    </div>
                                    <div class="col-lg-6">
                                        <input type="text" id="oldpassword" placeholder="Enter Existing Password"/>
                                        <input type="hidden" id="hiddenemail"/>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="col-lg-4">
                                        <small>New Password:</small>
                                    </div>
                                    <div class="col-lg-6">
                                        <input type="text" id="newpassword" placeholder="Enter New Password"/>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="col-lg-4">
                                        <small>Confirm Password:</small>
                                    </div>
                                    <div class="col-lg-6">
                                        <input type="password" id="confirmpassword"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer " id="resetfooter">
                            <button class="btn primarybutton " onclick="resetpassword();">Update</button>
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
        <script src="<c:url value="/resources/js/jquery.dataTable.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/dataTable.bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/logger.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/treeview.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/rightmenu.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/classie.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/hummingbird-treeview.js"/>" type="text/javascript"></script>

        <script>

                                $(function() {
//                                    onloadgetroles();
                                    $("#Manage-user").addClass("active");
                                    if ($("#Manage-user").hasClass("active")) {
                                        $("#liManage-user .sel-lr").show();
                                        $("#liManage-user .sel-br").show();
                                    }
                                    $("#treeview").hummingbird();

                                })
                                $("#showRight").click(function() {
                                    $("#add_deleteuser-heading").html("Add User");
                                    $("#firstname").val('');
                                    $("#lastname").val('');
                                    $("#email").val('');
                                    $("#adduserbtn").show();
                                    $("#updateuserbtn").hide();
                                    $('input:checkbox[name="check_name"]').prop('checked', false);

                                })
                                function onloadgetroles() {
                                    $.ajax({
                                        type: 'GET',
                                        url: "getrolemenu",//Controller Name is **Profilecontroller.java** inside (com.occularpharma.core.Profilecontroller.controller)package
                                        contentType: 'application/x-www-form-urlencoded',
                                        success: function(data) {
                                            var jsonlist = JSON.stringify(data);
//                                              localStorage.setItem("rolelids", jsonlist);

                                            var functionmodules = [];

                                            $.each(JSON.parse(jsonlist), function(idx, value) {
                                                functionmodules.push(value[2]);

                                            });
                                            var uniqueNames = [];

                                            $.each(functionmodules, function(i, el) {
                                                if ($.inArray(el, uniqueNames) === -1)
                                                    uniqueNames.push(el);
                                            });
                                            for (var i = 0; i < uniqueNames.length; i++) {
                                                var uniqvalue = "";
                                                $.each(JSON.parse(jsonlist), function(idx, value) {
                                                    if (value[2] == value[1]) {
                                                        if (value[2] == uniqueNames[i]) {

                                                            uniqvalue = value[0];
                                                        }
                                                    }
//                                                    if (value[2] !== value[1]) {
//                                                        if (value[2] == uniqueNames[i]) {
//
//                                                            uniqvalue = 0;
//                                                        }
//                                                    }
                                                });
                                                var dynamicid = uniqueNames[i].replace(" ", "_");
                                                var plus_dynamicid = "plus_" + uniqueNames[i].replace(" ", "_");

                                                var row = $('<li id="' + dynamicid + '"><i class="fa fa-plus" id="' + plus_dynamicid + '"></i> <label> <input class="getcheckvalues" id="node-1" name="check_name" data-id="custom-1" type="checkbox" onclick="doSelection(\'' + dynamicid + '\')" data-value="' + uniqvalue + '">' + uniqueNames[i] + '</label></li>')
                                                $("#treeview").append(row);
                                            }
                                            var templist = "";
                                            $.each(JSON.parse(jsonlist), function(idx, value) {
                                                var dynamicid = value[2].replace(" ", "_");
                                                var plus_dynamicid = "plus_" + dynamicid;
                                                var showid = 0;
                                                if (value[1] !== value[2]) {
                                                    showid = 1;
                                                    var dynamic_ullist = "ul_" + value[2].replace(" ", "_");

                                                    if (templist !== dynamic_ullist) {

                                                        templist = dynamic_ullist;
                                                        var rowli = $("<ul id=" + templist + " ></ul>");

                                                        $("#" + dynamicid).append(rowli);
                                                        $("#" + templist + "").css("padding-left", "20px")


                                                    }
                                                    var rowli = $("<li><label> <input class='getcheckvalues' id='node-1-1' data-id='custom-1-1' type='checkbox'  name='check_name'  data-value='" + value[0] + "'>" + value[1] + "</label></li>");

                                                    $("#" + dynamic_ullist).append(rowli);
                                                }


                                                if (showid <= 0) {
                                                    $("#" + plus_dynamicid).hide();
                                                } else {
                                                    $("#" + plus_dynamicid).show();
                                                }

                                            });
                                            $("#treeview").hummingbird();

                                        }, error: function(e) {
                                            alert(e);
                                        }
                                    });
                                }

                                function doSelection1(n) {
//                                    alert("doselection")
                                    var temp = n;
                                    n = "#ul_" + n;
                                    var manual_update = $("input[name='" + temp + "']:checked").val();
                                    if (manual_update == "on") {
                                        $(n).hummingbird("checkAll");
                                    } else {
                                        $(n).hummingbird("uncheckAll");
                                    }
                                }
                                function getAllusers() {

                                    $('#manageruser_table').DataTable().destroy();
                                    $.ajax({
                                        type: 'GET',
                                            url: "showallusers",//Controller Name is **Profilecontroller.java** inside (com.occularpharma.core.Profilecontroller.controller)package
                                        contentType: 'application/x-www-form-urlencoded',
                                        success: function(data) {
                                            //                                                                
                                            //                                                                alert(data);
                                            var jsonlist = JSON.stringify(data)
                                            //                                                                $("#venkat").html(jsonlist)
                                            $("#manageruser_table tr#dynamictablerow").remove();
                                            $.each(JSON.parse(jsonlist), function(idx, value) {
                                                //                                                                    alert(value[3])
                                                var regstatus = "";
                                                var enablestatus = "";
                                                if (value[4] == '1') {
                                                    regstatus = "YES";
                                                    $('#regstatuscolor').addClass("text-success");
                                                } else {
                                                    regstatus = "NO";
                                                    $("#regstatuscolor").addClass("text-danger");
                                                }
                                                if (value[2] == '1') {
                                                    enablestatus = "<label class='switch'>  <input type='checkbox' checked  id='checkuserstatus" + idx + "'> <div class='slider round'></div></label>";
                                                } else {
                                                    enablestatus = "<label class='switch'>  <input type='checkbox' id='checkuserstatus" + idx + "'> <div class='slider round'></div></label>";
                                                }
                                                var editimg = "<img src='resources/images/edit.png'/>";
                                                var resetimg = "<img src='resources/images/reset.png'/>";
                                                var deleteimg = "<img src='resources/images/delete-user.png'/>";
                                                var row = $("<tr id='dynamictablerow'><td >" + value[1] + "</td><td id='useremail" + idx + "'>" + value[0] + "</td><td class='text-success text-center'>" + regstatus + "</td><td class='text-center'><a onchange=enable_disableuser(\'" + idx + "\');>" + enablestatus + "</a></td><td class='text-center'><a onclick='edituser(" + idx + ")'; style='cursor:pointer'>" + editimg + "</a></td><td class='text-center'><a data-toggle='modal' data-target='#myModal' onclick=resetusermail(\'" + value[0] + "\')>" + resetimg + "</a></td><td class='text-center'><a id='delete_image' onClick=deleteuser(\'" + value[0] + "\')>" + deleteimg + "</a></td></tr>");
                                                $("#manageruser_table").append(row);
                                            });
                                            //                                                                
                                            $('#manageruser_table').DataTable({
                                                paging: false,
                                                "bSort": false,
                                                "bInfo": false,
                                                "oLanguage": {"sSearch": ""},
                                                "language": {
                                                    "emptyTable": "No data available"
                                                }

                                            });
                                        }, error: function(e) {
                                            alert(e);
                                        }
                                    });
                                }

                                function deleteuser(n) {
                                    $("#successdiv").show();
                                    var status = n;
                                    //                                                        alert(status)
                                    $.ajax({
                                        type: 'POST',
                                        url: "delete_manageuser",//Controller Name is **Profilecontroller.java** inside (com.occularpharma.core.Profilecontroller.controller)package
                                        data: "&status=" + status,
                                        contentType: 'application/x-www-form-urlencoded',
                                        success: function(data) {
                                            $("#successdiv").html(data);
                                            $("#successdiv").delay(1000).slideUp(function() {
                                                $("#successdiv").html("");
                                            });
                                            //                                            alert(data);
                                            if (data == "User Deleted successfully") {
                                                getAllusers();
                                            }

                                        }, error: function(data) {
                                            alert(data)
                                        }
                                    });
                                }
                                function regkeys() {
                                    //                alert("iam in key");
                                    var text = "";
                                    var possible = "ABCDEFGHIkLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                                    for (var i = 0; i < 10; i++) {
                                        text += possible.charAt(Math.floor(Math.random() * possible.length));
                                    }
                                    $("#reg-key").val(text);

                                    onloadgetroles();
                                }

                                function selectall(n) {
                                    var list = n.slice(0, -4);
                                    var namelist = document.getElementsByName(n);
                                    //                                alert(namelist.length);
                                    if (document.getElementById(list).checked) {
                                        for (var i = 0; i < namelist.length; i++) {
                                            namelist[i].checked = true;
                                        }
                                    } else {
                                        for (var i = 0; i < namelist.length; i++) {
                                            namelist[i].checked = false;
                                        }
                                    }

                                }
                                function enable_disableuser(n) {
                                    $("#successdiv").show();
                                    var checkstatus = document.getElementById("checkuserstatus" + n);
                                    var email = document.getElementById("useremail" + n).innerHTML;
                                    //                                                        alert(email)
                                    var checkvalue = "";
                                    //                                                        alert(checkstatus.checked);
                                    if (checkstatus.checked === true) {
                                        checkvalue = "1";
                                        document.getElementById("checkuserstatus" + n).setAttribute("checked", "");
                                    } else {
                                        checkvalue = "0";
                                        document.getElementById("checkuserstatus" + n).removeAttribute("checked", "");
                                    }

                                    //                                                        alert(checkvalue);
                                    $.ajax({
                                        type: 'POST',
                                        url: "enableu_disablestatus",//Controller Name is **Profilecontroller.java** inside (com.occularpharma.core.Profilecontroller.controller)package
                                        contentType: 'application/x-www-form-urlencoded',
                                        data: "&status=" + checkvalue + "&email=" + email,
                                        success: function(data) {
                                            $("#successdiv").html(data);
                                            $("#successdiv").delay(1000).slideUp(function() {
                                                $("#successdiv").html("");
                                            });
                                        }, error: function(data) {

                                        }
                                    });
                                }
                                function edituser(n) {
//alert("edit userr")
                                    $("#add_deleteuser-heading").html("Edit User");
                                    $("#reg-keydiv").hide();

                                    var email = document.getElementById("useremail" + n).innerHTML;
//                                    alert(email);
                                    $("#firstname").val('');
                                    $("#lastname").val('');
                                    $("#email").val('');
                                    $('input:checkbox[name="check_name"]').prop('checked', false);
                                    if (!$("#filter-nav").hasClass("cbp-spmenu-open")) {
                                        $("#showRight").trigger('click');
                                    }
                                    $("#updateuserbtn").show();
                                    $("#adduserbtn").hide();
//                                    alert(email);
                                    $.ajax({
                                        type: 'POST',
                                        url: "edituser",//Controller Name is **Profilecontroller.java** inside (com.occularpharma.core.Profilecontroller.controller)package
                                        data: "usermail=" + email,
                                        contentType: 'application/x-www-form-urlencoded',
                                        success: function(data) {
                                            $("#main-content").hide();
                                            $("#usr-row").show();
                                            $("#button-row").hide();
                                            $("#row-title").show();
                                            var jsonlist = JSON.stringify(data);
//                                            alert(jsonlist);
                                            $.each(JSON.parse(jsonlist), function(idx, value) {
                                                $.each(JSON.parse(JSON.stringify(value)), function(key, obj) {
//                                                    alert(idx)
                                                    if (idx === "geteditlist") {
//                                                        if ($("#filter-nav").hasClass("cbp-spmenu-open")) {
//                                                            $("#showRight").trigger('click');
                                                        $("#add_deleteuser-heading").html("Edit User");
//                                                        alert(obj[0] + "hkjk")
                                                        $("#firstname").val(obj[0]);
                                                        $("#lastname").val(obj[1]);
                                                        $("#email").val(obj[2]);
                                                        $("#hiddenuserid").val(obj[4]);
//                                                        }
                                                    }
                                                    if (idx === "getrolelist") {
//                                                        alert(obj)
                                                        var checkid = document.getElementsByName("check_name");
                                                        for (var i = 0; i < checkid.length; i++) {
                                                            if ($(checkid[i]).data("value") === obj) {
//                                                                checkid[i].checked=true;
                                                                $('input:checkbox[name="check_name"][data-value="' + obj + '"]').prop('checked', true);
//                                                                $(checkid[i]).hummingbird("checkAll");
                                                            }
                                                        }
                                                    }

                                                });

                                            });
                                        }, error: function(e) {
                                            alert("rtt" + e);
                                        }
                                    });
                                }

                                function resetpassword() {
                                    $("#successdiv").show();
                                    //                                    alert("password change");
                                    var email = document.getElementById("hiddenemail").value;
                                    //                                     alert(email);
                                    var oldpass = document.getElementById("oldpassword").value;
                                    var newpass = document.getElementById("newpassword").value;
                                    var confirmpass = document.getElementById("confirmpassword").value;
                                    if (newpass !== confirmpass) {
                                        alert("password doesnt matched")
                                    } else {
                                        $.ajax({
                                            type: 'POST',
                                            url: "resetuserpassword",//Controller Name is **Profilecontroller.java** inside (com.occularpharma.core.Profilecontroller.controller)package
                                            data: "usermail=" + email + "&pass=" + confirmpass,
                                            contentType: 'application/x-www-form-urlencoded',
                                            success: function(data) {
                                                $("#successdiv").html(data);
                                                $("#successdiv").delay(1000).slideUp(function() {
                                                    $("#successdiv").html("");
                                                });
                                            }, error: function(e) {
                                                alert(e);
                                            }
                                        });
                                    }
                                }
                                function resetusermail(n) {
                                    //                                    alert(n);
                                    $("#hiddenemail").val(n);
                                }

                                function adduser() {// Add New User 
                                    $("#successdiv").show();
                                    var fname = document.getElementById("firstname").value;
                                    var lname = document.getElementById("lastname").value;
                                    var email = document.getElementById("email").value;
                                    var regkey = document.getElementById("reg-key").value;
//                                    var getroles = $('#treeview_container').treeview('selectedValues');//getcheckvalues
//                                    var checkedValue = $('.getcheckvalues:checked').val();
//                                    var checkedValue = $('.getcheckvalues:checkbox:checked').val();
                                    var getroles = $('input:checkbox:checked.getcheckvalues').map(function() {
                                        return $(this).data("value");
                                    }).get();

                                    if (fname == "") {
                                        document.getElementById("firstname").style.border = "1px solid red";
                                        document.getElementById("firstname").focus() ;
                                    } else if (email == "") {
                                        document.getElementById("email").style.border = "1px solid red";
                                        document.getElementById("email").focus() ;
                                        document.getElementById("firstname").style.border = "1px solid #b7b7b7";
                                    } else {
                                        document.getElementById("firstname").style.border = "1px solid #b7b7b7";
                                        document.getElementById("firstname").style.border = "1px solid #b7b7b7";
//                                    alert(values);
                                        $("#pageloaddiv").show();
                                        $("#loading-content").show();
                                        $.ajax({
                                            type: 'POST',
                                            url: "adminregistration",//Controller Name is **Securityquestions.java** inside (com.occularpharma.core.login.controller)package
                                            data: "adminemail=" + email + "&adminfirstname=" + fname + "&adminlastname=" + lname + "&regkey=" + regkey + "&role=" + getroles,
                                            contentType: 'application/x-www-form-urlencoded',
                                            success: function(data) {
                                                alert(data);
                                                $("#pageloaddiv").hide();
                                                $("#loading-content").hide();
                                                $("#successdiv").html(data);
                                                $("#successdiv").delay(1000).slideUp(function() {
                                                    $("#successdiv").html("");
                                                });
                                                fliterclose();
                                                //                                            $("#successdiv").html(data);
                                                //                                             $('#successdiv').show();
                                                getAllusers();
                                            }, error: function(e) {
                                                alert(e);
                                            }
                                        });
                                    }
                                }
                                function updateuser() {// Update User
//                                    alert("update user")
                                    var fname = document.getElementById("firstname").value;
                                    var lname = document.getElementById("lastname").value;
                                    var email = document.getElementById("email").value;
                                    var userid = document.getElementById("hiddenuserid").value;
                                    var getroles = $('input:checkbox:checked.getcheckvalues').map(function() {
                                        return $(this).data("value");
                                    }).get();
//                                    $("#pageloaddiv").show();
//                                    $("#loading-content").show();

//                                    alert(getroles);
                                    $.ajax({
                                        type: 'POST',
                                        url: "updateuserdetails",//Controller Name is **Securityquestions.java** inside (com.occularpharma.core.login.controller)package
                                        data: "adminemail=" + email + "&adminfirstname=" + fname + "&adminlastname=" + lname + "&userid=" + userid + "&role=" + getroles,
                                        contentType: 'application/x-www-form-urlencoded',
                                        success: function(data) {
                                            alert(data);
                                           fliterclose();
                                           
                                        }, error: function(e) {
                                            alert(e);
                                        }
                                    });
                                }
        </script>
        <div id="pageloaddiv" style="display: none"><img  src="<c:url value="/resources/images/30.gif"/>"></div>
        <div id="loading-content" style="display: none"><p> Processing..Please Wait...</p></div> 
        <input type="hidden" id="hiddenuserid"/>
        <!--<div id="venkat"></div>-->
    </body>

</html>