UFlying.factory('UFlyingUtils', function () {
    var dateTmpl = _.template('<%=year%>-<%=month%>-<%=date%>');
    var timeTmpl = _.template('<%=hours%>:<%=minutes%>:<%=seconds%>');
    return {
        renderJsonDate: function (date) {
            return dateTmpl({
                year: date.getFullYear(),
                month: date.getMonth()+1,
                date: date.getDate()
            });
        },
        renderJsonTime: function (time) {
            return timeTmpl({        
                hours: time.getHours(),
                minutes: time.getMinutes(),
                seconds: time.getSeconds(),
            });
        }
    }
});
