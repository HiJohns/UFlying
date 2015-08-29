UfCommon.factory('misUtils', function ($templateCache, $http) {
    var jsonDateTmpl = _.template('<%=year%>-<%=month%>-<%=date%>');
    var jsonTimeTmpl = _.template('<%=hours%>:<%=minutes%>:<%=seconds%>');
    var timeTmpl = _.template('<%=hours%>:<%=minutes%>');

    function packZeros(user, upTo) {
        var s = user.toString();
        while (s.length < upTo) s = '0' + s;
        return s;
    }
    
    function waitForAll(callback) {
        function _callback(index, success) {
            finished++;
            results[index] = success;

            allSuccess = allSuccess && success;

            if (finished >= tasks.length) {
                callback(allSuccess, results);
            }
        }

        var tasks;
        if (_.isArray(arguments[1])) {
            tasks = arguments[1];
        }
        else {
            tasks = [];
            for (var i = 1; i < arguments.length; i++) {
                tasks.push(arguments[i]);
            }
        }

        var finished = 0;
        var results = [];
        var allSuccess = true;

        _.each(tasks, function(task, index) {
            task(_callback.bind(null, index));
        });

        if (tasks.length == 0) callback(true, []);
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
        packZeros: packZeros,
        waitForAll: waitForAll
    }
});
