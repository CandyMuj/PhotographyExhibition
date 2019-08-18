(function($) {
	
	"use strict";


    // Back to top
    $.scrollUp({
        scrollText: '<i class="fa fa-angle-up"></i>',
        easingType: 'linear',
        scrollSpeed: 900,
        animation: 'fade'
    });


    // Menu sticky
    $(window).on('scroll',function() {    
        var scroll = $(window).scrollTop();
        if (scroll < 20) {
         $(".header-area").removeClass("sticky-header");
        }else{
         $(".header-area").addClass("sticky-header");
        }
     });
    
	
    //js code for mobile menu toggle
   $(".menu-toggle").on("click", function() {
       $(this).toggleClass("is-active");
   });

    // banner content animation
    $(".hero-area").on("translate.owl.carousel", function() {
        $(".hero-sub h1").removeClass("animated flipInX").css("opacity", "0"),
        $(".hero-sub p").removeClass("animated fadeInUp").css("opacity", "0"),
        $(".hero-sub a").removeClass("animated fadeInUp").css("opacity", "0")
    }),
    $(".hero-area").on("translated.owl.carousel", function() {
        $(".hero-sub h1").addClass("animated flipInX").css("opacity", "1"),
        $(".hero-sub p").addClass("animated fadeInUp").css("opacity", "1"),
        $(".hero-sub a").addClass("animated fadeInUp").css("opacity", "1")
    });

    
     // Portfolio popup

    $(".portfolio-gallery").each(function () {
        $(this).find(".popup-gallery").magnificPopup({
            type: "image",
            gallery: {
                enabled: true
            }
        });
    }); 

    $('.video-popup').magnificPopup({
        type: 'iframe',
    });


    // Hero Slider
    $('.hero-area').owlCarousel({
        loop:true,
        dots: true,
        autoplay: true,
        mouseDrag: true,
        animateOut: 'fadeOut',
        animateIn: 'fadeIn',
        autoplayTimeout: 10000,
        smartSpeed: 1500,
        nav:false,
        responsive:{
            0:{
                items:1,
                nav:false,
            },
            576:{
                items:1
            },
            1000:{
                items:1
            }
        }
    });



    // Category Slider
    $('.cat-carousel').owlCarousel({
        loop:true,
        dots: false,
        autoplay: true,
        margin:30,
        smartSpeed: 500,
        nav:true,
        navText: [
            '<i class="fa fa-angle-left"></i>',
            '<i class="fa fa-angle-right"></i>'
        ],
        responsive:{
            0:{
                items:1
            },
            450:{
                items:2
            },
            575:{
                items:2
            },
            767:{
                items:3
            },
            991:{
                items:4
            },
            1140:{
                items:5
            }
        }
    });

    // Single Item Slider
    $('.single-item-contents').owlCarousel({
        loop:true,
        dots: true,
        autoplay: true,
        mouseDrag: true,
        animateOut: 'fadeOut',
        animateIn: 'fadeIn',
        autoplayTimeout: 5000,
        smartSpeed: 1000,
        nav:false,
        responsive:{
            0:{
                items:1,
                nav:false,
            },
            576:{
                items:1
            },
            1000:{
                items:1
            }
        }
    });

    



    // Preloader Js
    $(window).on('load', function(){
      $('.preloader').fadeOut(1000); // set duration in brackets    
    });


    // Wow js active
    new WOW().init(); 
    

    // Full Screen Search
    $(".search-btn").on('click', function(){
        $(".search-full").removeClass("close");
        $(".search-full").addClass("open");
    })

    $(".search-close").on('click', function(){
        $(".search-full").removeClass("open");
        $(".search-full").addClass("close");
    })
    

	
})(jQuery);