var _format = {
	members: 5,
    directors: 2,
    insurance: 3,
    missions: 3
};

$(document).ready(function () {
	$.ajax({
		url: contextPath + '/statistics',
		method: 'POST',
		success: function (data) {
			console.log(data);
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

