UfInsurance.controller('comEditorForm', function ($scope, modFlyermodels, Upload, modLogin, $timeout) {
	modFlyermodels.load(function (list) {
		$scope.flyerModels = list;
	});
	
	modLogin.watch(function (account) {
		$scope.account = account;
		$scope.$apply();
	});
	
	$scope.$on('save', function () {
		Upload.upload({
			url: 'mission/upload',
			file: $scope.flyerPictures_0
		})
		.success(function (data, status) {
			console.log('success: ', arguments);
			$scope.$emit('saved');
		})
		.error(function (data, status) {
			console.log('Uploading ' + $scope.flyerPictures_0.name + 'failed!')
			$scope.$emit('saved');
		});
	});
})
.directive('ufValidateFilelist', function () {
	return {
		require: 'ngModel',
		restrict: 'A',
		link: function(scope, elem, attr, ngModel) {
			function validate(value) {
				var found = [];
				if (_.isObject(value)) {
					for (var field in scope) {
						if (modelField.test(field) && _.isObject(scope[field]) && scope[field].name === value.name) {
							found.push(field);
						}
					};
				}
				
				ngModel.$setValidity('required', !required || !_.isObject(value));
				
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
