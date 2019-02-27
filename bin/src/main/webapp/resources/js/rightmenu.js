/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
menuRight = document.getElementById('filter-nav'),
        showRight.onclick = function() {
            if ($("#show-hidehamburger").hasClass("is-open")) {
//                alert("is-open")
                $(".hamburger").trigger('click');
                $("#leftmenucontent").hide();
                $("#wrapper").css("padding-right", '250px');
                $("#wrapper").css("padding-left", '0px');
            } else {
//                alert("closed")
                $("#wrapper").css("padding-right", '250px');
                $("#wrapper").css("padding-left", '0px');
                $(".dataTables_scrollHeadInner").css("width", "100%");
                $(".table").css("width", "100%");
                $(".dataTables_scrollFootInner").css("width", "100%");
            }
            classie.toggle(this, 'active');
            classie.toggle(menuRight, 'cbp-spmenu-open');
//            $("#right-contentmenu").removeClass('col-lg-12 col-md-12 col-sm-12 col-xs-12').addClass('col-lg-10 col-md-10 col-sm-10 col-xs-10');
            $("#maincontainer").addClass("maincontainer");
            $("#maincontainer").removeClass("container_align");
            $("#showRight").attr('disabled', true);
            $(".dataTables_scrollHeadInner").css('width', '100% !important');
            $(".table").css('width', '100% !important');



        };
function fliterclose() {
//    alert("cross")
    $("#right-contentmenu").removeClass('col-lg-10 col-md-10 col-sm-10 col-xs-10').addClass('col-lg-12 col-md-12 col-sm-12 col-xs-12');
    $("#filter-nav").removeClass('cbp-spmenu-open');
    $("#showRight").removeClass('disabled');
    $("#maincontainer").removeClass("maincontainer");
    $("#maincontainer").removeClass("container_align");
    $("#showRight").removeAttr('disabled', false);
    $("#wrapper").css("padding-right", '0px');
    $(".dataTables_scrollHeadInner").css("width", "100%");
    $(".dataTables_scrollFootInner").css("width", "100%");
    $(".table").css("width", "100%");

}
$(function() {
    $("#cancel_user").click(function() {
        fliterclose();
    });
})

