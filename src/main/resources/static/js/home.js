$('#toSection2').click(function () {
    $('html,body').animate({scrollTop: $("#context1").offset().top}, 400)
});
$('#toTop').click(function () {
    $('html,body').animate({scrollTop: $("#img1").offset().top}, 800)
});
$(window).scroll(function () {
    var len = $(window).scrollTop();
    var lenH = $("#context1").offset().top;
    if (len >= lenH) {
        $('#toTop').removeClass("invisible");
        $('#toTop').addClass("visible");
    } else {
        $('#toTop').removeClass("visible");
        $('#toTop').addClass("invisible");
    }
})
