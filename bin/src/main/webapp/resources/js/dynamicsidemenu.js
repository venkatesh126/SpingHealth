/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function sideMenu() {
//    alert("sidemenu calling")
    var pageInfo = "Manage Data";
//            alert(pageInfo);
    $.ajax({
        type: "POST",
        url: "getsidemenuformanagedata",
        data: "pageInfo=" + pageInfo,
        contentType: 'application/x-www-form-urlencoded',
        success: function(res) {

            var jsonlist = JSON.stringify(res);
            var functionsubmodules = "";
            var dynamicid = "maintaindata";
            var dynamicsublistid = "listdata";
            var finaldynamicdata = "";
            var finaldynamiclistdata = "";
            var lidynamicid = "";
            var incval = 0;
            var managedata = "managedata";

            $.each(JSON.parse(jsonlist), function(idx, value) {
                $.each(JSON.parse(JSON.stringify(value)), function(key, obj) {
                    finaldynamicdata = dynamicid + idx;
                    finaldynamiclistdata = dynamicsublistid + idx;
                    lidynamicid = managedata + idx;
//                    alert(key)
                    var replaceid = "";
                    if (key === 'functionSubmodule') {
                        if (functionsubmodules !== obj) {
                            functionsubmodules = obj;
                            replaceid = functionsubmodules.replace(" ", "_");
                            var dynamicli = "li" + replaceid;
                            var listdata = "<li id='" + dynamicli.replace(' ', '-') + "'  data-target='#datamaintainance'><a   id='" + replaceid.replace(' ', '_') + "' title='" + functionsubmodules + "'> " + obj + " <span class='arrow'></span></a> <ul class='sub-menu datamaintainance'   id='" + finaldynamicdata + "'></ul></li>";
                            $("#menu-content").append(listdata);
                            incval = idx;
                        } else {
                            functionsubmodules = obj;
                        }
                        if (functionsubmodules === "") {
                            functionsubmodules = obj;
                        }
                    }
                    if (key === 'functionName') {
                        var sublistdata = "<li title='" + obj + "' id='" + lidynamicid + "'><a id='" + finaldynamiclistdata + "'>" + obj + "</a></li>";
                        $("#maintaindata" + incval).append(sublistdata);
                    }
                    if (key === 'functionUrl') {

                        $("#listdata" + idx).attr("href", obj);


                    }
                });
            });
            var urlpagename = location.href.split("/").slice(-1);
            urlpagename = urlpagename.toString().replace(/[#]/gi, '');
            if (urlpagename == "getassignCdmNdcs") {
                $("#managedata0").addClass("active");
                $("#liMaster_Data-Maintanance").addClass("active");
            }
            else if (urlpagename == "getuploadscopeofpurchase") {
                $("#managedata7").addClass("active");
                $("#liMaster_Data-Maintanance").addClass("active");

            }
            else if (urlpagename == "getmaintaindispensefactors") {
                $("#managedata3").addClass("active");
                $("#liMaster_Data-Maintanance").addClass("active");
            }
            else if (urlpagename == "getutilizationparameters") {
                $("#managedata1").addClass("active");
                $("#liMaster_Data-Maintanance").addClass("active");
            }
            else if (urlpagename == "getupdateinventorybalance") {
                $("#managedata6").addClass("active");
                $("#liMaster_Data-Maintanance").addClass("active");
            }
            else if (urlpagename == "maintainformids") {
                $("#managedata4").addClass("active");
                $("#liMaster_Data-Maintanance").addClass("active");
            }
            else if (urlpagename == "displaybudget") {
                $("#managedata2").addClass("active");
                $("#liMaster_Data-Maintanance").addClass("active");
            }
            else if (urlpagename == "getuploadpricemaster") {
                $("#managedata12").addClass("active");
                $("#liData_loads").addClass("active");
            }
            else if (urlpagename == "getuploadinvoicdetails") {
                $("#managedata10").addClass("active");
                $("#liData_loads").addClass("active");
            }
            else if (urlpagename == "getuploaddispensequantity") {
                $("#managedata8").addClass("active");
                $("#liData_loads").addClass("active");
            }
            else if (urlpagename == "getuploadndcdefine") {
                $("#managedata9").addClass("active");
                $("#liData_loads").addClass("active");
            }
            else if (urlpagename == "getuploadpoacknowledge") {
                $("#managedata11").addClass("active");
                $("#liData_loads").addClass("active");
            }
            else if (urlpagename == "getmaintainanceparlevels") {
                $("#managedata5").addClass("active");
                $("#liMaster_Data-Maintanance").addClass("active");
            }
            else if (urlpagename == "defaultmangepage") {
                $("#maintaindata8").addClass("active");
                $("#liMaster_Data-Maintanance").addClass("active");
            }
            else if (urlpagename == "getpricemasterlist") {
//                $("#maintaindata8").addClass("active");
                $("#liMaster_Data-Maintanance").addClass("active");
            }
            
          
            $("#Master_Data_Maintanance").click(function() {
                $("#maintaindata0").show();
                $("#maintaindata8").hide();
            });
            $("#Data_loads").click(function() {
//                alert("load active")
                $("#maintaindata0").hide();
                $("#maintaindata8").show();
            });
            if ($("#liMaster_Data-Maintanance").hasClass("active")) {
//                alert("master active")
                $("#maintaindata0").show();
                $("#maintaindata8").hide();
            }
            if ($("#liData_loads").hasClass("active")) {
//                alert("load active")
                $("#maintaindata0").hide();
                $("#maintaindata8").show();
            }

        }, error: function(e) {
            alert(e);
            $("#pageloaddiv").hide();
            $("#loading-content").hide();
        }
    });
}

