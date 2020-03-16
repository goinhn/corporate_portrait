$(function(){
	$(".pro-nav li").mouseenter(function(){
		var i = $(this).index();
		$(".pro-list>ul>li").css("display","none");
		$(".pro-nav li").removeClass("active");
		$(".pro-list>ul>li").eq(i).css("display","block");
		$(".pro-nav li").eq(i).addClass("active");
	})
})
