var _data = {
    finish: 5263,
    trading: 15,
    com_num: 685,
    investor: 896
};

var _format = {
    finish: 4,
    trading: 2,
    com_num: 3,
    investor: 3
};

$(document).ready(function () {
    $('.odometer').each(function () {
        var value = _data[this.id];
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
});

