var rowWidth = 10;
var total = 22;
var padding = 3;
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
             'www.airdreamer.com'
             ];

$(document).ready(function () {
    var container = $('.vendors div');
    function padRight(n) {
        var s = (n+1).toString();
        while (s.length < padding) {
            s = '0' + s;
        }

        return s;
    }

    var div = container;
    for (var i = 0; i < total; i++) {

        var a = $('<a>').attr('href', 'http://' + _urls[i]).attr('target', '_blank').appendTo(div);
        $('<img>').attr('src', 'images/Vendors/' + padRight(i) + '.png').appendTo(a);
    }
    
	$.ajax({
		url: contextPath + '/statistics',
		method: 'POST',
		success: function (data) {
			var _format = {
					members: 5,
				    directors: 2,
				    insurance: 3,
				    missions: 3
				};

		    $('.odometer').each(function () {
		        var value = data[this.id];
		        var format = _format[this.id];

		        var stack = [];
		        while (format-- > 0) {
		            stack.push((value % 10).toString());
		            value = (value - (value % 10)) / 10;
		        }

		        for (var i = stack.length - 1; i >= 0; i--) {
		            $('<span>').text(stack[i]).appendTo(this);
		        }
		    });
		}
	})
});
