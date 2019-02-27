<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="description" content="Finalize-Charges for occular health project ">
        <meta name="keywords" content="Finalize-Charges"><!-- For seo  -->
        <meta name="author" content="Info-ways">
        <meta name="author" content="Venkatesh">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--<title>Order Drugs || OCCULAR PHARMA</title>-->
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">  <!-- bootstrap css  -->
        <link href="<c:url value="/resources/css/font-awesome.min.css"/>" rel="stylesheet"> <!--  using for fonts like fb icons something... -->
        <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet"><!-- My CSS for main web page..   -->
        <link href="<c:url value="/resources/css/scroll.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/animate.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/fSelect.css"/>" rel='stylesheet' />
        <link href="<c:url value="/resources/css/jquery.fancybox.css"/>" rel="stylesheet"><!-- My fancybox css for displaying popup  -->
        <link href="<c:url value="/resources/css/jquery-ui.css"/>" rel="stylesheet">
        <link href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css" rel='stylesheet' />
        <link href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css" rel='stylesheet' />


    </head><!--/head-->
    <body>

        <section >

            <table class="table   tab-content tab-pane table-bordered table-responsive" id="orderpopupData">
                <thead>
                    <tr>
                        <th>CIN</th>
                        <th>Item on Contract</th>
                        <th>Corporate Description</th>
                        <th>NDC</th>
                        <th>Size Txt</th>
                        <th>Unit Dose</th>
                        <th>Contract Group Name</th>
                        <th>Sell Direct Price</th>
                        <th>Unit Price</th>
                        <th>Previously Purchased Date</th>
                    </tr>
                </thead>
            </table>

        </section>
        <%@include file="../common/footer.jsp" %>
        <script src="//code.jquery.com/jquery-1.12.4.js" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/jquery.fancybox.pack.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/fSelect.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/scroll.js"/>" type="text/javascript"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="//cdn.datatables.net/buttons/1.3.1/js/buttons.flash.min.js" type="text/javascript"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js" type="text/javascript"></script>
        <script src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/pdfmake.min.js" type="text/javascript"></script>
        <script src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/vfs_fonts.js" type="text/javascript"></script>
        <script src="//cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js" type="text/javascript"></script>
        <script src="//cdn.datatables.net/buttons/1.3.1/js/buttons.print.min.js" type="text/javascript"></script>
        <script>
            $(function() {
                excel();
            });
            function excel() {
//                alert("calling excel ");
                $.ajax({
                    type: "GET",
                    url: "getexcelorderdrugs",
                    contentType: 'application/x-www-form-urlencoded',
                    success: function(res) {
                        alert(JSON.stringify(res));
//                        $("#venkat").html(JSON.stringify(res));
                        var purdate = "";
                        var cin = "", corpdesc = "", ndc = "", sizetxt = "", unitdose = "", groupname = "", selldprice = "", unitprice = "", prepurdate = "";
                        var cdm = "";
                        var itemoncontract = "", itemoncontractstatus = "";

                        var jsonlist = JSON.stringify(res).split("^");
                        cin = jsonlist[0].split("@");
                        corpdesc = jsonlist[1].split("@");
                        ndc = jsonlist[2].split("@");
                        sizetxt = jsonlist[3].split("@");
                        unitdose = jsonlist[4].split("@");
                        groupname = jsonlist[5].split("@");
                        selldprice = jsonlist[6].split("@");
                        unitprice = jsonlist[7].split("@");
                        prepurdate = jsonlist[8].split("@");
                        itemoncontract = jsonlist[9].split("@");
//alert(jsonlist[9]);
//            finallist = cin + "^" + corpdesc + "^" + ndc +"^" +sizetxt+"^"+unitdose+"^"+groupname+"^"+selldprice+"^"+unitprice+"^"+prepurdate;


                        for (var i = 0; i < cin.length - 1; i++) {
//                            alert(itemoncontract[i]);
                            if (parseInt(itemoncontract[i]) === 1) {
                                itemoncontractstatus = "Yes";
                            }
                            else {
                                itemoncontractstatus = "No";
                            }
//                            alert(itemoncontractstatus)
                            var row = $("<tr><td>" + cin[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '') + "</td><td>" + itemoncontractstatus + "</td><td>" + corpdesc[i] + "</td><td>" + ndc[i] + "</td><td>" + sizetxt[i] + "</td><td>" + unitdose[i] + "</td><td>" + groupname[i] + "</td><td>" + selldprice[i] + "</td><td>" + unitprice[i] + "</td><td>" + prepurdate[i] + "</td></tr>");
                            $("#orderpopupData").append(row);
                        }
                        $('#orderpopupData').DataTable({
                            dom: 'Bfrtip',
                            buttons: [
                                'copy', 'csv', 'excel', 'pdf', 'print'
                            ]
                        });
                    }, error: function(e) {
                        alert(e + "error")

                    }
                });
            }
        </script>

        <div id
             ="venkat"> 
        </div>            
    </body>
</html>
