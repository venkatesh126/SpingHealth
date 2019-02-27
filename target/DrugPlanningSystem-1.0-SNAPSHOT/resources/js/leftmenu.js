$(document).ready(function() {
    $('.hamburger').addClass('.is-open');
    var trigger = $('.hamburger'),
            isClosed = false;
    trigger.click(function() {
        hamburger_cross();
    });
    function hamburger_cross() {
        if (isClosed == true) {
//            alert("closed")
            trigger.addClass('is-open');
            trigger.removeClass('is-closed');
            isClosed = false;
            $("#showRight").removeAttr('disabled', false);
            $(".container_baground").css("margin-left", '0px');
//              $("#wrapper").css("padding-left",'250px');
            $(".dataTables_scrollHeadInner").css("width", "98%");
            $(".table").css("width", "100%");
        } else {
//            alert("open")
            trigger.addClass('is-closed');
            trigger.removeClass('is-open');
            isClosed = true;
            $(".container_baground").css("margin-left", '20px');
            $(".dataTables_scrollHeadInner").css("width", "98%");
            $(".table").css("width", "100%");

        }
    }
    $('[data-toggle="offcanvas"]').click(function() {
        $('#wrapper').toggleClass('toggled');
    });


    $(".hamburger").click(function() {

        if ($(".hamburger").children("i").hasClass("fa-angle-right")) {
//            alert("left");
            $("#manage-menubtn-angle").show();
            $("#show-hidehamburger").addClass("htmber-managebtn");
            $("#left-menu-angle").hide();
            $(".hamburger").children("i").removeClass("fa-angle-right").addClass("fa-angle-left");
            $("#wrapper").css("padding-left", '0px');
        } else {
//            alert("right");
            $("#manage-menubtn-angle").hide();
            $("#left-menu-angle").show();
            $("#show-hidehamburger").removeClass("htmber-managebtn");
            $(".hamburger").children("i").removeClass("fa-angle-left").addClass("fa-angle-right");
            $("#wrapper").css("padding-left", '250px');
        }
        if ($("#filter-nav").hasClass("cbp-spmenu-open")) {
//            alert("filete nav has class")
            $("#filter-nav").removeClass('cbp-spmenu-open');
            $("#showRight").removeClass('disabled');
//            $("#right-contentmenu").removeClass('col-lg-10 col-md-10 col-sm-10 col-xs-10').addClass('col-lg-12 col-md-12 col-sm-12 col-xs-12');
            $("#maincontainer").removeClass("maincontainer");
            $("#wrapper").css("padding-left", '250px');
            $("#wrapper").css("padding-right", '0px');
        }
        $("#maincontainer").removeClass("maincontainer");
    });
//    callinghamber();
});
function callinghamber() {
    $(".hamburger").trigger('click');
}
//$(document).ready(function() {
//    if ($("#datamaintainance-tab").hasClass("active")) {
//        $($("#datamaintainance-tab").data("target")).show();
//        $($("#dataloads-tab").data("target")).hide();
//    }
//    if ($("#dataloads-tab").hasClass("active")) {
//        $($("#dataloads-tab").data("target")).show();
//        $($("#datamaintainance-tab").data("target")).hide();
//    }
//});
$(document).ready(function() {
    
    if ($(window).width() < 700) {
        $(".hamburger").trigger('click');
        $("#myNavbar").css("float", "right");
        $(".sel-br").css("margin-right", "15px");
        $(".htmber-managebtn").css("border", "1px solid #1B6C8C");
    }
    else {

    }

});
//function clearsearchfield() {
//    $(this).next('.multiselect-search').val('');
////    $(".multiselect-search").val('');
//    $(this).next('.multiselect-clear-filter').trigger('click');
//
//}

