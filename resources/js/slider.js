$(document).ready(function() {
    $('.slider .owl-carousel').owlCarousel({
        loop: true,
        margin: 10,
        smartSpeed: 450,
        autoplay: true,
        autoplayTimeout: 4000,
        autoplayHoverPause: true,
        items: 1
    })
    $('.slidercont .owl-carousel').owlCarousel({
        loop: true,
        margin: 10,
        autoplayHoverPause: true,
        items: 1
    })
    $('.img-pro .owl-carousel').owlCarousel({
        loop: true,
        margin: 10,
        autoplayHoverPause: true,
        items: 1
    })

});