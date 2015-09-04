UfCommon.filter('missionPlace', function () {
    var text = [
            '',
           '室内',
           '室外',
           '室内+室外'
       ];

       return function (place) {
           return text[place];
       };
   })
   .filter('userId', function (misUtils) {
       return function (user) {
           var isIndividual = user > 0;
           if (!isIndividual) user = -user;
           return _.isNumber(user) ? ((isIndividual ? 'G' : 'E') + misUtils.packZeros(user, 10)) : '';
       }
   })
   .filter('accountId', function (misUtils) {
       return function (account) {
    	   if (!_.isObject(account)) return '';
           var isIndividual = _.isNumber(account.uid);
           return (isIndividual ? 'G' : 'E') + misUtils.packZeros(isIndividual ? account.uid: account.eid, 10);
       }
   })
   .filter('money', function () {
       return function (money) {
           return _.isNumber(money) ? money.toFixed(2) + '元' : money;
       }
   })
   .filter('dateFormat', function () {
       var _tmpl = _.template('<%=year%>年<%=month%>月<%=day%>日');
       return function (date) {
           return _.isDate(date) ? _tmpl({ year: date.getFullYear(), month: date.getMonth()+1, day: date.getDate() }) : date;
       }
   })
   .filter('timeFormat', function (misUtils) {
       return misUtils.renderTime;
   });