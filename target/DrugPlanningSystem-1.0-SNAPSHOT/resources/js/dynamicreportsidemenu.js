/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function sideMenu() {
    var pageInfo = "Generate Report";
//                alert(pageInfo);
    $.ajax({
        type: "POST",
        url: "getsidemenuformanagedata",
        data: "pageInfo=" + pageInfo,
        contentType: 'application/x-www-form-urlencoded',
        success: function(res) {
            var jsonlist = JSON.stringify(res);
            var functionsubmodules = "";
            var dynamicsublistid = "listdata";
            var finaldynamicdata = "";
            var finaldynamiclistdata = "";
            var activeliststatus = "";
            var dynamicclass="";
            $.each(JSON.parse(jsonlist), function(idx, value) {
                $.each(JSON.parse(JSON.stringify(value)), function(key, obj) {
//                                    alert(key)
                    finaldynamiclistdata = dynamicsublistid + idx;
                    activeliststatus = "generatereport" + idx;
                  dynamicclass="genarateclass"+obj;
                    if (key === 'functionSubmodule') {
                        if (functionsubmodules !== obj) {
                            functionsubmodules = obj;
                            var listdata = "<li data-target='#datamaintainance' class='active'><a href='#' title='" + functionsubmodules + "'> " + obj + " <span class='arrow'></span></a> <ul class='sub-menu datamaintainance' id='maintaindata'></ul></li>";
                            $("#menu-content").append(listdata);

                        } else {
                            functionsubmodules = obj;
                        }
                        if (functionsubmodules === "") {
                            functionsubmodules = obj;
                        }
                    }
                    if (key === 'functionName') {
//                                        alert(obj);
                        var sublistdata = "<li title='" + obj + "' class='"+dynamicclass+"' id='" + activeliststatus + "'><a id='" + finaldynamiclistdata + "'>" + obj + "</a></li>";
                        $("#maintaindata").append(sublistdata);
//                                        
                    }
                    if (key === 'functionUrl') {
                        $("#listdata" + idx).attr("href", obj);
                    }

                });

            });
            var urlpagename = location.href.split("/").slice(-1);
            urlpagename = urlpagename.toString().replace(/[#]/gi, '');
//            alert(urlpagename)
            if (urlpagename == "getdrugutilization") {
//                alert("iam in drug utilization")
                $(".genarateclassDrug").addClass("active");
            }
            else if (urlpagename == "getinventorystatus") {
                $(".genarateclassInventory").addClass("active");
            }
            else if (urlpagename == "getacknowledgementstatus") {
                $(".genarateclassPurchase").addClass("active");
            }
            else if (urlpagename == "getuploadstatus") {
                $(".genarateclassBatch").addClass("active");
            }
            else if (urlpagename == "getpricemasterlist") {
//                alert("iam in pricelist")
                $(".genarateclassPrice").addClass("active");
            }
            else if (urlpagename == "getpriceandvariance") {
//                alert("iam in pricelist")
                $(".genarateclassVariance").addClass("active");
            }
//                              break;
        }, error: function(e) {
            alert(e);
            $("#pageloaddiv").hide();
            $("#loading-content").hide();
        }
    });
}

