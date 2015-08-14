UFlying.factory('UFlyingUtils', function () {
    var jsonDateTmpl = _.template('<%=year%>-<%=month%>-<%=date%>');
    var jsonTimeTmpl = _.template('<%=hours%>:<%=minutes%>:<%=seconds%>');
    var timeTmpl = _.template('<%=hours%>:<%=minutes%>');

    function packZeros(user, upTo) {
        var s = user.toString();
        while (s.length < upTo) s = '0' + s;
        return s;
    }

    return {
        renderJsonDate: function (date) {
            if (!_.isDate(date)) return date;
            return jsonDateTmpl({
                year: date.getFullYear(),
                month: date.getMonth()+1,
                date: date.getDate()
            });
        },
        renderJsonTime: function (time) {
            if (!_.isDate(time)) return time;
            return jsonTimeTmpl({        
                hours: time.getHours(),
                minutes: time.getMinutes(),
                seconds: time.getSeconds(),
            });
        },
        renderTime: function (time) {
            if (!_.isDate(time)) return time;
            return timeTmpl({ hours: time.getHours(), minutes: packZeros(time.getMinutes(), 2) });
        },
        packZeros: packZeros

    }
});
