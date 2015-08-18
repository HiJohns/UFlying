UFlying.directive('ufVendors', function (UFlyingUtils) {
	var _urls = [
	             'www.avic.com.cn',
	             'www.pingan.com',
	             'www.95505.com.cn',
	             'www.zlbroker.com',
	             'www.sky-eye.com.cn',
	             'www.nestle.com.cn',
	             'www.ogilvy.com.cn',                                        
	             'www.samsung.com/cn',
	             'www.toyota.com.cn',
	             'www.10086.cn',
	             'www.cctv.com',
	             'www.btv.org',
	             'www.zjstv.com',
	             'www.jstv.com',
	             'www.tctc.com.cn',
	             'www.youku.com',
	             'tv.sohu.com',
	             'www.pptv.com',
	             'video.sina.com.cn',
	             'v.qq.com',
	             'www.xmbt.com.cn',
	             'itv.letv.com',
	             ];
	
	var _namePattern = 3;
	
	return {
		restrict: 'E',
		templateUrl: 'html/directives/Vendors.html',
		link: function (scope, elem, attrs) {
		    var container = $(elem).find('.vendors div');
		    var div = container;
		    for (var i = 0; i < _urls.length; i++) {

		        var a = $('<a>').attr('href', 'http://' + _urls[i]).attr('target', '_blank').appendTo(div);
		        $('<img>').attr('src', '../img/homepage/vendors/' + UFlyingUtils.packZeros(i+1, _namePattern) + '.png').appendTo(a);
		    }
		}
	}
})