$(function(){

	$('.hd-user').hover(function() {
		$(this).find('.dropdown').slideDown();
	}, function() {
		$(this).find('.dropdown').slideUp();
	});

	$('.hd-nav li').hover(function() {
		$(this).find('.subnav').stop().slideDown();
	}, function() {
		$(this).find('.subnav').slideUp();
	});


	$(window).on('scroll',function(){
		var wh = $(window).height();
		if($(this).scrollTop()>wh){
			$('.backtop').addClass('backtop-show')
		}else{
			$('.backtop').removeClass('backtop-show')
		}
	});

	$('.backtop').on('click',function(){
		$('html,body').animate({scrollTop:0},400);
	});


	function drift(cont,elem){
		var x,y,moveX,moveY;
  		$(cont).on('mouseenter',function(ev){
  			x = ev.pageX;
  			y = ev.pageY;
  		});

  		$(cont).on('mousemove',function(ev){
  			moveX = (ev.pageX - x)*(10/x);
  			moveY = (ev.pageY - y)*(5/y);
			$(this).find(elem).attr('style','transform: translate('+moveX+'px,'+moveY+'px)');
		});
		$(cont).mouseleave(function(ev){
			$(this).find(elem).attr('style','transform: translate(0,0)');
			$(cont).on('mousemove',null);
		});
	}
	drift('#banner .swiper-slide','.img');



});