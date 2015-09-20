UfInsurance.controller('comEditorForm', function ($scope, modDevices, Upload, modLogin, $timeout, misUtils) {
	$scope.devices = [];
	modDevices.load(function (list) {
		$scope.devices = list;
		$scope.$apply();
	});
	
	modLogin.watch(function (account) {
		$scope.data.insuree = account;
		$scope.data.owner = account.name;
		$scope.$apply();
	});
})
.filter('flyerInfo', function () {
	var _tmpl = _.template('生产厂商：<%=brandName%>，飞行器重量：<%=weightText%>');
	return function (flyer) {
		if (!_.isObject(flyer)) return '';
		flyer.weightText = _.isNumber(flyer.weight) && flyer.weight > 0 ? flyer.weight + '克' : '未知';
		return _tmpl(flyer);
	}
}).directive('ufValidateFilelist', function () {
	return {
		require: 'ngModel',
		restrict: 'A',
		link: function(scope, elem, attr, ngModel) {
			function validate(value) {
				var found = [];
				var data = scope.data;
				if (_.isObject(value)) {
					for (var field in data) {
						if (modelField.test(field) && _.isObject(data[field]) && data[field].name === value.name) {
							found.push(field);
						}
					};
				}
				
				ngModel.$setValidity('required', !required || _.isObject(value));
				
				if (_.isArray(ngModel.__duplicated)) {
					_.each(ngModel.__duplicated, function (name) {
						form[name].$setValidity('fileDuplicated', true);
						delete form[name].__duplicated;
					})
				}
				
				_.each(found, function (name) {
					form[name].$setValidity('fileDuplicated', found.length === 1);
					if (found.length > 1) {
						form[name].__duplicated = found;
					}
				});
				
				function _isTarget(model) {
					return modelField.test(model.$name);
				}
				
				scope[target[1] + '_duplicated'] = _.isArray(form.$error.fileDuplicated) && 
					_.isObject(_.find(form.$error.fileDuplicated, _isTarget));
				scope[target[1] + '_required'] = _.isArray(form.$error.required) && 
					_.isObject(_.find(form.$error.required, _isTarget));

				if (_.isObject(value)) ngModel.$setDirty(); // 若不设条件脏标志实际上就没有意义
		        return value;
			}
			
			var target = attr.ufValidateFilelist.split('.');
			var required = attr.hasOwnProperty('required');
			var form = scope[target[0]];
			var modelField = new RegExp(target[1] + '_\\d+');
			var duplicates = {};
			
	        //For model -> DOM validation
	        ngModel.$formatters.unshift(validate); 				
		}
	}
})
