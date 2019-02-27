<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty message}">
    <% response.sendRedirect("loginform");%>
</c:if>
<!-- session details.-->
<input type="hidden" id="user" value=" <c:out value="${message}"></c:out>"/><!-- session email id.-->
<input type="hidden" id="firstName" value=" <c:out value="${firstname}"></c:out>"/><!-- session firstname-->
<input type="hidden" id="menurole" value=" <c:out value="${menuroles}"></c:out>"><!-- session role.-->
<input type="hidden" id="customercordinalnumber" value=" <c:out value="${customercordinalnumber}"></c:out>"><!-- session role.-->
    <!-- session details.-->
    <!-- main page header section start here -->
    <!--banner-->
    <nav class="navbar navbar-default mynavigarion">
        <div class="container-flued">
            <div class="col-md-12">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand pull-left" href="Dashboard.html" ><img src="resources/images/OCCular Healthcare Logo-01.png" alt="LOGO" class="img-responsive" ></a>
                </div>
                <div class="collapse navbar-collapse navbar-right" id="myNavbar">

                </div>
            </div>
            <!--9390650822 sunitha-->
        </div>
    </nav><!-- Nav-bar end -->
    <script src="<c:url value="/resources/js/jquery-1.9.1.min.js"/>" type="text/javascript"></script>
<script>
    $(function() {// displaying session username dropdown menu 
        var urlpagename = location.href.split("/").slice(-1);
        urlpagename = urlpagename.toString().replace(/[#]/gi, '');

//         alert(urlpagename);
        var menurole = document.getElementById("menurole").value.trim();
        var sessionuser = document.getElementById("firstName").value;
        var firstletter = sessionuser.charAt(1);

        var rolearray = menurole.split("@");
        var row = $("<ul class='nav navbar-nav' id='mydivdata'></ul>");
        $("#myNavbar").append(row);
        for (var i = 0; i < rolearray.length - 1; i++) {
            var roledata = rolearray[i].split("^");
            var dynamic_id=roledata[0].replace(' ' ,'-'); 
            var lidynamic_id="li"+roledata[0].replace(' ' ,'-'); 
            var row = $("<li id="+lidynamic_id+"><span class='sel-lr' style='display:none'></span><a href=" + roledata[1] + "  id="+dynamic_id+" >" + roledata[0] + "</a><span class='sel-br' style='display:none'></span></li>");
            $("#mydivdata").append(row);
        }

        var row = $("<li class='dropdown'><a href='#' class='dropdown-toggle' role='button' aria-haspopup='true' data-toggle='dropdown'><span  id='userNamescript'>" + firstletter + "</span>" + sessionuser + " &nbsp;&nbsp;&nbsp;&nbsp;<i class='fa fa-bars fa-2x' aria-hidden='true'></i></a> <ul class='dropdown-menu' id='settingsdropdown'><li><a href='#'>User Settings</a></li><li><a href='logoutuser'>Logout</a></li></ul></li>");
        $("#mydivdata").append(row);

    });
</script>